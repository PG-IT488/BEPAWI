import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import java.util.*;

public class OrderForm extends JFrame {

	private JPanel contentPane;
	private JTextField ClientID;
	private JTextField OrderID;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int resultSetInt;
	public String SearchClID = null;
	public String SearchOrID = null;
	private JTable table;
	public static String RequestClID = null;
	public static LocalDate dateNow;
	public static int Order_ID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderForm frame = new OrderForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(OrderForm.class.getResource("/images/BEPAWI LOGO.PNG")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		JButton btnViewAllOrders = new JButton("View All Orders");
		btnViewAllOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnViewAllOrders.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		
		ClientID = new JTextField();
		ClientID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ClientID.setColumns(10);
		
		JButton btnSearchClient = new JButton("Search Client ID");
		btnSearchClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchClID = ClientID.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Orders Where Client_ID like '%" + SearchClID + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
					
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnSearchClient.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		OrderID = new JTextField();
		OrderID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		OrderID.setColumns(10);
		
		JButton btnSearchOrder = new JButton("Search Order ID");
		btnSearchOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchOrID = OrderID.getText();
					
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
		btnSearchOrder.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnCreateNewOrder = new JButton("Create New Order");
		btnCreateNewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RequestClID = JOptionPane.showInputDialog(null, "Client ID requesting Order?");
				dateNow = LocalDate.now();
				try {
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSetInt = statement.executeUpdate("Insert Into Orders(Client_ID, Order_Date) Values( '" + RequestClID + "', '" + dateNow + "');");
					resultSet = statement.executeQuery("Select Order_ID from Orders Where Client_ID = '" + RequestClID + "' and Order_Date = '" + dateNow + "';");
					while(resultSet.next()) {
						Order_ID = resultSet.getInt("Order_ID");
					}
					OrderCreate OrCreate = new OrderCreate();
					OrCreate.setVisible(true);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table or Client ID is invalid. Confirm Client ID and try again");
				}
			}
		});
		btnCreateNewOrder.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnUpdateOrder = new JButton("Update Order");
		btnUpdateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selCartRow = table.getSelectedRow();
				int selOrderID = (int)table.getValueAt(selCartRow, 1);
				Order_ID = selOrderID;
				OrderCreate OrCreate = new OrderCreate();
				OrCreate.setVisible(true);
			}
		});
		btnUpdateOrder.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnViewAllOrders, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClientID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchClient, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(OrderID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchOrder, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreateNewOrder, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdateOrder, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnViewAllOrders, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(ClientID, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchClient, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(OrderID, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchOrder, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnUpdateOrder, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCreateNewOrder, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 827, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
