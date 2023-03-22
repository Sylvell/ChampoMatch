package com.example.champomatch;

public class Recherche_pertinence {


    public static void main(String[] args) {
        System.out.println(JdbcDao.select("SELECT * FROM registration"));
    }



}
