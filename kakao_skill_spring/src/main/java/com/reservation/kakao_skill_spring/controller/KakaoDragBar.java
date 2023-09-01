package com.reservation.kakao_skill_spring.controller;

public class KakaoDragBar {
    private String label;
    private int minValue;
    private int maxValue;
    private int step;

    public String getLabel(){return label;}

    public void setLabel(String label) {
        this.label = label;
    }
    public int getMinValue(){return  minValue;}
    public void setMinValue(int minValue){ this.minValue = minValue;}

    public int getMaxValue(){return maxValue;}
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getStep(){return step;}
    public void setStep(int step){this.step=step;}

    public KakaoDragBar(String label, int minValue, int maxValue, int step) {
        super();
        this.label = label;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.step = step;
    }

    // 생성자, 게터 및 세터 메소드
}