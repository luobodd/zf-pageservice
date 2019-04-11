package com.zf.reservation.lab.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zf.common.constant.UsefulValues;
import com.zf.reservation.lab.entity.Lab;
import com.zf.reservation.lab.service.ILabService;
import com.zf.util.FileUtil;

import co.legu.modules.auth.annotation.NoNeedAuth;
import co.legu.modules.common.bean.Result;
import co.legu.modules.pager.annotation.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 实验信息管理控制层
 * 
 * @author hxy
 * @version 2019年4月2日 v1.0
 */
@Api("实验信息模块")
@RestController
@RequestMapping("/api/lab")
public class LabController {
	@Autowired
	private ILabService ILabService;

	/**
	 * 查询实验信息列表
	 * 
	 * @param lab
	 * @return
	 */
	@Pager
	@GetMapping("/list")
	@ApiOperation("查询实验信息列表")
	public Result<List<Lab>> list(Lab lab) {
		return Result.success(this.ILabService.list(lab));
	}

	/**
	 * 添加实验信息
	 * 
	 * @param lab
	 * @return
	 */
	@PostMapping("/addLab")
	@ApiOperation("添加实验信息")
	public Result<?> addLab(@RequestBody @ApiParam(name = "实验信息对象", value = "传入json格式", required = true) Lab lab) {
		return Result.success(ILabService.addLab(lab));
	}

	/**
	 * 根据 id查询实验信息
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getLabById")
	@ApiOperation("根据 id查询实验信息")
	public Result<Lab> getLabById(String id) {
		return Result.success(this.ILabService.selectLabById(id));
	}

	/**
	 * 修改实验信息
	 * 
	 * @param lab
	 * @return
	 */
	@PostMapping("/updateLab")
	@ApiOperation("修改实验信息")
	public Result<?> updateLab(@RequestBody @ApiParam(name = "实验信息对象", value = "传入json格式", required = true) Lab lab) {
		return Result.success(this.ILabService.updateLab(lab));
	}

	/**
	 * 删除实验信息
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteLab")
	@ApiOperation("删除实验信息")
	public Result<?> deleteLabByid(String id) {
		return Result.success(this.ILabService.removeById(id));
	}

	
	
	/**
	 * @function 实验封面上传
	 * @param file
	 * @return
	 */
	@PostMapping("/imgUpload")
	@ApiOperation("实验封面上传")
	public Result<?> imgUpload(@RequestParam(value = "file", required = false) MultipartFile file) {
		if (file == null) {
			return Result.fail("图片上传失败！");
		} else {
			String path = FileUtil.uploadFile(file, UsefulValues.SAVE_URL, UsefulValues.FILE_TYPE);
			return Result.success(path);
		}

	}
	
	/**
	 * 获取实验下拉菜单
	 * @return
	 */
	@GetMapping("/selectLab")
	@ApiOperation(value = "获取实验下拉菜单")
	public Result<List<Lab>> selectLab(Lab lab){
		return Result.success(this.ILabService.list(lab));
	}

}
