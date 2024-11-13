package com.study.tdd.service;

import com.study.tdd.dto.ReqStudentAddStudent;
import com.study.tdd.dto.RespSaveDto;
import com.study.tdd.entity.Student;
import com.study.tdd.repository.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock // 임시로 만든 것
    private StudentMapper studentMapper;

    // mock 객체를 주입해서 생성해라
    @InjectMocks
    private StudentService studentService; // 얘는 진짜인데 가짜를 의존하고 있는 것 ( service > mapper )

    // 코드를 일일리 확인하는 테스트 방법 / 옛날 방식
    @Test
    void saveStudent() { // save student 안에 있는 로직을 테스트
        ReqStudentAddStudent dto = new ReqStudentAddStudent();
        dto.setName("김준일");
        dto.setAge(31);

        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());

        given(studentMapper.save(student)).willReturn(1); // save를 호출하면 1을 return 해줌
        verify(studentMapper).save(student);
        student.setId(1l);

        RespSaveDto respSaveDto = null;

        if(studentMapper.save(student) < 1) {
            respSaveDto = new RespSaveDto(false, "학생 등록 실패");
        } else {
            respSaveDto = new RespSaveDto(true, "학생 등록 완료");
        }

        // service에 있는 로직들을 가지고 비교하기 위함
        assertEquals(new RespSaveDto(true, "학생 등록 완료"), respSaveDto);
    }

    // 테스트 할때 메소드를 여러개 만들어야 한다 : 경우의 수에 따라
    @Test
    void saveStudent2() {
        // 1로 리턴 하도록 고정으로 설정해놨음 / 가정
        given(studentMapper.save(any(Student.class))).willReturn(1); // 성공 : 1 실패 : 0 리턴

        ReqStudentAddStudent dto = new ReqStudentAddStudent();
        dto.setName("김준일");
        dto.setAge(31);

        RespSaveDto respSaveDto = studentService.saveStudent(dto);

        assertEquals(new RespSaveDto(true, "학생 등록 완료"), respSaveDto);
    }
}