<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>check.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<table>
		<caption>주간 식단 조회</caption>
		<tfoot>
			<tr>
				<td>
					<input type="button" value="조회" onclick="goPrintPage()">
				</td>
			</tr>
		</tfoot>
		<tr>
			<td>
				식당명
			</td>
			<td>
				<select name="rest" id="rest">
					<c:forEach var="item" items="${ restList }">
						<option value="${ item }">${ item }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				식사구분
			</td>
			<td>
				<select name="div" id="div">
					<c:forEach var="item" items="${ divList }" varStatus="status">
						<option value="${ divIdList[status.index] }">${ item }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				조회일자
			</td>
			<td>
				<input type="date" id="startDate"/>
				<input type="date" id="endDate"/>
				<input type="checkbox" id="chk" /> 주간 자동 선택
			</td>
		</tr>
	</table>
	<form name="checkForm" method="post" action="${ pageContext.request.contextPath }/printPreview">
		<input type="hidden" name="restName" />
		<input type="hidden" name="divName" />
		<input type="hidden" name="startDate" />
		<input type="hidden" name="endDate" />
	</form>
	<script>
		$(window).load(function(){
			document.getElementById('startDate').value = new Date().toISOString().substring(0, 10);
			$.post(
				"getSunday",
				{
					date : document.getElementById('startDate').value
				},
				function(data, status) {
					document.getElementById('endDate').max = "2099-12-31";
					document.getElementById('endDate').value = data;
				}
			)
		});
		$(function() {
			$("input[id='startDate']").change(function() {
				if(document.getElementById("chk").checked) {
					setEndDate();
				}
			});
			$("input[id='chk']").change(function() {
				if(!document.getElementById("chk").checked) {
					document.getElementById('endDate').min = "2000-01-01";
					document.getElementById('endDate').max = "2099-12-31";
					document.getElementById('endDate').value = document.getElementById('startDate').value;
				} else {
					setEndDate();
				}
			});
		});
		function goPrintPage() {
			var start_date = new Date(document.getElementById('startDate').value).toISOString().substring(0, 10);
			var selected_date = new Date(document.getElementById('endDate').value).toISOString().substring(0, 10);
			if(selected_date < start_date) {
				alert("종료일이 시작일 이전입니다.");
			} else {
				var standard_date;
				$.post(
					"getSunday",
					{
						date : document.getElementById('startDate').value
					},
					function(data, status) {
						standard_date = new Date(data).toISOString().substring(0, 10);
						if(selected_date <= standard_date) {
							var form = document.forms["checkForm"];
							form["restName"].value = document.getElementById('rest').value;
							form["divName"].value = document.getElementById("div").value;
							form["startDate"].value = document.getElementById('startDate').value;
							form["endDate"].value = document.getElementById('endDate').value;
							form.submit();
						} else {
							alert("종료일이 시작일의 주간을 벗어났습니다.");
						}
					}
				)
			}
		}
		function setEndDate() {
			$.post(
				"getSunday",
				{
					date : document.getElementById('startDate').value
				},
				function(data, status) {
					document.getElementById('endDate').min = document.getElementById('startDate').value;
					document.getElementById('endDate').max = "2099-12-31";
					document.getElementById('endDate').value = data;
					document.getElementById('endDate').max = data;
				}
			)
		}
	</script>
</body>
</html>