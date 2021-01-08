package com.example.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    @Size(min = 5, max = 10)
    private String password;
    private String email;
    private Date dob;



}
