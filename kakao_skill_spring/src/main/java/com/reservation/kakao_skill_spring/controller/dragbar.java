package com.reservation.kakao_skill_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dragbar {

    @PostMapping("/webhook")
    public KakaoResponse webhook(@RequestBody String kakaoRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String userjson = objectMapper.writeValueAsString(kakaoRequest);
        System.out.println(userjson);

        KakaoMessage message = null;

        JsonNode rootNode = objectMapper.readTree(kakaoRequest); // JSON 문자열을 JsonNode로 파싱

        // 원하는 필드에 접근
        JsonNode actionNode = rootNode.path("action");
        JsonNode idNode = actionNode.path("id");
        JsonNode contentNode = actionNode.path("params").path("content");

        // 필드 값을 문자열로 가져오기
        String username = actionNode.path("name").asText();
        String usertext = contentNode.asText();
        String userid = idNode.asText();

        System.out.println("username: " + username);
        System.out.println("userid: " + userid);
        System.out.println("usertext: " + usertext);


        if ("버튼을 누르면 드래그바를 표시합니다".equals(usertext)) {
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