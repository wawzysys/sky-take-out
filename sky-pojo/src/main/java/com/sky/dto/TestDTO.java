package com.sky.dto;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TestDTO {
    @NotBlank(message = "名称不能为空")
    private String name;

    @NotNull(message = "类型不能为空")
    @Min(value = 1, message = "类型必须大于等于1")
    @Max(value = 2, message = "类型必须小于等于2")
    private Integer type;
} 