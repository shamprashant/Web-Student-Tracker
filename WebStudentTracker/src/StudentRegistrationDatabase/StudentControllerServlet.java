package StudentRegistrationDatabase;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Student oldStudent;
	private static int oldStudentId;
    
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("command");
		
		if (command == null)
			command = "index";
		try {	
			switch(command) {
				case "ADMIN":
					adminLogin(request,response);
					break;
				case "REGISTRAR":
					registrar(request, response);
					break;
				case "SEARCH":
					searchStudent(request, response);
					break;
				case "ADD":
					addStudent(request, response);
					break;
				case "UPDATE":
					updateStudent(request, response);
					break;
				case "DELETE":
					deleteStudent(request, response);
					break;
				case "ADD-DONE":
					addStudentToDatabase(request, response);
					break;
				case "A-LOGIN":
					openAdminGUI(request,response);
					break;
				case "UPDATE-DONE":
					updateStudentInDatabase(request,response);
					break;
				default:
					openIndexPage(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void updateStudentInDatabase(HttpServletRequest request, HttpServletResponse response) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "prashant");
			Statement stmt = conn.createStatement();
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNo = request.getParameter("phoneNo");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String course = request.getParameter("course");
			String paid = request.getParameter("payment");
			String startDate = request.getParameter("startDate");
			
			String query = "UPDATE Student SET ";
			boolean toAppend = false;
			String toAdd = "";
			if (!firstName.equals(oldStudent.getFirstName())) {
				query+="firstname = " + "'"+firstName+"'";
				toAppend = true;
				toAdd = "";
			}
			if (!lastName.equals(oldStudent.getLastName())) {
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "lastname = "+"'" + lastName + "'");
				toAdd = "";
				toAppend = true;
			}
			
			if (!email.equals(oldStudent.getEmail())) {
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "email = "+"'" + email + "'");
				toAdd = "";
				toAppend = true;
			}
			
			if (!phoneNo.equals(oldStudent.getPhoneNo())){
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "phoneno = "+"'" + phoneNo + "'");
				toAdd = "";
				toAppend = true;
			}
			if (!address.equals(oldStudent.getAddress())){
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "address = "+"'" + address + "'");
				toAdd = "";
				toAppend = true;
			}
			if (!city.equals(oldStudent.getCity())) {
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "city = "+"'" + city + "'");
				toAdd = "";
				toAppend = true;
			}
			if (!course.equals(oldStudent.getCourse())) {
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "course = "+"'" + course + "'");
				toAdd = "";
				toAppend = true;
			}
			if (!paid.equals(oldStudent.getPaid())) {
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "paid = "+"'" + paid + "'");
				toAdd = "";
				toAppend = true;
			}
			if (!startDate.equals(oldStudent.getStartDate())) {
				if (toAppend)
					toAdd = ",";
				query+=(toAdd + "startDate = "+"'" + startDate + "'");
				toAdd = "";
				toAppend = true;
			}
			query+=(" WHERE id = " + oldStudentId + ";");
			stmt.executeUpdate(query);
			registrar(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	private void openAdminGUI(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String uName = request.getParameter("uname");
		String pass = request.getParameter("pass");
		
		if (uName.equals("prashant") && pass.equals("root")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("AdminGUI.html");
			dispatcher.forward(request, response);
		}
		else {
			adminLogin(request, response);
		}
	}

	private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminLogin.html");
		dispatcher.forward(request, response);
	}

	private void addStudentToDatabase(HttpServletRequest request, HttpServletResponse response) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "prashant");
			Statement stmt = conn.createStatement();
			
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNo = request.getParameter("phoneNo");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String course = request.getParameter("course");
			String paid = request.getParameter("payment");
			String startDate = request.getParameter("startDate");
			
			String sql = "INSERT INTO " + "Student" + "(firstName,lastName,email,phoneNo,address,city,course,paid,startDate)"
						+ "VALUES('"+firstName+"','"+lastName+"','"+email+"','"+phoneNo+"','"+address
						+"','"+city+"','"+course+"','"+paid+"','"+startDate+"');";
			
			stmt.executeUpdate(sql);
			registrar(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("deleteId"));
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "prashant");
			Statement stmt = conn.createStatement();
			int affect = stmt.executeUpdate("DELETE From Student Where id = " + id);
			RequestDispatcher dispatcher;
			if (affect > 0) {
				dispatcher = request.getRequestDispatcher("deletionSuccessful.html");
				dispatcher.forward(request, response);
			}
			else {
					dispatcher = request.getRequestDispatcher("StudentNotFound.html");
					dispatcher.forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("updateId"));
		Student student = StudentDBUtil.getStudentById(id);
		
		if (student != null) {
			request.setAttribute("student", student);
			request.setAttribute("id", id);
			oldStudent = student;
			oldStudentId = id;
			RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateForm.jsp");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentNotFound.html");
			dispatcher.forward(request, response);
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher dispatcher = request.getRequestDispatcher("AddStudent.html");
		dispatcher.forward(request, response);
		
	}

	private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("searchId"));
		Student student = StudentDBUtil.getStudentById(id);
		if (student != null) {
			request.setAttribute("student", student);
			request.setAttribute("id", id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("SearchStudent.jsp");
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("StudentNotFound.html");
			dispatcher.forward(request, response);
		}
		
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response) throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("registrarGUI.html");
		dispatcher.forward(request, response);
	}

	private void openIndexPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if (command == null) {
			command = "index";
		}
		try {
			switch(command) {
				case "ALL":
					showAllStudents(request,response);
					break;
				case "UNPAID":
					showUnpaidStudents(request, response);
					break;
				case "SELECTION":
					showSelectedCourseStudents(request, response);
					break;
				default:
					openAdminGUI(request,response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void showSelectedCourseStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		String selected = (String)request.getParameter("SELECTION");
		ArrayList<Student> students = StudentDBUtil.getAllStudents(selected);
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-all-students.jsp");
		dispatcher.forward(request, response);
	}

	private void showUnpaidStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		ArrayList<Student> students = StudentDBUtil.getAllStudents("UNPAID");
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-all-students.jsp");
		dispatcher.forward(request, response);
	}

	private void showAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		ArrayList<Student> students = StudentDBUtil.getAllStudents("ALL");
		request.setAttribute("students", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-all-students.jsp");
		dispatcher.forward(request, response);
	}
}
