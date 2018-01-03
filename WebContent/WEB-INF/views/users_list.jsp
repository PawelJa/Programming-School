<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/header.jspf"%>
	<br>
	<a href='Users_list?sel=add'>Dodaj użytkownika</a>
	<br>
	<h2>Lista użytkowników:</h2>

	<table>
		<tr>
			<th>ID użytkownika</th>
			<th>ID grupy użytkownika</th>
			<th>Nazwa użytkownika</th>
			<th>Email użytkownika</th>
			<th>Akcja</th>
		</tr>

		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.getId()}</td>
				<td>${user.getUser_group_id()}</td>
				<td>${user.getUsername()}</td>
				<td>${user.getEmail()}</td>
				<td><a href="Users_list?sel=edit&id=${user.getId()}">Edycja</a>  <a href='Users_list?sel=del&id=${user.getId()}'>Usuń</a></td>
			</tr>

		</c:forEach>

	</table>
	<%@ include file="/WEB-INF/views/footer.jspf"%>

</body>
</html>