package com.example.laba4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String summa;

    private String currency;

    private String type;

    private String date;
    private String date1;
    private Integer procent;

    public Deposit() {
    }

    public Deposit( String summa, String currency, String type, String date, String date1, Integer procent) {
        this.summa = summa;
        this.currency = currency;
        this.type = type;
        this.date = date;
        this.date1 = date1;
        this.procent = procent;
    }
}

