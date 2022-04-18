package com.example.spring_book_project.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDao {
    private Integer id;
    private String nomi;
    private Integer narxi;
    private boolean payed;
    private Integer soni;
}
