<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="org.spring.printMenu.dto.*" %>
<%
	Paging pg = (Paging)request.getAttribute("pg");
	List<FoodDataOfDay> list = pg.getFoodDodList();
	int pgTotal = 1;
	boolean flag = true;
	try {
		int temp = pg.getFoodDodList().get(0).getTotalPageCount();
		if(temp > 0) {
			pgTotal = pg.getFoodDodList().get(0).getTotalPageCount();
		}
	} catch(IndexOutOfBoundsException e) {
		flag = false;
		System.out.println("err");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>식단표 출력</title>
<style>
	html {
		width: 100%;
		height: 100%;
		text-align: center;
	}
	table {
		width: 80%;
		height: 100%;
		border: 2px solid gray;
		text-align: center;
		margin: 0 auto;
		border-collapse: collapse;
	}
	td, th {
		border: 1px solid gray;
	}
	th {
		width: 20em;
		background-color: #cccccc;
	}
	.div {
		border-top: 1px double solid gray;
		<!-- 흠... -->
	}
	.data {
		margin-top: 0em; 
		padding: 0em;
	}
	.Food {
		background-color: lime;
		text-align: center;
		height: 1.3em;
	}
	.null {
		height: 0em;
	}
	ul, ol {
		margin-top: 0em; 
		margin-bottom: 0em;
		padding: 0em;
		text-align: center;
	}
	li {
		list-style-type: none;
		margin-top: 0em; 
		margin-bottom: 0em;
		padding: 0em;
		text-align: left;
		height: 1.3em;
	}
	p {
		margin-top: 0em; 
		margin-bottom: 0em;
		padding: 0em;
		text-align: center;
	}
	p.Ingredient {
		list-style-type: none;
		margin-top: 0em; 
		margin-bottom: 0em;
		padding: 0em;
		text-align: left;
		height: 1.3em;
	}
</style>
<link type="text/css" href="./../css/main.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
<br>
<%
	for(int pgNum=1; pgNum<=pgTotal; pgNum++) {
%>
	<br>
	<h3>식단 구성표</h3>
	<h4><%= pg.getNowDate() %></h4>
	<table>
		<caption>식당명 : <%= pg.getRestName() %> / 급식기간 : <%= pg.getStartDate() %> ~ <%= pg.getEndDate() %></caption>
	<thead>
		<tr>
			<th id="div">구분</th>
	<%
		for(FoodDataOfDay temp : list) {
	%>
			<th><%= temp.getDate() %>(<%= temp.getDay() %>)</th>
	<%
		}
	%>
		</tr>
	</thead>
	<%
		if(list != null && flag) {
			String divName = "";
			for(int index=0; index<list.get(0).getList(pgNum).size(); index++) {
				if(!list.get(0).getList(pgNum).get(index).getDivName().equals(divName)) {
					divName = list.get(0).getList(pgNum).get(index).getDivName();
	%>
				<tr>
					<td>
						<%= divName %>
					</td>
	<%
					for(FoodDataOfDay temp : list) {
	%>
						<td class="data">
	<%	
						String foodName="";
						int num = index;
						int end = num + list.get(0).getCntDiv(list.get(0).getList(pgNum), divName);
						if(end > 20) {
							end = 20;
						}
						for(; num<end; num++) {
							if(num == index) {
	%>
								<ul class="null" >
	<%								
							}
							String className = temp.getList(pgNum).get(num).getClass().getName();
							className = className.substring(className.lastIndexOf(".")+1);
							
							if(className.equals("Food")) {
	%>
								</ul>
								<ul ><p class="<%= className %>"><%= temp.getList(pgNum).get(num).getName() %></p>
	<%
							} else {
								if(num == index) {
	%>
									</ul>
	<%								
								}
	%>
								<li ><p class="<%= className %>"><%= temp.getList(pgNum).get(num).getName() %></p></li>
	<%
							}
					
						}
	%>
							</ul>
						</td>
	<%	
					}
	%>
				</tr>
	<%		
				}
			}
		} else {
	%>
		<tr>
			<td></td>
			<td colspan="<%= list.size() %>">No Data </td>
		</tr>
	<%
		}
	%>
	</table>
	<h6><%= pgNum %>/<%= pgTotal %></h6>
	<br>
	<br>
<%
	}
%>
<script>
	$(window).load(function(){
		if(confirm("인쇄하시겠습니까?")) {
			window.print();
		}
	});
</script>
</body>
</html>