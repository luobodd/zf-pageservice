package com.zf.reservation.labroom.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hxy
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="实验室对象", description="")
public class Labroom implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;
    
    @ApiModelProperty(value = "实验室名称")
    private String name;

    @ApiModelProperty(value = "实验id")
    private String lab_id;

	@TableLogic
	@TableField(value="status")
	@ApiModelProperty(value = "状态",example="0=逻辑删除 1=正常")
    private Integer status;

    @ApiModelProperty(value = "创建人")
    private String create_user;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime update_time;
    
    @TableField(exist = false)
    @ApiModelProperty(value = "实验名称")
    private String labName;
    
    @TableField(exist = false)
    @ApiModelProperty(value = "创建人姓名")
    private String userName;
    
    @ApiModelProperty(value = "实验室地址")
    private String address;

    @ApiModelProperty(value = "负责人姓名")
    private String person;
    
    @ApiModelProperty(value = "负责人电话")
    private String phone;
    
    @ApiModelProperty(value = "备注")
    private String remark;
}
