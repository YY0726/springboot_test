package com.qf.service;

import com.qf.entity.Student;

import java.util.List;

/**
 * @user 60941
 * @data 2019/5/16 19:26
 */

public interface IStudentService {
    List<Student> stuList();

    Student addStudent(Student student);

    int updateStudent(Student id);

    Student getById(Integer id);

    int deleteStudent(Integer id);

    Student login(String username, String password);

    Student findEmail(String username);

    int updatePassword(Student student);
}
