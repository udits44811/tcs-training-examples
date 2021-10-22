package com.tcs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

// DAO layer class having CRUD operations
public class EmployeeDaoImpl {
	// stores the employee object and returns the generated id
	public int store(Employee employee) {
		int key = 0;
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		key = (int)session.save(employee); // saves & returns the primary key
		transaction.commit();
		session.close();
		factory.close();
		return key;
	}
	// fetch a particular employee based on the id
	public Employee fetchEmployee(int empId) {
		Employee employee = null;
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = factory.openSession();
		/*get(class, primaryKey) class is the entity class name & primary key is the value for the primary key column
		internally it generate select * from employee where emp_id = empId, returns employee object if id is present
		else returns null
		 */
		employee = session.get(Employee.class, empId);  // get() method initializes other properties of Employee automatically
		session.close();
		factory.close();
		return employee;
	}
	// fetching all the employees and returning the employees in a List
	public List<Employee> fetchEmployees() {
		List<Employee> employees = null;
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = factory.openSession();
		// createQuery() is used to pass the HQL and get the collection of Employee
		Query<Employee> query = session.createQuery("select e from Employee e", Employee.class);
		employees = query.getResultList();
		session.close();
		factory.close();
		return employees;
	}
	// updating employee salary by employee id
	public void updateEmployee(int EmployeeID, double salary)
	{
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = factory.openSession();
		Employee emp = (Employee)session.get(Employee.class, EmployeeID);
		if(emp != null)
		{
		Transaction transaction = session.beginTransaction();
		emp.setSalary(salary);
		session.update(emp);
		transaction.commit();
		}
		else
			System.out.println("Employee Not Found with given ID");
		session.close();
		factory.close();
	}
	//deleting employee by id
	public void deleteEmployee(int EmployeeID)
	{
		SessionFactory factory = HibernateUtil.createSessionFactory();
		Session session = factory.openSession();
		Employee emp = (Employee)session.get(Employee.class, EmployeeID);
		if(emp != null)
		{
		Transaction transaction = session.beginTransaction();
		session.delete(emp);
		transaction.commit();
		}
		else
			System.out.println("Employee Not Found with given ID");
		session.close();
		factory.close();
	}
}
