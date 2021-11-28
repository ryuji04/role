package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Company;
import com.example.domain.UserForTest;
import com.example.service.RoleService;

@Controller
@RequestMapping("/role_edit")
public class RoleController {
	@Autowired
	public RoleService roleService;
	
	@RequestMapping("")
	public String index() {
		return "role_detail";
	}
	
	@RequestMapping("detail")
	public String detail(String userId,String userName,String companyName,Model model) {
		
		
		
		
		List<UserForTest>userList=roleService.findUserForTestByUserIdOrUserNameOrCompanyName(userId, userName, companyName);
		
		model.addAttribute("userList", userList);
		
		
		return "role_detail";
	}
}
