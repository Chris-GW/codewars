package com.codewars.chrisgw.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public int getLeaderboardPosition() {
        return leaderboardPosition;
    }

    public void setLeaderboardPosition(int leaderboardPosition) {
        this.leaderboardPosition = leaderboardPosition;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }

    public UserRank getRanks() {
        return ranks;
    }

    public void setRanks(UserRank ranks) {
        this.ranks = ranks;
    }

    public int getTotalAuthored() {
        return totalAuthored;
    }

    public void setTotalAuthored(int totalAuthored) {
        this.totalAuthored = totalAuthored;
    }

    public int getTotalCompleted() {
        return totalCompleted;
    }

    public void setTotalCompleted(int totalCompleted) {
        this.totalCompleted = totalCompleted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        return new EqualsBuilder().append(getHonor(), user.getHonor())
                .append(getLeaderboardPosition(), user.getLeaderboardPosition())
                .append(getTotalAuthored(), user.getTotalAuthored())
                .append(getTotalCompleted(), user.getTotalCompleted())
                .append(getUsername(), user.getUsername())
                .append(getName(), user.getName())
                .append(getClan(), user.getClan())
                .append(getSkills(), user.getSkills())
                .append(getRanks(), user.getRanks())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getUsername())
                .append(getName())
                .append(getHonor())
                .append(getClan())
                .append(getLeaderboardPosition())
                .append(getSkills())
                .append(getRanks())
                .append(getTotalAuthored())
                .append(getTotalCompleted())
                .toHashCode();
    }

    @Override
    public String toString() {
        return username;
    }

}
