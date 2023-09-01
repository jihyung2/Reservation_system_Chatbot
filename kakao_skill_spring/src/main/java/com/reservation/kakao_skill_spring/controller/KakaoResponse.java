package com.reservation.kakao_skill_spring.controller;

public class KakaoResponse {
    private KakaoMessage message;

    // 생성자, 게터 및 세터 메소드
    public KakaoMessage getMessage() { return message; }
    public void setMessage(KakaoMessage message){ this.message = message; }

    public KakaoResponse(KakaoMessage message){
        super();
        this.message = message;
    }
}
