package com.sky.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {

    //员工姓名，可选
    private String name;

    //页码，必填，最小值为1
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    private Integer page;

    //每页显示记录数，必填，最小值为1
    @NotNull(message = "每页显示记录数不能为空")
    @Min(value = 1, message = "每页显示记录数最小为1")
    private Integer pageSize;

}
