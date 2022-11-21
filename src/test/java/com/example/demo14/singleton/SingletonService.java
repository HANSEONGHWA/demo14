package com.example.demo14.singleton;

public class SingletonService {
    //객체 인스턴스 생성 (static 영역에 객체를 1개만 생성)
    private static final SingletonService instance = new SingletonService();

    //조회 (public 으로 열어서 객체 인스턴스가 필요하면 static 메서드를 통해서만 조회하도록 허용.)
    public static SingletonService getInstance() {
        return instance;
    }

    //생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 하지 못하게 막음.
    private SingletonService(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
