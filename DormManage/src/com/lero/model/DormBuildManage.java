package com.lero.model;

public class DormBuildManage {
	private int floor;
	private String dormName;
	private String first;
	private String second;
	private String third;
	private String forth;
	private String fifth;
	private String sixth;
	
	public DormBuildManage() {

	}
	
	public DormBuildManage(int floor,String dormName,String first,String second,String third,String forth,String fifth,String sixth) {
		this.setFloor(floor);
		this.setDormName(dormName);
		this.setFirst(first);
		this.setSecond(second);
		this.setThird(third);
		this.setForth(forth);
		this.setFifth(fifth);
		this.setSixth(sixth);
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getDormName() {
		return dormName;
	}

	public void setDormName(String dormName) {
		this.dormName = dormName;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public String getThird() {
		return third;
	}

	public void setThird(String third) {
		this.third = third;
	}

	public String getForth() {
		return forth;
	}

	public void setForth(String forth) {
		this.forth = forth;
	}

	public String getFifth() {
		return fifth;
	}

	public void setFifth(String fifth) {
		this.fifth = fifth;
	}

	public String getSixth() {
		return sixth;
	}

	public void setSixth(String sixth) {
		this.sixth = sixth;
	}
	
}
