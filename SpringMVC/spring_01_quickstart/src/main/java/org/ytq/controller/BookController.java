package org.ytq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ytq.domain.Address;
import org.ytq.domain.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @RequestMapping("/save")
    // 4. 设置当前操作的返回值类型
    @ResponseBody
    public String save(){
        System.out.println("book save ...");
        return "{'module':'book save'}";
    }
    // 响应页面 跳转
    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("跳转页面");
        return "/index.jsp";
    }

    // 响应文本数据
    @RequestMapping("/toText")
    @ResponseBody
    public String toText(){
        System.out.println("返回纯文本数据");
        return "response text";
    }

    // 响应json数据
    // pojo对象
    @RequestMapping("/toJsonPOJO")
    @ResponseBody
    public User toJsonPOJO(){
        System.out.println("返回json对象数据");
        User user = new User();
        user.setName("ytq");
        user.setAge(25);
        Address address1 = new Address();
        address1.setProvince("heilongjiang");
        address1.setCity("harbin");
        user.setAddress(address1);
        return user;
    }

    // 响应POJO集合对象
    @RequestMapping("/toJsonList")
    @ResponseBody
    public List<User> toJsonList(){
        System.out.println("返回json集合数据");
        User user = new User();
        Address address1 = new Address();
        address1.setProvince("heilongjiang");
        address1.setCity("harbin");
        user.setName("ytq");
        user.setAge(25);
        user.setAddress(address1);
        User user2 = new User();
        Address address2 = new Address();
        address2.setProvince("jiangsu");
        address2.setCity("nanjing");
        user2.setName("ccc");
        user2.setAge(20);
        user2.setAddress(address2);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        return users;
    }
}
