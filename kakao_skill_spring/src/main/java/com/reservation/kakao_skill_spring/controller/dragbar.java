package com.reservation.kakao_skill_spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dragbar {

    @PostMapping("/webhook")
    public KakaoResponse webhook(@RequestBody KakaoRequest kakaoRequest) {
        String userMessage = kakaoRequest.getContent();

        KakaoMessage message = null;

        if ("버튼을 누르면 드래그바를 표시합니다".equals(userMessage)) {
            // 드래그바 항목 생성
            KakaoButton[] buttons = new KakaoButton[2];
            buttons[0] = new KakaoButton("webLink", "홈페이지로 이동", "http://www.example.com");
            buttons[1] = new KakaoButton("message", "메세지 전송", "hello");

            KakaoDragBar dragBar = new KakaoDragBar("총 전류 선택", 0, 10, 1);

            // 응답 메시지 생성
            message = new KakaoMessage("drag", buttons, dragBar);

        } else {
            // 기타 로직 처리
            // ...
        }
        KakaoResponse kakaoResponse = new KakaoResponse(message);
        return kakaoResponse;
    }
}