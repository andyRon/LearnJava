package com.andyron;

import com.andyron.mapper.UserMapper;
import com.andyron.pojo.User;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    /*
    继承了BaseMapper，也可以编写自己的扩展方法
     */
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询全部用户. 参数是一个Wrapper，条件构造器
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
//        user.setId(6L);
        user.setName("AndyRon1");
        user.setAge(3);
        user.setEmail("rongming.2008@163.com");
        int insert = userMapper.insert(user);  // id自动生成了
        System.out.println(insert);     // 受影响的行数
        System.out.println(user);
    }

    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1452569851037016067L);
        user.setName("Andy");
        user.setAge(24);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    // 测试乐观锁成功的情况
    @Test
    void testOptimisticLocker() {
        User user = userMapper.selectById(1L);
        user.setName("andy");
        user.setEmail("xxxx@qq.com");
        userMapper.updateById(user);
    }
    // 测试乐观锁失败的情况，多线程
    @Test
    void testOptimisticLocker2() {
        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("andy1111");
        user.setEmail("xxxx@qq.com");

        // 模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("andy222");
        user2.setEmail("yyyyy@qq.com");
        userMapper.updateById(user2);

        // 可通过自旋锁来尝试多次提交（🔖JUC）
        userMapper.updateById(user);  // 如果没有乐观锁，user的值就应该覆盖user2的值
    }

    // 测试查询
    @Test
    void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    // 测试批量查询
    @Test
    void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 按条件查询之一：map
    @Test
    void testSelectMap() {
        HashMap<String, Object> map = new HashMap<>();
        // 自定义查询
        map.put("name", "AndyRon");
        map.put("age", 3);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    // 测试分页查询
    @Test
    void testPage() {
        // 参数：当前页，页面大小
        Page<User> page = new Page<>(2, 5);
        userMapper.selectPage(page, null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    // 测试删除
    @Test
    void testDelete() {
//        userMapper.deleteById(1452569851037016065L);
//        userMapper.deleteBatchIds(Arrays.asList(1452569851037016067L, 1452562031445110787L));

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "AndyRon1");
        userMapper.deleteByMap(map);
    }

    // 测试逻辑删除
    @Test
    void testLogicDel() {
//        userMapper.deleteById(1L);
        userMapper.selectById(1L);
    }


}
