package jdbc.assignment;

public class StudentsInfo {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	int id;
	String name;
	String rollno;
	String phoneno;
	
	public StudentsInfo() {

	}

	public StudentsInfo(int id, String name, String rollno, String phoneno) {
		this.id = id;
		this.name = name;
		this.rollno = rollno;
		this.phoneno = phoneno;
	}
}
