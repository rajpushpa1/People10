package com.example.dao;

import com.example.pojo.Customer;

import java.sql.SQLException;

public interface CustomerDao {



    void insertEmployee(Customer emp);
    Customer getEmployeeById(String empid) throws SQLException;
}
