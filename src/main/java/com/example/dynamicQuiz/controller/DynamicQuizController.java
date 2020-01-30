package com.example.dynamicQuiz.controller;

import com.example.dynamicQuiz.client.ScoreClient;
import com.example.dynamicQuiz.dto.DynamicQuizDTO;
import com.example.dynamicQuiz.dto.ActiveQuestionDTO;
import com.example.dynamicQuiz.dto.QuestionDto;
import com.example.dynamicQuiz.dto.SendQuestionDTO;
import com.example.dynamicQuiz.entity.DynamicQuiz;
import com.example.dynamicQuiz.response.APIResponse;
import com.example.dynamicQuiz.service.DynamicQuizService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@RequestMapping(value = "/dynamic")
public class DynamicQuizController {

    @Autowired
    DynamicQuizService dynamicQuizService;

    @Autowired
    ScoreClient scoreClient;

    @PostMapping(value = "/sendQuizQuestion")
    public ResponseEntity<APIResponse<String>> addQuestionToQuiz(@RequestBody DynamicQuizDTO dynamicQuizDTO) {
        if(dynamicQuizDTO!=null) {
            UUID uuid;
            uuid = UUID.randomUUID();
            DynamicQuiz dynamicQuiz = new DynamicQuiz();
            dynamicQuiz.setDynamicId(uuid.toString());
            BeanUtils.copyProperties(dynamicQuizDTO, dynamicQuiz);
            String message = dynamicQuizService.saveQuizEntry(dynamicQuiz);
            return new ResponseEntity<>(new APIResponse<>(1000, message), HttpStatus.OK);
        }
        else
        {
            return  new ResponseEntity<>(new APIResponse<>(400,"Couldn't get the question"),HttpStatus.OK);
        }
    }

    //todo : change the logic using findBy method .. rather than writing custom implementation
    @PostMapping(value = "/sendActiveQuestion")
    public ResponseEntity<APIResponse<String>> saveActiveQuestion(@RequestBody ActiveQuestionDTO activeQuestionDTO){
        if(activeQuestionDTO!=null){
            String message=dynamicQuizService.saveActiveQuestion(activeQuestionDTO.getQuizId(),activeQuestionDTO.getQuestionId());
            return new ResponseEntity<>(new APIResponse<>(1000,message),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new APIResponse<>(400,"Couldn't get the Active Question"),HttpStatus.OK);
    }



    // todo : change the logic to use findBy method .. we have developed for the previous use case
    @PostMapping(value = "/getQuestion")
    public ResponseEntity<APIResponse<DynamicQuiz>> getActiveQuestion(@RequestBody SendQuestionDTO sendQuestionDTO){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuizId(sendQuestionDTO.getQuizId());
        questionDto.setUserId(sendQuestionDTO.getUserId());
        String questionId =  scoreClient.sendQuestion(questionDto);
        if(sendQuestionDTO!=null){
            DynamicQuiz dynamicQuiz=dynamicQuizService.getQuestion(sendQuestionDTO.getQuizId());
            if(dynamicQuiz==null){
                return new ResponseEntity<>(new APIResponse<>(400,"There is no Active Question now"),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(new APIResponse<>(dynamicQuiz,1000,"Question sent"),HttpStatus.OK);
            }
        }
        else
            return new ResponseEntity<>(new APIResponse<>(400,"Couldn't get the request"),HttpStatus.OK);
    }
}
