package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.DormCollegeDao;
import com.lero.model.DormCollege;
import com.lero.model.DormManager;
import com.lero.model.PageBean;
import com.lero.util.DbUtil;
import com.lero.util.PropertiesUtil;
import com.lero.util.StringUtil;

public class DormCollegeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	DormCollegeDao dormCollegeDao = new DormCollegeDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String s_dormCollegeName = request.getParameter("s_dormCollegeName");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		DormCollege dormCollege = new DormCollege();
		if("preSave".equals(action)) {
			dormCollegePreSave(request, response);
			return;
		} else if("save".equals(action)){
			dormCollegeSave(request, response);
			return;
		} else if("delete".equals(action)){
			dormCollegeDelete(request, response);
			return;
		} else if("manager".equals(action)){
			dormCollegeManager(request, response);
			return;
		} else if("addManager".equals(action)){
			dormCollegeAddManager(request, response);
		} else if("move".equals(action)){
			managerMove(request, response);
		} else if("list".equals(action)) {
			if(StringUtil.isNotEmpty(s_dormCollegeName)) {
				dormCollege.setDormCollegeName(s_dormCollegeName);
			}
			session.removeAttribute("s_dormCollegeName");
			request.setAttribute("s_dormCollegeName", s_dormCollegeName);
		} else if("search".equals(action)){
			if(StringUtil.isNotEmpty(s_dormCollegeName)) {
				dormCollege.setDormCollegeName(s_dormCollegeName);
				session.setAttribute("s_dormCollegeName", s_dormCollegeName);
			}else {
				session.removeAttribute("s_dormCollegeName");
			}
		} else {
			if(StringUtil.isNotEmpty(s_dormCollegeName)) {
				dormCollege.setDormCollegeName(s_dormCollegeName);
				session.setAttribute("s_dormCollegeName", s_dormCollegeName);
			}
			if(StringUtil.isEmpty(s_dormCollegeName)) {
				Object o = session.getAttribute("s_dormCollegeName");
				if(o!=null) {
					dormCollege.setDormCollegeName((String)o);
				}
			}
		}
		if(StringUtil.isEmpty(page)) {
			page="1";
		}
		Connection con = null;
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		request.setAttribute("pageSize", pageBean.getPageSize());
		request.setAttribute("page", pageBean.getPage());
		try {
			con=dbUtil.getCon();
			List<DormCollege> dormCollegeList = dormCollegeDao.dormCollegeList(con, pageBean, dormCollege);
			int total=dormCollegeDao.dormCollegeCount(con, dormCollege);
			String pageCode = this.genPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("dormCollegeList", dormCollegeList);
			request.setAttribute("mainPage", "admin/dormCollege.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void managerMove(HttpServletRequest request,
			HttpServletResponse response) {
		String dormCollegeId = request.getParameter("dormCollegeId");
		String dormManagerId = request.getParameter("dormManagerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			dormCollegeDao.managerUpdateWithId(con, dormManagerId, "0");
			request.getRequestDispatcher("dormCollege?action=manager&dormCollegeId="+dormCollegeId).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dormCollegeAddManager(HttpServletRequest request,
			HttpServletResponse response) {
		String dormCollegeId = request.getParameter("dormCollegeId");
		String dormManagerId = request.getParameter("dormManagerId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			dormCollegeDao.managerUpdateWithId(con, dormManagerId, dormCollegeId);
			request.getRequestDispatcher("dormCollege?action=manager&dormCollegeId="+dormCollegeId).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dormCollegeManager(HttpServletRequest request,
			HttpServletResponse response) {
		String dormCollegeId = request.getParameter("dormCollegeId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			List<DormManager> managerListWithId = dormCollegeDao.dormManWithCollegeId(con, dormCollegeId);
			List<DormManager> managerListToSelect = dormCollegeDao.dormManWithoutCollege(con);
			request.setAttribute("dormCollegeId", dormCollegeId);
			request.setAttribute("managerListWithId", managerListWithId);
			request.setAttribute("managerListToSelect", managerListToSelect);
			request.setAttribute("mainPage", "admin/selectManager.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void dormCollegeDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String dormCollegeId = request.getParameter("dormCollegeId");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if(dormCollegeDao.existManOrDormWithId(con, dormCollegeId)) {
				request.setAttribute("error", "����¥����������޹ܣ�����ɾ��������¥");
			} else {
				dormCollegeDao.dormCollegeDelete(con, dormCollegeId);
			}
			request.getRequestDispatcher("dormCollege?action=list").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void dormCollegeSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String dormCollegeId = request.getParameter("dormCollegeId");
		String dormCollegeName = request.getParameter("dormCollegeName");
		String detail = request.getParameter("detail");
		DormCollege dormCollege = new DormCollege(dormCollegeName, detail);
		if(StringUtil.isNotEmpty(dormCollegeId)) {
			dormCollege.setDormCollegeId(Integer.parseInt(dormCollegeId));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if(StringUtil.isNotEmpty(dormCollegeId)) {
				saveNum = dormCollegeDao.dormCollegeUpdate(con, dormCollege);
			} else {
				saveNum = dormCollegeDao.dormCollegeAdd(con, dormCollege);
			}
			if(saveNum > 0) {
				request.getRequestDispatcher("dormCollege?action=list").forward(request, response);
			} else {
				request.setAttribute("dormCollege", dormCollege);
				request.setAttribute("error", "����ʧ��");
				request.setAttribute("mainPage", "dormCollege/dormCollegeSave.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void dormCollegePreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String dormCollegeId = request.getParameter("dormCollegeId");
		if(StringUtil.isNotEmpty(dormCollegeId)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				DormCollege dormCollege = dormCollegeDao.dormCollegeShow(con, dormCollegeId);
				request.setAttribute("dormCollege", dormCollege);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} 
		request.setAttribute("mainPage", "admin/dormCollegeSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}

	private String genPagation(int totalNum, int currentPage, int pageSize){
		int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='dormCollege?page=1'>��ҳ</a></li>");
		if(currentPage==1) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		}else {
			pageCode.append("<li><a href='dormCollege?page="+(currentPage-1)+"'>��һҳ</a></li>");
		}
		for(int i=currentPage-2;i<=currentPage+2;i++) {
			if(i<1||i>totalPage) {
				continue;
			}
			if(i==currentPage) {
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			} else {
				pageCode.append("<li><a href='dormCollege?page="+i+"'>"+i+"</a></li>");
			}
		}
		if(currentPage==totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		} else {
			pageCode.append("<li><a href='dormCollege?page="+(currentPage+1)+"'>��һҳ</a></li>");
		}
		pageCode.append("<li><a href='dormCollege?page="+totalPage+"'>βҳ</a></li>");
		return pageCode.toString();
	}
}
