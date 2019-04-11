package com.zf.reservation.record.mapper;

import com.zf.reservation.record.entity.Record;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2019-04-09
 */
public interface RecordMapper extends BaseMapper<Record> {
	List<Record> recordList(Record record);
	Record getRecordById(@Param("id") String id);
}
