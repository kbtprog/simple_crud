package com.example.crud.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private String firstName;
    private String lastName;
    private String emailId;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateRegister;
    
    private BigDecimal salary;
}
