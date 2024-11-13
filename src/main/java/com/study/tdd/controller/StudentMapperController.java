package com.study.tdd.controller;

import com.study.tdd.dto.ReqStudentAddStudent;
import com.study.tdd.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentMapperController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@RequestBody ReqStudentAddStudent dto) {
        return ResponseEntity.ok().body(studentService.saveStudent(dto));
    }
}
