package com.atguigu.eduservice.query;

import com.atguigu.servicebase.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.io.Serializable;

@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
@Data
public class TeacherQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称, 模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2023-01-01 10:10:10")
    private String begin; // 注意，这里使用的String类型，前端传来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2023-02-26 10:10:10")
    private String end;

}
