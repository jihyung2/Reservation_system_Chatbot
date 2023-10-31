package com.reservation.kakao_skill_spring.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reservation")
public class Database {

    @Column(name = "id")
    private String id;
    @Id
    @Column(name = "number", unique = true)
    private String  number;

    @Column(name = "text")
    private String text;
    public Database(){

    }

    public String getId(){return id;}
    public void setId(String id) {this.id = id;}

    public String getText(){return text;}
    public void setText(String text) {this.text = text;}

    public String getNumber(){return number;}
    public void setNumber(String number) {this.number = number;}

    public Database(String number, String text, String id ){
        super();
        this.id = id;
        this.number =  number;
        this.text = text;
    }

    @Override
    public String toString() {
        return
                "고객님 전화번호 = " + number +
                "\n고객님 예약 내용 = '" + text + '\'' ;
    }
}
