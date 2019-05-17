package com.qf.controller;

import com.qf.common.SendEmail;
import com.qf.entity.Student;
import com.qf.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @user 60941
 * @data 2019/5/16 19:25
 */
@Controller
@RequestMapping(value = "studentController")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    /**
     * 跳转到学生展示页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/stuList")
    public String stuList(Model model, HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("student");
        if(student==null){
            return "login";
        }
        List<Student> list = studentService.stuList();
        model.addAttribute("list",list);
        return "stuList";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping(value = "/addstu")
    public String addStu(){
        return "addStudent";
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @RequestMapping(value = "/addStudent")
    public String addStudent(Student student){
        Student addStudent = studentService.addStudent(student);
        return "redirect:/studentController/stuList";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/getById")
    public String getById(Integer id,Model model){
        Student student = studentService.getById(id);
        model.addAttribute("student",student);
        return "updateStudent";
    }

    /**
     * 修改资料
     * @param student
     * @return
     */
    @RequestMapping(value = "/updateStudent")
    public String  updateStudent(Student student){
        int update = studentService.updateStudent(student);

        return "redirect:/studentController/stuList";
    }

    /**
     * 删除学生
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteStudent")
    public String deleteStudent(Integer id){
        int delete = studentService.deleteStudent(id);
        return "redirect:/studentController/stuList";
    }

    /**
     * 登陆功能
     */

    @RequestMapping(value = "login")
    public String login(String username,String password,HttpServletRequest request,Model model){
        Student student = studentService.login(username,password);
        if(student!=null){
            request.getSession().setAttribute("student",student);
            return "redirect:/studentController/stuList";
        }
        model.addAttribute("msg","用户名或密码错误");
        return "login";
    }

    /**
     * 跳转到发邮件页面
     * @return
     */
    @RequestMapping(value = "/sendEmail")
    public String sendEmail(){
        return "sendEmail";
    }

    /**
     * 发邮件
     */
    @RequestMapping(value = "findEmail")
    public String findEmail(String username) throws Exception {
        Student student = studentService.findEmail(username);
        SendEmail.SendEmailInfoUser(student.getEmail(),"修改密码","<a href='http://127.0.0.1:8080/studentController/updateEmail?id="+student.getId()+"'>点击修改密码</a>","");
        return "login";
    }

    /**
     * 进入修改密码页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateEmail")
    public String updateEmail(Integer id,Model model) {
        model.addAttribute("id",id);
        return "updateEmail";
    }

    @RequestMapping(value = "/updatePassword")
    public String updatePassword(Integer id,String password){
        Student student = studentService.getById(id);
        student.setPassword(password);
        int update = studentService.updatePassword(student);
        return "success";
    }
}
