package com.example.demo14.lifecycle;
//*빈 생명주기 콜백
//스프링빈 생성 및 소멸되기 직전에 호출해주는 메서드

//가상의 클라이언트
public class NetworkClient {

    // 접속해야하는 서버의 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect(); /*객체가 상성될 떄*/
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect = " + url);
    }

}
