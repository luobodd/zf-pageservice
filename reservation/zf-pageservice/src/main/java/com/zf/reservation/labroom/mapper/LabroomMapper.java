package com.zf.reservation.labroom.mapper;

import com.zf.reservation.labroom.entity.Labroom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
public interface LabroomMapper extends BaseMapper<Labroom> {
	List<Labroom> selectLabroomList(Labroom labroom);
	Labroom getLabroomById(@Param("id") String id);
}
