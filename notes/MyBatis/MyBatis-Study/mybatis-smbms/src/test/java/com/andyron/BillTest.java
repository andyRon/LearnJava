package com.andyron;

import com.andyron.mapper.BillMapper;
import com.andyron.pojo.Bill;
import com.andyron.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class BillTest {

    SqlSession sqlSession = MyBatisUtils.getSqlSession();
    BillMapper mapper = sqlSession.getMapper(BillMapper.class);


    @Test
    public void selet() {

//        Bill bill = mapper.getBillById(2l);
//        System.out.println(bill);

        List<Bill> bills = mapper.getAllBill();
        for (Bill bill : bills) {
            System.out.println(bill);
        }

        sqlSession.close();
    }

    @Test
    public void insert() {

        Bill bill = new Bill();
        bill.setId(19l);
        bill.setBillCode("BILL2022_001");
        bill.setProductName("xigua");

        System.out.println(mapper.add(bill));

        sqlSession.close();
    }

    @Test
    public void upodate() {
        Bill bill = new Bill();
        bill.setId(19l);
        bill.setProductName("西瓜");
        bill.setProductDesc("水果");
        bill.setProductUnit("个");
        bill.setIsPayment(2);
        bill.setProductCount(1000.0);

        System.out.println(mapper.modify(bill));

        sqlSession.close();
    }

    @Test
    public void deleteBillById() {
        System.out.println(mapper.deleteBillById(19l));

        sqlSession.close();
    }

    @Test
    public void getBillById() {
        System.out.println(mapper.getBillById(2l));

        sqlSession.close();
    }

    @Test
    public void getBillCountByProviderId() {
        System.out.println(mapper.getBillCountByProviderId(2l));
        sqlSession.close();
    }

    @Test
    public void getBillList() {
        Bill bill = new Bill();
        bill.setProductName("%油%");
//        bill.setIsPayment(2);
        bill.setProviderId(6l);
        System.out.println(mapper.getBillList(bill));
        sqlSession.close();
    }




}
