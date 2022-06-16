package com.andyron;

import com.andyron.mapper.ProviderMapper;
import com.andyron.pojo.Provider;
import com.andyron.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class ProviderTest {
    SqlSession sqlSession = MyBatisUtils.getSqlSession();
    ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);

    @Test
    public void add() {
        Provider provider = new Provider();
        provider.setId(16l);
        provider.setProCode("SH_GYS001");
        provider.setProName("上海家乐福");
        System.out.println(mapper.add(provider));

        sqlSession.close();
    }

    @Test
    public void modify() {
        Provider provider = new Provider();
        provider.setId(16l);
        provider.setProDesc("长期合作小伙伴。。。");
        provider.setProContact("张三");
        provider.setProPhone("15331566739");
        System.out.println(mapper.modify(provider));
        sqlSession.close();
    }

    @Test
    public void getProviderById() {

        System.out.println(mapper.getProviderById(16l));
        sqlSession.close();
    }

    @Test
    public void getProviderList() {
        Provider provider = new Provider();
        provider.setProName("%北京%");
        System.out.println(mapper.getProviderList(provider));
        sqlSession.close();
    }
}
