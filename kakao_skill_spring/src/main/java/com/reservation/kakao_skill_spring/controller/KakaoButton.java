package com.reservation.kakao_skill_spring.controller;

public class KakaoButton {
    private String type;
    private String label;
    private String action;

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public String getLabel(){return label;}
    public void setLabel(String label){this.label = label;}

    public String getAction() {return action;}
    public void setAction(String action){this.action = action;}

    public KakaoButton(String type, String label, String action) {
        super();
        this.type = type;
        this.label = label;
        this.action = action;
    }

    // 생성자, 게터 및 세터 메소드
}
