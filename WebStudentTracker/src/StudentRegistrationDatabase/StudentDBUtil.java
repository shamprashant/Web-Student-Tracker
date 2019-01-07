package StudentRegistrationDatabase;

import java.sql.*;
import java.util.ArrayList;

public class StudentDBUtil {

	public static Student getStudentById(int id) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "prashant");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Student WHERE id = " + id + ";";
			ResultSet rSet = stmt.executeQuery(sql);
			if (rSet.next()) {
				String firstName = rSet.getString(2);
				String lastName = rSet.getString(3);
				String email = rSet.getString(4);
				String phoneNo = rSet.getString(5);
				String address = rSet.getString(6);
				String city = rSet.getString(7);
				String course = rSet.getString(8);
				String paid = rSet.getString(9);
				String startDate = rSet.getString(10);
				
				return new Student(firstName,lastName,email,phoneNo,address,city,course,paid,startDate);
			}
		}
		catch(Exception e) {
			
		}
		return null;
	}

	public static ArrayList<Student> getAllStudents(String command) {
		// TODO Auto-generated method stub
		String sql = "";
		switch(command) {
			case "ALL":
				sql = ";";
				break;
			case "UNPAID":
				sql+=("WHERE paid = 'half';");
				break;
			default:
				sql+=("WHERE course = '" + command + "';");
				break;
		}
		
		ArrayList<Student> students = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dvdrental", "postgres", "prashant");
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM Student " + sql;
			ResultSet rSet = stmt.executeQuery(query);
			while(rSet.next()) {
				int id = rSet.getInt(1);
				String firstName = rSet.getString(2);
				String lastName = rSet.getString(3);
				String email = rSet.getString(4);
				String phoneNo = rSet.getString(5);
				String address = rSet.getString(6);
				String city = rSet.getString(7);
				String course = rSet.getString(8);
				String paid = rSet.getString(9);
				String startDate = rSet.getString(10);
				
				students.add(new Student(id,firstName,lastName,email,phoneNo,address,city,course,paid,startDate));
			}
		}
		catch(Exception e) {
			
		}
		return students;
	}

}
