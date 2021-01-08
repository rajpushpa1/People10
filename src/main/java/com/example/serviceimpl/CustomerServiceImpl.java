package com.example.serviceimpl;

import com.example.dao.CustomerDao;
import com.example.pojo.Customer;
import com.example.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.SQLException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDao empDao;

    @Override
    public void insertEmployee(Customer emp) {

        empDao.insertEmployee(emp);

    }



    @Override
    public Customer getEmployeeById(String empid) throws SQLException {

        return empDao.getEmployeeById(empid);
    }
}
