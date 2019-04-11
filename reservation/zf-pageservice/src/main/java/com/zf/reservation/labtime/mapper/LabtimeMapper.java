package com.zf.reservation.labtime.mapper;

import com.zf.reservation.labtime.entity.Labtime;
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
public interface LabtimeMapper extends BaseMapper<Labtime> {
	List<Labtime> selectLabtimeList(@Param("labroom_id") String labroom_id);
	Labtime getLabtimeById(@Param("id") String id);
	boolean changeStatus(Labtime labtime);
}
