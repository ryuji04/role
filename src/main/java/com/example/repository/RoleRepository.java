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
import com.example.domain.Role;
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

	private static final RowMapper<Role> ROLE_ROW_MAPPER = (rs, i) -> {
		Role role = new Role();
		role.setRoleId(rs.getString("role_id"));
		role.setRoleDescription(rs.getString("role_description"));
		return role;
	};

	private static final RowMapper<UserForTest> USERFORTEST_ROW_MAPPER = (rs, i) -> {
		UserForTest user = new UserForTest();
		user.setUserId(rs.getString("u_user_id"));
		user.setNameKnjSei(rs.getString("u_name_knj_sei"));
		user.setNameKnjMei(rs.getString("u_name_knj_mei"));
		user.setUserRoleId(rs.getString("u_user_role_id"));

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
		SqlParameterSource param = new MapSqlParameterSource().addValue("companyName", "%" + companyName + "%");

		List<Company> companyList = new ArrayList<>();
		companyList = template.query(sql, param, COMPANY_ROW_MAPPER);
		return companyList;
	}

	public List<UserForTest> findUserForTestByUserIdOrUserNameOrCompanyName(String userId, String userName,
			List<Company> companyList) {

		SqlParameterSource param = null;

		// このsqlはあってると思う
		String sql = "select u.user_id u_user_id,u.name_knj_sei u_name_knj_sei, "
				+ "u.name_knj_mei u_name_knj_mei,u.user_role_id u_user_role_id,l.hijoji_heiji_kbn l_hijoji_heiji_kbn "
				+ "from user_for_test AS u LEFT OUTER JOIN licence AS l ON u.user_id=l.shozoku_user_id where 1=1 ";

		if (!userId.equals("") && userId != null) {
			System.out.println("userId:" + userId);
			sql = sql.concat("AND user_id = :userId ");
		}

		if (!userName.equals("") && userName != null) {
			sql = sql.concat("AND( name_knj_sei like :userName OR name_knj_mei like :userName ) ");
		}

		if (companyList.size() != 0) {
			// TODO 削除
			System.out.println("companyList!=nullの条件が処理されている");
			System.out.println("companyList:" + companyList);
			List<UserForTest> userList2 = new ArrayList<>();

			for (Company company : companyList) {
				String companyId = company.getCompanyId();
				sql = sql.concat("AND shozoku_company_id like :companyId ORDER BY user_id ");
				param = new MapSqlParameterSource().addValue("companyId", companyId);
				userList2 = template.query(sql, param, USERFORTEST_ROW_MAPPER);

			}
			return userList2;
		}

		param = new MapSqlParameterSource().addValue("userId", userId).addValue("userName", "%" + userName + "%");
		sql = sql.concat("ORDER BY user_id ");

		List<UserForTest> userList = template.query(sql, param, USERFORTEST_ROW_MAPPER);
		return userList;

	}

	public List<Role> findAllRole() {
		String sql = "select role_id,role_description from role ";

		return template.query(sql, ROLE_ROW_MAPPER);
	}

	public List<UserForTest> findUserByUserRoleId(String roleId) {
		String sql = "select user_id,name_knj_sei,name_knj_mei from user_for_test " + "where userRoleId=:userRoleId ";

		SqlParameterSource param = new MapSqlParameterSource().addValue("userRoleId", roleId);

		List<UserForTest> userList = template.query(sql, param, USERFORTEST_ROW_MAPPER);

		return userList;

	}

}
