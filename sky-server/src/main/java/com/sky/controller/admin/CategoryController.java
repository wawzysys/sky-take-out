package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping (value = "/admin/category")
@Slf4j
@Api(tags = "分类管理")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping(value = "/add")
    @ApiOperation(value = "添加分类")
    public Result add(@RequestBody CategoryDTO categoryDTO) {
        log.info("添加分类：{}", categoryDTO);
        return categoryService.add(categoryDTO);
    }

    @PostMapping(value = "status/{status}")
    @ApiOperation(value = "修改分类状态")
    public void updateStatus(@PathVariable Integer status, Long id) {
        log.info("修改分类状态：{}", status);
        categoryService.update(status, id);
        // 调用service层方法
        // ...
        // 不需要显式返回，会自动包装为 Result.success()
    }

//    @GetMapping("/list")
//    @ApiOperation(value = "查询分类列表")
//    public List<Category> list() {
//        // 返回列表会自动包装为 Result.success(list)
//        return categoryService.list();
//    }
}
