package com.zf.reservation.record.entity;

import java.time.LocalDateTime;
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
 * @since 2019-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "时间段id")
    private String labtime_id;

    @ApiModelProperty(value = "预约时间")
    private LocalDateTime date;

    @ApiModelProperty(value = "预约人")
    private String create_user;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "参与人员组")
    private String participants;

    @ApiModelProperty(value = "审核状态(0审批中=有人预约，1审批通过 = 预约满  2=审批未通过 null=无人预约)")
    private String state;

    @ApiModelProperty(value = "审核人")
    private String examine_user;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime examine_time;

    @ApiModelProperty(value = "未通过原因")
    private String reason;
    
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    
    @ApiModelProperty(value = "教师姓名")
    private String teaName;
    
    @ApiModelProperty(value = "实验名称")
    private String labName;
    
    @ApiModelProperty(value = "实验室名称")
    private String labroomName;
    
    @ApiModelProperty(value = "时间段")
    private String time;

}
