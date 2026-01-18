package com.clinicbooking.service;

import com.clinicbooking.dao.DoctorDao;
import com.clinicbooking.dto.DoctorDto;

import java.util.List;

public class DoctorService {
    private final DoctorDao doctorDao = new DoctorDao();

    public List<DoctorDto> findAll() {
        return doctorDao.findAllDto();
    }


}
