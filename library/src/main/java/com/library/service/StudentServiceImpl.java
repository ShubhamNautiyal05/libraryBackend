package com.library.service;

import com.library.entity.Student;
import com.library.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudent = studentRepository.findAll();
        if (CollectionUtils.isEmpty(allStudent)){
            throw new IllegalArgumentException("No student found");
        } else {
            return allStudent;
        }
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Student record found"));
    }

    @Override
    public Student saveStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("Student record did not saved. Please try again");
        }

    }

    @Override
    public String deleteStudent(Long id) {
        try {
            studentRepository.deleteById(id);
            return "Student record successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Some error occurred while delete the student record";
        }
    }
}