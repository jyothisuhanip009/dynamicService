package com.example.dynamicQuiz.redisRepo;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Quiz")
public class Quiz implements Serializable {
    @Id
    private String userId;
    private String quizId;
    private String questionId;
}
