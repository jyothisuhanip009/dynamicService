package com.example.dynamicQuiz.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;
    public APIResponse(int status,String message){
        this.status=status;
        this.message=message;
    }
    public APIResponse(T data,int status,String message){
        this.data=data;
        this.status=status;
        this.message=message;
    }
}
