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
	<a href='Exercises_list?sel=add'>Dodaj zadanie</a>
	<br>
	<h2>Lista zadań:</h2>

	<table>
		<tr>
			<th>ID zadania</th>
			<th>Tytył zadania</th>
			<th>Opis</th>
			<th>Akcje</th>
		</tr>

		<c:forEach items="${exercises}" var="exercise">
			<tr>
				<td>${exercise.getId()}</td>
				<td>${exercise.getTitle()}</td>
				<td>${exercise.getDescription()}</td>
				<td><a href="Exercises_list?sel=edit&id=${exercise.getId()}">Edycja</a>  <a href='Exercises_list?sel=del&id=${exercise.getId()}'>Usuń</a></td>
			</tr>

		</c:forEach>

	</table>
	<%@ include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>