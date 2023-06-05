package org.ytq.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

// lombok
@Setter
@Getter
@ToString


@TableName("tbl_user")
// @Data 包含所有，但不包含构造方法
public class User {
//    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;

    @TableField(value = "pwd", select = false)
    private String password;
    private Integer age;
    private String tel;
    @TableField(exist = false)
    private Integer online;

    // 判断是否被删除
//    @TableLogic(value = "0", delval = "1")
    private Integer deleted;

    @Version
    private Integer version;


//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", age=" + age +
//                ", tel='" + tel + '\'' +
//                '}';
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getTel() {
//        return tel;
//    }
//
//    public void setTel(String tel) {
//        this.tel = tel;
//    }
}
