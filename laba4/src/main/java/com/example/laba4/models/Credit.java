package com.example.laba4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String summa;

    private String currency;
    private String type;

    private String date;
    private String date1;
    private Integer procent;


    public Credit() {
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public Integer getProcent() {
        return procent;
    }

    public void setProcent(Integer procent) {
        this.procent = procent;
    }

    public Credit(String summa, String currency, String type, String date, String date1, Integer procent) {
        this.summa = summa;
        this.currency = currency;
        this.type = type;
        this.date = date;
        this.date1 = date1;
        this.procent = procent;
    }

}
