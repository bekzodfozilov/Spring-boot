package com.example.spring_book_project.Repository;

import com.example.spring_book_project.Dao.OrderDao;
import com.example.spring_book_project.Dao.UserDao;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

import java.util.ArrayList;


@Repository
public class UserRepository {

    @Autowired
    private Statement statement;
    @Autowired
    private Connection connection;


    public ArrayList<UserDao> getUsersDoa() {
        String query = "select id , mahsulot_nomi , mahsulot_sotilish_narxi , soni from magzin order by id";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<UserDao> UsersDao = new ArrayList<>();
            while (resultSet.next()) {
                UserDao usersDao = new UserDao();
                usersDao.setId(resultSet.getInt("id"));
                usersDao.setMahsulot_nomi(resultSet.getString("mahsulot_nomi"));
                usersDao.setMahsulot_sotilish_narxi(resultSet.getInt("mahsulot_sotilish_narxi"));
                usersDao.setSoni(resultSet.getInt("soni"));
                UsersDao.add(usersDao);
            }
            return UsersDao;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDao sale(String tavar) {
        String query = "select  * from magzin where mahsulot_nomi = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tavar);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDao usersDao = new UserDao();
            while (resultSet.next()) {
                usersDao.setId(resultSet.getInt("id"));
                usersDao.setMahsulot_nomi(resultSet.getString("mahsulot_nomi"));
                usersDao.setMahsulot_sotilish_narxi(resultSet.getInt("mahsulot_sotilish_narxi"));
                usersDao.setSoni(resultSet.getInt("soni"));
                return usersDao;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void push(Integer narxi, String tavar, Integer soni) {
        String query = "insert into m_order(nomi, narxi , soni) " +
                "values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tavar);
            preparedStatement.setInt(2, narxi);
            preparedStatement.setInt(3, soni);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public OrderDao order(String nomi) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from M_order where nomi = ? and payed = false");
            preparedStatement.setString(1, nomi);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDao orderDao = new OrderDao();
                orderDao.setNomi(resultSet.getString(2));
                orderDao.setNarxi(resultSet.getInt(3));
                orderDao.setSoni(resultSet.getInt(5));
                return orderDao;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(String nomi, Integer soni, Integer narxi) {
        String query = "select soni , sotilgan_maxsulot_narxlari from magzin where  mahsulot_nomi = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nomi);
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDao userDao = new UserDao();
            while (resultSet.next()) {
                userDao.setSoni(resultSet.getInt("soni"));
                userDao.setMahsulot_sotilish_narxi(resultSet.getInt("sotilgan_maxsulot_narxlari"));
            }
            String update = "update magzin set soni = ? , sotilgan_maxsulot_narxlari = ? where mahsulot_nomi = ?";
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setInt(1, userDao.getSoni() - soni);
            preparedStatement1.setInt(2, userDao.getSotilgan_maxsulot_narxlari() + narxi);
            preparedStatement1.setString(3, nomi);
            preparedStatement1.executeUpdate();
            statement.executeUpdate("update m_order set payed = true ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
