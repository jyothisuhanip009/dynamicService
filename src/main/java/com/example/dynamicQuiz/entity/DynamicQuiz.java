package com.example.dynamicQuiz.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "DYNAMICQUIZ")
public class DynamicQuiz implements Serializable {
    @Id
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
//    private static final long serialVersionUID = 1L;

//    private static final long serialVersionUID = 3902791422537649163;


    @Override
    public String toString() {
        return "DynamicQuiz{" +
                "dynamicId='" + dynamicId + '\'' +
                ", question='" + question + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answerOne='" + answerOne + '\'' +
                ", answerTwo='" + answerTwo + '\'' +
                ", answerThree='" + answerThree + '\'' +
                ", questionType='" + questionType + '\'' +
                ", answerType='" + answerType + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", quizId='" + quizId + '\'' +
                ", binaryFilePath='" + binaryFilePath + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}
