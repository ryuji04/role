package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.UserForTest;
import com.example.form.TestForm;
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
	
	@RequestMapping("/detail")
	public String detail(String userId,String userName,String companyName,Model model) {
		
		
		
		
		List<UserForTest>userList=roleService.findUserForTestByUserIdOrUserNameOrCompanyName(userId, userName, companyName);
		
		model.addAttribute("userList", userList);
		
		
		return "role_detail";
	}
	
	@RequestMapping("/change")
	public String change() {
		return "role_change";
	}
	
	@RequestMapping("/change2")
	public String change(Model model,String item) {
		System.out.println("item:"+item);
		model.addAttribute("item", item);
		return "result";
	}
	
	@RequestMapping("/test")
	public String test(Model model,TestForm testForm) {
		System.out.println("testForm:"+testForm);
		String testId=testForm.getTestId();
		String[]testArray=testId.split(",");
		System.out.println("testArray:"+testArray);
		List<TestForm>testFormList=new ArrayList<>();
		for(int i=0;i<testArray.length;i++) {
			TestForm testForm2=new TestForm();
			System.out.println("testArray:"+testArray[i]);
			testForm2.setTestId(testArray[i]);
			testFormList.add(testForm2);
		}
		for(TestForm test:testFormList) {
		System.out.println("testForm;"+test);
		}
		model.addAttribute("testForm",testForm);
		return "result";
	}
}







