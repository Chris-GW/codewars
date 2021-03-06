package com.codewars.chrisgw.restapi;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@XmlRootElement
public class CodeChallenge {

    private String id;
    private String name;
    private String slug;
    private String url;
    private String description;

    private String category;
    private Set<String> tags = new HashSet<>();
    private Set<String> languages = new HashSet<>();

    private int totalAttempts;
    private int totalCompleted;
    private int totalStars;
    private int voteScore;
    private CodeChallengeRank rank;

    private LocalDateTime publishedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime createdAt;

    private User createdBy;
    private User approvedBy;


    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);
    }

}
