package com.andyron.bcdlj.c03.ecommerce;

import java.util.Date;

public class Order {
    private String id;
    private User user;
    /**
     * 购买产品列表及其数量
     */
    private OrderItem[] items;
    /**
     * 下单时间
     */
    private Date createtime;
    /**
     * 收货人
     */
    private String receiver;
    private String address;
    private String phone;
    private String status;

    public double computeTotalPrice() {
        double totalPrice = 0;
        if (items != null) {
            for(OrderItem item : items) {
                totalPrice += item.computePrice();
            }
        }
        return totalPrice;
    }
}
