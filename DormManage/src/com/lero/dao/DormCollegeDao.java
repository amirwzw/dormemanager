package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.DormCollege;
import com.lero.model.DormManager;
import com.lero.model.PageBean;
import com.lero.util.StringUtil;

public class DormCollegeDao {
	public List<DormCollege> dormCollegeList(Connection con, PageBean pageBean, DormCollege s_dormCollege)throws Exception {
		List<DormCollege> dormCollegeList = new ArrayList<DormCollege>();
		StringBuffer sb = new StringBuffer("select * from t_dormCollege t1");
		if(StringUtil.isNotEmpty(s_dormCollege.getDormCollegeName())) {
			sb.append(" where t1.dormCollegeName like '%"+s_dormCollege.getDormCollegeName()+"%'");
		}
		if(pageBean != null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
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
	
	public static String dormCollegeName(Connection con, int dormCollegeId)throws Exception {
		String sql = "select * from t_dormCollege where dormCollegeId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dormCollegeId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getString("dormCollegeName");
		}
		return null;
	}
	
	public int dormCollegeCount(Connection con, DormCollege s_dormCollege)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_dormCollege t1");
		if(StringUtil.isNotEmpty(s_dormCollege.getDormCollegeName())) {
			sb.append(" where t1.dormCollegeName like '%"+s_dormCollege.getDormCollegeName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public DormCollege dormCollegeShow(Connection con, String dormCollegeId)throws Exception {
		String sql = "select * from t_dormCollege t1 where t1.dormCollegeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollegeId);
		ResultSet rs=pstmt.executeQuery();
		DormCollege dormCollege = new DormCollege();
		if(rs.next()) {
			dormCollege.setDormCollegeId(rs.getInt("dormCollegeId"));
			dormCollege.setDormCollegeName(rs.getString("dormCollegeName"));
			dormCollege.setDetail(rs.getString("dormCollegeDetail"));
		}
		return dormCollege;
	}
	
	public int dormCollegeAdd(Connection con, DormCollege dormCollege)throws Exception {
		String sql = "insert into t_dormCollege values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollege.getDormCollegeName());
		pstmt.setString(2, dormCollege.getDetail());
		return pstmt.executeUpdate();
	}
	
	public int dormCollegeDelete(Connection con, String dormCollegeId)throws Exception {
		String sql = "delete from t_dormCollege where dormCollegeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollegeId);
		return pstmt.executeUpdate();
	}
	
	public int dormCollegeUpdate(Connection con, DormCollege dormCollege)throws Exception {
		String sql = "update t_dormCollege set dormCollegeName=?,dormCollegeDetail=? where dormCollegeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollege.getDormCollegeName());
		pstmt.setString(2, dormCollege.getDetail());
		pstmt.setInt(3, dormCollege.getDormCollegeId());
		return pstmt.executeUpdate();
	}
	
	public boolean existManOrDormWithId(Connection con, String dormCollegeId)throws Exception {
		boolean isExist = false;
//		String sql="select * from t_dormBuild,t_dormManager,t_connection where dormManId=managerId and dormBuildId=buildId and dormBuildId=?";
		String sql = "select *from t_dormManager where dormCollegeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollegeId);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			isExist = true;
		} else {
			isExist = false;
		}
		String sql1="select * from t_dormCollege t1,t_dorm t2 where t1.dormCollegeId=t2.dormCollegeId and t1.dormCollegeId=?";
		PreparedStatement p=con.prepareStatement(sql1);
		p.setString(1, dormCollegeId);
		ResultSet r = pstmt.executeQuery();
		if(r.next()) {
			return isExist;
		} else {
			return false;
		}
	}
	
	public List<DormManager> dormManWithoutCollege(Connection con)throws Exception {
		List<DormManager> dormManagerList = new ArrayList<DormManager>();
		String sql = "SELECT * FROM t_dormManager WHERE dormCollegeId IS NULL OR dormCollegeId=0";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormManager dormManager=new DormManager();
			dormManager.setDormCollegeId(rs.getInt("dormCollegeId"));
			dormManager.setDormManagerId(rs.getInt("dormManId"));
			dormManager.setName(rs.getString("name"));
			dormManager.setUserName(rs.getString("userName"));
			dormManager.setSex(rs.getString("sex"));
			dormManager.setTel(rs.getString("tel"));
			dormManager.setDormNumber(rs.getInt("dormNumber"));
			dormManager.setSpecialty(rs.getString("specialty"));
			dormManagerList.add(dormManager);
		}
		return dormManagerList;
	}
	
	public List<DormManager> dormManWithCollegeId(Connection con, String dormCollegeId)throws Exception {
		List<DormManager> dormManagerList = new ArrayList<DormManager>();
		String sql = "select *from t_dormManager where dormCollegeId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollegeId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormManager dormManager=new DormManager();
			dormManager.setDormCollegeId(rs.getInt("dormCollegeId"));
			dormManager.setDormManagerId(rs.getInt("dormManId"));
			dormManager.setName(rs.getString("name"));
			dormManager.setUserName(rs.getString("userName"));
			dormManager.setSex(rs.getString("sex"));
			dormManager.setTel(rs.getString("tel"));
			dormManager.setDormNumber(rs.getInt("dormNumber"));
			dormManager.setSpecialty(rs.getString("specialty"));
			dormManagerList.add(dormManager);
		}
		return dormManagerList;
	}
	
	public int managerUpdateWithId (Connection con, String dormManagerId, String dormCollegeId)throws Exception {
		String sql = "update t_dormManager set dormCollegeId=? where dormManId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormCollegeId);
		pstmt.setString(2, dormManagerId);
		return pstmt.executeUpdate();
	}
}
