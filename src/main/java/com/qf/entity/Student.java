package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @user 60941
 * @data 2019/5/16 19:26
 */
@Data
@TableName(value = "t_student")
@NoArgsConstructor
@AllArgsConstructor

public class Student {

    @TableId(type = IdType.AUTO)
    private int id;
    private String username;
    private int age;
    private int sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String password;
    private String email;
}
