package fa.training.problem02.dao;

import java.sql.SQLException;

import fa.training.problem02.model.Department;

public interface DepartmentDao {
	/**
	 * function to save a department to database
	 * 
	 *  @method save
	 *  @param department
	 *  @return true if inserts success to database, else false
	 *  @throws SQLException
	 */
	public boolean save(Department department) throws SQLException;
}
