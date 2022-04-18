package com.example.spring_book_project.Dto;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private boolean success;
    private Integer code;
    private T data;
    private String massage;

    public ResponseDto(boolean success, Integer code, T data, String massage) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.massage = massage;
    }
}
