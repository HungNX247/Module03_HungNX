//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

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
        this.customerDao = new CustomerDao();
    }

    public List<CustomerDto> findAll() {
        List<CustomerDto> dtoList = new ArrayList();

        for(Customer entity : this.customerDao.getAll()) {
            CustomerDto dto = CustomerMapper.entityToDto(entity);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public CustomerDto find(int id) {
        Customer entity = this.customerDao.get(id);
        return CustomerMapper.entityToDto(entity);
    }

    public List<CustomerDto> search(String searchingName) {
        List<Customer> entities = this.customerDao.fetch(searchingName);
        return CustomerMapper.entitiesToDtoList(entities);
    }

    public void add(CustomerDto customerDto) {
        Customer newCustomer = CustomerMapper.dtoToEntity(customerDto);
        this.customerDao.insert(newCustomer);
    }

    public void edit(CustomerDto customerDto) {
        Customer existingCustomer = CustomerMapper.dtoToEntity(customerDto);
        this.customerDao.update(existingCustomer);
    }

    public void remove(Integer id) {
        this.customerDao.delete(id);
    }
}
