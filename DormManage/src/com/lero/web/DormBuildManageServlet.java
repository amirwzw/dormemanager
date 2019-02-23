package com.lero.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lero.dao.DormBuildManageDao;
import com.lero.model.DormBuildManage;
import com.lero.model.PageBean;
import com.lero.util.DbUtil;
import com.lero.util.PropertiesUtil;
import com.lero.util.StringUtil;

public class DormBuildManageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DbUtil dbUtil = new DbUtil();
	DormBuildManageDao dormBuildManageDao = new DormBuildManageDao();
	
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
		String s_dormBuildManageText = request.getParameter("s_dormBuildManageText");
		String searchType = request.getParameter("searchType");
		String page = request.getParameter("page");
		String action = request.getParameter("action");
		DormBuildManage dormBuildManage = new DormBuildManage();
		if("preSave".equals(action)) {
			dormBuildManagePreSave(request, response);
			return;
		} else if("save".equals(action)){
			dormBuildManageSave(request, response);
			return;
		} else if("delete".equals(action)){
			dormBuildManageDelete(request, response);
			return;
		} else 
			if("list".equals(action)) {
			session.removeAttribute("s_dormBuildManageText");
			session.removeAttribute("searchType");
			request.setAttribute("s_dormBuildManageText", s_dormBuildManageText);
			request.setAttribute("searchType", searchType);
		} else if("search".equals(action)){
			if (StringUtil.isNotEmpty(s_dormBuildManageText)) {
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_dormBuildManageText", s_dormBuildManageText);
			} else {
				session.removeAttribute("s_dormBuildManageText");
				session.removeAttribute("searchType");
			}
		} else {
			if(StringUtil.isNotEmpty(s_dormBuildManageText)) {
				session.setAttribute("searchType", searchType);
				session.setAttribute("s_dormBuildManageText", s_dormBuildManageText);
			}
			if(StringUtil.isEmpty(s_dormBuildManageText)) {
				Object o1 = session.getAttribute("s_dormBuildManageText");
				Object o2 = session.getAttribute("searchType");
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
			List<DormBuildManage> dormBuildManageList = dormBuildManageDao.dormBuildManageList(con, pageBean, dormBuildManage);
			int total=dormBuildManageDao.dormBuildManageCount(con, dormBuildManage);
			String pageCode = this.genPagation(total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("dormBuildManageList", dormBuildManageList);
			request.setAttribute("mainPage", "admin/dormBuildManage.jsp");
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

	private void dormBuildManageDelete(HttpServletRequest request,
			HttpServletResponse response) {
		String dormName = request.getParameter("dormName");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			dormBuildManageDao.dormBuildManageDelete(con, dormName);
			request.getRequestDispatcher("dormBuildManage?action=list").forward(request, response);
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

	private void dormBuildManageSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String floor = request.getParameter("floor");
		String dormName = request.getParameter("dormName");
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String third = request.getParameter("third");
		String forth = request.getParameter("forth");
		String fifth = request.getParameter("fifth");
		String sixth = request.getParameter("sixth");
		DormBuildManage dormBuildManage = new DormBuildManage(Integer.parseInt(floor),dormName,first,second,third,forth,fifth,sixth);
		if(StringUtil.isNotEmpty(floor)) {
			dormBuildManage.setFloor(Integer.parseInt(floor));
		}
		Connection con = null;
		try {
			con = dbUtil.getCon();
			int saveNum = 0;
			if(StringUtil.isNotEmpty(floor)) {
				saveNum = dormBuildManageDao.dormBuildManageUpdate(con, dormBuildManage);
			} else if(dormBuildManageDao.haveManageByUser(con, dormBuildManage.getDormName())){
				request.setAttribute("dormBuildManage", dormBuildManage);
				request.setAttribute("error", "���û����Ѵ���");
				request.setAttribute("mainPage", "admin/dormBuildManage.jsp");
				request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			} else {
				saveNum = dormBuildManageDao.dormBuildManageAdd(con, dormBuildManage);
			}
			if(saveNum > 0) {
				request.getRequestDispatcher("dormBuildManage?action=list").forward(request, response);
			} else {
				request.setAttribute("dormBuildManage", dormBuildManage);
				request.setAttribute("error", "����ʧ��");
				request.setAttribute("mainPage", "dormBuildManage/dormBuildManageSave.jsp");
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

	private void dormBuildManagePreSave(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		String floor = request.getParameter("floor");
		if(StringUtil.isNotEmpty(floor)) {
			Connection con = null;
			try {
				con = dbUtil.getCon();
				DormBuildManage dormBuildManage = dormBuildManageDao.dormBuildManageShow(con, floor);
				request.setAttribute("dormBuildManage", dormBuildManage);
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
		request.setAttribute("mainPage", "admin/dormBuildManageSave.jsp");
		request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
	}

	private String genPagation(int totalNum, int currentPage, int pageSize){
		int totalPage = totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode = new StringBuffer();
		pageCode.append("<li><a href='dormBuildManage?page=1'>��ҳ</a></li>");
		if(currentPage==1) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		}else {
			pageCode.append("<li><a href='dormBuildManage?page="+(currentPage-1)+"'>��һҳ</a></li>");
		}
		for(int i=currentPage-2;i<=currentPage+2;i++) {
			if(i<1||i>totalPage) {
				continue;
			}
			if(i==currentPage) {
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			} else {
				pageCode.append("<li><a href='dormBuildManage?page="+i+"'>"+i+"</a></li>");
			}
		}
		if(currentPage==totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		} else {
			pageCode.append("<li><a href='dormBuildManage?page="+(currentPage+1)+"'>��һҳ</a></li>");
		}
		pageCode.append("<li><a href='dormBuildManage?page="+totalPage+"'>βҳ</a></li>");
		return pageCode.toString();
	}
	
}
