package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (value = "/admin/category")
@Slf4j
@Api(tags = "分类管理")
//@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping()
    @ApiOperation(value = "添加分类")
    public Result add(@RequestBody @Valid CategoryDTO categoryDTO) {
//        if(bindingResult.hasErrors()){
//            log.error("提交信息有误");
//            return Result.error(bindingResult.getFieldError().getDefaultMessage());
//        }
        log.info("添加分类：{}", categoryDTO);
        return categoryService.add(categoryDTO);
    }

    @PostMapping(value = "status/{status}")
    @ApiOperation(value = "修改分类状态")
    public void updateStatus(@PathVariable("status") Integer status, Long id) {
        log.info("修改分类状态：{}", status);
        categoryService.updateStatus(status, id);
        // 调用service层方法
        // ...
        // 不需要显式返回，会自动包装为 Result.success()
    }

    @GetMapping(value = "page")
    @ApiOperation(value = "查询")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("查询分类：{}", categoryPageQueryDTO);
        return categoryService.pageQuery(categoryPageQueryDTO);
    }

    @PutMapping()
    @ApiOperation(value = "修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO) {
        log.info("修改分类：{}", categoryDTO);
        return categoryService.update(categoryDTO);
    }
    @DeleteMapping()
    @ApiOperation(value = "delete")
    public Result delect(Long id ){
        log.info("删除分类{}", id);
        return categoryService.delect(id);
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询分类列表")
    public Result<List<Category>> listByTyep(Integer type) {
        // 返回列表会自动包装为 Result.success(list)
        return categoryService.listByType(type);
    }
}
