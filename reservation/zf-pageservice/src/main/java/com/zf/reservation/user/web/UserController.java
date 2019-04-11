package com.zf.reservation.user.web;


import co.legu.modules.auth.annotation.NoNeedAuth;
import co.legu.modules.auth.annotation.Permission;
import co.legu.modules.common.bean.Result;
import co.legu.modules.common.web.util.ServletBox;
import co.legu.modules.pager.annotation.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.zf.reservation.user.entity.Student;
import com.zf.reservation.user.entity.Teacher;
import com.zf.reservation.user.service.IUserService;
import com.zf.reservation.user.service.IStudentService;
import com.zf.reservation.user.vo.AddUserVO;
import com.zf.util.ValidateCodeUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户登陆类
 * @author hxy
 * @version v1.0 2019年3月27日14:59:43
 *
 */
@Api("用户模块")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService IUserService;
    @Autowired
    private IStudentService IStudentService;

    /**
     * 生成验证码并返回
     * @return stream
     * @throws Exception
     */
    @ApiOperation("生成验证码并返回")
    @NoNeedAuth
	@GetMapping("/validateCode")
	public void validateCode() throws Exception {
		// 设置响应的类型格式为图片格式
		ServletBox.response().setContentType("image/jpeg");
		// 禁止图像缓存
        ServletBox.response().setHeader("Pragma", "no-cache");
        ServletBox.response().setHeader("Cache-Control", "no-cache");
        ServletBox.response().setDateHeader("Expires", 0);
 
		HttpSession session = ServletBox.request().getSession();
 
		ValidateCodeUtils vCode = new ValidateCodeUtils(120,40,5,100);
		session.setAttribute("code", vCode.getCode());
		vCode.write(ServletBox.response().getOutputStream());
	}

    /**
     * 登录接口
     * @param user 用户信息
     * @return 通用返回
     */
    @NoNeedAuth
    @ApiOperation("登陆接口")
    @PostMapping("/login")
    public Result<?> login(@RequestBody AddUserVO user) {
/*		String sessionCode = (String) ServletBox.session().getAttribute("code");
        // 忽略验证码大小写
		if (!admin.getValidateCode().toLowerCase().equals(sessionCode.toLowerCase())) {
			return Result.fail("验证码错误");
		}else {
			return Result.success(this.IAdminService.login(admin));
        }*/
		
		return Result.success(this.IUserService.login(user));
    }

    /**
     * 注销接口
     * @return 通用返回
     */
    @PostMapping("/logout")
    @ApiOperation("注销登陆接口")
    public Result<?> logout() {
        // 销毁session
        ServletBox.session().invalidate();
        return Result.success("注销成功！");
    }

    /**
     * 注册用户
     * @param addUserVO 用户信息
     * @return 通用返回
     */
    @NoNeedAuth
    @PostMapping("/add")
    @ApiOperation("注册用户")
    public Result<?> addUser(@RequestBody AddUserVO addUserVO) {
        return Result.success(this.IUserService.addUser(addUserVO));
    }

    /**
     * 用户名模糊账户查询
     * @param username 用户名
     * @return 账户信息列表
     */
    @Pager
    @GetMapping("/blurry")
    @ApiOperation("用户名模糊账户查询")
    public Result<List<Teacher>> blurry(String username){
        return Result.success(this.IUserService.blurry(username));
    }

    /**
     * 修改密码
     * @param addUserVO 账户信息
     * @return 通用返回
     */
    @PostMapping("/changepwd")
    @ApiOperation("修改密码")
    public Result<?> changePwd(@RequestBody AddUserVO addUserVO){
        return Result.success(this.IUserService.changePwd(addUserVO));
    }

    /**
     * 查询账号列表，除去自己
     * @param teacher 账户信息
     * @return 账户信息列表
     */
    @Pager
    @GetMapping("/list")
    @ApiOperation("查询账号列表，除去自己")
    public Result<List<Teacher>> list(Teacher teacher){
        return Result.success(this.IUserService.list(teacher));
    }

    /**
     * 查询学生账号列表
     * @param teacher 账户信息
     * @return 账户信息列表
     */
    @Pager
    @GetMapping("/stulist")
    @ApiOperation("查询学生账号列表")
    public Result<List<Student>> stulist(Student student){
        return Result.success(this.IStudentService.list(student));
    }
    /**
     * 根据 id 查询单个用户
     * @param id 账户 id
     * @return 账户信息
     */
    @GetMapping("/getUserById")
    @ApiOperation("根据 id 查询单个用户")
    public Result<Teacher> getUserById(String id){
        return Result.success(this.IUserService.selectUserById(id));
    }

    /**
     * 教师用户修改学生用户密码
     * @param addUserVO 账户信息
     * @return 通用返回
     */
    @PostMapping("/userpwd")
    @ApiOperation("教师用户修改学生用户密码")
    public Result<?> sauser_changePwd(@RequestBody AddUserVO addUserVO) {
        return Result.success(this.IUserService.userChangePwd(addUserVO));
    }

    /**
     * 教师用户修改学生用户的信息
     * @param teacher 账户信息
     * @return 通用返回
     */
    @PostMapping("/updateUser")
    @ApiOperation("教师用户修改学生用户的信息")
    public Result<?> updateUser(@RequestBody Teacher teacher) {
        return Result.success(this.IUserService.updateUser(teacher));
    }

    /**
     * 修改可用状态
     * @param teacher 账户信息
     * @return 通用返回
     */
    @PostMapping("/changeState")
    @ApiOperation("修改可用状态")
    public Result<?> changeState(@RequestBody Teacher teacher){
    	 return Result.success(this.IUserService.changeState(teacher));
    }

}
