package com.jdbc.SpringJdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.SpringJdbc.dao.EmployeeDao;
import com.jdbc.SpringJdbc.model.Employee;
import com.jdbc.SpringJdbc.model.EmployeeDto;

@RestController
public class Controller {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@GetMapping("/getData")
	public List<Employee> getEmpData(){
		return employeeDao.getEmps();
	}
	
	@PostMapping("/insertData")
	public String giveData(@RequestBody Employee emp) {
		return employeeDao.insertEmp(emp);
	}
	
	@DeleteMapping("/deleteData/{id}")
	public String delete(@PathVariable Integer id) {
		return employeeDao.deleteEmp(id);
	}
	
	@PutMapping("/update")
	public String update(@RequestBody EmployeeDto empdto) {
		return employeeDao.updateEmp(empdto);
	}
	
	@PostMapping("/data")
	public String get(@RequestParam("name") String name) {
		return name;
	}
	
}
