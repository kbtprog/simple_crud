package com.example.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Employee;
import com.example.crud.model.EmployeeDTO;
import com.example.crud.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<EmployeeDTO> getAllEmployees(){
		List<EmployeeDTO> result = new ArrayList<EmployeeDTO>(); 
		
		// Get all employees and set them in the DTO to return for the front-end
		List<Employee> employees = employeeRepository.findAll();
		employees.forEach(e -> result.add(new EmployeeDTO(e.getFirstName(), e.getLastName(), e.getEmailId()))); 
		 
		return result;
	}
	
	public Employee createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		
		employee.setEmailId(employeeDTO.getEmailId());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		
		return employeeRepository.save(employee);
	}
	
	public EmployeeDTO getEmployeeById(Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
        		.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		
		EmployeeDTO result;

		if (Objects.isNull(employee)) {
			result = new EmployeeDTO();
		
		} else {
			result = new EmployeeDTO(employee.getFirstName(), employee.getLastName(), employee.getEmailId());
		}

		return result;
	}
	
	public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        
        final Employee updatedEmployee = employeeRepository.save(employee);
		
		EmployeeDTO result = new EmployeeDTO(
				updatedEmployee.getFirstName(), updatedEmployee.getLastName(), updatedEmployee.getEmailId());
		
		return result;
	}
	
	public Map<String,Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException {
		
		Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        
		return response;
	}
}
