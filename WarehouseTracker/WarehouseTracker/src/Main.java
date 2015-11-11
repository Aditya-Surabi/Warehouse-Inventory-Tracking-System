import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Main {

	static HashMap<String, Product[]> items = new HashMap<String, Product[]>();

	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {
		initialize();
		Login.initiate();
		new TrackerGUI().main(null);
	}

	public static void initialize() {
		try {
			Scanner sc = new Scanner(new File("Database.txt"));

			while (sc.hasNextLine()) {
				Product[] products = new Product[20];

				for (int i = 0; i < products.length; i++) {
					String[] split = sc.nextLine().split(" ");
					Product product = new Product(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]),
							Integer.parseInt(split[3]), Integer.parseInt(split[4]), Double.parseDouble(split[5]));
					products[i] = product;
				}
				items.put(products[0].getSection(), products);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Product findProduct(String section, int barcode, boolean remove) {
		Product[] p = items.get(section);
		Product ret = null;
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (p[i].getBarcode() == barcode
						&& Product.cells[section.charAt(0) - 65][p[i].getStart()][p[i].getFloor()] == true) {
					ret = p[i];
					if (remove) {
						Product.cells[section.charAt(0) - 65][p[i].getStart()][p[i].getFloor()] = false;
						p[i].setStock(p[i].getStock() - 1);
						if (p[i].getStock() > 0)
							p[i].setStart(p[i].getStart() + 1);
					}
					break;
				}
			}
		}
		return ret;
	}

	public static Product findProduct(int barcode, boolean remove) {
		Product ret = null;
		for (String section : items.keySet()) {
			Product[] p = items.get(section);
			for (int i = 0; i < p.length; i++) {
				for (int j = 0; j < 4; j++) {
					if (p[i].getBarcode() == barcode
							&& Product.cells[section.charAt(0) - 65][p[i].getStart()][p[i].getFloor()] == true) {
						ret = p[i];
						if (remove) {
							Product.cells[section.charAt(0) - 65][p[i].getStart()][p[i].getFloor()] = false;
							p[i].setStock(p[i].getStock() - 1);
							if (p[i].getStock() > 0)
								p[i].setStart(p[i].getStart() + 1);
						}
						break;
					}
				}
			}
		}
		return ret;
	}

	public class TrackerGUI extends javax.swing.JFrame {

		/**
		 * Creates new form TrackerGUI
		 */
		public TrackerGUI() {
			initComponents();
		}

		/**
		 * This method is called from within the constructor to initialize the
		 * form. WARNING: Do NOT modify this code. The content of this method is
		 * always regenerated by the Form Editor.
		 */
		@SuppressWarnings("unchecked")
		// <editor-fold defaultstate="collapsed" desc="Generated Code">
		private void initComponents() {

			mainPanel = new javax.swing.JPanel();
			loginPanel = new javax.swing.JPanel();
			jLabel1 = new javax.swing.JLabel();
			userField = new javax.swing.JTextField();
			jLabel2 = new javax.swing.JLabel();
			passField = new javax.swing.JPasswordField();
			jLabel3 = new javax.swing.JLabel();
			sectionField = new javax.swing.JTextField();
			login = new javax.swing.JButton();
			managerPanel = new javax.swing.JPanel();
			logoutManager = new javax.swing.JButton();
			jScrollPane1 = new javax.swing.JScrollPane();
			jTextArea1 = new javax.swing.JTextArea();
			openLog = new javax.swing.JButton();
			resetLog = new javax.swing.JButton();
			currBarcode = new javax.swing.JTextField();
			jLabel4 = new javax.swing.JLabel();
			jLabel5 = new javax.swing.JLabel();
			newBarcode = new javax.swing.JTextField();
			jLabel6 = new javax.swing.JLabel();
			newPrice = new javax.swing.JTextField();
			confirm = new javax.swing.JButton();
			workerPanel = new javax.swing.JPanel();
			jLabel7 = new javax.swing.JLabel();
			itemSearch = new javax.swing.JTextField();
			jLabel8 = new javax.swing.JLabel();
			jLabel9 = new javax.swing.JLabel();
			jLabel10 = new javax.swing.JLabel();
			findItem = new javax.swing.JButton();
			withdrawItem = new javax.swing.JButton();
			logoutWorker = new javax.swing.JButton();
			jLabel11 = new javax.swing.JLabel();

			setTitle("Warehouse Tracker");
			setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = screenSize.height;
			int width = screenSize.width;
			setSize(width / 2, height / 2);
			setLocationRelativeTo(null);
			setResizable(false);

			mainPanel.setLayout(new java.awt.CardLayout());

			jLabel1.setText("Enter username");

			jLabel2.setText("Enter password");

			jLabel3.setText("Enter section (workers only)");

			login.setText("Login");
			login.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					loginActionPerformed(evt);
				}
			});

			javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
			loginPanel.setLayout(loginPanelLayout);
			loginPanelLayout.setHorizontalGroup(loginPanelLayout
					.createParallelGroup(
							javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(
							loginPanelLayout.createSequentialGroup().addGap(241, 241, 241)
									.addGroup(
											loginPanelLayout
													.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
															false)
													.addComponent(jLabel3)
													.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(userField).addComponent(sectionField).addComponent(passField)
									.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
											javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap(153, Short.MAX_VALUE))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
							loginPanelLayout.createSequentialGroup()
									.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(login).addContainerGap()));
			loginPanelLayout.setVerticalGroup(loginPanelLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(loginPanelLayout.createSequentialGroup().addGap(91, 91, 91).addComponent(jLabel1)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18).addComponent(jLabel2)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(18, 18, 18).addComponent(jLabel3)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(sectionField, javax.swing.GroupLayout.PREFERRED_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
							.addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));

			mainPanel.add(loginPanel, "loginCard");

			logoutManager.setText("Logout");
			logoutManager.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					logoutManagerActionPerformed(evt);
				}
			});

			jTextArea1.setColumns(20);
			jTextArea1.setRows(5);
			jScrollPane1.setViewportView(jTextArea1);

			openLog.setText("Open log");
			openLog.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					openLogActionPerformed(evt);
				}
			});

			resetLog.setText("Reset");
			resetLog.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					resetLogActionPerformed(evt);
				}
			});

			jLabel4.setText("Current barcode");

			jLabel5.setText("New barcode");

			jLabel6.setText("New price");

			confirm.setText("Confirm");
			confirm.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					confirmActionPerformed(evt);
				}
			});

			javax.swing.GroupLayout managerPanelLayout = new javax.swing.GroupLayout(managerPanel);
			managerPanel.setLayout(managerPanelLayout);
			managerPanelLayout.setHorizontalGroup(managerPanelLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(managerPanelLayout.createSequentialGroup().addContainerGap()
							.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGroup(managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									.addGroup(managerPanelLayout.createSequentialGroup().addPreferredGap(
											javax.swing.LayoutStyle.ComponentPlacement.RELATED,
											javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(logoutManager))
									.addGroup(managerPanelLayout.createSequentialGroup().addPreferredGap(
											javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
											.addGroup(managerPanelLayout
													.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
													.addComponent(openLog, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
															javax.swing.GroupLayout.PREFERRED_SIZE)
													.addComponent(resetLog, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
															javax.swing.GroupLayout.PREFERRED_SIZE))
											.addGap(28, 28, 28)
											.addGroup(managerPanelLayout
													.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
													.addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 125,
															javax.swing.GroupLayout.PREFERRED_SIZE)
													.addGroup(managerPanelLayout
															.createParallelGroup(
																	javax.swing.GroupLayout.Alignment.LEADING, false)
															.addComponent(currBarcode,
																	javax.swing.GroupLayout.DEFAULT_SIZE, 125,
																	Short.MAX_VALUE)
															.addComponent(jLabel4).addComponent(jLabel5)
															.addComponent(newBarcode).addComponent(jLabel6)
															.addComponent(newPrice)))
											.addGap(0, 56, Short.MAX_VALUE)))
							.addContainerGap()));
			managerPanelLayout.setVerticalGroup(managerPanelLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									.addComponent(jScrollPane1)
									.addGroup(managerPanelLayout.createSequentialGroup().addGroup(managerPanelLayout
											.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
											.addGroup(managerPanelLayout.createSequentialGroup().addComponent(jLabel4)
													.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(currBarcode, javax.swing.GroupLayout.PREFERRED_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.PREFERRED_SIZE))
											.addComponent(openLog, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
													javax.swing.GroupLayout.PREFERRED_SIZE))
											.addGap(52, 52, 52).addComponent(jLabel5)
											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(newBarcode, javax.swing.GroupLayout.PREFERRED_SIZE,
													javax.swing.GroupLayout.DEFAULT_SIZE,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
											.addComponent(jLabel6)
											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
											.addComponent(newPrice, javax.swing.GroupLayout.PREFERRED_SIZE,
													javax.swing.GroupLayout.DEFAULT_SIZE,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGroup(managerPanelLayout
													.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
													.addGroup(managerPanelLayout.createSequentialGroup()
															.addGap(18, 18, Short.MAX_VALUE)
															.addComponent(confirm,
																	javax.swing.GroupLayout.PREFERRED_SIZE, 50,
																	javax.swing.GroupLayout.PREFERRED_SIZE)
															.addGap(0, 33, Short.MAX_VALUE))
													.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
															managerPanelLayout.createSequentialGroup()
																	.addPreferredGap(
																			javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																			javax.swing.GroupLayout.DEFAULT_SIZE,
																			Short.MAX_VALUE)
																	.addComponent(resetLog,
																			javax.swing.GroupLayout.PREFERRED_SIZE, 45,
																			javax.swing.GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(logoutManager, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));

			mainPanel.add(managerPanel, "managerCard");

			jLabel7.setText("Enter barcode");

			jLabel8.setText("Location: ");

			jLabel9.setText("Stock:");

			jLabel10.setText("Price:");

			findItem.setText("Find Item");
			findItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					findItemActionPerformed(evt);
				}
			});

			withdrawItem.setText("Withdraw");
			withdrawItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					withdrawItemActionPerformed(evt);
				}
			});

			logoutWorker.setText("Logout");
			logoutWorker.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					logoutWorkerActionPerformed(evt);
				}
			});

			jLabel11.setText("Item withdraw time:");

			javax.swing.GroupLayout workerPanelLayout = new javax.swing.GroupLayout(workerPanel);
			workerPanel.setLayout(workerPanelLayout);
			workerPanelLayout.setHorizontalGroup(workerPanelLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(workerPanelLayout.createSequentialGroup().addContainerGap().addGroup(workerPanelLayout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
									workerPanelLayout.createSequentialGroup().addGap(0, 0,
											Short.MAX_VALUE).addComponent(logoutWorker))
							.addGroup(workerPanelLayout.createSequentialGroup().addGroup(workerPanelLayout
									.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
									.addGroup(workerPanelLayout
											.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
											.addGroup(workerPanelLayout
													.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
															false)
													.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
															javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(itemSearch))
											.addComponent(findItem, javax.swing.GroupLayout.Alignment.TRAILING))
									.addComponent(withdrawItem).addComponent(jLabel11)
									.addGroup(workerPanelLayout
											.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
											.addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING,
													javax.swing.GroupLayout.DEFAULT_SIZE,
													javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING,
													javax.swing.GroupLayout.DEFAULT_SIZE,
													javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING,
													javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)))
									.addGap(0, 418, Short.MAX_VALUE)))
							.addContainerGap()));
			workerPanelLayout.setVerticalGroup(workerPanelLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(workerPanelLayout.createSequentialGroup().addContainerGap().addComponent(jLabel7)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(itemSearch, javax.swing.GroupLayout.PREFERRED_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel8)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel9)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel10)
							.addGap(18, 18, 18).addComponent(findItem)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(withdrawItem)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel11)
							.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
							.addComponent(logoutWorker, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addContainerGap()));

			mainPanel.add(workerPanel, "workerCard");

			javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
			getContentPane().setLayout(layout);
			layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE));
			layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
					mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE));

			pack();
		}// </editor-fold>

		Worker curr;

		private void loginActionPerformed(java.awt.event.ActionEvent evt) {
			CardLayout card = (CardLayout) mainPanel.getLayout();
			String user = userField.getText();
			String pass = passField.getText();
			String section = sectionField.getText().trim();
			if (Login.isValid(user, pass) == 2) {
				if (Pattern.matches("[a-zA-Z]", section)) {
					curr = new Worker(user, pass, section);
					card.show(mainPanel, "workerCard");
				} else {
					JOptionPane.showMessageDialog(null, "Invalid section");
				}
			} else if (Login.isValid(user, pass) == 1) {
				card.show(mainPanel, "managerCard");
			} else {
				JOptionPane.showMessageDialog(null, "Invalid username/password.");
			}
		}

		private void logoutManagerActionPerformed(java.awt.event.ActionEvent evt) {
			CardLayout card = (CardLayout) mainPanel.getLayout();
			card.show(mainPanel, "loginCard");
			userField.setText("");
			passField.setText("");
			sectionField.setText("");
		}

		private void openLogActionPerformed(java.awt.event.ActionEvent evt) {
			jTextArea1.setText(Manager.log());
		}

		private void resetLogActionPerformed(java.awt.event.ActionEvent evt) {
			jTextArea1.setText("");
		}

		private void logoutWorkerActionPerformed(java.awt.event.ActionEvent evt) {
			CardLayout card = (CardLayout) mainPanel.getLayout();
			card.show(mainPanel, "loginCard");
			curr.logout();
			userField.setText("");
			passField.setText("");
			sectionField.setText("");
		}

		private void findItemActionPerformed(java.awt.event.ActionEvent evt) {
			Product p = findProduct(sectionField.getText(), Integer.parseInt(itemSearch.getText().trim()), false);

			if (p == null)
				JOptionPane.showMessageDialog(null, "Item not found in section " + sectionField.getText());
			else {
				jLabel8.setText("Location: " + p.getSection() + "-" + (p.getStart() + 1) + "-" + (p.getFloor() + 1));
				jLabel9.setText("Stock: " + p.getStock());
				jLabel10.setText("Price: " + p.getPrice());
			}
		}

		private void withdrawItemActionPerformed(java.awt.event.ActionEvent evt) {
			Product p = findProduct(sectionField.getText(), Integer.parseInt(itemSearch.getText().trim()), true);

			if (p == null)
				JOptionPane.showMessageDialog(null, "Item not found in section " + sectionField.getText());
			else {
				jLabel8.setText("Location: " + p.getSection() + "-" + (p.getStart() + 1) + "-" + (p.getFloor() + 1));
				jLabel9.setText("Stock: " + p.getStock());
				jLabel10.setText("Price: " + p.getPrice());
				jLabel11.setText("Time withdrawn: " + p.getCurrentTime());
			}
		}

		private void confirmActionPerformed(java.awt.event.ActionEvent evt) {
			int oldCode = Integer.parseInt(currBarcode.getText().trim());
			int newCode = Integer.parseInt(newBarcode.getText().trim());
			double newVal = Double.parseDouble(newPrice.getText().trim());
			Product p = findProduct(oldCode, false);
			if (p != null && newCode > 0 && newVal > 0) {
				p.setBarcode(newCode);
				p.setPrice(newVal);
				JOptionPane.showMessageDialog(null, "New barcode and value set");
			} else {
				JOptionPane.showMessageDialog(null, "Input invalid");
			}
		}

		/**
		 * @param args
		 *            the command line arguments
		 */
		public void main(String args[]) {
			/* Set the Nimbus look and feel */
			// <editor-fold defaultstate="collapsed" desc=" Look and feel
			// setting code (optional) ">
			/*
			 * If Nimbus (introduced in Java SE 6) is not available, stay with
			 * the default look and feel. For details see
			 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/
			 * plaf.html
			 */
			try {
				for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (ClassNotFoundException ex) {
				java.util.logging.Logger.getLogger(TrackerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			} catch (InstantiationException ex) {
				java.util.logging.Logger.getLogger(TrackerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			} catch (IllegalAccessException ex) {
				java.util.logging.Logger.getLogger(TrackerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			} catch (javax.swing.UnsupportedLookAndFeelException ex) {
				java.util.logging.Logger.getLogger(TrackerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
						ex);
			}
			// </editor-fold>

			/* Create and display the form */
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new TrackerGUI().setVisible(true);
				}
			});
		}

		// Variables declaration - do not modify
		private javax.swing.JButton confirm;
		private javax.swing.JTextField currBarcode;
		private javax.swing.JButton findItem;
		private javax.swing.JTextField itemSearch;
		private javax.swing.JLabel jLabel1;
		private javax.swing.JLabel jLabel10;
		private javax.swing.JLabel jLabel11;
		private javax.swing.JLabel jLabel2;
		private javax.swing.JLabel jLabel3;
		private javax.swing.JLabel jLabel4;
		private javax.swing.JLabel jLabel5;
		private javax.swing.JLabel jLabel6;
		private javax.swing.JLabel jLabel7;
		private javax.swing.JLabel jLabel8;
		private javax.swing.JLabel jLabel9;
		private javax.swing.JScrollPane jScrollPane1;
		private javax.swing.JTextArea jTextArea1;
		private javax.swing.JButton login;
		private javax.swing.JPanel loginPanel;
		private javax.swing.JButton logoutManager;
		private javax.swing.JButton logoutWorker;
		private javax.swing.JPanel mainPanel;
		private javax.swing.JPanel managerPanel;
		private javax.swing.JTextField newBarcode;
		private javax.swing.JTextField newPrice;
		private javax.swing.JButton openLog;
		private javax.swing.JPasswordField passField;
		private javax.swing.JButton resetLog;
		private javax.swing.JTextField sectionField;
		private javax.swing.JTextField userField;
		private javax.swing.JButton withdrawItem;
		private javax.swing.JPanel workerPanel;
		// End of variables declaration
	}
}