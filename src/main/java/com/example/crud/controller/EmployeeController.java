package com.example.crud.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.Employee;
import com.example.crud.model.EmployeeDTO;
import com.example.crud.service.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping(value = "/employees")
    public List<EmployeeDTO> getAllEmployees(){
		
		List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return allEmployees;
    }
	
	@PostMapping(value = "/employees")
    public Employee createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		
		Employee result = employeeService.createEmployee(employeeDTO);
        return result;
    }
	
	@GetMapping(value = "/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(value = "id") Long employeeId) 
    		throws ResourceNotFoundException {
		
		EmployeeDTO result = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(result);
    }
	
	@PutMapping(value = "/employees/{id}")
    public ResponseEntity <EmployeeDTO> updateEmployee(
    		@PathVariable(value = "id") Long employeeId,
    		@Valid @RequestBody EmployeeDTO employeeDetails) throws ResourceNotFoundException {
		
        EmployeeDTO result = employeeService.updateEmployee(employeeId, employeeDetails);
        return ResponseEntity.ok(result);
    }
	
	@DeleteMapping(value = "/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        
		Map<String, Boolean> response = employeeService.deleteEmployee(employeeId);
        return response;
    }
}
