package com.andyron.bcdlj.c03.ecommerce;

/**
 * 订单条目：描述单个产品及选购数量
 */
public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double computePrice() {
        return product.getPrice() * quantity;
    }
}
