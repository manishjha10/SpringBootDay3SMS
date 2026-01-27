package com.example.Day3SMS.service;

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

    public StudentModel addStudent(StudentModel student) {
        return repository.save(student);
    }

    // Display All students
    public List<StudentModel> getStudent(){
        return repository.findAll();
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
    public void deleteStudent(String id) {
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No student found"));

        repository.delete(existingStudent);
    }




}
