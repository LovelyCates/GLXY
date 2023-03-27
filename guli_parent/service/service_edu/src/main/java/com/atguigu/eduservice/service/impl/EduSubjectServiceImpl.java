package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sakura
 * @since 2023-03-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 文件输入流
            InputStream in = file.getInputStream();
            // 调用方法进行读
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 课程分类，树形
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 1. 查库获得 一级分类
        QueryWrapper<EduSubject> oneSubjectQuery = new QueryWrapper<>();
        oneSubjectQuery.eq("parent_id", 0);
        List<EduSubject> oneSubjects = baseMapper.selectList(oneSubjectQuery);

        // 2. 查库获得 二级分类
        QueryWrapper<EduSubject> twoSubjectQuery = new QueryWrapper<>();
        twoSubjectQuery.ne("parent_id", 0);
        List<EduSubject> twoSubjects = baseMapper.selectList(twoSubjectQuery);

        List<OneSubject> subjects = new ArrayList<>();

        // 1. 包装一级分类
        oneSubjects.forEach(item -> {

            List<TwoSubject> twoSubjectList = new ArrayList<>();

            // 二级分类
            twoSubjects.stream().filter(
                            eduSubject -> Objects.equals(eduSubject.getParentId(), item.getId()))
                    .collect(Collectors.toList())
                    .forEach(subject -> {
                        twoSubjectList.add(new TwoSubject(subject.getId(), subject.getTitle()));
                    });

            subjects.add(
                    new OneSubject(
                            item.getId(),
                            item.getTitle(),
                            twoSubjectList
                            )
                    );
        });

        return subjects;
    }
}
