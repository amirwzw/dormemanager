package com.lero.model;

public class DormCollege {
	private int dormCollegeId;
	private String dormCollegeName;
	private String detail;
	
	
	public DormCollege() {
	}
	
	public DormCollege(String dormCollegeName, String detail) {
		this.dormCollegeName = dormCollegeName;
		this.detail = detail;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
