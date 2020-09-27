package com.dao;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bean.SkillBean;

@Repository
public class SkillDao {
@Autowired

	JdbcTemplate stmt;
	public void insertSkill(SkillBean skillBean) {
		// TODO Auto-generated method stub
		stmt.update("insert into skill (skillname) values (?)",skillBean.getSkillName());
	}
	public java.util.List<SkillBean> listSkill(){
		 java.util.List<SkillBean> skillBean = stmt.query("select * from skill",BeanPropertyRowMapper.newInstance(SkillBean.class)); 
		 return skillBean;
	 }
	public void deleteSkill(int skillId) {
		// TODO Auto-generated method stub
		stmt.update("delete from skill where skillid = ?",skillId);
	}
	public void updateskill(SkillBean skillBean) {
		// TODO Auto-generated method stub
		stmt.update("update skill set skillname=? where skillid=?",skillBean.getSkillName(),skillBean.getSkillId());
		System.out.println("Hello update...!!");
	}
	

}
