package com.sgic.employee.server.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.sgic.employee.server.entities.Employee;

public interface ExportEmployeeService {
	
	ByteArrayInputStream exportEmployee(List<Employee> employees);

}
