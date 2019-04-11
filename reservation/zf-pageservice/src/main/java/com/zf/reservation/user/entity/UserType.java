package com.zf.reservation.user.entity;

import lombok.Getter;

/**
 * 用户类型 枚举
 */
public enum UserType {
    /**
     * TODO
     */
    ENTERPRISE,
    /**
     * 管理员
     */
    ADMIN("管理员"),

    /**
     * 教师用户
     */
    TEACHER_USER("教师用户"),

    /**
     * 学生用户
     */
    STUDENT_USER("学生用户");

    /**
     * 用户名称
     */
    @Getter
    private final String name;
    /**
     * 用户描述
     */
    @Getter
    private final String remark;

    UserType() {
        this(null);
    }

    UserType(String name) {
        this(name, name);
    }

    UserType(String name, String remark) {
        this.name = name == null ? this.name() : name;
        this.remark = remark == null ? this.name : remark;
    }

}
