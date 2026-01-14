package com.codegym.mapper;

import com.codegym.model.dto.CustomerDto;
import com.codegym.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerDto entityToDto(Customer entity) {
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPosition(entity.getPosition());
        dto.setOffice(entity.getOffice());
        dto.setAge(entity.getAge());
        dto.setStartDate(entity.getStartDate());
        dto.setSalary(entity.getSalary());
        return dto;
    }

    public static List<CustomerDto> entitiesToDtoList(List<Customer> entities) {
        List<CustomerDto> dtoList = new ArrayList<>();
        for (Customer entity : entities) {
            CustomerDto dto = entityToDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static Customer dtoToEntity(CustomerDto dto) {
        Customer entity = new Customer();

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }

        entity.setName(dto.getName());
        entity.setPosition(dto.getPosition());
        entity.setOffice(dto.getOffice());
        entity.setAge(dto.getAge());
        entity.setStartDate(dto.getStartDate());
        entity.setSalary(dto.getSalary());
        return entity;
    }
}
