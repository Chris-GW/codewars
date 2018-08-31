package com.codewars.chrisgw.restapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime publishedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime approvedAt;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    private User createdBy;

    private User approvedBy;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public int getTotalCompleted() {
        return totalCompleted;
    }

    public void setTotalCompleted(int totalCompleted) {
        this.totalCompleted = totalCompleted;
    }

    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }

    public int getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(int voteScore) {
        this.voteScore = voteScore;
    }

    public CodeChallengeRank getRank() {
        return rank;
    }

    public void setRank(CodeChallengeRank rank) {
        this.rank = rank;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CodeChallenge)) {
            return false;
        }

        CodeChallenge that = (CodeChallenge) o;

        return new EqualsBuilder().append(getTotalAttempts(), that.getTotalAttempts())
                .append(getTotalCompleted(), that.getTotalCompleted())
                .append(getTotalStars(), that.getTotalStars())
                .append(getVoteScore(), that.getVoteScore())
                .append(getId(), that.getId())
                .append(getName(), that.getName())
                .append(getSlug(), that.getSlug())
                .append(getUrl(), that.getUrl())
                .append(getDescription(), that.getDescription())
                .append(getCategory(), that.getCategory())
                .append(getTags(), that.getTags())
                .append(getLanguages(), that.getLanguages())
                .append(getRank(), that.getRank())
                .append(getPublishedAt(), that.getPublishedAt())
                .append(getApprovedAt(), that.getApprovedAt())
                .append(getCreatedAt(), that.getCreatedAt())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getId())
                .append(getName())
                .append(getSlug())
                .append(getUrl())
                .append(getDescription())
                .append(getCategory())
                .append(getTags())
                .append(getLanguages())
                .append(getTotalAttempts())
                .append(getTotalCompleted())
                .append(getTotalStars())
                .append(getVoteScore())
                .append(getRank())
                .append(getPublishedAt())
                .append(getApprovedAt())
                .append(getCreatedAt())
                .toHashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s) %s", id, name);
    }

}
