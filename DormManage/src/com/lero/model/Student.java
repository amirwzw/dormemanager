package com.lero.model;

public class Student {
	private int studentId;
	private String stuNumber;
	private String userName;
	private String password;
	private int dormBuildId = 0;
	private String dormBuildName;
	private String dormName;
	private String name;
	private String sex;
	private int dormNumber;
	private String specialty;
	private int dormCollegeId = 0;
	private String dormCollegeName;
	private String nation;
	private String teacher;
	private String origo;
	private String politics;
	private String job;
	
	public Student() {
	}
	
	public Student(String userName, String password) {
		this.stuNumber = userName;
		this.userName = userName;
		this.password = password;
	}
	
	
	public Student(String stuNumber, String password, int dormBuildId,
			String dormName, String name, String sex,int dormNumber, String specialty, int dormCollegeId, String nation, String teacher, String origo, String politics, String job) {
		this.stuNumber = stuNumber;
		this.userName = stuNumber;
		this.password = password;
		this.dormBuildId = dormBuildId;
		this.dormName = dormName;
		this.name = name;
		this.sex = sex;
		this.dormNumber = dormNumber;
		this.specialty = specialty;
		this.dormCollegeId = dormCollegeId;
		this.nation = nation;
		this.teacher = teacher;
		this.origo = origo;
		this.politics = politics;
		this.job = job;
		
	}

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
		this.stuNumber = userName;
	}
	public String getStuNumber() {
		return stuNumber;
	}
	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
		this.userName = stuNumber;
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
	
	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
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

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getOrigo() {
		return origo;
	}

	public void setOrigo(String origo) {
		this.origo = origo;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
}
