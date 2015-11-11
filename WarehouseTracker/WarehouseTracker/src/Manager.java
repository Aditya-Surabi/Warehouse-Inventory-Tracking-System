import java.util.List;

public class Manager extends Employee {

	public Manager(String user, String pass) {
		super(user, pass);
	}

	public void setBarcode(Product p, int barcode) {
		p.setBarcode(barcode);
	}

	public void setPrice(Product p, double price) {
		p.setPrice(price);
	}

	public static String log() {
		String ret = "";
		List<Worker> workers = Worker.workers;
		for (int i = 0; i < workers.size(); i++) {
			Worker w = workers.get(i);
			ret += "ID: " + w.getName() + " - Section: " + w.getSection() + "\n";
			ret += "Start Time: " + w.getStartTime() + "\n";
			ret += "End Time: " + w.getEndTime() + "\n";
		}
		return ret;
	}
}
