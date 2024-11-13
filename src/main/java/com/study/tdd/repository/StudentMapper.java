package com.study.tdd.repository;

import com.study.tdd.entity.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    int save(Student student);

    Student findById(Long id);
}
