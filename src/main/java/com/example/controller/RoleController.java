package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Role;
import com.example.domain.UserForTest;
import com.example.form.TestForm;
import com.example.service.RoleService;

@Controller
@RequestMapping("/role_edit")
public class RoleController {
	@Autowired
	public RoleService roleService;

	@Autowired
	private HttpSession session;

	@RequestMapping("list")
	public String list(Model model) {
		List<Role> roleList = roleService.findAllRole();
//TODO 削除
		System.out.println("roleList cont:" + roleList);
		model.addAttribute("roleList", roleList);

		return "role_list";
	}

	@RequestMapping("showDetail")
	public String index(String roleId) {

		session.setAttribute("roleId", roleId);

		return "role_detail";
	}

	@RequestMapping("/detail")
	public String detail(String userId, String userName, String companyName, Model model) {

		

		// 検索された全てのユーザ
		List<UserForTest> userList = roleService.findUserForTestByUserIdOrUserNameOrCompanyName(userId, userName,
				companyName);
		// 元々所属していたユーザ
		List<UserForTest> userList2 = new ArrayList<>();

		// 所属しているユーザを省いたリスト
		List<UserForTest> userList3 = new ArrayList<>();
		String str = (String) session.getAttribute("roleId");
		System.out.println("str:"+str+"test");
		for (UserForTest user : userList) {
			System.out.println("userroleId:"+user.getUserRoleId()+"test");
			Integer roleId=Integer.valueOf(str);
			Integer userRoleId = Integer.parseInt(user.getUserRoleId());

			if (roleId==userRoleId) {
				// TODO 削除
				System.out.println("userroleIdとroleIdが同じ時の条件処理");
				userList2.add(user);
			} else {
				System.out.println("userroleIdとroleIdが違う時の条件処理");
				// TODO 削除
				System.out.println("userroleId:" + user.getUserRoleId());
				System.out.println("roleId:" + roleId);
				userList3.add(user);
			}
		}

		model.addAttribute("userList3", userList3);
		model.addAttribute("userList2", userList2);

		return "role_detail";
	}

	@RequestMapping("/change")
	public String change() {
		return "role_change";
	}

	@RequestMapping("/change2")
	public String change(Model model, String str) {
		System.out.println("str:" + str);
		model.addAttribute("item", str);
		return "result";
	}

	@RequestMapping("/test")
	public String test(Model model, TestForm testForm) {
		System.out.println("testForm:" + testForm);
		String testId = testForm.getTestId();
		String[] testArray = testId.split(",");
		System.out.println("testArray:" + testArray);
		List<TestForm> testFormList = new ArrayList<>();
		for (int i = 0; i < testArray.length; i++) {
			TestForm testForm2 = new TestForm();
			System.out.println("testArray:" + testArray[i]);
			testForm2.setTestId(testArray[i]);
			testFormList.add(testForm2);
		}
		for (TestForm test : testFormList) {
			System.out.println("testForm;" + test);
		}
		model.addAttribute("testForm", testForm);
		return "result";
	}
}
