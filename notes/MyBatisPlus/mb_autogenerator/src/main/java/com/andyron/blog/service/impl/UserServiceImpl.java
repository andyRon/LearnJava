package com.andyron.blog.service.impl;

import com.andyron.blog.entity.User;
import com.andyron.blog.mapper.UserMapper;
import com.andyron.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AndyRon
 * @since 2021-10-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
