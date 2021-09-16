package com.example.laba4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomer,activ,name,surname,patronymic;

    public Account() {
    }

    public Account(String nomer, String activ, String name, String surname, String patronymic) {
        this.nomer = nomer;
        this.activ = activ;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }
}
