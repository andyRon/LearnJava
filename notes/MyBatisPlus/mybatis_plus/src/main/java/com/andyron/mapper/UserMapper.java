package com.andyron.mapper;

import com.andyron.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 在对应的mapper上实现基本BaseMapper，就完成以前所有的curd功能都已经完成
 */
@Repository // 代表持久层
public interface UserMapper extends BaseMapper<User> {
}
