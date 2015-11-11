import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {

	private String section;
	private int barcode;
	private int floor;
	private int start;
	private int end;
	private double price;
	private int stock;
	public static boolean[][][] cells = new boolean[26][1000][4];

	public Product(String section, int barcode, int floor, int start, int end, double price) {
		this.section = section;
		this.barcode = barcode;
		this.floor = floor;
		this.start = start;
		this.end = end;
		this.price = price;
		stock = 200;

		for (int i = start; i < end; i++) {
			cells[section.charAt(0) - 65][i][floor] = true;
		}
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}
	
	public void setStock(int value) {
		this.stock = value;
	}
	
	public String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date logTime = new Date();
		return dateFormat.format(logTime);
	}
}
