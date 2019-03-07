import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Invoices extends JFrame {

	private JPanel contentPane;
	private JTextField searchClientID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoices frame = new Invoices();
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
	private JTable table;
	public String SearchClID = null;
	public String SearchOrID = null;
	private JTextField searchOrderID;
	/**
	 * Create the frame.
	 */
	public Invoices() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("BEPAWI");
		//label.setIcon(new ImageIcon(Invoices.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		JButton btnViewAllInvoices = new JButton("View All Invoices");
		btnViewAllInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Orders;");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnViewAllInvoices.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		searchClientID = new JTextField();
		searchClientID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchClientID.setColumns(10);
		
		JButton btnSearchClientId = new JButton("Search Client ID");
		btnSearchClientId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SearchClID = searchClientID.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Orders Where Order_ID like '%" + SearchClID + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}

			}
		});
		btnSearchClientId.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		
		searchOrderID = new JTextField();
		searchOrderID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		searchOrderID.setColumns(10);
		
		JButton btnSearchOrderId = new JButton("Search Order ID");
		btnSearchOrderId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SearchOrID = searchOrderID.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Orders Where Order_ID like '%" + SearchOrID + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnSearchOrderId.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnViewAllInvoices, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(searchClientID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearchClientId, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(searchOrderID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearchOrderId, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE))
					.addGap(0))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnViewAllInvoices, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(searchClientID, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchClientId, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(searchOrderID, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchOrderId, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
