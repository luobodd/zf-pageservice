package com.zf.reservation.labtime.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zf.reservation.labtime.entity.Labtime;
import com.zf.reservation.labtime.service.ILabtimeService;
import co.legu.modules.common.bean.Result;
import co.legu.modules.pager.annotation.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
@Api("实验室时间段模块")
@RestController
@RequestMapping("/api/labtime")
public class LabtimeController {
	@Autowired
	private ILabtimeService ILabtimeService;
	
	/**
	 * 查询实验信息列表
	 * @param labtime
	 * @return
	 */
    @Pager
    @GetMapping("/list")
    @ApiOperation("查询实验室时间段信息列表")
    public Result<List<Labtime>> list(String labroom_id){
        return Result.success(this.ILabtimeService.list(labroom_id));
    }
    
    /**
     * 保存实验室时间段信息对象
     * @param labtime
     * @return
     */
    @PostMapping("addLabtime")
    @ApiOperation("保存实验室时间段信息对象")
    public Result<?> addLabtime(@RequestBody @ApiParam(name="实验室时间段信息对象",value="传入json格式",required=true) Labtime labtime){
		return Result.success(ILabtimeService.addLabtime(labtime));
    }
    
    
    /**
     * 根据 id查询实验信息
     * @param id
     * @return
     */
    @GetMapping("/getLabtimeById")
    @ApiOperation("根据 id查询实验室时间段信息")
    public Result<Labtime> getLabtimeById(String id){
        return Result.success(this.ILabtimeService.getLabtimeById(id));
    }
    
    /**
   * 修改实验室时间段信息
     * @param labtime
     * @return
     */
    @PostMapping("/updateLabtime")
    @ApiOperation("修改实验室时间段信息")
    public Result<?> updateLabtime(@RequestBody @ApiParam(name="实验室时间段信息对象",value="传入json格式",required=true) Labtime labtime){
    	return Result.success(this.ILabtimeService.updateLabtime(labtime));
    }
    
    
    /**
     * 删除实验室时间段信息
     * @param id
     * @return
     */
    @PostMapping("/deleteLabtime")
    @ApiOperation("删除实验室时间段信息")
    public Result<?> deleteLabtimeByid(String id){
    	return Result.success(this.ILabtimeService.removeById(id));
    }
    
    /**
     * 
     * @param labtime
     * @return
     */
    @PostMapping("/changeStatus")
    @ApiOperation("修改实验室时间段状态")
    public Result<?> changeStatus(@RequestBody Labtime labtime){
		return Result.success(this.ILabtimeService.changeState(labtime));
    }
    
}

