package com.zf.reservation.record.service.impl;

import com.zf.reservation.record.entity.Record;
import com.zf.reservation.record.mapper.RecordMapper;
import com.zf.reservation.record.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hxy
 * @since 2019-04-09
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

	@Autowired
	private RecordMapper recordMapper;
	
	
	@Override
	public List<Record> recordList(Record record) {
		return recordMapper.recordList(record);
	}


	@Override
	public boolean updateRecord(Record record) {
		record.setExamine_time(LocalDateTime.now());
		return this.updateById(record);
	}


	@Override
	public Record getRecordById(String id) {
		return recordMapper.getRecordById(id);
	}
	
}
