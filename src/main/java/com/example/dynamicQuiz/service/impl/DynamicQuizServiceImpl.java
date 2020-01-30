package com.example.dynamicQuiz.service.impl;


import com.example.dynamicQuiz.dto.DynamicQuizDTO;
import com.example.dynamicQuiz.entity.DynamicQuiz;
import com.example.dynamicQuiz.repository.DynamicInterface;
import com.example.dynamicQuiz.repository.DynamicQuizRepository;
import com.example.dynamicQuiz.service.DynamicQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DynamicQuizServiceImpl implements DynamicQuizService {

    @Autowired
    DynamicQuizRepository dynamicQuizRepository;

    /*@Autowired
    DynamicInterface dynamicInterface;*/


    @Override
    public String saveQuizEntry(DynamicQuiz dynamicQuiz) {
        dynamicQuizRepository.save(dynamicQuiz);
        return "Question saved Successfully";
    }

    @Override
    @CacheEvict(value = "quiz", key = "#quizId")
    public String saveActiveQuestion(String quizId, String questionId) {
        DynamicQuiz dynamicQuiz1=dynamicQuizRepository.findByQuizIdAndActiveStatus(quizId,true);
        if(dynamicQuiz1!=null) {
            dynamicQuiz1.setActiveStatus(false);
            dynamicQuizRepository.save(dynamicQuiz1);
        }
        DynamicQuiz dynamicQuiz2=dynamicQuizRepository.findByQuizIdAndQuestionId(quizId,questionId);
        dynamicQuiz2.setActiveStatus(true);
        dynamicQuizRepository.save(dynamicQuiz2);
        return "Active Question saved";
    }

    @Override
    @Cacheable(value = "quiz", key = "#quizId")
    public DynamicQuiz getQuestion(String quizId) {
        return dynamicQuizRepository.findByQuizIdAndActiveStatus(quizId,true);
    }

    /*@Override
    public String saveQuizEntries(DynamicQuiz dynamicQuiz) {
        String quizId=UUID.randomUUID().toString();
        if(dynamicQuiz!=null) {
            for (DynamicQuiz dynamicQuiz : dynamicQuizList) {
                dynamicQuiz.setQuizId(quizId);
                try {
                    boolean nullFields = checkNull(dynamicQuiz);
                    if(!nullFields) {
                        System.out.println(dynamicQuiz.toString());
                        dynamicQuizRepository.save(dynamicQuiz);
                    }
                }
                catch (Exception e){
                    return "Couldn't get the fields";
                }
            }
            return quizId;
        }
        return "Couldn't get Quiz";

    }*/
    /*@Override
   @Cacheable(value = "quiz", key = "#quizId", cacheManager = "redisCacheManager")
    @Cacheable(value = "quiz", key = "#quizId")
    public DynamicQuiz sendQuestion(String quizId) {
        Query query = new Query();
        Query queryDelete=new Query();
        query.addCriteria(Criteria.where("quizId").is(quizId));
        DynamicQuiz dynamicQuiz = mongoOperation.findOne(query, DynamicQuiz.class);
        queryDelete.addCriteria(Criteria.where("quizId").is(quizId).and("questionId").is(dynamicQuiz.getQuestionId()));
        DynamicQuiz deleteDynamicQuiz= mongoOperation.findAndRemove(queryDelete, DynamicQuiz.class);
        return dynamicQuiz;
    }*/

    /*public boolean checkNull(DynamicQuiz dynamicQuiz) throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) == null)
                return true;
        return false;
    }*/
}
