package com.codewars.chrisgw.mcduell;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class QuizQuestion {

    private String frageName;
    private String text;
    private String type;
    private List<QuizAnswer> answers = new ArrayList<>();



    public boolean addAnswer(QuizAnswer quizAnswer) {
        return answers.add(quizAnswer);
    }

}
