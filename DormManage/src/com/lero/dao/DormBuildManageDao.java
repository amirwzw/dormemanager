package com.lero.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lero.model.DormBuildManage;
import com.lero.model.DormManager;
import com.lero.model.PageBean;
import com.lero.util.StringUtil;

public class DormBuildManageDao {
	public List<DormBuildManage> dormBuildManageList(Connection con, PageBean pageBean, DormBuildManage s_dormBuildManage)throws Exception {
		List<DormBuildManage> dormBuildManageList = new ArrayList<DormBuildManage>();
		StringBuffer sb = new StringBuffer("SELECT * FROM t_dormBuildManage t1");
		if(pageBean != null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			DormBuildManage dormBuildManage=new DormBuildManage();
			dormBuildManage.setFloor(rs.getInt("floor"));
			dormBuildManage.setDormName(rs.getString("dormName"));
			dormBuildManage.setFirst(rs.getString("first"));
			dormBuildManage.setSecond(rs.getString("second"));
			dormBuildManage.setThird(rs.getString("third"));
			dormBuildManage.setForth(rs.getString("forth"));
			dormBuildManage.setFifth(rs.getString("fifth"));
			dormBuildManage.setSixth(rs.getString("sixth"));
			dormBuildManageList.add(dormBuildManage);
		}
		return dormBuildManageList;
	}
	
	public int dormBuildManageCount(Connection con, DormBuildManage s_dormBuildManage)throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_dormBuildManage t1");
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}
	
	public DormBuildManage dormBuildManageShow(Connection con, String floor)throws Exception {
		String sql = "select * from t_dormBuildManage t1 where t1.floor=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, floor);
		ResultSet rs=pstmt.executeQuery();
		DormBuildManage dormBuildManage = new DormBuildManage();
		if(rs.next()) {
			dormBuildManage.setFloor(rs.getInt("floor"));
			dormBuildManage.setDormName(rs.getString("dormName"));
			dormBuildManage.setFirst(rs.getString("first"));
			dormBuildManage.setSecond(rs.getString("second"));
			dormBuildManage.setThird(rs.getString("third"));
			dormBuildManage.setForth(rs.getString("forth"));
			dormBuildManage.setFifth(rs.getString("fifth"));
			dormBuildManage.setSixth(rs.getString("sixth"));
		}
		return dormBuildManage;
	}
	
	public int dormBuildManageAdd(Connection con, DormBuildManage dormBuildManage)throws Exception {
		String sql = "insert into t_dormBuildManage values(null,?,?,null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, dormBuildManage.getFloor());
		pstmt.setString(2, dormBuildManage.getDormName());
		pstmt.setString(3, dormBuildManage.getFirst());
		pstmt.setString(4, dormBuildManage.getSecond());
		pstmt.setString(5, dormBuildManage.getThird());
		pstmt.setString(6, dormBuildManage.getForth());
		pstmt.setString(7, dormBuildManage.getFifth());
		pstmt.setString(8, dormBuildManage.getSixth());	
		return pstmt.executeUpdate();
	}
	
	public int dormBuildManageDelete(Connection con, String floor)throws Exception {
		String sql = "delete from t_dormBuildManage where floor=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, floor);
		return pstmt.executeUpdate();
	}
	
	public int dormBuildManageUpdate(Connection con, DormBuildManage dormBuildManage)throws Exception {
		String sql = "update t_dormBuildManage set dormName=?,first=?,second=?,third=?,forth=?,fifth=?,sixth=? where floor=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormBuildManage.getDormName());
		pstmt.setString(2, dormBuildManage.getFirst());
		pstmt.setString(3, dormBuildManage.getSecond());
		pstmt.setString(4, dormBuildManage.getThird());
		pstmt.setString(5, dormBuildManage.getForth());
		pstmt.setString(6, dormBuildManage.getFifth());
		pstmt.setString(7, dormBuildManage.getSixth());
		pstmt.setInt(8, dormBuildManage.getFloor());
		return pstmt.executeUpdate();
	}

	public boolean haveManageByUser(Connection con, String dormName) throws Exception {
		String sql = "select * from t_dormBuildManage t1 where t1.dormName=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, dormName);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			return true;
		}
		return false;
	}
}
