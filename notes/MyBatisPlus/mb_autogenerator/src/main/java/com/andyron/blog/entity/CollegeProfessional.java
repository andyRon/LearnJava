package com.andyron.blog.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学院专业
 * </p>
 *
 * @author AndyRon
 * @since 2021-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CollegeProfessional对象", description="学院专业")
public class CollegeProfessional implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学院id")
    private Integer collegeId;

    @ApiModelProperty(value = "专业搜索时分类id，对应classify表")
    private Integer classId;

    @ApiModelProperty(value = "0学历1非学历")
    private Boolean type;

    @ApiModelProperty(value = "学位，学历，关联classify")
    private Integer degree;

    @ApiModelProperty(value = "非学历项目类型")
    private Integer projectType;

    @ApiModelProperty(value = "授课语言(en, zh, en|zh)")
    private String instructionLanguage;

    @ApiModelProperty(value = "学习期限")
    private String duration;

    @ApiModelProperty(value = "学费")
    private BigDecimal tuitionFee;

    @ApiModelProperty(value = "申请费")
    private BigDecimal applicationFee;

    @ApiModelProperty(value = "入学时间")
    private String admissionTime;

    @ApiModelProperty(value = "截止时间(申请时间时间段)")
    private String deadline;

    @ApiModelProperty(value = "是否有奖学金(0是无，1是有)")
    private Boolean scholarship;

    @ApiModelProperty(value = "最大年龄要求")
    private Integer maxage;

    @ApiModelProperty(value = "最小年龄要求")
    private Integer minage;

    @ApiModelProperty(value = "语言要求")
    private String languageRequire;

    @ApiModelProperty(value = "留学生学历要求(对应classify)")
    private Integer degreeRequire;

    @ApiModelProperty(value = "绩点要求")
    private String gpa;

    @ApiModelProperty(value = "是否自行组织入学资格测试，0否1是")
    private Boolean test;

    @ApiModelProperty(value = "是否需要推荐信,0不要1要")
    private Boolean letter;

    @ApiModelProperty(value = "是否需要面试，0否1是")
    private Boolean interview;

    @ApiModelProperty(value = "专业组织活动")
    private Integer activityId;

    @ApiModelProperty(value = "是否有旅游参观0否1是")
    private Boolean visit;

    @ApiModelProperty(value = "是否有实习,0否1是")
    private Boolean practice;

    @ApiModelProperty(value = "是否有学分,0否1是")
    private Boolean credit;

    @ApiModelProperty(value = "是否有证书,0否1是")
    private Boolean certificate;

    @ApiModelProperty(value = "现有规模")
    private Integer scale;

    @ApiModelProperty(value = "授课形式（如：单独开班／中外混班）")
    private Boolean form;

    @ApiModelProperty(value = "专业介绍图")
    private String picture;

    @ApiModelProperty(value = "申请专业所需材料，管理classify表，|隔开")
    private String materials;

    @ApiModelProperty(value = "权重")
    private String weight;

    @ApiModelProperty(value = "编辑人")
    private Integer adminid;

    @ApiModelProperty(value = "状态0未审核1已审核")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    private Integer createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer updateTime;

    @ApiModelProperty(value = "原始数据id，审核通过覆盖原始数据")
    private Integer original;


}
