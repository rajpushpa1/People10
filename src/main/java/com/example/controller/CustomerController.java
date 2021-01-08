package com.example.controller;


import com.example.pojo.Customer;
import com.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Inject
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer>  getEmployee(@PathVariable String id) throws SQLException {
        Customer result = new Customer();

        result = customerService.getEmployeeById(id);
        if (result == null) {
                return new ResponseEntity<Customer>(result, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Customer>(result, HttpStatus.OK);
            }
    }


    @PostMapping("/customer")
    public  ResponseEntity<String> saveCustomer(@RequestBody @Valid Customer product) {
        try {
            customerService.insertEmployee(product);
        }catch (Exception e){
            return new ResponseEntity<String>( "User already exist!",HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<String>( "User created...",HttpStatus.CREATED);
    }





}
