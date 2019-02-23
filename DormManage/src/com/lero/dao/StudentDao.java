package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.DormBuild;
import com.lero.model.DormCollege;
import com.lero.model.Student;
import com.lero.util.StringUtil;

public class StudentDao {

//	public List<Student> studentList(Connection con, PageBean pageBean, Student s_student)throws Exception {
//		List<Student> studentList = new ArrayList<Student>();
//		StringBuffer sb = new StringBuffer("select * from t_student t1");
//		if(StringUtil.isNotEmpty(s_student.getName())) {
//			sb.append(" and t1.name like '%"+s_student.getName()+"%'");
//		} else if(StringUtil.isNotEmpty(s_student.getStuNumber())) {
//			sb.append(" and t1.stuNum like '%"+s_student.getStuNumber()+"%'");
//		} else if(StringUtil.isNotEmpty(s_student.getDormName())) {
//			sb.append(" and t1.dormName like '%"+s_student.getDormName()+"%'");
//		}
//		if(s_student.getDormBuildId()!=0) {
//			sb.append(" and t1.dormBuildId="+s_student.getDormBuildId());
//		}
//		if(pageBean != null) {
//			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
//		}
//		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
//		ResultSet rs = pstmt.executeQuery();
//		while(rs.next()) {
//			Student student=new Student();
//			student.setStudentId(rs.getInt("studentId"));
//			int dormBuildId = rs.getInt("dormBuildId");
//			student.setDormBuildId(dormBuildId);
//			student.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
//			student.setDormName(rs.getString("dormName"));
//			student.setName(rs.getString("name"));
//			student.setSex(rs.getString("sex"));
//			student.setStuNumber(rs.getString("stuNum"));
//			student.setTel(rs.getString("tel"));
//			student.setPassword(rs.getString("password"));
//			studentList.add(student);
//		}
//		return studentList;
//	}
	
	public List<Student> studentList(Connection con, Student s_student)throws Exception {
		List<Student> studentList = new ArrayList<Student>();
		StringBuffer sb = new StringBuffer("select * from t_student t1");
		if(StringUtil.isNotEmpty(s_student.getName())) {
			sb.append(" and t1.name like '%"+s_student.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_student.getStuNumber())) {
			sb.append(" and t1.stuNum like '%"+s_student.getStuNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_student.getDormName())) {
			sb.append(" and t1.dormName like '%"+s_student.getDormName()+"%'");
		}
		if(s_student.getDormBuildId()!=0) {
			sb.append(" and t1.dormBuildId="+s_student.getDormBuildId());
		}
		if(s_student.getDormCollegeId()!=0) {
			sb.append(" and t1.dormCollegeId="+s_student.getDormCollegeId());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Student student=new Student();
			student.setStudentId(rs.getInt("studentId"));
			int dormBuildId = rs.getInt("dormBuildId");
			student.setDormBuildId(dormBuildId);
			student.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			student.setDormName(rs.getString("dormName"));
			student.setName(rs.getString("name"));
			student.setSex(rs.getString("sex"));
			student.setStuNumber(rs.getString("stuNum"));
			student.setPassword(rs.getString("password"));
			student.setDormNumber(rs.getInt("dormNumber"));
			student.setSpecialty(rs.getString("specialty"));
			int dormCollegeId = rs.getInt("dormCollegeId");
			student.setDormCollegeId(dormCollegeId);
			student.setDormCollegeName(DormCollegeDao.dormCollegeName(con, dormCollegeId));
			student.setNation(rs.getString("nation"));
			student.setTeacher(rs.getString("teacher"));
			student.setOrigo(rs.getString("origo"));
			student.setPolitics(rs.getString("politics"));
			student.setJob(rs.getString("job"));
			studentList.add(student);
		}
		return studentList;
	}
	
