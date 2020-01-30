package com.example.dynamicQuiz.client;

import com.example.dynamicQuiz.dto.QuestionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="scoreservice")
public interface ScoreClient {
    @PostMapping("/getQuestionId")
    String sendQuestion(@RequestBody QuestionDto questionDto);

}
