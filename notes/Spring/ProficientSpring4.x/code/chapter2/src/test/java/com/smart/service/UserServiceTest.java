package com.smart.service;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Andy Ron
 */
@ContextConfiguration("classpath*:/smart-context.xml")  // 启动Spring容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test   // 标注测试方法
    public void hasMatchUser() {
        boolean b1 = userService.hasMatchUser("admin", "12131");
        boolean b2 = userService.hasMatchUser("admin", "12345");
        assertTrue(b1);
        assertTrue(!b2);

    }

    @Test
    public void findUserByUserName() {
        User user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }
}
