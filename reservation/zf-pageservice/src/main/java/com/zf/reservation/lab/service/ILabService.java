package com.zf.reservation.lab.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.reservation.lab.entity.Lab;

import co.legu.modules.common.bean.Result;

public interface ILabService extends IService<Lab>{
	List<Lab> list(Lab lab);
	Result<?> addLab(Lab lab);
	Lab selectLabById(String id);
	boolean updateLab(Lab lab);
	boolean deleteById(String id);
} 
