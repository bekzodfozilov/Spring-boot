package com.example.spring_book_project.Repository;

import com.example.spring_book_project.Dao.AdminDao;
import com.example.spring_book_project.Dto.AdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class AdminRepository {
    @Autowired
    private Statement statement;
    @Autowired
    private Connection connection;

    public ArrayList<AdminDao> get() {
        String query = "select * from magzin";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<AdminDao> adminsDaos = new ArrayList<>();
            while (resultSet.next()) {
                AdminDao adminDao = new AdminDao();
                adminDao.setId(resultSet.getInt(1));
                adminDao.setMahsulot_nomi(resultSet.getString(2));
                adminDao.setMahsulot_sotilsh_narxi(resultSet.getInt(3));
                adminDao.setSoni(resultSet.getInt(4));
                adminDao.setSotilgan_maxsulot_narxlari(resultSet.getInt(5));
                adminsDaos.add(adminDao);
            }
            return adminsDaos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(ArrayList<AdminDto> adminsDto) {
        for (AdminDto adminDto : adminsDto) {
            AdminDao adminDao = new AdminDao();
            adminDao.setMahsulot_nomi(adminDto.getMahsulot_nomi());
            adminDao.setSoni(adminDto.getSoni());
            adminDao.setMahsulot_sotilsh_narxi(adminDto.getMahsulot_sotilsh_narxi());
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into magzin(mahsulot_nomi, soni) values(?,?)");
                preparedStatement.setString(1,adminDao.getMahsulot_nomi());
                preparedStatement.setInt(2,adminDao.getSoni());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("update magzin set mahsulot_sotilish_narxi = ? where mahsulot_nomi = ?");
                preparedStatement.setInt(1,adminDao.getMahsulot_sotilsh_narxi());
                preparedStatement.setString(2,adminDao.getMahsulot_nomi());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
