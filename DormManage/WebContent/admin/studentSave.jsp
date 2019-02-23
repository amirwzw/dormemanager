<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		var rPassword=document.getElementById("rPassword").value;
		var dormBuildId=document.getElementById("dormBuildId").value;
		var dormName=document.getElementById("dormName").value;
		var name=document.getElementById("name").value;
		var sex=document.getElementById("sex").value;
		var dormNumber=document.getElementById("dormNumber").value;
		var specialty=document.getElementById("specialty").value;
		var dormCollegeId=document.getElementById("dormCollegeId").value;
		var nation=document.getElementById("nation").value;
		var teacher=document.getElementById("teacher").value;
		var origo=document.getElementById("origo").value;
		var politics=document.getElementById("politics").value;
		var job=document.getElementById("job").value;
		if(userName==""||password==""||rPassword==""||name==""||sex==""||dormBuildId==""||dormName==""||dormNumber==""||specialty==""||dormCollegeId==""||nation==""||teacher==""||origo==""||politics==""||job==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		} else if(password!=rPassword){
			document.getElementById("error").innerHTML="密码填写不一致！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(2)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${student.studentId!=null }">
				修改学生信息
			</c:when>
			<c:otherwise>
				添加学生
			</c:otherwise>
		</c:choose>
		</div>
		<form action="student?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="studentId" name="studentId" value="${student.studentId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>学号：</td>
							<td><input type="text" id="userName"  name="userName" value="${student.userName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>密码：</td>
							<td><input type="password" id="password"  name="password" value="${student.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>重复密码：</td>
							<td><input type="password" id="rPassword"  name="rPassword" value="${student.password }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>姓名：</td>
							<td><input type="text" id="name"  name="name" value="${student.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="">请选择...</option>
									<option value="男" ${student.sex eq "男"?'selected':'' }>男</option>
									<option value="女" ${student.sex eq "女"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>宿舍楼：</td>
							<td>
								<select id="dormBuildId" name="dormBuildId" style="width: 90px;">
									<c:forEach var="dormBuild" items="${dormBuildList }">
										<option value="${dormBuild.dormBuildId }" ${student.dormBuildId==dormBuild.dormBuildId?'selected':'' }>${dormBuild.dormBuildName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>寝室：</td>
							<td><input type="text" id="dormName"  name="dormName" value="${student.dormName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>床号：</td>
							<td><input type="text" id="dormNumber"  name="dormNumber" value="${student.dormNumber }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>专业（请输入完整专业名称）：</td>
							<td><input type="text" id="specialty"  name="specialty" value="${student.specialty }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>学院：</td>
							<td>
								<select id="dormCollegeId" name="dormCollegeId" style="width: 90px;">
									<c:forEach var="dormCollege" items="${dormCollegeList }">
										<option value="${dormCollege.dormCollegeId }" ${student.dormCollegeId==dormCollege.dormCollegeId?'selected':'' }>${dormCollege.dormCollegeName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>民族：</td>
							<td><input type="text" id="nation"  name="nation" value="${student.nation }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>辅导员老师：</td>
							<td><input type="text" id="teacher"  name="teacher" value="${student.teacher }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>籍贯：</td>
							<td><input type="text" id="origo"  name="origo" value="${student.origo }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>政治面貌：</td>
							<td><input type="text" id="politics"  name="politics" value="${student.politics }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>职务：</td>
							<td><input type="text" id="job"  name="job" value="${student.job }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='student'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>