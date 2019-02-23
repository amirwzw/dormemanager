 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function dormNameDelete(dormNameId) {
		if(confirm("您确定要删除这个寝室吗？")) {
			window.location="dormName?action=delete&dormNameId="+dormNameId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<form action="dormBuild?action=manage" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="dormBuildId" name="dormBuildId" value="${dormBuild.dormBuildId }"/>
					<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<!-- <th>编号</th> -->
					<th>楼层</th>
					<th>寝室</th>
					<th>1床</th>
					<th>2床</th>
					<th>3床</th>
					<th>4床</th>
					<th>5床</th>
					<th>6床</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="dormBuild" items="${dormBuildList }">
					<tr>
						<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
						<td>${i.count+(page-1)*pageSize }</td>
						<td>${dormBuildManage.floor }</td>
						<td>${dormBuildManage.dormName }</td>
						<td>${dormBuildManage.first }</td>
						<td>${dormBuildManage.second }</td>
						<td>${dormBuildManage.third }</td>
						<td>${dormBuildManage.forth }</td>
						<td>${dormBuildManage.fifth }</td>
						<td>${dormBuildManage.sixth }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild?action=preSave&dormBuildId=${dormBuild.dormBuildId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="dormBuildDelete(${dormBuild.dormBuildId})">删除</button></td>
						<!-- <td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='dormBuild?action=buildManage&studentId=${student.studentId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="studentDelete(${student.studentId })">删除</button></td> -->
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
			</div>
		</form>
</div>