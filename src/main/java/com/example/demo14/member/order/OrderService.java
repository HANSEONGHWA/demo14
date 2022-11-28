package com.example.demo14.member.order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
