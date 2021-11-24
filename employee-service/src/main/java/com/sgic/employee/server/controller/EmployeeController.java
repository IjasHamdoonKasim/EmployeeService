package com.sgic.employee.server.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgic.employee.server.dto.EmployeeDto;
import com.sgic.employee.server.entities.Employee;
import com.sgic.employee.server.repositories.EmployeeRepository;
import com.sgic.employee.server.services.EmployeeService;
import com.sgic.employee.server.services.ExportEmployeeService;
import com.sgic.employee.server.util.ErrorCodes;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	ErrorCodes errorMessages;
	@Autowired
	private ExportEmployeeService exportEmployeeService;
	@Autowired
	private EmployeeRepository employeeRepository;
	
//	@GetMapping("/test")
//	public ResponseEntity<String> test() {
//		String name = " danuhan";
//		return ResponseEntity.ok(name);
//	}

	@PostMapping("/createEmployee")
	public ResponseEntity<Object> createIncomingSample(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee);
		return ResponseEntity.ok("Saved Successfully!");
	}

	@GetMapping(path = "/employeeDetails")
	public List<EmployeeDto> findAllEmployeeDetails() {
		return employeeService.findAllEmployeeDetails();
	}
	
	@RequestMapping(value = "employeeById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getEmployeeDto(@PathVariable Long id) {
		Employee employeeDtoResponse = (Employee) employeeService.getEmployeeDto(id);
		return employeeDtoResponse;
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Object> updateDetails(@PathVariable(value = "id") Long employeeId,
			@RequestBody EmployeeDto employeeDto) {

		return ResponseEntity.ok().body(employeeService.updateEmployeeDto(employeeId, employeeDto));
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
		employeeService.deleteEmployee(id);
		return ResponseEntity.ok( "Deleted Successfully!" );
	}
	
	@GetMapping("/employeeName/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeNameById(@PathVariable Long id){
		EmployeeDto employeeDto = employeeService.getEmployeeNameById(id);
		return ResponseEntity.ok(employeeDto);	
	}
	
	@GetMapping("/exportEmployee")
	public void exportEmployee(HttpServletResponse response) throws IOException {
		List<Employee> employees = (List<Employee>)employeeRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = exportEmployeeService.exportEmployee(employees);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
	}
	
}
