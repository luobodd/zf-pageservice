package com.zf.reservation.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户密码输入错误登录实体类
 */
@Data
@Accessors(chain = true)
public class UserErrorLogin {
    private String id;
    /**
     * 用户名
     */
    private String username;
   /**
    * 密码输入错误次数
    */
    private Integer errorLoginCount;
    /**
     * 错误登录时间
     */
    private LocalDateTime errorLoginDate;
}
