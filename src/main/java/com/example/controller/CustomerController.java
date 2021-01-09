package com.example.controller;


import com.example.pojo.Customer;
import com.example.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Inject
    private CustomerService customerService;

    @CrossOrigin
    @GetMapping("/customer/{id}")
    /**
     * this method will return customer details on basis customer id...
     */
    public ResponseEntity<Customer>  getCustomer(@PathVariable String id) throws SQLException {

        logger.info("Inside getCustomer Mehthod ==>>>" + id);

        Customer result = new Customer();
        result = customerService.getEmployeeById(id);
        if (result == null) {
                return new ResponseEntity<Customer>(result, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Customer>(result, HttpStatus.OK);
            }
    }


    @CrossOrigin
    @PostMapping("/customer")
    /**
     * this method will save the customer details into db...
     */
    public  ResponseEntity<String> saveCustomer(@RequestBody @Valid Customer customer){

        logger.info("Inside saveCustomer Mehthod ==>>>" + customer.toString());
        try {
            customerService.insertEmployee(customer);
        }catch (Exception e){
            logger.error("Exception ====" + e);
            return new ResponseEntity<String>( "User already exist!",HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<String>( "User created...",HttpStatus.CREATED);
    }





}
