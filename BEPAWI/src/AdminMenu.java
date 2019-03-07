import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	public static final String DATABASE_URL = "jdbc:sqlserver://bepawidatabase.csnb6xcefqki.us-east-1.rds.amazonaws.com:1433;database=BEPAWI";
	public static String UserName = null;
	public static String Password = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	/**
	 * Create the frame.
	 */
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("BEPAWI");
		//label.setIcon(new ImageIcon(AdminMenu.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inventory inv = new Inventory();
				inv.setVisible(true);
			}
		});
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnInvoices = new JButton("Invoices");
		btnInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Invoices invo = new Invoices();
				invo.setVisible(true);
			}
		});
		btnInvoices.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderForm orfo = new OrderForm();
				orfo.setVisible(true);
			}
		});
		btnOrders.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clients cli = new Clients();
				cli.setVisible(true);
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInventory, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOrders, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnClients, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInvoices, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(80)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInventory, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnInvoices, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOrders, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClients, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(217, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
