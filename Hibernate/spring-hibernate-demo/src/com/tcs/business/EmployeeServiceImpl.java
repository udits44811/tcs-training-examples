package com.tcs.business;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcs.beans.Employee;
import com.tcs.dao.EmployeeDao;
import com.tcs.exception.EmployeeNotFoundException;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	@Transactional
	public Employee store(Employee employee) {
		int result = employeeDao.save(employee);
		Employee emp = employeeDao.fetchEmployee(result);
		return emp;
	}

	@Override
	public Employee findEmployeeById(int id) throws EmployeeNotFoundException {
		Employee emp = employeeDao.fetchEmployee(id);
		if(emp == null) {
			throw new EmployeeNotFoundException("Sorry "+id+" not found!");
		}
		// returns only if employee found
		return emp;
	}
	// Assignment
	@Override
	public void updateEmployeeSalary(int id, double salary) throws EmployeeNotFoundException {
		Employee emp = employeeDao.fetchEmployee(id);
		if(emp == null) {
			throw new EmployeeNotFoundException("Sorry "+id+" not found!");
		}
		emp.setSalary(salary);
		employeeDao.updateEmployee(emp);
	}
	
	// Assignment
	@Override
	public void updateEmployeeName(int id, String name) throws EmployeeNotFoundException {
		Employee emp = employeeDao.fetchEmployee(id);
		if(emp == null) {
			throw new EmployeeNotFoundException("Sorry "+id+" not found!");
		}
		emp.setName(name);
		employeeDao.updateEmployee(emp);
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeDao.fetchEmployees();
	}

	// Assignment
	@Override
	public List<Employee> getEmployeesOrderByName() {
		// using Comparator to sort the list
		List<Employee> employees = getEmployees();
		Collections.sort(employees, (e1, e2) -> e1.getName().compareTo(e2.getName()));
		return employees;
	}

}
