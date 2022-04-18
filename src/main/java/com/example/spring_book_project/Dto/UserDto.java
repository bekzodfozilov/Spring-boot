package com.example.spring_book_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String mahsulot_nomi;
    private Integer mahsulot_sotilish_narxi;
    private Integer soni;
}
