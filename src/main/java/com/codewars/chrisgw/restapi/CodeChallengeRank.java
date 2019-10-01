package com.codewars.chrisgw.restapi;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;


@Data
@XmlRootElement
public class CodeChallengeRank {

    private int id;
    private String name;
    private String color;


    public boolean isNull() {
        return name == null;
    }

    @Override
    public String toString() {
        return name;
    }

}
