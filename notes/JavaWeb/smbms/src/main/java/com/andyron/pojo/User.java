package com.andyron.pojo;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class User {
    private Integer id; //id
    private String userCode; //用户编码
    private String userName; //用户名称
    private String userPassword; //用户密码
    private Integer gender;  //性别
    private Date birthday;  //出生日期
    private String phone;   //电话
    private String address; //地址
    private Integer userRole;    //用户角色
    private Integer createdBy;   //创建者
    private Date creationDate; //创建时间
    private Integer modifyBy;     //更新者
    private Date modifyDate;   //更新时间

    private Integer age;//年龄
    private String userRoleName;    //用户角色名称

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Integer getAge() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer nowYear = cal.get(Calendar.YEAR);
        cal.setTime(birthday);
        Integer birthYear = cal.get(Calendar.YEAR);
//        Integer age = date.getYear() - birthday.getYear();
        return nowYear - birthYear;
    }
}
