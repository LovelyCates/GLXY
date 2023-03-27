package com.atguigu.eduservice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sakura
 * @since 2023-03-25
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    /**
     * @Author sakura
     * @Description 插入课程信息
     * @Date 21:47 2023/3/27
     * @Param [courseInfoVo]
     * @return id -> 课程id
     **/
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        // 1. 转换对象 - 插入
        EduCourse eduCourse = new EduCourse();
        BeanUtil.copyProperties(courseInfoVo, eduCourse);
        Boolean isInsert = this.save(eduCourse);
        if (!isInsert)
            throw new GuliException(20001, "课程信息插入失败");

        // 2. 插入课程简介
        String couId = eduCourse.getId();
        EduCourseDescription description = new EduCourseDescription();
        description.setId(couId);
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(description);

        return couId;
    }
}
