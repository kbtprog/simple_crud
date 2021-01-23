package com.example.crud.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "first_name", nullable = false)
    private String firstName;
    
	@Column(name = "last_name", nullable = false)
    private String lastName;
    
	@Column(name = "email_address", nullable = false)
    private String emailId;
	
	@Column(name = "date_register", columnDefinition = "DATE")
	private LocalDate dateRegister;
	
	@Column(name = "salary")
	private BigDecimal salary;
}
