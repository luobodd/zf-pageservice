package com.zf.reservation.labtime.service.impl;

import com.zf.common.constant.UsefulValues;
import com.zf.reservation.labtime.entity.Labtime;
import com.zf.reservation.labtime.mapper.LabtimeMapper;
import com.zf.reservation.labtime.service.ILabtimeService;
import co.legu.modules.common.bean.Result;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
@Service
public class LabtimeServiceImpl extends ServiceImpl<LabtimeMapper, Labtime> implements ILabtimeService {
	@Autowired
	private LabtimeMapper labtimeMapper;

	@Override
	public List<Labtime> list(String labroom_id) {

		return labtimeMapper.selectLabtimeList(labroom_id);
	}

	@Override
	public Result<?> addLabtime(Labtime labtime) {
		String uid = IdWorker.getIdStr();
		labtime.setId(uid).setStatus(UsefulValues.TRUE).setCreate_time(LocalDateTime.now());
		if (this.save(labtime)) {
			return Result.success(labtime);
		}
		return Result.fail("系统错误！请联系后台管理员！");
	}

	@Override
	public Labtime getLabtimeById(String id) {
		return labtimeMapper.getLabtimeById(id);
	}

	@Override
	public boolean updateLabtime(Labtime labtime) {
		labtime.setUpdate_time(LocalDateTime.now());
		return this.updateById(labtime);
	}

	@Override
	public boolean deleteById(String id) {
		return this.removeById(id);
	}

	@Override
	public Result<?> changeState(Labtime labtime) {
		if(labtimeMapper.changeStatus(labtime)) {
			return Result.success("修改成功！");
		}
		return Result.fail("系统错误，请联系后台管理员！");
	}
}
