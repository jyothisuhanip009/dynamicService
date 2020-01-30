package com.example.dynamicQuiz.repository.impl;

import com.example.dynamicQuiz.config.MongoConfig;
import com.example.dynamicQuiz.entity.DynamicQuiz;
import com.example.dynamicQuiz.repository.DynamicInterface;
import com.example.dynamicQuiz.repository.DynamicQuizRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DynamicQuizRepoImpl implements DynamicInterface {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");


    public DynamicQuiz getQuestion(String quizId) {
        Query getActiveQuestionId=new Query();
        getActiveQuestionId.addCriteria(Criteria.where("quizId").is(quizId).and("activeStatus").is(true));
        DynamicQuiz dynamicQuizQuestion = mongoOperation.findOne(getActiveQuestionId, DynamicQuiz.class);
        return dynamicQuizQuestion;
    }


    public String saveActiveQuestion(String quizId,String questionId){
        Query queryActiveStatus=new Query();
        Query queryPreviousActiveStatus=new Query();
        Update updateCurrentActiveStatus=new Update();
        Update updatePreviousActiveStatus=new Update();

        queryPreviousActiveStatus.addCriteria(Criteria.where("activeStatus").is(true));
        updatePreviousActiveStatus.set("activeStatus",false);
        mongoOperation.updateFirst(queryPreviousActiveStatus,updatePreviousActiveStatus,DynamicQuiz.class);

        queryActiveStatus.addCriteria(Criteria.where("quizId").is(quizId).and("questionId").is(questionId));
        updateCurrentActiveStatus.set("activeStatus",true);
        mongoOperation.updateFirst(queryActiveStatus,updateCurrentActiveStatus,DynamicQuiz.class);

        return "Active Question saved";
    }

}
