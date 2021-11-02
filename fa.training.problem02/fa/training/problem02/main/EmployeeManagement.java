package fa.training.problem02.main;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fa.training.problem02.dao.DepartmentDao;
import fa.training.problem02.dao.DepartmentDaoImpl;
import fa.training.problem02.dao.EmployeeDao;
import fa.training.problem02.dao.EmployeeDaoImpl;
import fa.training.problem02.dao.WorkingHistoryDao;
import fa.training.problem02.dao.WorkingHistoryDaoImpl;
import fa.training.problem02.model.Department;
import fa.training.problem02.model.Employee;
import fa.training.problem02.model.WorkingHistory;
import fa.training.problem02.utils.Validation;

public class EmployeeManagement {
	private static EmployeeDao employeeDao = new EmployeeDaoImpl();
	private static DepartmentDao departmentDao = new DepartmentDaoImpl();
	private static WorkingHistoryDao workingHistoryDao = new WorkingHistoryDaoImpl();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) throws SQLException, ParseException {

		Scanner sc = new Scanner(System.in);
		int choice;
		do {
			getMenu();
			System.out.println("Enter your choice");
			choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				Employee e1 = new Employee();
				System.out.println(
						"Enter emp no, birth date, first name, last name, gender, hire date, split by comma: ");
				String[] input1 = sc.nextLine().split(",");
				if (Validation.validateName(input1[2]) && Validation.validateName(input1[3])) {
					e1.setEmpNo(Integer.parseInt(input1[0]));
					Date birth_date = new java.sql.Date(dateFormat.parse(input1[1]).getTime());
					e1.setBirthDate(birth_date);
					e1.setFirstName(input1[2]);
					e1.setLastName(input1[3]);
					char gender = String.valueOf(input1[4]).charAt(0);
					e1.setGender(gender);
					Date hire_date = new java.sql.Date(dateFormat.parse(input1[5]).getTime());
					e1.setHireDate(hire_date);

					boolean check = employeeDao.save(e1);
					if (check) {
						System.out.println("Employee save successfully");
					}
				} else {
					System.out.println("input name invalid");
				}
				break;

			case 2:
				Employee e2 = new Employee();
				System.out
						.println("Enter birth date, first name, last name, gender, hire date, emp no split by comma:");
				String[] input2 = sc.nextLine().split(",");

				if (Validation.validateName(input2[1]) && Validation.validateName(input2[2])) {
					Date birth_date2 = new java.sql.Date(dateFormat.parse(input2[0]).getTime());
					e2.setBirthDate(birth_date2);
					e2.setFirstName(input2[1]);
					e2.setLastName(input2[2]);
					char gender2 = String.valueOf(input2[3]).charAt(0);
					e2.setGender(gender2);
					Date hire_date2 = new java.sql.Date(dateFormat.parse(input2[4]).getTime());
					e2.setHireDate(hire_date2);
					e2.setEmpNo(Integer.parseInt(input2[5]));

					if (employeeDao.update(e2)) {
						System.out.println("update successfully");
					}

				} else {
					System.out.println("input name invalid");
				}
				break;

			case 3:
				System.out.println("Enter employee number you want to find: ");
				int inputNum = Integer.parseInt(sc.nextLine());

				Employee e = employeeDao.findById(inputNum);
				if (e == null) {
					System.out.println("this employee does not exist");
				} else {
					System.out.println(e.toString());
				}
				break;

			case 4:
				WorkingHistory wk = new WorkingHistory();
				System.out.println("Enter dept no, emp no, from date, to date split by comma: ");
				String[] input3 = sc.nextLine().split(",");
				wk.setDeptNum(Integer.parseInt(input3[0]));
				wk.setEmpNum(Integer.parseInt(input3[1]));
				Date from_date = new Date(dateFormat.parse(input3[2]).getTime());
				Date to_date = new Date(dateFormat.parse(input3[3]).getTime());
				if (Validation.validateDate(from_date, to_date)) {
					wk.setFromDate(from_date);
					wk.setToDate(to_date);

					boolean checkWk = workingHistoryDao.save(wk);
					if (checkWk) {
						System.out.println("save working history success");
					} else {
						System.out.println("fail to add working history");
					}
				} else {
					System.out.println("from date must before to date");
				}
				break;

			case 5:

				System.out.println("Enter from date, to date split by comma: ");
				String[] input4 = sc.nextLine().split(",");
				Date from_date2 = new java.sql.Date(dateFormat.parse(input4[0]).getTime());
				Date to_date2 = new java.sql.Date(dateFormat.parse(input4[1]).getTime());
				if (Validation.validateDate(from_date2, to_date2)) {
					List<Employee> e4 = new ArrayList<>();
					e4 = employeeDao.findByWorkTime(from_date2, to_date2);
					if (e4.size() == 0) {
						System.out.println("Can not find any employee ~~ ");
					} else {
						for (Employee emp : e4) {
							System.out.println(emp.toString());
						}
					}
				} else {
					System.out.println("from date must before to date");
				}
				break;

			case 6:
				Department dp = new Department();
				System.out.println("Enter dept_no, dept_name, description split by comma: ");
				String[] input5 = sc.nextLine().split(",");
				if (Validation.validateName(input5[1])) {
					dp.setDeptNo(Integer.parseInt(input5[0]));
					dp.setDeptName(input5[1]);
					dp.setDescription(input5[2]);

					boolean checkDp = departmentDao.save(dp);
					if (checkDp) {
						System.out.println("Add new department success");
					}
				} else {
					System.out.println("invalid input name");
				}
				break;

			case 7:
				System.exit(0);
			}

		} while (true);
	}

	public static void getMenu() {
		System.out.println("__________MENU_________");
		System.out.println("-----------------------");
		System.out.println("1. Add a new employee");
		System.out.println("2. Update a specific Employee");
		System.out.println("3. Find an employee by emp_no");
		System.out.println("4. Add the working history");
		System.out.println("5. Find all the employees by working period of time");
		System.out.println("6. Add a new department");
		System.out.println("7. Close program.");

	}

}
