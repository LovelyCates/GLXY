package com.atguigu.eduservice.controller;


import com.atguigu.servicebase.config.SwaggerConfig;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sakura
 * @since 2023-03-25
 */
@CrossOrigin
@RestController
@Api(tags = {SwaggerConfig.ChapterTag})
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

}

