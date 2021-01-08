package com.example.service;

import com.example.pojo.Customer;

import java.sql.SQLException;

public interface CustomerService {

    void insertEmployee(Customer emp);

    Customer getEmployeeById(String empid) throws SQLException;
}
