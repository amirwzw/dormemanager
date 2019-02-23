package com.lero.model;

public class DormManager {
	
	private int dormManagerId;
	private String userName;
	private String password;
	private int dormBuildId;
	private String dormBuildName;
	private String name;
	private String sex;
	private String tel;
	private int dormNumber;
	private String specialty;
	private int dormCollegeId;
	private String dormCollegeName;
	
	public DormManager() {

	}
	
	public DormManager(String userName, String password, 
			String name, String sex, String tel, int dormNumber, String specialty) {
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.dormNumber = dormNumber;
		this.specialty = specialty;
	}



	public DormManager(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public int getDormManagerId() {
		return dormManagerId;
	}
	public void setDormManagerId(int dormManagerId) {
		this.dormManagerId = dormManagerId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getDormBuildId() {
		return dormBuildId;
	}
	public void setDormBuildId(int dormBuildId) {
		this.dormBuildId = dormBuildId;
	}
	
	public String getDormBuildName() {
		return dormBuildName;
	}


	public void setDormBuildName(String dormBuildName) {
		this.dormBuildName = dormBuildName;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getDormNumber() {
		return dormNumber;
	}

	public void setDormNumber(int dormNumber) {
		this.dormNumber = dormNumber;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getDormCollegeId() {
		return dormCollegeId;
	}

	public void setDormCollegeId(int dormCollegeId) {
		this.dormCollegeId = dormCollegeId;
	}

	public String getDormCollegeName() {
		return dormCollegeName;
	}

	public void setDormCollegeName(String dormCollegeName) {
		this.dormCollegeName = dormCollegeName;
	}
	
	
}
