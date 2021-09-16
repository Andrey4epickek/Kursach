package com.example.laba4.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name,surname,patronymic,pol,seriapasporta,nomerpasporta,
            kemvidan,identnome,placeofbirth,gorodprop,adres,gorodprog,sempologenie,gragdanstvo,invalid,pensioner,
            voen,dohod;
    private String birth,datavidachi;
    public Info() {
    }

    public Info(String name, String surname, String patronymic, String pol, String seriapasporta, String nomerpasporta, String kemvidan, String identnome, String placeofbirth, String gorodprop, String adres, String gorodprog, String sempologenie, String gragdanstvo, String invalid, String pensioner, String voen, String dohod, String birth, String datavidachi) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.pol = pol;
        this.seriapasporta = seriapasporta;
        this.nomerpasporta = nomerpasporta;
        this.kemvidan = kemvidan;
        this.identnome = identnome;
        this.placeofbirth = placeofbirth;
        this.gorodprop = gorodprop;
        this.adres = adres;
        this.gorodprog = gorodprog;
        this.sempologenie = sempologenie;
        this.gragdanstvo = gragdanstvo;
        this.invalid = invalid;
        this.pensioner = pensioner;
        this.voen = voen;
        this.dohod = dohod;
        this.birth = birth;
        this.datavidachi = datavidachi;
    }
}

