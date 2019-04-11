package com.zf.reservation.labtime.entity;

import java.time.LocalDateTime;

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
@ApiModel(value="Labtime对象", description="")
public class Labtime implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;
    
    @ApiModelProperty(value = "实验室id")
    private String labroom_id;

    @ApiModelProperty(value = "时间段")
    private String time;
    
    @TableLogic
	@TableField(value="status")
	@ApiModelProperty(value = "状态",example="0=逻辑删除 1=正常")
    private Integer status;

    @ApiModelProperty(value = "添加人")
    private String create_user;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime update_time;
    
    @TableField(exist = false)
    @ApiModelProperty(value = "实验室名称")
    private String labroomName;
    
    @TableField(exist = false)
    @ApiModelProperty(value = "创建人姓名")
    private String userName;


}
