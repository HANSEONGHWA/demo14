package com.example.demo14.member.order;

import com.example.demo14.member.MemberRepository;
import org.springframework.stereotype.Service;


public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
