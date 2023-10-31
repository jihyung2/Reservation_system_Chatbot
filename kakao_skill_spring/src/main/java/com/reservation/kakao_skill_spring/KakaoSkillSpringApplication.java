package com.reservation.kakao_skill_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.reservation.kakao_skill_spring.repository")
@EntityScan(basePackages = "com.reservation.kakao_skill_spring.entity")
public class KakaoSkillSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoSkillSpringApplication.class, args);
    }

}
