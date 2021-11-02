package fa.training.problem02.utils;

public class SQLQuery {
	
	public static String EMPLOYEE_QUERY_FINDALL = "SELECT*FROM employee";
	
	public static String EMPLOYEE_QUERY_UPDATE = "UPDATE employee SET birth_date=?, first_name = ?, last_name=?,gender=?,hire_date=? WHERE emp_no=?";
	
	public static String EMPLOYEE_QUERY_SAVE = "INSERT INTO employee(emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES (?, ?, ?, ?, ?, ?)";
	
	public static String EMPLOYEE_QUERY_FINDBYID = "SELECT*FROM employee WHERE emp_no=?";
	
	public static String DEPARTMENT_QUERY_SAVE = "INSERT INTO department(dept_no, dept_name, description) VALUES (?, ?, ?)";
	
	public static String WORKINGHISTORY_QUERY_SAVE = "INSERT INTO working_history(dept_no, emp_no, from_date, to_date) VALUES (?, ?, ?, ?)";
	
	public static String EMPLOYEE_QUERY_FINDBYWORKTIME = 
			"SELECT employee.emp_no, employee.birth_date, employee.first_name,employee.last_name,employee.gender,employee.hire_date"
			+ "from employee"
			+ "inner join working_history on employee.emp_no = working_history.emp_no"
			+ "inner join department on department.dept_no = working_history.dept_no"
			+ "where from_date = ? and to_date = ?;";
}
