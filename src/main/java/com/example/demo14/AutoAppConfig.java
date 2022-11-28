package com.example.demo14;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 탐색할 패키지의 시작 위치 지정. 이 패키지 포함 하위 패키지 탐색함.
        // basePackages = {"",""} 시작 위치 여러 개 지정 가능
        // 지정하지 않을 경우 @ComponentScan 이 붙은 설정 정보 클래스의 패키지 시작 위치가 됨.
        // 권장 방법: 설정 정보 클래스(AppConfig)의 위치를 프로젝트 최상단에 두는 것이 좋음.

        // 컴포넌트 스캔 기본 대상 > 컴포넌트 스캔은 @Component 뿐 아니라 다음 내용도 추가 대상에 포함.
        // @Component : 컴포넌트 스캔에서 사용
        // @Controller : 스프링 MVC컨트롤러에서 사용
        // @Service : 스프링 비즈니스 로직에서 사용
        // @Repository : 스프링 데이터 접근 계층에서 사용
        // @Configuration : 스프링 설정 정보에서 사용
        basePackages = "com.example.demo14.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
//@Configuration이 붙은 클래스는 필터로 거른 후 등록
//예제에 붙어 있는게 있어서 거름.
public class AutoAppConfig {

}

