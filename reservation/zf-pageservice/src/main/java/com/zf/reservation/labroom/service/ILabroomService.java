package com.zf.reservation.labroom.service;

import com.zf.reservation.labroom.entity.Labroom;

import co.legu.modules.common.bean.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
public interface ILabroomService extends IService<Labroom> {
	List<Labroom> list(Labroom labroom);
	Result<?> addLabroom(Labroom labroom);
	Labroom getLabroomById(String id);
	boolean updateLabroom(Labroom labroom);
	boolean deleteById(String id);
}
