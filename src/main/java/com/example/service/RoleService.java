package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Company;
import com.example.domain.UserForTest;
import com.example.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	@Autowired
	public RoleRepository roleRepository;

	public List<Company> finadCompanyByCompanyName(String companyName) {
//TODO 削除
		System.out.println("companyForServiceの処理:");
		return roleRepository.findCompanyByCompanyName(companyName);
	}

	public List<UserForTest> findUserForTestByUserIdOrUserNameOrCompanyName(String userId, String userName,String companyName) {
		
		List<Company>companyList=new ArrayList<>();
		if(!companyName.equals("")&&!companyName.equals(" ")) {
			roleRepository.findCompanyByCompanyName(companyName);
		}

		List<UserForTest> userList = roleRepository.findUserForTestByUserIdOrUserNameOrCompanyName(userId, userName,
				companyList);
		return userList;

	}
}
