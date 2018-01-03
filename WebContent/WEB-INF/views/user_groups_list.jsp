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
	<h2>Lista grup użytkowników:</h2>

	<a href="User_groups_list?sel=add">Dodaj nowy wpis</a>
	<br>

	<table>
		<tr>
			<th>ID grupy</th>
			<th>Nazwa grupy</th>
			<th>Akcje</th>
		</tr>

		<c:forEach items="${groups}" var="group">
			<tr>
				<td>${group.getId()}</td>
				<td>${group.getName()}</td>
				<td><a href="User_groups_list?sel=edit&id=${group.getId()}">Edycja</a>  <a href='User_groups_list?sel=del&id=${group.getId()}'>Usuń</a></td>
			</tr>

		</c:forEach>

	</table>
	<%@ include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>