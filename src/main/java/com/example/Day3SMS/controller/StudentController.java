package com.example.Day3SMS.controller;

import com.example.Day3SMS.dto.StudentRequestDto;
import com.example.Day3SMS.dto.StudentResponseDto;
import com.example.Day3SMS.model.StudentModel;
import com.example.Day3SMS.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.Day3SMS.dto.StudentRequestDto;


import java.util.List;

@RestController

public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

//    create function API
    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto student) {
        return service.addStudent(student);
    }

    @GetMapping("/students")
    public List<StudentModel> getStudent(){
        return service.getStudent();
    }
    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student){
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable String id) {
        service.deleteStudent(id);
    }
    @PutMapping("/update/{id}")
    public void updateStudent(
            @PathVariable String id,
            @RequestBody StudentRequestDto dto
    ) {
        service.updateStudent(id, dto);
    }


}
