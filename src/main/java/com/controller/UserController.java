package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {
	
	@Autowired
	UserDao userDao;
	//signup
	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(UserBean userBean) {
		System.out.println("Signup called...");
		System.out.println(userBean.getEmail());
		userDao.insertUser(userBean);
//		return new ResponseEntity<UserBean>(userBean,null,HttpStatus.CREATED);

		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userBean);
		responseBean.setMsg("Signup Successfully!!!");
		responseBean.setStatus(201);
		return responseBean;
	}
	
	@GetMapping("/users")
	public ResponseBean<ArrayList<UserBean>> getUsers(){
	
		ResponseBean<ArrayList<UserBean>> responseBean = new ResponseBean<>();
		
		responseBean.setData(userDao.listUsers());
		responseBean.setMsg("list of users");
		responseBean.setStatus(200);
		return responseBean;
		
	}
	
	@GetMapping("/getuser/{userId}")
	public ResponseBean<UserBean> getUser(@PathVariable("userId") int userId){
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userDao.getUserById(userId));
		responseBean.setMsg("Single User Return");
		responseBean.setStatus(200);
	
		return responseBean;
	}
	@DeleteMapping("/getuser/{userId}")
	public ResponseBean<UserBean> getDeleteUser(@PathVariable("userId") int userId){
		
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		
		responseBean.setData(userDao.deleteUserById(userId));
		responseBean.setMsg("User Deleted...!!!");
		responseBean.setStatus(200);
	
		return responseBean;
	}
}
	 
 

 