package com.codewars.chrisgw.restapi;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
public class CodeChallengeRank {

    private int id;
    private String name;
    private String color;


    @Override
    public String toString() {
        return name;
    }

}
