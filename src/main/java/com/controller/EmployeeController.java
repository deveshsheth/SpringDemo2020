package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.bean.ResponseBean;
import com.bean.SkillBean;
import com.dao.EmployeeDao;

@RestController
public class EmployeeController {
@Autowired
	EmployeeDao empDao;

	@PostMapping("/addemployee")
	public ResponseBean<EmployeeBean> insertemp(@RequestBody EmployeeBean empBean)
	{
		ResponseBean<EmployeeBean> responseBean = new ResponseBean<>();
		System.out.println(empBean.getEmpName());
		
		int empId = empDao.insertemp(empBean);
		for(SkillBean sb : empBean.getSkills()) {
			empDao.addSkillEmployee(empId, sb.getSkillId());
			System.out.println(sb.getSkillId());
			System.out.println(sb.getSkillName());
		}
		empBean.setEmpId(empId);
		responseBean.setData(empBean);
		responseBean.setMsg("insert successfully....!!");
		responseBean.setStatus(200);
		return responseBean;
	}
	
	@GetMapping("/listEmployee")
	public ResponseBean<List<EmployeeBean>> listemp()
	{
		
		ResponseBean<List<EmployeeBean>> responseBean = new ResponseBean<>();
		List<EmployeeBean> empBean = empDao.listemp();
		for(int i =0;i<empBean.size();i++) {
			EmployeeBean sb = empBean.get(i);
			List<SkillBean> skills = empDao.getSkillForEmployee(sb.getEmpId());
			sb.setSkills((ArrayList<SkillBean>)skills);
		}
		responseBean.setData(empBean);
		responseBean.setMsg("List Updated...!!!");
		responseBean.setStatus(201);
		return responseBean;
		
	}
	
	@DeleteMapping("/addemployee/{empId}")
	public String deleteemp(@PathVariable("empId") int empId){
		empDao.deleteemp(empId);
		empDao.deleteSkillForEmployee(empId);
		return "UserDeleted....!!!";
		
	}
	@PutMapping("/updateEmployee")
	public ResponseBean<EmployeeBean> updateemp(@RequestBody EmployeeBean employeeBean){
		ResponseBean<EmployeeBean> response = new ResponseBean<>();
		empDao.updateEmployee(employeeBean);
		empDao.deleteSkillForEmployee(employeeBean.getEmpId());
		for(SkillBean sb : employeeBean.getSkills()) {
		empDao.addSkillEmployee(employeeBean.getEmpId(),sb.getSkillId());
		}
		response.setData(employeeBean);
		response.setMsg("updated succcessfully....!!!");
		response.setStatus(200);
		return response;
	}
	
}
