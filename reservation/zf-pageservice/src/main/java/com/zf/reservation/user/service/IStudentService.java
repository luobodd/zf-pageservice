package com.zf.reservation.user.service;

import co.legu.modules.common.bean.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.reservation.user.entity.Student;
import com.zf.reservation.user.vo.AddUserVO;

import java.util.List;

public interface IStudentService extends IService<Student> {
	Student selectUserById(String id);
	
    Result<?> login(AddUserVO user);

    Result<?> changePwd(AddUserVO addUserVO);

    List<Student> list(Student student);

    List<Student> blurry(String username);

    Result<?> userChangePwd(AddUserVO addUserVO);

    Result<?> changeState(Student student);

    boolean updateUser(Student student);

}
