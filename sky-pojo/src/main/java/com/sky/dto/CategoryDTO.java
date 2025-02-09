package com.sky.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {

    //主键
    private Long id;

    @NotBlank(message = "分类名称不能为空")  // 字符串非空校验
    private String name;

    @NotNull(message = "分类类型不能为空")   // 对象非空校验
    @Max(value = 2, message = "分类类型最大值为2")
    @Min(value = 1, message = "分类类型最小值为1")
    //类型 1 菜品分类 2 套餐分类
    private Integer type;

    @NotNull(message = "排序号不能为空")
    private Integer sort;

}
