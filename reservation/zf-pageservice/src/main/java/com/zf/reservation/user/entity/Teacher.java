package com.zf.reservation.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 教师实体类
 */
@Data
@Accessors(chain = true)
public class Teacher {
    private String id;
    /**
     * 用户名
     */
    private String login_name;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 电话
     */
    private String tel;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 最近登录时间
     */
    private LocalDateTime login_time;
    /**
     * 创建时间
     */
    private LocalDateTime create_time;
    /**
     * 创建人
     */
    private String create_user;
    /**
     * 序号
     */
    private Integer num;
   /**
    * 状态 0 不可登录 1可登录
    */
    private Integer status;
    /**
     * 说明
     */
    private String comment;
    /**
     * 类型
     */
    private UserType type;
    
}
