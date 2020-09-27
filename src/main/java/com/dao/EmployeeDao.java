package com.dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bean.EmployeeBean;
import com.bean.SkillBean;
@Repository
public class EmployeeDao {
	@Autowired
	JdbcTemplate stmt;
	
	public int insertemp(EmployeeBean empBean) {
		// TODO Auto-generated method stub
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String insertQ = "insert into employee (empname,empsalary) values (?,?)";
	stmt.update(new PreparedStatementCreator() {
		
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = con.prepareStatement(insertQ,java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,empBean.getEmpName());
			pstmt.setString(2, empBean.getEmpSalary());
			return pstmt;
		}
	},keyHolder);	
	
	int empId = (Integer) keyHolder.getKeys().get("empid");
	
	return empId;
	}

	public List<EmployeeBean> listemp() {
		// TODO Auto-generated method stub
		List<EmployeeBean> empBean = stmt.query("select * from employee", BeanPropertyRowMapper.newInstance(EmployeeBean.class));
		return empBean;
	}

	public void deleteemp(int eid) {
		// TODO Auto-generated method stub
		stmt.update("delete from employee where empid=?",eid);
	}
	
	public void addSkillEmployee(int empId,int skillId) {
		stmt.update("insert into empskill (eid,sid) values (?,?)",empId,skillId);
	}

	public void updateEmployee(EmployeeBean employeeBean) {
		// TODO Auto-generated method stub
		stmt.update("update employee set empname = ?,empsalary = ? where empid = ?",employeeBean.getEmpName(),employeeBean.getEmpSalary(),employeeBean.getEmpId());
		
	}

	public void deleteSkillForEmployee(int empId) {
		// TODO Auto-generated method stub
		stmt.update("delete from empskill where eid = ?",empId);
	}

	public List<SkillBean> getSkillForEmployee(int empId) {
		// TODO Auto-generated method stub
		List<SkillBean> skills = stmt.query("select s.skillid,s.skillname from skill s , empskill es where es.eid = "+empId+" and s.skillid = es.sid", BeanPropertyRowMapper.newInstance(SkillBean.class));
		return skills;
	}




}
