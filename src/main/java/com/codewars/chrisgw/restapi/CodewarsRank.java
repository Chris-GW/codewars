package com.codewars.chrisgw.restapi;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
public class CodewarsRank {

    private int rank;
    private String name;
    private String color;
    private int score;


    @Override
    public String toString() {
        return name + ":" + score;
    }

}
