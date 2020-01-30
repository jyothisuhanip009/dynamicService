package com.example.dynamicQuiz.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class DynamicQuizDTO implements Serializable {
    String dynamicId;
    String question;
    String questionId;
    String answerOne;
    String answerTwo;
    String answerThree;
    String questionType;
    String answerType;
    String difficulty;
    String quizId;
    String binaryFilePath;
    boolean activeStatus;
}
