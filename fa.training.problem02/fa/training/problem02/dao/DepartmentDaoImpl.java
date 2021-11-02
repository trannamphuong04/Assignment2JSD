package fa.training.problem02.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fa.training.problem02.model.Department;
import fa.training.problem02.utils.DBConnection;
import fa.training.problem02.utils.SQLQuery;

public class DepartmentDaoImpl implements DepartmentDao {
	
	private Connection con = null;
	private PreparedStatement prepareStatement = null;
	CallableStatement cas = null;

	@Override
	public boolean save(Department department) throws SQLException {
		boolean check = false;
		con = DBConnection.getConnection();
		try {
			PreparedStatement prepareStatement = con.prepareStatement(SQLQuery.DEPARTMENT_QUERY_SAVE);
			prepareStatement.setInt(1, department.getDeptNo());
			prepareStatement.setString(2, department.getDeptName());
			prepareStatement.setString(3, department.getDescription());

			int row = prepareStatement.executeUpdate();
			check = row>0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection();
		}
		return check;
	}

}
