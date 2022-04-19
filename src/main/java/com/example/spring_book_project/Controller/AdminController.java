package com.example.spring_book_project.Controller;

import com.example.spring_book_project.Dto.AdminDto;
import com.example.spring_book_project.Dto.ResponseDto;
import com.example.spring_book_project.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin bazadagi bor tavarlarni korish uchun

    @GetMapping("get")
    private ResponseDto<ArrayList<AdminDto>> get() {
        return adminService.get();
    }

    // Yangi tavarlarni bazaga qoshish uchn

    @PostMapping("insert")
    private ResponseDto<Object> insert(@RequestBody ArrayList<AdminDto> adminsDto) {
        return adminService.insert(adminsDto);
    }
}
