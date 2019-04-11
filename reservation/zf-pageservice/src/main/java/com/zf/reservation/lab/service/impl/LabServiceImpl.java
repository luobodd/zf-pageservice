package com.zf.reservation.lab.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.common.constant.UsefulValues;
import com.zf.reservation.lab.entity.Lab;
import com.zf.reservation.lab.mapper.ILabMapper;
import com.zf.reservation.lab.service.ILabService;
import co.legu.modules.common.bean.Result;
@Service
@Transactional
public class LabServiceImpl extends ServiceImpl<ILabMapper,Lab> implements ILabService{
	
	@Autowired
	private ILabMapper labMapper;
	/**
	 * 查询实验列表
	 */
	@Override
	public List<Lab> list(Lab lab) {
		LambdaQueryWrapper<Lab> labList = new LambdaQueryWrapper<>();
		if(Objects.nonNull(lab.getName())) {
			labList.like(Lab::getName,lab.getName());
		}
		return this.list(labList);
	}
	
	/**
	 * 添加实验信息
	 */
	@Override
	public Result<?> addLab(Lab lab) {
		String uid = IdWorker.getIdStr();
		lab
        	.setId(uid)
        	.setStatus(UsefulValues.TRUE)
        	.setCreate_time(LocalDateTime.now());
        if(this.save(lab)){
            return Result.success(lab);
        }
        return Result.fail("系统错误！请联系后台管理员！");
	}
	
	/**
	 * 根据id查询实验信息
	 */
	@Override
	public Lab selectLabById(String id) {
		return this.getById(id);
	}

	@Override
	public boolean updateLab(Lab lab) {
		lab.setUpdate_time(LocalDateTime.now());
		return this.updateById(lab);
	}

	@Override
	public boolean deleteById(String id) {
		return this.removeById(id);
	}

}
