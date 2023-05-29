package org.ytq.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytq.domain.User;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

// 2. 使用@Controller来定义bean对象
@Controller
@RequestMapping("/user")
public class UserController {
    // 3. 设置访问路径
    @RequestMapping("/save")
    // 4. 设置当前操作的返回值类型
    @ResponseBody
    public String save(){
        System.out.println("user save ...");
        return "{'module':'springmvc'}";
    }

    @RequestMapping("/delete")
    // 4. 设置当前操作的返回值类型
    @ResponseBody
    public String delete(){
        System.out.println("user delete ...");
        return "{'module':'springmvc delete'}";
    }

    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name, int age){
        System.out.println("name == " + name + " age == " + age);
        return "{'module' : 'common param'}";
    }

    @RequestMapping("/commonDifferentParam")
    @ResponseBody
    public String commonDifferentParam(@RequestParam("name") String userName, int age){
        System.out.println(userName);
        System.out.println(age);
        return "{'module' : 'common param different name'}";
    }

    // POJO参数
    @RequestMapping("/pojoParam")
    @ResponseBody
    public String pojoParam(User user){
        System.out.println("pojo参数传递 user ==> " + user);
        return "{'module' : 'pojo param'}";
    }

    // 嵌套POJO参数
    @RequestMapping("/pojoContainPojoParam")
    @ResponseBody
    public String pojoContainPojoParam(User user){
        System.out.println("pojo参数传递 user ==> " + user);
        return "{'module' : 'pojo contain pojo param'}";
    }

    // 数组参数
    @RequestMapping("/arrayParam")
    @ResponseBody
    public String arrayParam(String[] likes){
        System.out.println("数组参数传递 likes => " + Arrays.toString(likes));
        return "{'module' : 'array param'}";
    }

    // 集合参数
    @RequestMapping("/listParam")
    @ResponseBody
    public String listParam(@RequestParam List<String> likes){
        System.out.println("集合参数传递 likes => " + likes);
        return "{'module' : 'list param'}";
    }

    // 集合参数: json格式
    @RequestMapping("/listParaForJson")
    @ResponseBody
    public String listParamForJson(@RequestBody List<String> likes){
        System.out.println(likes);
        return "{'module' : 'list common for json param'}";
    }
    // pojo参数: json格式
    @RequestMapping("/listPojoParamForJson")
    @ResponseBody
    public String pojoParamForJson(@RequestBody User user){
        System.out.println(user);
        return "{'module' : 'pojo for json param'}";
    }

    // 嵌套pojo参数: json格式
    @RequestMapping("/listPojoContainParamForJson")
    @ResponseBody
    public String listPojoContainParamForJson(@RequestBody List<User> list){
        System.out.println(list);
        return "{'module' : 'list pojo for json param'}";
    }

    // 日期参数
    @RequestMapping("/dateParam")
    @ResponseBody
    // 指定日期格式
    public String dataParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date){
        System.out.println(date);
        return "{'module' : 'date param'}";
    }
}
