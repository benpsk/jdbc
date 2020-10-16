package jdbc.assignment;

import java.sql.*;
import java.util.*;

public class MainDB {

	public static void main(String[] args) throws SQLException {

		System.out.println("Welcome");
		int key = 1;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println();
			System.out.println("Enter 1 to insert student, 2 to show, 3 to delete Student, 4 to update Student");
			System.out.print("Enter Here: ");
			key = sc.nextInt();

			if (key == 1) {
				addStudent();
			} else if (key == 2) {
				showStudent();
			} else if (key == 3) {
				deleteStudent();
			} else if (key == 4) {
				updateStudent();
			} else {
				System.out.println("No code found.");
				break;
			}
		} while (key != 0);

	}

	private static void updateStudent() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		Scanner sc = new Scanner(System.in);
		int key = 1;

		while (key == 1) {
			showStudent();
			System.out.print("Enter roll No to update: ");
			String rollNo = sc.next();
			System.out.print("Enter Name to update: ");
			String wh = sc.next();

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root");
				String sql = "UPDATE students SET name = ? WHERE rollno = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, wh);
				pstmt.setString(2, rollNo);
				pstmt.executeUpdate();

				System.out.println("Update Successfully");

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
			System.out.print("Enter 1 to update more or 0 to break: ");
			key = sc.nextInt();
		}
	}

	private static void deleteStudent() throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		Scanner sc = new Scanner(System.in);

		int key = 1;
		while (key == 1) {
			showStudent();
			System.out.print("Enter roll no to delete: ");
			String rollNo = sc.next();

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root");
				String sql = "DELETE FROM students WHERE rollno = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rollNo);
				pstmt.executeUpdate();
				System.out.println(rollNo + " delete sucessfully.");

			} catch (Exception e) {
				System.out.println(rollNo + " does not exit.");
				System.out.println(e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}
			System.out.print("Enter 1 to delete more or 0 to break: ");
			key = sc.nextInt();
		}
	}

	public static ArrayList<StudentsInfo> setStudents() throws SQLException {
		Connection con = null;
		ArrayList<StudentsInfo> stdList = new ArrayList<StudentsInfo>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM students");
			StudentsInfo std = null;
			while (rs.next()) {
				std = new StudentsInfo();
				std.setId(rs.getInt("id"));
				std.setName(rs.getString("name"));
				std.setRollno(rs.getString("rollno"));
				std.setPhoneno(rs.getString("phoneno"));
				stdList.add(std);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (con != null)
				con.close();
		}
		return stdList;
	}

	private static void showStudent() {
		// TODO Auto-generated method stub
		System.out.println("Name\tRollno\tPhoneno");
		try {
			for (StudentsInfo st : setStudents()) {
				System.out.println(st.getName() + "\t" + st.getRollno() + "\t" + st.getPhoneno());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addStudent() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		int key = 1;
		Scanner sc = new Scanner(System.in);

		do {
			showStudent();
			System.out.print("Enter name: ");
			String name = sc.next();
			System.out.print("Enter rollno: ");
			String rollno = sc.next();
			System.out.print("Enter phoneno: ");
			String phoneno = sc.next();

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root");
				String sql = "INSERT INTO students(name, rollno, phoneno) VALUES (?, ?, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, rollno);
				pstmt.setString(3, phoneno);
				pstmt.executeUpdate();

				System.out.println("Successfully Inserted.");
				System.out.print("Enter 1 to add more or 0 to break: ");
				key = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				if (con != null)
					con.close();
			}

		} while (key == 1);

	}

}
