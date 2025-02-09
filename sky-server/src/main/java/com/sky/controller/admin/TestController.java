package com.sky.controller.admin;

import com.sky.dto.TestDTO;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/test")
@Api(tags = "测试接口")
@Slf4j
public class TestController {

    @PostMapping("/validate")
    @ApiOperation("测试参数校验")
    public Result validate(@RequestBody @Validated TestDTO testDTO) {
        log.info("接收到的数据：{}", testDTO);
        
//        // 手动验证type值
//        if (testDTO.getType() != null && (testDTO.getType() < 1 || testDTO.getType() > 2)) {
//            return Result.error("类型值必须在1-2之间");
//        }
        
        return Result.success(testDTO);
    }
} 