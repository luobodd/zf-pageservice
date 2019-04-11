package com.zf.reservation.user.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

import com.zf.reservation.user.entity.UserType;

/**
 * 添加管理员账户 VO
 */

@Data
@Accessors(chain = true)
public class AddUserVO {
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
	private LocalDateTime birthday;
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
	/**
	 * 验证码
	 */
	private String validateCode;
	/**
	 * 操作人id 超级管理员修改其他人的密码时使用
	 */
	private String superid;
	/**
	 * 管理员账号
	 */
	private String adminname;
	/**
	 * 原始密码
	 */
	private String pwd1;
	/**
	 * 新密码
	 */
	private String pwd2;
}
