package com.reservation.kakao_skill_spring.controller;

public class KakaoRequest {
    private String userKey;
    private String type;
    private String content;
    private String name;
    private String id;

    public String getUserKey() {return userKey;}
    public void setUserKey(String userKey) {this.userKey = userKey;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public KakaoRequest(String userKey, String type, String content, String id, String name){
        super();
        this.userKey = userKey;
        this.type = type;
        this.content = content;
        this.id = id;
        this.name = name;
    }
}
