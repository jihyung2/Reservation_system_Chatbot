package com.reservation.kakao_skill_spring.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class button {

    @RequestMapping(value = "/keyboard", method = RequestMethod.POST)
    public String keyboard() {
        System.out.println("/keyboard");

        JSONObject Btn1 = new JSONObject();
        ArrayList<String> btns = new ArrayList<>();
        btns.add("신청");
        btns.add("나의 신청 내역 (고객센터)");

        Btn1.put("type", "buttons");
        Btn1.put("buttons", btns);

        return Btn1.toString();
    }
}