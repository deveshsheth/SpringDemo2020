package com.bean;

import java.util.ArrayList;

public class EmployeeBean {
	
	int empId;
	String empName;
	String empSalary;
	
	ArrayList<SkillBean> skills = new ArrayList<>();

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(String empSalary) {
		this.empSalary = empSalary;
	}

	public ArrayList<SkillBean> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<SkillBean> skills) {
		this.skills = skills;
	}
	
	
	
}
