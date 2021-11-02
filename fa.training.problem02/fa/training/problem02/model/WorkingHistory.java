package fa.training.problem02.model;

import java.sql.Date;

public class WorkingHistory {
	private int empNum;
	private int deptNum;
	private Date fromDate;
	private Date toDate;
	
	public WorkingHistory() {
	}
	
	public WorkingHistory(Date fromDate, Date toDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public int getEmpNum() {
		return empNum;
	}

	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}

	public int getDeptNum() {
		return deptNum;
	}

	public void setDeptNum(int deptNum) {
		this.deptNum = deptNum;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
}