	public static Student getNameById(Connection con, String studentNumber, int dormBuildId, int dormCollegeId)throws Exception {
		String sql = "select * from t_student t1 where t1.stuNum=? and t1.dormBuildId=? and t1.dormCollegeId";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, studentNumber);
		pstmt.setInt(2, dormBuildId);
		pstmt.setInt(3, dormCollegeId);
		ResultSet rs=pstmt.executeQuery();
		Student student = new Student();
		if(rs.next()) {
			student.setName(rs.getString("name"));
			student.setDormBuildId(rs.getInt("dormBuildId"));
			student.setDormCollegeId(rs.getInt("dormCollegeId"));
			student.setDormName(rs.getString("dormName"));
		}
		return student;
	}
	
	public boolean haveNameByNumber(Connection con, String studentNumber)throws Exception {
		String sql = "select * from t_student t1 where t1.stuNum=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, studentNumber);
		ResultSet rs=pstmt.executeQuery();
		Student student = new Student();
		if(rs.next()) {
			student.setName(rs.getString("name"));
			student.setDormBuildId(rs.getInt("dormBuildId"));
			student.setDormCollegeId(rs.getInt("dormCollegeId"));
			student.setDormName(rs.getString("dormName"));
			return true;
		}
		return false;
	}
	
	public List<Student> studentListWithBuild(Connection con, Student s_student, int buildId, int collegeId)throws Exception {
		List<Student> studentList = new ArrayList<Student>();
		StringBuffer sb = new StringBuffer("select * from t_student t1");
		if(StringUtil.isNotEmpty(s_student.getName())) {
			sb.append(" and t1.name like '%"+s_student.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_student.getStuNumber())) {
			sb.append(" and t1.stuNum like '%"+s_student.getStuNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_student.getDormName())) {
			sb.append(" and t1.dormName like '%"+s_student.getDormName()+"%'");
		}
		sb.append(" and t1.dormBuildId="+buildId);
		sb.append(" and t1.dormCollegeId="+collegeId);
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			Student student=new Student();
			student.setStudentId(rs.getInt("studentId"));
			int dormBuildId = rs.getInt("dormBuildId");
			student.setDormBuildId(dormBuildId);
			student.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			student.setDormName(rs.getString("dormName"));
			student.setName(rs.getString("name"));
			student.setSex(rs.getString("sex"));
			student.setStuNumber(rs.getString("stuNum"));
			student.setPassword(rs.getString("password"));
			student.setName(rs.getString("name"));
			student.setDormNumber(rs.getInt("dormNumber"));
			student.setSpecialty(rs.getString("specialty"));
			int dormCollegeId = rs.getInt("dormCollegeId");
			student.setDormCollegeId(dormCollegeId);
			student.setDormCollegeName(DormCollegeDao.dormCollegeName(con, dormCollegeId));
			student.setNation(rs.getString("nation"));
			student.setTeacher(rs.getString("teacher"));
			student.setOrigo(rs.getString("origo"));
			student.setPolitics(rs.getString("politics"));
			student.setJob(rs.getString("job"));
			studentList.add(student);
		}
		return studentList;
	}
	
	public List<DormBuild> dormBuildList(Connection con)throws Exception {
		List<DormBuild> dormBuildList = new ArrayList<DormBuild>();
		String sql = "select * from t_dormBuild";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormBuild dormBuild=new DormBuild();
			dormBuild.setDormBuildId(rs.getInt("dormBuildId"));
			dormBuild.setDormBuildName(rs.getString("dormBuildName"));
			dormBuild.setDetail(rs.getString("dormBuildDetail"));
			dormBuildList.add(dormBuild);
		}
		return dormBuildList;
	}
	
	public List<DormCollege> dormCollegeList(Connection con)throws Exception {
		List<DormCollege> dormCollegeList = new ArrayList<DormCollege>();
		String sql = "select * from t_dormCollege";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormCollege dormCollege=new DormCollege();
			dormCollege.setDormCollegeId(rs.getInt("dormCollegeId"));
			dormCollege.setDormCollegeName(rs.getString("dormCollegeName"));
			dormCollege.setDetail(rs.getString("dormCollegeDetail"));
			dormCollegeList.add(dormCollege);
		}
		return dormCollegeList;
	}
	
	public int studentCount(Connection con, Student s_student)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_student t1");
		if(StringUtil.isNotEmpty(s_student.getName())) {
			sb.append(" and t1.name like '%"+s_student.getName()+"%'");
		} else if(StringUtil.isNotEmpty(s_student.getStuNumber())) {
			sb.append(" and t1.stuNum like '%"+s_student.getStuNumber()+"%'");
		} else if(StringUtil.isNotEmpty(s_student.getDormName())) {
			sb.append(" and t1.dormName like '%"+s_student.getDormName()+"%'");
		}
		if(s_student.getDormBuildId()!=0) {
			sb.append(" and t1.dormBuildId="+s_student.getDormBuildId());
		}
		if(s_student.getDormCollegeId()!=0) {
			sb.append(" and t1.dormCollegeId="+s_student.getDormCollegeId());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public Student studentShow(Connection con, String studentId)throws Exception {
		String sql = "select * from t_student t1 where t1.studentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, studentId);
		ResultSet rs=pstmt.executeQuery();
		Student student = new Student();
		if(rs.next()) {
			student.setStudentId(rs.getInt("studentId"));
			int dormBuildId = rs.getInt("dormBuildId");
			student.setDormBuildId(dormBuildId);
			student.setDormBuildName(DormBuildDao.dormBuildName(con, dormBuildId));
			student.setDormName(rs.getString("dormName"));
			student.setName(rs.getString("name"));
			student.setSex(rs.getString("sex"));
			student.setStuNumber(rs.getString("stuNum"));
			student.setPassword(rs.getString("password"));
			student.setDormNumber(rs.getInt("dormNumber"));
			student.setSpecialty(rs.getString("specialty"));
			int dormCollegeId = rs.getInt("dormCollegeId");
			student.setDormCollegeId(dormCollegeId);
			student.setDormCollegeName(DormCollegeDao.dormCollegeName(con, dormCollegeId));
			student.setNation(rs.getString("nation"));
			student.setTeacher(rs.getString("teacher"));
			student.setOrigo(rs.getString("origo"));
			student.setPolitics(rs.getString("politics"));
			student.setJob(rs.getString("job"));
		}
		return student;
	}
	
	public int studentAdd(Connection con, Student student)throws Exception {
		String sql = "insert into t_student values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getStuNumber());
		pstmt.setString(2, student.getPassword());
		pstmt.setString(3, student.getName());
		pstmt.setInt(4, student.getDormBuildId());
		pstmt.setString(5, student.getDormName());
		pstmt.setString(6, student.getSex());
		pstmt.setInt(7, student.getDormNumber());
		pstmt.setString(8, student.getSpecialty());
		pstmt.setInt(9, student.getDormCollegeId());
		pstmt.setString(10, student.getNation());
		pstmt.setString(11, student.getTeacher());
		pstmt.setString(12, student.getOrigo());
		pstmt.setString(13, student.getPolitics());
		pstmt.setString(14, student.getJob());
		return pstmt.executeUpdate();
	}
	
	public int studentDelete(Connection con, String studentId)throws Exception {
		String sql = "delete from t_student where studentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, studentId);
		return pstmt.executeUpdate();
	}
	
	public int studentUpdate(Connection con, Student student)throws Exception {
		String sql = "update t_student set stuNum=?,password=?,name=?,dormBuildId=?,dormName=?,sex=?,dormNumber=?,specialty=?,dormCollegeId=?,nation=?,teacher=?,origo=?,politics=?,job=? where studentId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getStuNumber());
		pstmt.setString(2, student.getPassword());
		pstmt.setString(3, student.getName());
		pstmt.setInt(4, student.getDormBuildId());
		pstmt.setString(5, student.getDormName());
		pstmt.setString(6, student.getSex());
		pstmt.setInt(7, student.getDormNumber());
		pstmt.setString(8, student.getSpecialty());
		pstmt.setInt(9, student.getDormCollegeId());
		pstmt.setString(10, student.getNation());
		pstmt.setString(11, student.getTeacher());
		pstmt.setString(12, student.getOrigo());
		pstmt.setString(13, student.getPolitics());
		pstmt.setString(14, student.getJob());
		pstmt.setInt(15, student.getStudentId());
		return pstmt.executeUpdate();
	}
	
	
}
