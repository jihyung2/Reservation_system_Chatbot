package com.reservation.kakao_skill_spring.controller;

public class KakaoMessage {
    private String text;
    private KakaoButton[] buttons;
    private KakaoDragBar dragBar;

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}

    public KakaoButton[] getButtons() {return buttons;}
    public void setButtons(KakaoButton[] buttons) {this.buttons = buttons;}

    public KakaoDragBar getDragBar() {return dragBar;}
    public void setDragBar(KakaoDragBar dragBar) {this.dragBar = dragBar;}

    // 생성자, 게터 및 세터 메소드
    public KakaoMessage(String text, KakaoButton[] buttons, KakaoDragBar drageBar){
        super();
        this.text = text;
        this.buttons = buttons;
        this.dragBar = drageBar;
    }
}