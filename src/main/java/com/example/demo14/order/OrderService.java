package com.example.demo14.order;


public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
