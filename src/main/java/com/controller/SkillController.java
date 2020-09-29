package com.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.SkillDao;

@RestController
public class SkillController {
	@Autowired
	SkillDao skillDao;
	
	@PostMapping("/skill")
	public ResponseBean<SkillBean> addSkill(@RequestBody SkillBean skillBean)
	{
		skillDao.insertSkill(skillBean);
		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		responseBean.setData(skillBean);
		responseBean.setMsg("Skill Added");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listskill")
	public ResponseBean<java.util.List<SkillBean>> listSkill()
	{
		ResponseBean<java.util.List<SkillBean>> responseBean = new ResponseBean<>();

		java.util.List<SkillBean> skillbeans = skillDao.listSkill();
		responseBean.setData(skillbeans);
		responseBean.setMsg("All Skills..!!");
		responseBean.setStatus(201);
		return responseBean;
	}
	
	@DeleteMapping("/skill/{skillId}")
	public ResponseBean<SkillBean> deleteskill(@PathVariable("skillId")int skillId){
		
		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		skillDao.deleteSkill(skillId);
		responseBean.setMsg("Deleted Successfully..!!");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@PutMapping("/updateskill")
	public ResponseBean<SkillBean> updateskill(SkillBean skillBean){
		skillDao.updateskill(skillBean);
		ResponseBean<SkillBean> responseBean = new ResponseBean<>();
		responseBean.setData(skillBean);
		responseBean.setMsg("Updated Successfully..!!");
		
		return responseBean;
	}
	}
