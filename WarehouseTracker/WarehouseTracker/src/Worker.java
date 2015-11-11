import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Worker extends Employee {

	private String section;
	private String startTime;
	private String endTime;
	public static List<Worker> workers = new ArrayList<Worker>();

	public Worker(String user, String pass, String section) {
		super(user, pass);
		this.section = section;
		startTime = getCurrentTime();
		workers.add(this);
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection() {
		return section;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void logout() {
		endTime = getCurrentTime();
	}

	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date logTime = new Date();
		return dateFormat.format(logTime);
	}
}
