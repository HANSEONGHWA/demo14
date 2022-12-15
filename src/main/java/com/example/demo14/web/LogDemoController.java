package com.example.demo14.web;

import com.example.demo14.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//로거가 잘 작동하는지 확인하는 테스트용 컨트롤러.
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")  /*"log-demo" 요청이 오면*/
    @ResponseBody /*문자 반환*/
    public String logDemo(HttpServletRequest request) {  /*HttpServletRequest을 통해 고객요청 정보 받음.*/
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL); /*로그가 남도록 함. [http://localhost:8080/log-demo]*/
        /*이렇게 받은 requestULR값을 myLogger에 저장해둠. myLogger는 HTTP 요청 당 각각 구분되므로 값이 섞이지 않음. */
        //컨트롤러 보다는 공통처리가 가능한 스프링 인터셉터나 서블릿 필터를 활용하는 것이 좋음.


        //컨트롤러에서 controller test 로그를 남김.
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
