package com.example.dynamicQuiz.service;

import com.example.dynamicQuiz.entity.DynamicQuiz;

public interface DynamicQuizService {
    String saveQuizEntry(DynamicQuiz dynamicQuiz);
    String saveActiveQuestion(String quizId,String questionId);
    DynamicQuiz getQuestion(String quizId);
}
