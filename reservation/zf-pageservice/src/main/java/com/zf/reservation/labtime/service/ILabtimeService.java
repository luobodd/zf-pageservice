package com.zf.reservation.labtime.service;

import com.zf.reservation.labtime.entity.Labtime;
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
public interface ILabtimeService extends IService<Labtime> {
	List<Labtime> list(String labroom_id);
	Result<?> addLabtime(Labtime labtime);
	Labtime getLabtimeById(String id);
	boolean updateLabtime(Labtime labtime);
	boolean deleteById(String id);
	Result<?> changeState(Labtime labtime);
}
