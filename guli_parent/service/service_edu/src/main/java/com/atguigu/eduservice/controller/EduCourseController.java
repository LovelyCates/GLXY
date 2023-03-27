package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sakura
 * @since 2023-03-25
 */
@Api(tags = {SwaggerConfig.CourseTag})
@RestController
@CrossOrigin
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    /**
     * @Author sakura
     * @Description //TODO 新增课程接口
     * @Date 21:41 2023/3/27
     * @Param [courseInfoVo]
     * @return com.atguigu.commonutils.R
     **/
    @ApiOperation("新增课程")
    @PostMapping("/saveCourseInfo")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo){
            String couId = eduCourseService.saveCourseInfo(courseInfoVo);
            return R.ok().data("courseId", couId);
    }

}

