package com.example.demo14.web;

import com.example.demo14.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//비즈니스 로직...이 있는 서비스 계층에서 로그 출력
// request scope를 사용하지 않고 파라미터로 정보를 넘긴다면, requestULR 같은 웹과 관련된 정보가 웹과 관련없는 서비스 계층까지 넘어감.
//웹과 관련된 정보는 컨트롤러까지만 사용.
//request scope의 myLogger덕분에 파라미터로 넘기지 않고, MyLogger의 멤버변수에 저정해서 코드와 계층을 깔끔하게 유지가능.
@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
