package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.exception.BaseException;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     *
     * @param categoryDTO
     * @return
     */
    @Override
    public Result add(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        //设置状态默认值
        category.setStatus(StatusConstant.DISABLE);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insertCategory(category);
        return Result.success();
    }

    @Override
    public void updateStatus(Integer status, Long id) {
        // 使用 MyBatis-Plus 的方法获取实体
        Category category = categoryMapper.selectById(id);
        if (category == null) {
            throw new BaseException("分类不存在");
        }
        
        // 设置需要更新的字段
        category.setStatus(status);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        
        // 使用 MyBatis-Plus 的 updateById 方法更新
        categoryMapper.updateById(category);
    }

    @Override
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getResult());
        return Result.success(pageResult);
    }

    @Override
    public Result update(CategoryDTO categoryDTO) {
        // 创建实体对象
        Category category = new Category();
        // 拷贝属性
        BeanUtils.copyProperties(categoryDTO, category);
        // 设置更新时间和更新人
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());
        
        // 使用注入的 categoryMapper 实例
        categoryMapper.updateById(category);
        return Result.success("修改成功");
    }

    @Override
    public Result delect(Integer id) {
        // 先查询该分类是否存在
        Category category = categoryMapper.selectById(id);
        
        if (category == null) {
            return Result.error("该分类不存在");
        }
        
        // 如果存在则删除
        categoryMapper.deleteById(id);
        return Result.info("删除成功");
    }

    @Override
    public Result listByType(Integer type) {
        if(type != 1 && type != 2){
            return Result.error(MessageConstant.CATEGORY_TYPE_ERROR);
        }
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Category::getType, type);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return Result.success(categories);
    }


}
