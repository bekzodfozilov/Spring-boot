package com.example.spring_book_project.Service;

import com.example.spring_book_project.Dao.AdminDao;
import com.example.spring_book_project.Dto.AdminDto;
import com.example.spring_book_project.Dto.ResponseDto;
import com.example.spring_book_project.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public ResponseDto<ArrayList<AdminDto>> get() {
        ArrayList<AdminDao> adminsDao = adminRepository.get();
        ArrayList<AdminDto> adminsDto = new ArrayList<>();
        for (AdminDao adminDao : adminsDao) {
            AdminDto adminDto = new AdminDto();
            adminDto.setId(adminDao.getId());
            adminDto.setMahsulot_nomi(adminDao.getMahsulot_nomi());
            adminDto.setMahsulot_sotilish_narxi(adminDao.getMahsulot_sotilish_narxi());
            adminDto.setSoni(adminDao.getSoni());
            adminDto.setSotilgan_maxsulot_narxlari(adminDao.getSotilgan_maxsulot_narxlari());
            adminsDto.add(adminDto);
        }
        return new ResponseDto<>(true, 0, adminsDto, "ok");
    }

    public ResponseDto<Object> insert(ArrayList<AdminDto> adminsDto) {
       adminRepository.insert(adminsDto);
       return new ResponseDto<>(true,0,"All inset","ok");
    }
}
