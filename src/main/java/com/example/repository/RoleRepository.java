package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Company;
import com.example.domain.UserForTest;

@Repository

public class RoleRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Company> COMPANY_ROW_MAPPER = (rs, i) -> {
		Company company = new Company();
		company.setCompanyId(rs.getString("company_id"));
		company.setCompanyName(rs.getString("company_name"));
		System.out.println("mapper:" + company);
		return company;
	};

	private static final RowMapper<UserForTest> USERFORTEST_ROW_MAPPER = (rs, i) -> {
		UserForTest user = new UserForTest();
		user.setUserId(rs.getString("u_user_id"));
		user.setNameKnjSei(rs.getString("u_name_knj_sei"));
		user.setNameKnjMei(rs.getString("u_name_knj_mei"));
		System.out.println("userForTestのmapper");

		return user;
	};

	/**
	 * companyNameからcompany情報を取得
	 * 
	 * @param companyName
	 * @return
	 */
	public List<Company> findCompanyByCompanyName(String companyName) {
		String sql = "SELECT company_id,company_name FROM company WHERE company_name like :companyName ";
		// TODO 削除
		System.out.println("findCompanyの処理1");
		System.out.println("companyName:" + companyName);
		SqlParameterSource param = new MapSqlParameterSource().addValue("companyName", companyName);

		// TODO 削除
		System.out.println("findCompanyの処理2");
		List<Company> companyList = new ArrayList<>();
		companyList = template.query(sql, param, COMPANY_ROW_MAPPER);
		// TODO 削除
		System.out.println("companyList:" + companyList);
		return companyList;
	}

	public List<UserForTest> findUserForTestByUserIdOrUserNameOrCompanyName(String userId, String userName,
			List<Company>companyList) {

		
		
		
		SqlParameterSource param = null;

		// このsqlはあってると思う
		String sql = "select u.user_id u_user_id,u.name_knj_sei u_name_knj_sei, "
				+ "u.name_knj_mei u_name_knj_mei,l.hijoji_heiji_kbn l_hijoji_heiji_kbn "
				+ "from user_for_test AS u LEFT OUTER JOIN licence AS l ON u.user_id=l.shozoku_user_id where 1=1 ";

		if (!userId.equals("") && !userId.equals(" ")) {
			System.out.println("userId:" + userId);
			sql = sql.concat("AND user_id = :userId ");
			// param=new MapSqlParameterSource().addValue("userId", userId);
			// TODO 削除
			System.out.println("userId==0の時実行されている");
		}

		if (!userName.equals("") && !userName.equals(" ")) {
			sql = sql.concat("OR name_knj_sei like :userName OR name_knj_mei like :userName ");
			// param=new MapSqlParameterSource().addValue("userName", userName);
			// TODO 削除
			System.out.println("userName==0の時実行されている");
		}

		if (!companyList.isEmpty()||companyList!=null) {
			System.out.println("companyList!=nullの条件が処理されている");
			List<UserForTest> userList2 = new ArrayList<>();

			
			for (Company company : companyList) {
				UserForTest user2 = new UserForTest();
				String companyId = company.getCompanyId();
				sql = sql.concat("OR shozoku_company_id like :companyId ORDER BY user_id ");
				param = new MapSqlParameterSource().addValue("companyId", companyId);
				userList2 = template.query(sql, param, USERFORTEST_ROW_MAPPER);

			}
			return userList2;
		}

		sql = sql.concat("ORDER BY user_id ");

		param = new MapSqlParameterSource().addValue("userId", userId).addValue("userName", userName);

		List<UserForTest> userList = template.query(sql, param, USERFORTEST_ROW_MAPPER);
		return userList;

	}

}
