package fa.training.problem02.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import fa.training.problem02.model.Employee;

public interface EmployeeDao {	
	
	/**
	 * function to save an employee information to database
	 * 
	 *  @method save
	 *  @param employee
	 *  @return true if inserts success to database, else false
	 *  @throws SQLException
	 */
	public boolean save(Employee employee) throws SQLException;
	
	
	/**
	 * function to get all employee information from database
	 * 
	 *  @method findAll
	 *  @return list of employees
	 *  @throws SQLException
	 */
	public List<Employee> findAll() throws SQLException;
	
	/**
	 * function to update an employee information
	 * 
	 *  @method update
	 *  @param employee
	 *  @throws SQLException
	 */
	public boolean update(Employee employee) throws SQLException;
	
	/**
	 * function to find an employee by ID
	 * 
	 *  @method findById
	 *  @param empNo
	 *  @return employee
	 *  @throws SQLException
	 */
	public Employee findById(int empNo) throws SQLException;
	
	/**
	 * function to find all the employees who were working in a department in a period of time, which is entered from user
	 * 
	 *  @method findByWorkTime
	 *  @param fromDate, toDate
	 *  @return employee
	 *  @throws SQLException
	 */
	public  List<Employee> findByWorkTime(Date fromDate, Date toDate) throws SQLException;
	
}
