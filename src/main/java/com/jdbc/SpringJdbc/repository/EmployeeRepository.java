package com.jdbc.SpringJdbc.repository;

import java.util.List;

import com.jdbc.SpringJdbc.model.Employee;
import com.jdbc.SpringJdbc.model.EmployeeDto;

public interface EmployeeRepository {
	public List<Employee> getEmps();
	
	public String insertEmp(Employee emp);
	
	public String deleteEmp(int id);
	
	public String updateEmp(EmployeeDto edto);
}
