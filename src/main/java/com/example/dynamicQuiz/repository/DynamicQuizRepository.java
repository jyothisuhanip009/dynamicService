package com.example.dynamicQuiz.repository;

import com.example.dynamicQuiz.entity.DynamicQuiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface DynamicQuizRepository extends MongoRepository<DynamicQuiz,String> {

    DynamicQuiz findByQuizIdAndQuestionId(String quizId, String questionId);
    DynamicQuiz findByQuizIdAndActiveStatus(String quizId, boolean activeStatus);

}
