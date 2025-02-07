package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.result.Result;




public interface CategoryService {

    Result add(CategoryDTO categoryDTO);

    void update(Integer status, Long id);
}
