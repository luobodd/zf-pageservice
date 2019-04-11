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
public class UserServiceImpl extends ServiceImpl<IUserMapper, Teacher> implements IUserService {
    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private IUserErrorLoginService iUserErrorLoginService;
    
    @Autowired
    private IStudentService iStudentService;

    /**
     * 添加用户接口 权限：教师用户
     * @param addUserVO 用户信息
     * @return 通用返回
     */
    @Override
    public Result<?> addUser(AddUserVO addUserVO) {
        Teacher hasUser = this.selectUserByUsername(addUserVO.getAdminname());
        if(hasUser.getType() == UserType.ADMIN){
            if(Objects.nonNull(this.selectUserByName(addUserVO.getName()))){
                return Result.fail("名字重复！");
            }
            if(Objects.nonNull(this.selectUserByUsername(addUserVO.getLogin_name()))){
                return Result.fail("用户名已存在！");
            }
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(addUserVO,teacher);
            String uid = IdWorker.getIdStr();
            teacher
                    .setId(uid)
                    .setPwd(Passwords.saltPwd(addUserVO.getPwd(),uid))
                    .setStatus(UsefulValues.TRUE)
                    .setCreate_time(LocalDateTime.now());
            if(this.save(teacher)){
                return Result.success(teacher);
            }
            return Result.fail("系统错误！请联系后台管理员！");
        }
        else if(hasUser.getType() == UserType.TEACHER_USER){
            if(Objects.nonNull(this.selectUserByName(addUserVO.getName()))){
                return Result.fail("名字重复！");
            }
            if(Objects.nonNull(this.selectUserByUsername(addUserVO.getLogin_name()))){
                return Result.fail("用户名已存在！");
            }
            Student student = new Student();
            BeanUtils.copyProperties(addUserVO,student);
            String uid = IdWorker.getIdStr();
            student
                    .setId(uid)
                    .setPwd(Passwords.saltPwd(addUserVO.getPwd(),uid))
                    .setStatus(UsefulValues.TRUE)
                    .setCreate_time(LocalDateTime.now());
            if(iStudentService.save(student)){
                return Result.success(student);
            }
            return Result.fail("系统错误！请联系后台管理员！");
        }
        return Result.fail("没有权限访问！");
    }

    /**
     * 登录接口
     * @param user 用户信息
     * @return 通用返回
     */
    @Override
    public Result<?> login(AddUserVO user) {
        // 根据用户名查询是否存在此用户
        Teacher isExit = this.selectUserByUsername(user.getLogin_name());
        if(Objects.nonNull(isExit)){

        	// 先查询 usererrorlogin 表中账户最近一次登录错误的信息
        	UserErrorLogin errorLoginList = this.getUserErrorLoginByAccount(user.getLogin_name());

        	if(Objects.nonNull(errorLoginList) &&
                    errorLoginList.getErrorLoginCount() >= UsefulValues.ERROR_LOGIN_COUNT &&
                    !this.belongCalendar(errorLoginList.getErrorLoginDate())) {

        		return Result.fail("账户被锁定，" + UsefulValues.ERROR_LAST_TIME + "分钟后将自动解锁");
        	}

        	LocalDateTime date = LocalDateTime.now();
            // 验证密码
            if(Passwords.eqPwd(user.getPwd(),isExit.getPwd(),isExit.getId())){
                // 获取当前时间添加到登录时间里
                isExit.setLogin_time(date);
            	this.updateById(isExit);
            	// 密码正确 进行登录设置...
                this.userLoginSuccess(isExit);
                return Result.success(isExit);
            }

            // 密码输入错误，保存到 usererrorlogin 表中
            // 用户第一次登录密码错误
            int count;
            if(Objects.isNull(errorLoginList) || this.belongCalendar(errorLoginList.getErrorLoginDate())) {
            	UserErrorLogin userErrorLogin = new UserErrorLogin();
            	userErrorLogin
                        .setId(IdWorker.getIdStr())
                        .setErrorLoginDate(date)
                        .setUsername(user.getLogin_name())
                        .setErrorLoginCount(UsefulValues.TRUE);
            	this.iUserErrorLoginService.save(userErrorLogin);
            	count = userErrorLogin.getErrorLoginCount();

            }else {
            	errorLoginList
                        .setId(IdWorker.getIdStr())
                        .setErrorLoginDate(date)
                        .setUsername(user.getLogin_name())
                        .setErrorLoginCount(errorLoginList.getErrorLoginCount() + 1);
            	this.iUserErrorLoginService.save(errorLoginList);
            	count = errorLoginList.getErrorLoginCount();
            }

            String message = count >= UsefulValues.ERROR_LOGIN_COUNT ? "账户被锁定，" + UsefulValues.ERROR_LAST_TIME + "分钟后将自动解锁" : "密码错误！";
            return Result.fail(message);
        }
        return Result.fail("用户名不存在！");
    }

