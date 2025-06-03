package co.kr.metacoding.backendtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BackendtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendtestApplication.class, args);
    }

}
