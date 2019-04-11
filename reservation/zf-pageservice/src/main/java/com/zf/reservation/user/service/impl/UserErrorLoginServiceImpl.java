package com.zf.reservation.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.reservation.user.entity.UserErrorLogin;
import com.zf.reservation.user.mapper.IUserErrorLoginMapper;
import com.zf.reservation.user.service.IUserErrorLoginService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserErrorLoginServiceImpl extends ServiceImpl<IUserErrorLoginMapper, UserErrorLogin> implements IUserErrorLoginService {
}
