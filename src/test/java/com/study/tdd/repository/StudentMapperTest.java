package com.study.tdd.repository;

import com.study.tdd.entity.Student;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
// 실제 데이터베이스에 영향을 주지 않겠다 - 테스트 데이터베이스를 따로 쓴다
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// 테스트가 실행되기 전에 SQL을 실행해라
@Sql(scripts = "/student_schema.sql")
@Transactional // service와 mapper와 같이 쓰여질때 걸어지면 다시 롤백이(초기화) 된다
class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void save() {
        Student student = new Student(1L, "김준일", 31);
        int result = studentMapper.save(student);
        assertThat(result).isEqualTo(1);
    }

    // mapper의 메소드들을 테스트
    @Test
    void findById() {
        Student student = new Student(1L, "김준일", 31);
        int result = studentMapper.save(student);
        assertThat(result).isEqualTo(1);

        Student foundStudent = studentMapper.findById(1l);
        assertThat(foundStudent).isEqualTo(student); // save에 있는 student와 비교
//        assertThat(foundStudent.getId()).isEqualTo(1l);
//        assertThat(foundStudent.getName()).isEqualTo("김준일");
//        assertThat(foundStudent.getAge()).isEqualTo(31);

    }


}