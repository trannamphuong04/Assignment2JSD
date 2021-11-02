package fa.training.problem02.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fa.training.problem02.model.WorkingHistory;
import fa.training.problem02.utils.DBConnection;
import fa.training.problem02.utils.SQLQuery;

public class WorkingHistoryDaoImpl implements WorkingHistoryDao{
	private Connection con = null;
	
	@Override
	public boolean save(WorkingHistory workingHistory) throws SQLException {
		boolean check = false;
		con = DBConnection.getConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(SQLQuery.WORKINGHISTORY_QUERY_SAVE);

			prepareStatement.setInt(1, workingHistory.getDeptNum());
			prepareStatement.setInt(2, workingHistory.getEmpNum());
			prepareStatement.setDate(3, workingHistory.getFromDate());
			prepareStatement.setDate(4, workingHistory.getToDate());

			int row = prepareStatement.executeUpdate();
			check = row > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
		return check;
	}

}

