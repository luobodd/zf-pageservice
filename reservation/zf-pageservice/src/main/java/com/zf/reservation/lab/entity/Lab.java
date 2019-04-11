package com.zf.reservation.lab.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 实验信息实体类
 * @author hxy
 * @version 2019年4月2日 v1.0
 */
@Data
@Accessors(chain = true)
public class Lab {
	@ApiModelProperty(value="主键",name="id",required=true)
	private String id;
	
	@ApiModelProperty(value="实验名称",name="name",required=true,example="心肺复苏实验")
	private String name;
	
	@ApiModelProperty(value="实验封面图地址",example="http://xxx")
	private String img_url;
	
	@TableLogic
	@TableField("status")
	@ApiModelProperty(value="状态",example="0=逻辑删除 1=正常")
	private Integer status;
	
	@ApiModelProperty(value="创建人")
	private String create_user;
	
	@ApiModelProperty(value="创建时间")
	private LocalDateTime create_time;
	
	@ApiModelProperty(value="修改时间")
	private LocalDateTime update_time;
	
	
}
