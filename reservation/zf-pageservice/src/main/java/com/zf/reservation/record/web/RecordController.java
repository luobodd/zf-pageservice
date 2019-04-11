package com.zf.reservation.record.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zf.reservation.labroom.entity.Labroom;
import com.zf.reservation.record.entity.Record;
import com.zf.reservation.record.service.IRecordService;
import co.legu.modules.common.bean.Result;
import co.legu.modules.pager.annotation.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 *  预约记录管理
 * </p>
 *
 * @author hxy
 * @since 2019-04-09
 */
@Api("预约记录管理")
@RestController
@RequestMapping("/api/record")
public class RecordController {
	
	@Autowired
	private IRecordService recordService;
	
	/**
	 * 查询预约记录列表
	 * @param record
	 * @return
	 */
    @Pager
    @GetMapping("/list")
    @ApiOperation("查询预约记录列表")
    public Result<List<Record>> list(Record record){
        return Result.success(this.recordService.recordList(record));
    }
    
    /**
               * 预约记录审核
     * @param record
     * @return
     */
    @PostMapping("/updateRecord")
    @ApiOperation("预约记录审核")
    public Result<?> updateRecord(@RequestBody @ApiParam(name="预约记录审核",value="传入json格式",required=true) Record record){
    	return Result.success(this.recordService.updateRecord(record));
    }
    
    /**
     * 根据 id查询审核信息
     * @param id
     * @return
     */
    @GetMapping("/getRecordById")
    @ApiOperation("根据 id查询审核信息")
    public Result<Record> getRecordById(String id){
        return Result.success(this.recordService.getRecordById(id));
    }
    
}

