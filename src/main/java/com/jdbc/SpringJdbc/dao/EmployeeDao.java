package com.jdbc.SpringJdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbc.SpringJdbc.model.Employee;
import com.jdbc.SpringJdbc.model.EmployeeDto;
import com.jdbc.SpringJdbc.repository.EmployeeRepository;

@Repository
public class EmployeeDao implements EmployeeRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> getEmps() {
		String sql = "select * from employee";
		List<Employee> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
		return list;
	}
	
	public List<String> getDepts(){
		String sql = "select name from departments";
		List<String> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<String>(String.class));
		return list;
	}
	
	public Integer getDeptIdbyName(String name) {
		String sql = "select id from departments where name='"+name+"'";
		Integer id = jdbcTemplate.queryForObject(sql, Integer.class);
		return id;
	}

	@Override
	public String insertEmp(Employee emp) {
		String dept_name =emp.getDepartment().getName();
		List<String> depts = getDepts();
		if(!depts.contains(dept_name)) {
			String sql = "insert into departments values(1,?)";
			jdbcTemplate.update(sql, dept_name);
		}
		String sql = "insert into employee values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {emp.getId(),emp.getName(),emp.getEmail(),emp.getMobile(),emp.getCity(),getDeptIdbyName(dept_name)});
		return "Employee inserted";
	}

	@Override
	public String deleteEmp(int id) {
		String sql = "delete from employee where id= ?";
		jdbcTemplate.update(sql, id);
		return "Employee Deleted";
	}

	@Override
	public String updateEmp(EmployeeDto edto) {
		String sql = "update employee set name=?,city=? where id=?";
		jdbcTemplate.update(sql, new Object[] {edto.getName(),edto.getCity(),edto.getId()});
		return "Employee Updated Succesfully";
	}
		
}