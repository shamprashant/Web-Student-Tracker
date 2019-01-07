<%@page import = "StudentRegistrationDatabase.*,java.util.*" %>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<html>
	<head>
	<title>All Students</title>
	</head>
	<style>
		#customers{
      border-collapse: collapse;
      width: 100%;
      margin: 20px 0px;
    }
    #customers tr{
      border: 1px solid silver;
    }
    #customers td{
      padding: 10px;
      border: 1px solid #ddd;
    }
    #customers tr:nth-child(even){
      background-color: silver;
    }
    #customers th{
      padding: 20px;
      background-color: lightgreen;
      text-align: left;
      color: white;
      border: 1px solid silver;
    }
	</style>
	<body>
		<%ArrayList<Student> students = (ArrayList<Student>)request.getAttribute("students"); %>
		<table border = "1px" id = "customers">
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
			<c:forEach var = "student" items = "${students }">
			  <tr>
				<td>${student.id}</td>
				<td>${student.firstName}</td>
				<td>${student.lastName}</td>
				<td>${student.email}</td>
				<td>${student.phoneNo}</td>
				<td>${student.address}</td>
				<td>${student.city}</td>
				<td>${student.course}</td>
				<td>${student.paid}</td>
				<td>${student.startDate}</td>
			  </tr>	
			</c:forEach>
		</tbody>
	</table>
	</body>
</html>