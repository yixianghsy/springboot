package hsy.com.aop.controller;

import hsy.com.aop.dto.Student;
import hsy.com.aop.validator.WebLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController{

    private final Logger logger= LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/list")
    @WebLogger("学生列表")
    public @ResponseBody List<Student> list(){
        ArrayList<Student> list=new ArrayList<Student>();
        Student student0=new Student(1,"kobe",30);
        Student student1=new Student(2,"james",30);
        Student student2=new Student(3,"rose",30);

        list.add(student0);
        list.add(student1);
        list.add(student2);

        return list;
    }

    @WebLogger("学生实体")
    @RequestMapping("/detail")
    public @ResponseBody Student student(int id){
        return new Student(1,"kobe",30);
    }
}
