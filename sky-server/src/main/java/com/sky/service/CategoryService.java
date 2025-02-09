package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;

import java.util.List;


public interface CategoryService {

    Result add(CategoryDTO categoryDTO);

    void updateStatus(Integer status, Long id);

    Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    Result update(CategoryDTO categoryDTO);

    Result delect(Integer id);

    Result listByType(Integer type);
}
