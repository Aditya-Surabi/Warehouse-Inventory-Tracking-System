public class Employee {

	private String user;
	private String pass;

	// Constructor
	public Employee(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getName() {
		return user;
	}

	public String getPass() {
		return pass;
	}
}
