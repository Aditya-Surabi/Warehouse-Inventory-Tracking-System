import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {

	static HashMap<String, LoginInfo> logins = new HashMap<String, LoginInfo>();

	public static void initiate() {
		try {
			Scanner sc = new Scanner(new File("Logins.txt"));

			while (sc.hasNextLine()) {
				String name = sc.nextLine();
				String pass = sc.nextLine();
				int rank = Integer.parseInt(sc.nextLine());
				LoginInfo info = new LoginInfo(pass, rank);
				logins.put(name, info);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static int isValid(String user, String pass) {
		for (String key : logins.keySet()) {
			if (key.equals(user)) {
				if (logins.get(user).getPassword().equals(pass)) {
					return logins.get(user).getRank();
				}
			}
		}
		return 0;
	}

	public static class LoginInfo {
		private String password;
		private int rank;

		public LoginInfo(String password, int rank) {
			this.password = password;
			this.rank = rank;
		}

		public String getPassword() {
			return password;
		}

		public int getRank() {
			return rank;
		}
	}
}
