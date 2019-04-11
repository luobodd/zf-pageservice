package com.zf.reservation.labroom.web;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zf.reservation.labroom.entity.Labroom;
import com.zf.reservation.labroom.service.ILabroomService;
import co.legu.modules.common.bean.Result;
import co.legu.modules.pager.annotation.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 *  实验室信息前端控制器
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
@Api("实验室信息模块")
@RestController
@RequestMapping("/api/labroom")
public class LabroomController {
	@Autowired
	private ILabroomService ILabroomService;
	
	/**
	 * 查询实验信息列表
	 * @param labroom
	 * @return
	 */
    @Pager
    @GetMapping("/list")
    @ApiOperation("查询实验室信息列表")
    public Result<List<Labroom>> list(Labroom labroom){
        return Result.success(this.ILabroomService.list(labroom));
    }
    
    /**
     * 保存实验室信息对象
     * @param labroom
     * @return
     */
    @PostMapping("addLabroom")
    @ApiOperation("保存实验室信息对象")
    public Result<?> addLabroom(@RequestBody @ApiParam(name="实验室信息对象",value="传入json格式",required=true) Labroom labroom){
		return Result.success(ILabroomService.addLabroom(labroom));
    }
    
    
    /**
     * 根据 id查询实验信息
     * @param id
     * @return
     */
    @GetMapping("/getLabroomById")
    @ApiOperation("根据 id查询实验室信息")
    public Result<Labroom> getLabroomById(String id){
        return Result.success(this.ILabroomService.getLabroomById(id));
    }
    
    /**
     * 修改实验室信息
     * @param labroom
     * @return
     */
    @PostMapping("/updateLabroom")
    @ApiOperation("修改实验室信息")
    public Result<?> updateLabroom(@RequestBody @ApiParam(name="实验室信息对象",value="传入json格式",required=true) Labroom labroom){
    	return Result.success(this.ILabroomService.updateLabroom(labroom));
    }
    
    
    /**
     * 删除实验室信息
     * @param id
     * @return
     */
    @PostMapping("/deleteLabroom")
    @ApiOperation("删除实验室信息")
    public Result<?> deleteLabroomByid(String id){
    	return Result.success(this.ILabroomService.removeById(id));
    }
    
}

