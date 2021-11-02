package fa.training.problem02.dao;

import java.sql.SQLException;
import fa.training.problem02.model.WorkingHistory;

public interface WorkingHistoryDao {
	/**
	 * function to add the working history of an employee
	 * 
	 *  @method save
	 *  @param workingHistory
	 *  @return true if inserts success to database, else false
	 *  @throws SQLException
	 */
	public boolean save(WorkingHistory workingHistory) throws SQLException;
}
