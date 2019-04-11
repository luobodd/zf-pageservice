package com.zf.reservation.labroom.service.impl;

import com.zf.common.constant.UsefulValues;
import com.zf.reservation.labroom.entity.Labroom;
import com.zf.reservation.labroom.mapper.LabroomMapper;
import com.zf.reservation.labroom.service.ILabroomService;
import com.zf.reservation.labtime.entity.Labtime;
import com.zf.reservation.labtime.mapper.LabtimeMapper;

import co.legu.modules.common.bean.Result;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
@Service
public class LabroomServiceImpl extends ServiceImpl<LabroomMapper, Labroom> implements ILabroomService {

	@Autowired
	private LabroomMapper labroomMapper;
	@Autowired
	private LabtimeMapper labtimeMapper;
	
	@Override
	public List<Labroom> list(Labroom labroom) {
		
		return labroomMapper.selectLabroomList(labroom);
	}
	@Override
	public Result<?> addLabroom(Labroom labroom) {
		String uid = IdWorker.getIdStr();
		labroom
        	.setId(uid)
        	.setStatus(UsefulValues.TRUE)
        	.setCreate_time(LocalDateTime.now());
		
        if(this.save(labroom)){
        	this.saveLabRoomTime(uid);
            return Result.success(labroom);
        }
        return Result.fail("系统错误！请联系后台管理员！");
	}
	
	@Override
	public Labroom getLabroomById(String id) {
		return labroomMapper.getLabroomById(id);
	}
	
	@Override
	public boolean updateLabroom(Labroom labroom) {
		labroom.setUpdate_time(LocalDateTime.now());
		return this.updateById(labroom);
	}
	
	@Override
	public boolean deleteById(String id) {
		return this.removeById(id);
	}
	
	/**
	 * 根据实验室id 批量插入实验室时间段(固定)
	 * @return
	 */
	@Transactional
	public void saveLabRoomTime(String labroomId) {
		Labtime time = new Labtime();
		String[] timeArr = {"09:00-10:00","11:00-12:00","14:00-15:00","18:00-19:00"};
		for(String t : timeArr) {
			time.setId(IdWorker.getIdStr());
			time.setTime(t);
			time.setLabroom_id(labroomId);
			time.setCreate_time(LocalDateTime.now());
			time.setStatus(UsefulValues.TRUE);
			labtimeMapper.insert(time);
		}
	}
}
