<%@ page import = "StudentRegistrationDatabase.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>Your Search Result
	<title>Search Result</title>
</head>
<body>
	<%Student student = (Student)request.getAttribute("student"); %>
	<%int id = (Integer)request.getAttribute("id"); %>
	<table border = "1px">
		<thead>
			<tr>
				<th>ID</th>
				<th>FIRST NAME</th>
				<th>LAST NAME </th>
				<th>EMAIL</th>
				<th>PHONE NO</th>
				<th>ADDRESS </th>
				<th>CITY </th>
				<th>COURSE </th>
				<th>PAID</th>
				<th>ENROLLMENT DATE </th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%= id%></td>
				<td><%= student.getFirstName()%></td>
				<td><%=student.getLastName()%></td>
				<td><%=student.getEmail()%></td>
				<td><%=student.getPhoneNo()%></td>
				<td><%=student.getAddress()%></td>
				<td><%=student.getCity()%></td>
				<td><%=student.getCourse()%></td>
				<td><%=student.getPaid()%></td>
				<td><%=student.getStartDate()%></td>
			</tr>
		</tbody>
	</table>
</body>
</html>