    /**
     * 修改密码
     * @param addUserVO 账户信息
     * @return 通用返回
     */
    @Override
    public Result<?> changePwd(AddUserVO addUserVO) {
        Teacher teacher = this.selectUserById(addUserVO.getId());
        if(Objects.nonNull(teacher)){
            // 验证密码是否正确
            if(Passwords.eqPwd(addUserVO.getPwd1(),teacher.getPwd(),teacher.getId())){
                // 修改
            	teacher.setPwd(Passwords.saltPwd(addUserVO.getPwd2(),teacher.getId()));
                if(this.updateById(teacher)){
                    return Result.success("修改成功，请牢记您的密码！");
                }
                return Result.fail("系统错误！请联系后台管理员！");
            }
            return Result.fail("原始密码校验失败！");
        }
        return Result.fail("获取信息失败，建议您重新登录！");
    }

    /**
     * 用户名模糊账户查询
     * @param username 用户名
     * @return 账户信息列表
     */
    @Override
    public List<Teacher> blurry(String username) {
        LambdaQueryWrapper<Teacher> userWrapper = new LambdaQueryWrapper<>();
        userWrapper
                .like(Teacher::getLogin_name, username);
        return this.list(userWrapper);
    }

    /**
     * 查询账号列表，除去自己
     * @param teacher 账户信息
     * @return 账户信息列表
     */
    @Override
    public List<Teacher> list(Teacher teacher) {
        LambdaQueryWrapper<Teacher> userList = new LambdaQueryWrapper<>();

        userList
            .ne(Teacher::getId, teacher.getId());
        if (Objects.nonNull(teacher.getName())) {
        	userList.like(Teacher::getName, teacher.getName());
        }
        if (Objects.nonNull(teacher.getLogin_name())) {
        	userList.like(Teacher::getLogin_name, teacher.getLogin_name());
        }
        return this.list(userList);
    }

    /**
     * 教师用户修改学生用户密码
     * @param addUserVO 账户信息
     * @return 通用返回
     */
    @Override
    public Result<?> userChangePwd(AddUserVO addUserVO) {
        Teacher teacher = this.getById(addUserVO.getSuperid());
        // 操作人是否角色为超级用户
        if(teacher.getType() == UserType.TEACHER_USER){
            Teacher userPwd = this.getById(addUserVO.getId());
            userPwd.setPwd(Passwords.saltPwd(addUserVO.getPwd(),addUserVO.getId()));
            if(this.updateById(userPwd)){
                return Result.success("密码修改成功！");
            }
            return Result.fail("系统错误，请联系后台管理员！");
        }
        return Result.fail("权限不足，无法访问！");
    }

    /**
     * 修改冻结状态
     * @param teacher 账户信息
     * @return 通用返回
     */
	@Override
	public Result<?> changeState(Teacher teacher) {
		if(this.updateById(teacher)) {
			return Result.success("修改成功！");
		}
		return Result.fail("系统错误，请联系后台管理员！");
	}

    /**
     * 根据 id 查询单个用户
     * @param id 用户 id
     * @return User
     */
    @Override
	public Teacher selectUserById(String id) {
		 return this.getById(id);
	}

    /**
     * 修改用户信息
     * @param teacher 用户信息
     * @return boolean
     */
	@Override
	public boolean updateUser(Teacher teacher) {
		return this.updateById(teacher);
	}

    /**
     * 判断是否在解锁时间后
     * @param lastLoginTime 最后登陆时间
     * @return boolean
     */
	public boolean belongCalendar(LocalDateTime lastLoginTime) {
		// 超时毫秒数
        long outTimeMillis = UsefulValues.ERROR_LAST_TIME * 60 * 1000;
        return System.currentTimeMillis() - lastLoginTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() > outTimeMillis;
	}

    /**
     * 根据账户号查询最后一次错误登录记录
     * @param username 用户名
     * @return UserErrorLogin
     */
	public UserErrorLogin getUserErrorLoginByAccount(String username) {
	    QueryWrapper<UserErrorLogin> errorLogin = new QueryWrapper<>();
	    errorLogin
                .eq("USERNAME", username)
                .orderByDesc("ERRORLOGINDATE", "ERRORLOGINCOUNT");
		return this.iUserErrorLoginService.getOne(errorLogin);
	}

    /**
     * 根据用户名查询账户信息
     * @param username 用户名
     * @return User
     */
    public Teacher selectUserByUsername(String loginName) {
        QueryWrapper<Teacher> userWrapper = new QueryWrapper<>();
        userWrapper
                .eq("LOGIN_NAME", loginName);

        return this.userMapper.selectOne(userWrapper);
    }

    /**
     * 根据昵称查询用户
     * @param nickname 昵称
     * @return User
     */
    public Teacher selectUserByName(String name) {
        QueryWrapper<Teacher> userWrapper = new QueryWrapper<>();
        userWrapper
                .eq("NAME", name);

        return this.userMapper.selectOne(userWrapper);
    }

    /**
     * 登陆成功后添加相关 session 信息
     * @param teacher 账户信息
     */
    public void userLoginSuccess(Teacher teacher) {
        Collections.list(ServletBox.session().getAttributeNames())
                .forEach(ServletBox.session()::removeAttribute);

        Login.uid(teacher.getId());
        Login.username(teacher.getLogin_name());
        Login.setPermission(Collections.singletonList(teacher.getType().name()));
    }
}
