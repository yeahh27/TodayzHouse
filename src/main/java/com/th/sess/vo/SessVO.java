package com.th.sess.vo;

public class SessVO {

	private String email;
	private String name;
	private String loginTime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "SessVO [email=" + email + ", name=" + name + ", loginTime=" + loginTime + "]";
	}

}
