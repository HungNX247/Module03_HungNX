package com.codegym.model.service;

import com.codegym.mapper.CustomerMapper;
import com.codegym.model.dao.CustomerDao;
import com.codegym.model.dto.CustomerDto;
import com.codegym.model.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao = null;

    public CustomerService() {
        customerDao = new CustomerDao();
    }

    public List<CustomerDto> findAll() {
        List<CustomerDto> dtoList = new ArrayList<>();
        List<Customer> entities = customerDao.getAll();

        for (Customer entity: entities) {
            CustomerDto dto = CustomerMapper.entityToDto(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public CustomerDto find(int id) {
        Customer entity = customerDao.get(id);
        return CustomerMapper.entityToDto(entity);
    }

    public List<CustomerDto> search(String searchingName) {
        List<Customer> entities = customerDao.fetch(searchingName);
        return CustomerMapper.entitiesToDtoList(entities);
    }

    public void add(CustomerDto customerDto) {
        Customer newCustomer = CustomerMapper.dtoToEntity(customerDto);
        customerDao.insert(newCustomer);
    }

    public void edit(CustomerDto customerDto) {
        Customer existingCustomer = CustomerMapper.dtoToEntity(customerDto);
        customerDao.update(existingCustomer);
    }

    public void remove(Integer id) {
        customerDao.delete(id);
    }
}
