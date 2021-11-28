package com.example.domain;

public class Licence {
	private String licenceId;
	//userテーブルのuserIdと紐づける
	private String shozokuUserId;
	private String hijojiHeijiKbn;
	public String getLicenceId() {
		return licenceId;
	}
	public void setLicenceId(String licenceId) {
		this.licenceId = licenceId;
	}
	public String getShozokuUserId() {
		return shozokuUserId;
	}
	public void setShozokuUserId(String shozokuUserId) {
		this.shozokuUserId = shozokuUserId;
	}
	public String getHijojiHeijiKbn() {
		return hijojiHeijiKbn;
	}
	public void setHijojiHeijiKbn(String hijojiHeijiKbn) {
		this.hijojiHeijiKbn = hijojiHeijiKbn;
	}
	@Override
	public String toString() {
		return "Licence [licenceId=" + licenceId + ", shozokuUserId=" + shozokuUserId + ", hijojiHeijiKbn="
				+ hijojiHeijiKbn + "]";
	}
	
	
}
