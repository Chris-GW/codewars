package com.codewars.chrisgw.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Data
@XmlRootElement
public class User {

    private String username;
    private String name;
    private int honor;
    private String clan;
    private int leaderboardPosition;

    private Set<String> skills = new HashSet<>();
    private UserRank ranks;

    private int totalAuthored;
    private int totalCompleted;


    @JsonProperty("codeChallenges")
    public void setCodeChannelngeStatistic(Map<String, String> codeChallengeStatistic) {
        this.totalAuthored = Integer.parseInt(codeChallengeStatistic.getOrDefault("totalAuthored", "0"));
        this.totalCompleted = Integer.parseInt(codeChallengeStatistic.getOrDefault("totalCompleted", "0"));
    }


    @Override
    public String toString() {
        return username;
    }

}
