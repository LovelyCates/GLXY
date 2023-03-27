package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.query.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.config.SwaggerConfig;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 * http://192.168.0.102:8001/eduservice/edu-teacher/findAll
 * @author sakura
 * @since 2023-02-26
 */
/**
 * 浏览器从一个域名的网页去请求另一个域名的资源时，域名、端口、协议任一不同，都是跨域 。前后端分离开发中，需要考虑ajax跨域的问题。
 * 这里我们可以从服务端解决这个问题
 */
@CrossOrigin
@Api(tags = {SwaggerConfig.TeacherTag})
@RequestMapping("/eduservice/teacher")
@RestController
public class EduTeacherController {

    // service 注入
    @Autowired
    private EduTeacherService teacherService;

    // 1. 查询讲师表中所有数据
    // rest 风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        // 调用 service 方法来实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id
    ){
        boolean result = teacherService.removeById(id);
        return result ? R.ok() : R.error().message("删除失败");
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery

    ){

        Page<EduTeacher> pageParam = new Page<>(current, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> teachers = pageParam.getRecords();
        long total = pageParam.getTotal();

        return R.ok().data("total", total).data("rows", teachers);
    }


    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = false)
            @RequestBody EduTeacher teacher
    ) {
      return teacherService.save(teacher) ? R.ok() : R.error().message("保存失败");
    }

    @ApiOperation(value = "根据ID查询讲师")
    @PostMapping("/getTeacher/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher")
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacherService.updateById(teacher);
        return R.ok();
    }







}

