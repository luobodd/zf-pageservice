package com.zf.reservation.user.service;

import co.legu.modules.common.bean.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.reservation.user.entity.Teacher;
import com.zf.reservation.user.vo.AddUserVO;

import java.util.List;

public interface IUserService extends IService<Teacher> {
    Teacher selectUserById(String id);

    Result<?> addUser(AddUserVO addUserVO);

    Result<?> login(AddUserVO user);

    Result<?> changePwd(AddUserVO addUserVO);

    List<Teacher> list(Teacher teacher);

    List<Teacher> blurry(String username);

    Result<?> userChangePwd(AddUserVO addUserVO);

    Result<?> changeState(Teacher teacher);

    boolean updateUser(Teacher teacher);

}
