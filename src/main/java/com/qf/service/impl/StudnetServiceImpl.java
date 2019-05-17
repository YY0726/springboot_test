package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.IStudentDao;
import com.qf.entity.Student;
import com.qf.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @user 60941
 * @data 2019/5/16 19:28
 */
@Service
public class StudnetServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Override
    public List<Student> stuList() {
        return studentDao.selectList(null);
    }

    @Override
    public Student addStudent(Student student) {
        studentDao.insert(student);
        return student;
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateById(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentDao.selectById(id);
    }

    @Override
    public int deleteStudent(Integer id) {
        return studentDao.deleteById(id);
    }

    @Override
    public Student login(String username, String password) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        return studentDao.selectOne(queryWrapper);
    }

    @Override
    public Student findEmail(String username) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return studentDao.selectOne(queryWrapper);
    }

    @Override
    public int updatePassword(Student student) {
        return studentDao.updateById(student);
    }
}
