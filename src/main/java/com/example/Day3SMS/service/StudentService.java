package com.example.Day3SMS.service;

import com.example.Day3SMS.dto.StudentRequestDto;
import com.example.Day3SMS.dto.StudentResponseDto;
import com.example.Day3SMS.model.StudentModel;
import com.example.Day3SMS.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository Repository) {
        this.repository = Repository;
    }

//    public StudentModel addStudent(StudentModel student) {
//        return repository.save(student);
//    }

    // Display All students
    public List<StudentModel> getStudent(){
        return repository.findAll();
    }
    public List<StudentResponseDto> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(s -> new StudentResponseDto(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                ))
                .toList();
    }


    //  Get the student detail by id [update]
    public StudentModel updateStudent(String id, StudentModel student){
        StudentModel  existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found"));

        existingStudent.setName(student.getName()); //
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student);
        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }
    public void deleteStudent(String id) {
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found"));

        repository.delete(existingStudent);
    }

    public void updateStudent(String id, StudentRequestDto dto) {
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found"));

        existingStudent.setName(dto.getName());
        existingStudent.setAge(dto.getAge());
        existingStudent.setEmail(dto.getEmail());

        repository.save(existingStudent);
    }




}
