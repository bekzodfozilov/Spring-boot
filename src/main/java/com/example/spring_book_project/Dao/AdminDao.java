package com.example.spring_book_project.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDao {
    public Integer id;
    private String mahsulot_nomi;
    private Integer mahsulot_sotilsh_narxi;
    private Integer soni;
    private Integer sotilgan_maxsulot_narxlari;
}
