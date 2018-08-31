package com.codewars.chrisgw.restapi;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CodeChallengeRank {

    private int id;
    private String name;
    private String color;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeChallengeRank)) {
            return false;
        }

        CodeChallengeRank that = (CodeChallengeRank) o;

        return new EqualsBuilder().append(getId(), that.getId())
                .append(getName(), that.getName())
                .append(getColor(), that.getColor())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId()).append(getName()).append(getColor()).toHashCode();
    }

    @Override
    public String toString() {
        return name;
    }

}
