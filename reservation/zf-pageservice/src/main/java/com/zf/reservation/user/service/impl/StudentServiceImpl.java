package com.zf.reservation.user.service.impl;

import co.legu.modules.auth.util.Login;
import co.legu.modules.common.bean.Result;
import co.legu.modules.common.web.util.ServletBox;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.common.constant.UsefulValues;
import com.zf.reservation.user.entity.Student;
import com.zf.reservation.user.entity.Teacher;
import com.zf.reservation.user.entity.UserErrorLogin;
import com.zf.reservation.user.entity.UserType;
import com.zf.reservation.user.mapper.IStudentMapper;
import com.zf.reservation.user.mapper.IUserMapper;
import com.zf.reservation.user.service.IStudentService;
import com.zf.reservation.user.service.IUserErrorLoginService;
import com.zf.reservation.user.service.IUserService;
import com.zf.reservation.user.vo.AddUserVO;
import com.zf.util.Passwords;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<IStudentMapper, Student> implements IStudentService {@Override
	public Student selectUserById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> login(AddUserVO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> changePwd(AddUserVO addUserVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> list(Student student) {
        LambdaQueryWrapper<Student> userList = new LambdaQueryWrapper<>();

        if (Objects.nonNull(student.getName())) {
        	userList.like(Student::getName, student.getName());
        }
        if (Objects.nonNull(student.getLogin_name())) {
        	userList.like(Student::getLogin_name, student.getLogin_name());
        }
        return this.list(userList);
	}

	@Override
	public List<Student> blurry(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> userChangePwd(AddUserVO addUserVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> changeState(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

}
