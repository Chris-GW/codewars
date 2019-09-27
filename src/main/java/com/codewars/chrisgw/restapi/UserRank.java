package com.codewars.chrisgw.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;


@Data
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

}
