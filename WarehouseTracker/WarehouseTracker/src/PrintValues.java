import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class PrintValues {
	public static void main(String[] args) {
		try {
			PrintWriter pw = new PrintWriter(new File("Database.txt"));
			Random r = new Random();
			for (int i = 'A'; i <= 'Z'; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 1000; k += 200) {
						pw.println((char) i + " " + (r.nextInt(10000000) + 100000000) + " " + j + " " + k + " "
								+ (k + 199) + " " + (r.nextInt(100) + 10) + ".99");
					}
				}
			}
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
