package com.example.dynamicQuiz.repository;

import com.example.dynamicQuiz.entity.DynamicQuiz;

public interface DynamicInterface {
    public String saveActiveQuestion(String quizId,String questionId);
    public DynamicQuiz getQuestion(String quizId);
}
