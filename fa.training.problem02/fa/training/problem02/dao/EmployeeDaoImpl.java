package fa.training.problem02.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fa.training.problem02.model.Employee;
import fa.training.problem02.utils.DBConnection;
import fa.training.problem02.utils.SQLQuery;
import fa.training.problem02.utils.Validation;

public class EmployeeDaoImpl implements EmployeeDao {

	private Connection con = null;
	private PreparedStatement prepareStatement = null;
	private ResultSet rs = null;

	@Override
	public boolean save(Employee employee) throws SQLException {
		boolean check = false;
		con = DBConnection.getConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(SQLQuery.EMPLOYEE_QUERY_SAVE);

			prepareStatement.setInt(1, employee.getEmpNo());
			prepareStatement.setDate(2, employee.getBirthDate());
			prepareStatement.setString(3, employee.getFirstName());
			prepareStatement.setString(4, employee.getLastName());
			prepareStatement.setString(5, String.valueOf(employee.getGender()));
			prepareStatement.setDate(6, employee.getHireDate());
			
			int row = prepareStatement.executeUpdate();
			check = row > 0;
			
		} catch (SQLException e) {
			System.out.println("input error");
		} finally {
			DBConnection.closeConnection();
		}
		return check;
	}

	@Override
	public List<Employee> findAll() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		con = DBConnection.getConnection();
		Employee e = null;
		try {
			Statement stm = con.createStatement();
			rs = stm.executeQuery(SQLQuery.EMPLOYEE_QUERY_FINDALL);
			while (rs.next()) {
				e = new Employee();
				e.setEmpNo(rs.getInt(1));
				e.setBirthDate(rs.getDate(2));
				e.setFirstName(rs.getString(3));
				e.setLastName(rs.getString(4));
				e.setGender(rs.getString(5).charAt(0));
				e.setHireDate(rs.getDate(6));

				employees.add(e);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		} finally {
			DBConnection.closeConnection();

		}

		return employees;
	}

	@Override
	public boolean update(Employee employee) throws SQLException {
		boolean isUpdated = false;
		try {
			con = DBConnection.getConnection();
			prepareStatement = con.prepareStatement(SQLQuery.EMPLOYEE_QUERY_UPDATE);
			prepareStatement.setDate(1, employee.getBirthDate());
			prepareStatement.setString(2, employee.getFirstName());
			prepareStatement.setString(3, employee.getLastName());
			prepareStatement.setString(4, String.valueOf(employee.getGender()));
			prepareStatement.setDate(5, employee.getHireDate());
			prepareStatement.setInt(6, employee.getEmpNo());

			int row = prepareStatement.executeUpdate();
			isUpdated = row > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
		return isUpdated;
	}

	@Override
	public Employee findById(int empNo) throws SQLException {
		Employee employee = null;
		try {
			con = DBConnection.getConnection();
			prepareStatement = con.prepareStatement(SQLQuery.EMPLOYEE_QUERY_FINDBYID);
			prepareStatement.setInt(1, empNo);
			rs = prepareStatement.executeQuery();
			if (rs.next()) {
				employee = new Employee();

				employee.setEmpNo(rs.getInt(1));
				employee.setBirthDate(rs.getDate(2));
				employee.setFirstName(rs.getString(3));
				employee.setLastName(rs.getString(4));
				employee.setGender(rs.getString(5).charAt(0));
				employee.setHireDate(rs.getDate(6));
			}
		} finally {
			DBConnection.closeConnection();
		}

		return employee;
	}

	@Override
	public List<Employee> findByWorkTime(Date fromDate, Date toDate) throws SQLException {
		List<Employee> employees = new ArrayList<>();
		con = DBConnection.getConnection();
		String sql = "SELECT employee.emp_no, employee.birth_date, employee.first_name,employee.last_name,employee.gender,employee.hire_date FROM employee INNER JOIN working_history ON employee.emp_no = working_history.emp_no INNER JOIN department ON department.dept_no = working_history.dept_no WHERE from_date = '"+fromDate +"' AND to_date = '"+ toDate+"';";
		try {
			prepareStatement = con.prepareStatement(sql);
			rs = prepareStatement.executeQuery();
			while (rs.next()) {
				int emp_no = rs.getInt("emp_no");
				Date birth = rs.getDate("birth_date");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				char gender = rs.getString("gender").charAt(0);
				Date hire_date = rs.getDate("hire_date");
				employees.add(new Employee(emp_no, birth, first_name, last_name, gender, hire_date));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBConnection.closeConnection();

		}
		return employees;
	}
	
	
}