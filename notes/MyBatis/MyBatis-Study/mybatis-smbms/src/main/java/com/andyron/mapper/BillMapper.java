package com.andyron.mapper;

import com.andyron.pojo.Bill;

import java.util.List;

public interface BillMapper {

    int add(Bill bill);

    List<Bill> getBillList(Bill bill);

    List<Bill> getAllBill();

    int deleteBillById(Long id);

    Bill getBillById(Long id);

    int modify(Bill bill);

    int getBillCountByProviderId(Long providerId);
}
