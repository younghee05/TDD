package com.study.tdd.service;

import com.study.tdd.dto.ReqStudentAddStudent;
import com.study.tdd.dto.RespSaveDto;
import com.study.tdd.entity.Student;
import com.study.tdd.repository.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    // 단위테스트 : 하나씩 테스트를 하겠다
    // saveStudent를 테스트 하기 위해 mapper가 구현이 되어야지만 쓸 수 있다
    public RespSaveDto saveStudent(ReqStudentAddStudent dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());

        if(studentMapper.save(student) < 1) {
            return new RespSaveDto(false, "학생 등록 실패");
        }
        return new RespSaveDto(true, "학생 등록 완료");
    }


}
