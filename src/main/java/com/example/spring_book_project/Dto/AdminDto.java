package com.example.spring_book_project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    public Integer id;
    private String mahsulot_nomi;
    private Integer mahsulot_sotilsh_narxi;
    private Integer soni;
    private Integer sotilgan_maxsulot_narxlari;
}
