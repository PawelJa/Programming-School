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

	<form action='Exercises_list' method="POST">
		<p>Nazwa</p>
		<input type='text' name='newTitle'>
		<%
			String id = request.getParameter("id");
		%>
		<input type='hidden' name='id' value='<%=id%>'>
		<%
			String sel = request.getParameter("sel");
		%>
		<input type='hidden' name='sel' value='<%=sel%>'>
		<p>Opis</p>
		<textarea name='description' rows="4" cols="60"></textarea>
		<br>
		<input type='submit'>

	</form>

</body>
</html>