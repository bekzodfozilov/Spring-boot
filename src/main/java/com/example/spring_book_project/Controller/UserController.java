package com.example.spring_book_project.Controller;

import com.example.spring_book_project.Dto.ResponseDto;
import com.example.spring_book_project.Dto.UserDto;
import com.example.spring_book_project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController("/User")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("info")
    public ResponseDto<ArrayList<UserDto>> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("sale")
    public ResponseDto<Object> sale(@RequestParam String nomi, @RequestParam Integer soni) {
        return userService.sale(nomi, soni);
    }

    @PostMapping("order")
    public ResponseDto<Object> order(@RequestParam String nomi, @RequestParam Integer puli) {
        return userService.order(nomi, puli);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String error(IllegalArgumentException e) {
        return e.getMessage();
    }
}
