package com.codewars.chrisgw.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;


@XmlRootElement
public class UserRank {

    private int rank;
    private String name;
    private String color;
    private int score;


    @JsonProperty("overall")
    public void setOverallRank(Map<String, String> overallRank) {
        rank = Integer.parseInt(overallRank.getOrDefault("rank", "0"));
        name = overallRank.get("name");
        color = overallRank.get("color");
        score = Integer.parseInt(overallRank.getOrDefault("score", "0"));
    }


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
        if (this == o) {
            return true;
        }

        if (!(o instanceof UserRank)) {
            return false;
        }

        UserRank userRank = (UserRank) o;

        return new EqualsBuilder().append(getRank(), userRank.getRank())
                .append(getScore(), userRank.getScore())
                .append(getName(), userRank.getName())
                .append(getColor(), userRank.getColor())
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

}
