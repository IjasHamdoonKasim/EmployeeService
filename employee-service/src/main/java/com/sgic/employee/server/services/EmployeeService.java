package com.sgic.employee.server.services;

import java.util.List;

import com.sgic.employee.server.dto.EmployeeDto;
import com.sgic.employee.server.entities.Employee;

public interface EmployeeService {

	List<EmployeeDto> findAllEmployeeDetails();

	Object updateEmployeeDto(Long employeeId, EmployeeDto e_EmployeeDto);

	List<EmployeeDto> selectAllUser();

	Employee getEmployeeDto(Long id);

	void saveEmployee(Employee employee);
	
	void deleteEmployee(Long id);

	EmployeeDto getEmployeeNameById(Long id);
	
	//public Employee getEmployee(Long id);


//	public Employee createEmployee(Employee employee);
//
//	public boolean isEmailAlreadyExist(String emailId);

}
