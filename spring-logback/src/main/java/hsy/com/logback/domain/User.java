package hsy.com.logback.domain;

import java.util.Date;

public class User {

	private int age;
	
	private String pwd;
	
	private String phone;
	
	private Date createTime;

	
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User() {
		super();
	}

	public User(int age, String pwd, String phone, Date createTime) {
		super();
		this.age = age;
		this.pwd = pwd;
		this.phone = phone;
		this.createTime = createTime;
	}

	
	
	
}
