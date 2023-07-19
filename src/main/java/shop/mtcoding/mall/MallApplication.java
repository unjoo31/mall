package shop.mtcoding.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 어노테이션을 통해 스프링 Bean을 읽어와 자동으로 생성해준다.
// 이 어노테이션이 있는 파일 위치부터 설정들을 읽어가므로, 반드시 프로젝트의 최상단에 만들어야 한다.
@SpringBootApplication
public class MallApplication {

    public static void main(String[] args) {
        // SpringApplication.run() 으로 해당 클래스를 run하면 내장 WAS를 실행한다.
        // 내장 WAS의 장점으로는 개발자가 따로 톰캣과 같은 외부 WAS를 설치 후 설정해두지 않아도 애플리케이션을 실행할 수 있다.
        SpringApplication.run(MallApplication.class, args);
    }
}
