package com.example.spring_book_project.Service;

import com.example.spring_book_project.Dao.OrderDao;
import com.example.spring_book_project.Dao.UserDao;
import com.example.spring_book_project.Dto.OrderDto;
import com.example.spring_book_project.Dto.ResponseDto;
import com.example.spring_book_project.Dto.UserDto;
import com.example.spring_book_project.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public ResponseDto<ArrayList<UserDto>> getUsers() {
        ArrayList<UserDao> UsersDao = repository.getUsersDoa();
        ArrayList<UserDto> UsersDto = new ArrayList<>();
        for (UserDao usersDao : UsersDao) {
            UserDto usersDto = new UserDto();
            usersDto.setId(usersDao.getId());
            usersDto.setMahsulot_nomi(usersDao.getMahsulot_nomi());
            usersDto.setMahsulot_sotilish_narxi(usersDao.getMahsulot_sotilish_narxi());
            usersDto.setSoni(usersDao.getSoni());
            UsersDto.add(usersDto);
        }
        return new ResponseDto<>(true, 0, UsersDto, "ok");
    }

    public ResponseDto<Object> sale(String tavar, Integer soni) {
        UserDao userDao = repository.sale(tavar);
        if (userDao != null) {
            UserDto userDto = new UserDto();
            userDto.setId(userDao.getId());
            userDto.setMahsulot_nomi(userDao.getMahsulot_nomi());
            userDto.setMahsulot_sotilish_narxi(userDao.getMahsulot_sotilish_narxi());
            userDto.setSoni(userDao.getSoni());
            if (userDto.getSoni() > Math.abs(soni)) {
                repository.push(userDto.getMahsulot_sotilish_narxi() * soni, tavar, soni);
                return new ResponseDto<>(true, 0, Math.abs(soni) * userDto.getMahsulot_sotilish_narxi(), "ok");
            } else
                return new ResponseDto<>(false, -1, String.format("Bizda %d ta %s yoq", Math.abs(soni), tavar), "Xatolik");

        } else
            return new ResponseDto<>(false, -1, "Bunday tavar mavjud emas", "Xatolik");
    }

    public ResponseDto<Object> order(String nomi, Integer puli) {
        OrderDao orderDao = repository.order(nomi);
        if (orderDao != null) {
            OrderDto orderDto = new OrderDto();
            orderDto.setNarxi(orderDao.getNarxi());
            orderDto.setNomi(orderDao.getNomi());
            orderDto.setSoni(orderDao.getSoni());
            if (Objects.equals(orderDto.getNarxi(), Math.abs(puli))) {
                repository.update(nomi, orderDto.getSoni(), orderDto.getNarxi());
                return new ResponseDto<>(true, 0, String.format("Siz %d ta %s Sotib oldizgiz", orderDto.getSoni(), orderDto.getNomi()), "ok");
            }
            if (orderDto.getNarxi() > Math.abs(puli))
                return new ResponseDto<>(false, -1, String.format("Sizga " + String.valueOf(orderDto.getNarxi() - Math.abs(puli)) + " ming yetmadi"), "Xatolik");
            if (orderDto.getNarxi() < Math.abs(puli)) {
                repository.update(nomi, orderDto.getSoni(),  orderDto.getNarxi());
                return new ResponseDto<>(true, 0, String.format("Sizning qaytimingiz %d", puli - orderDto.getNarxi()), "ok");
            }

        }
        return new ResponseDto<>(false, -1, "Bunday maxsulotga zakaz tushmagan", "Xatolik");
    }
}


