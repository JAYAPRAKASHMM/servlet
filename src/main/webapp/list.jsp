<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h1>User Management</h1>
	      
	 
	<br>
	<h3>List of Users</h3>
	<a href="<%=request.getContextPath()%>/new">Add New User</a>
	<br>
	<table border="5px">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Country</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
			    <h1><c:out value="${user.name}" /></h1>
				 
			<td><c:out value="${user.name}"/></td>
				<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>   <a
					href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

</body>

</html>