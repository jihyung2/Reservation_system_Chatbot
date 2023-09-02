package com.reservation.kakao_skill_spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
#
@RestController
public class button {

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestBody String userId) {
        String sendMessageUrl = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

        String messageJson = generateMessageJson(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + kakaoApiKey);

        HttpEntity<String> request = new HttpEntity<>(messageJson, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(sendMessageUrl, request, String.class);
        return response;
    }

    private String generateMessageJson(String userId) {
        // 메시지 JSON 생성 및 버튼 정보 설정
        String messageJson = "{"
                + "\"object_type\": \"text\","
                + "\"text\": \"버튼을 눌러주세요!\","
                + "\"link\": {"
                + "\"web_url\": \"https://www.example.com\""
                + "},"
                + "\"buttons\": ["
                + "{"
                + "\"label\": \"버튼 1\","
                + "\"action\": \"webLink\","
                + "\"webLinkUrl\": \"https://www.example.com/button1\""
                + "},"
                + "{"
                + "\"label\": \"버튼 2\","
                + "\"action\": \"webLink\","
                + "\"webLinkUrl\": \"https://www.example.com/button2\""
                + "}"
                + "]"
                + "}";

        return messageJson;
    }
}