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
<h2>Szczegóły użytkownia: ${user.getUsername()}</h2>
<br>
<p>Nazwa: <strong>${user.getUsername()}</strong></p>
<p>Email: <strong>${user.getEmail()}</strong></p>
<br>
<p><strong>Dodane rozwiązania:</strong></p>
	<table>
		<tr>
			<th>Tytyuł zadania</th>
			<th>Data dodania</th>
			<th>Akcje</th>
		</tr>

		<c:forEach items="${solutions}" var="solution">
			<tr>
				<td>${solution.getExercise().getTitle()}</td>
				<td>${solution.created}</td>
				<td><a href=' '>Szczegóły</a>
			</tr>

		</c:forEach>

	</table>

	<%@ include file="/WEB-INF/views/footer.jspf"%>

</body>
</html>