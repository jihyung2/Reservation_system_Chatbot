package com.reservation.kakao_skill_spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dragbar {

    @PostMapping("/webhook")
    public KakaoResponse webhook(@RequestBody KakaoRequest kakaoRequest) {
        KakaoResponse kakaoResponse = new KakaoResponse();

        // 사용자로부터 받은 메시지 내용
        String userMessage = kakaoRequest.getContent();

        if ("버튼을 누르면 드래그바를 표시합니다".equals(userMessage)) {
            // 드래그바 항목 생성
            KakaoDragBar dragBar = new KakaoDragBar("총 전류 선택", 0, 10, 1);

            // 응답 메시지 생성
            KakaoMessage message = new KakaoMessage("drag", "전류를 선택하세요.", dragBar);

            kakaoResponse.setMessage(message);
        } else {
            // 기타 로직 처리
            // ...
        }

        return kakaoResponse;
    }
}