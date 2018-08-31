package com.codewars.chrisgw.restapi;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CodewarsRank {

    private int rank;
    private String name;
    private String color;
    private int score;


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof CodewarsRank))
            return false;

        CodewarsRank that = (CodewarsRank) o;

        return new EqualsBuilder().append(getRank(), that.getRank())
                .append(getScore(), that.getScore())
                .append(getName(), that.getName())
                .append(getColor(), that.getColor())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getRank())
                .append(getName())
                .append(getColor())
                .append(getScore())
                .toHashCode();
    }

    @Override
    public String toString() {
        return name + ":" + score;
    }

}
