package com.andyron.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Andy Ron
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

//    @TableId(type = IdType.ID_WORKER) // 默认的
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version  // 乐观锁注解
    private Integer version;

    @TableLogic // 逻辑删除
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
