package com.sgic.employee.server.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.sgic.employee.server.dto.EmployeeDto;
import com.sgic.employee.server.entities.Employee;
import com.sgic.employee.server.repositories.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

//  @Transactional(readOnly = false) public Employee createEmployee(Employee employee)
//  {
//  Employee responseEmployee = employeeRepository.save(employee);
//  return responseEmployee;
//  }
//  
//  @Transactional(readOnly = true) public boolean isEmailAlreadyExist(String emailId)
//  {
//  return employeeRepository.existsByEmailId(emailId);
//  
//  }
	
	@Override
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	public EmployeeDto convertToEmployeeDto(Employee employee) {

		EmployeeDto employeeDto = new EmployeeDto();

		employeeDto.setEmployeeId(employee.getEmployeeId());
		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		employeeDto.setGender(employee.getGender());
		employeeDto.setEmailId(employee.getEmailId());
		employeeDto.setPhoneNo(employee.getPhoneNo());
		return employeeDto;

	}
	
	@Override
	public List<EmployeeDto> findAllEmployeeDetails() {

		return employeeRepository.findAll().stream().map(this::convertToEmployeeDto).collect(Collectors.toList());

	}
	
	@Override
	public Object updateEmployeeDto(Long employeeId, EmployeeDto e_EmployeeDto) {

		Optional<Employee> emp = employeeRepository.findById(employeeId);
		Employee employeeDto = emp.get();

		employeeDto.setFirstName(e_EmployeeDto.getFirstName());
		employeeDto.setLastName(e_EmployeeDto.getLastName());
		employeeDto.setGender(e_EmployeeDto.getGender());
		employeeDto.setEmailId(e_EmployeeDto.getEmailId());
		employeeDto.setPhoneNo(e_EmployeeDto.getPhoneNo());

		employeeRepository.save(employeeDto);
		return employeeDto;
	}

	@Override
	public Employee getEmployeeDto(@PathVariable Long id) {
		Optional<Employee> employeeDtoResponse =  employeeRepository.findById(id);
		Employee employeeDto = employeeDtoResponse.get();
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> selectAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Employee> listAll() {
        return employeeRepository.findAll(Sort.by("employeeId").ascending());
    }
	
	@Override
	public void deleteEmployee(Long id){
		Employee employee = employeeRepository.findById(id).orElseThrow();
		employeeRepository.delete(employee);

	}
	
	@Override
	public EmployeeDto getEmployeeNameById(Long id){
		
		Employee employee = employeeRepository.findById(id).orElseThrow();
		
		EmployeeDto employeeDto = new EmployeeDto();

		employeeDto.setFirstName(employee.getFirstName());
		employeeDto.setLastName(employee.getLastName());
		
		return employeeDto;
		
	}

	

	
////////////////////////////////////////////////////////////////////////////////////////////
	
	
}
