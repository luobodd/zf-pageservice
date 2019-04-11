package com.zf.reservation.record.service;

import com.zf.reservation.record.entity.Record;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hxy
 * @since 2019-04-09
 */
public interface IRecordService extends IService<Record> {
	List<Record> recordList(Record record);
	boolean updateRecord(Record record);
	Record getRecordById(String id);
}
