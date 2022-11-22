package com.example.demo14.singleton;
//싱글톤 방식의 주의점
//공유되는 필드의 값을 변경하는 것이 문제.
//스프링 빈은 항상 무상태(stateless)로 설계해야함.
public class StatefulService {

//    private int price; // 상태를 유지하는 필드 10000->20000

    public int order(String name, int price){
        System.out.println("name = " + name + "price" + price);
//        this.price = price; //여기가 문제
        return price;
    }

//    public int getPrice(){
//        return price; //20000을 꺼냄.
//    }
}
