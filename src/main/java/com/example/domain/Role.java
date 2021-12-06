package com.example.domain;

public class Role {
	private String roleId;
	private String roleName;
	private String roleDescription;
	private String hijojiHeijiKbn;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getHijojiHeijiKbn() {
		return hijojiHeijiKbn;
	}

	public void setHijojiHeijiKbn(String hijojiHeijiKbn) {
		this.hijojiHeijiKbn = hijojiHeijiKbn;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", roleDescription=" + roleDescription
				+ ", hijojiHeijiKbn=" + hijojiHeijiKbn + "]";
	}

}
