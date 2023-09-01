package com.reservation.kakao_skill_spring.controller;

public class KakaoRequest {
    private String userKey;
    private String type;
    private String content;

    public String getUserKey() {return userKey;}
    public void setUserKey(String userKey) {this.userKey = userKey;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public KakaoRequest(String userKey, String type, String content){
        super();
        this.userKey = userKey;
        this.type = type;
        this.content = content;
    }
}
