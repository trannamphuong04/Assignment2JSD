package fa.training.problem02.model;

import java.io.Serializable;

import fa.training.problem02.utils.Validation;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int deptNo;
	private String deptName;
	private String description;
	
	public Department() {
	}

	public Department(int deptNo, String deptName, String description) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.description = description;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		if(Validation.validateName(deptName))
		this.deptName = deptName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
