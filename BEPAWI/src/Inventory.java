import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Inventory extends JFrame {

	private JPanel contentPane;

	public String SearchName = null;
	public String SearchPrice = null;
	public String SearchType = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory frame = new Inventory();
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
	private JTextField SearchNameField;
	private JTextField SearchPriceField;
	private JTextField SearchTypeField;
	/**
	 * Create the frame.
	 */
	public Inventory() {
		setTitle("Inventory");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("BEPAWI");
		//label.setIcon(new ImageIcon(Inventory.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnViewAllInventory = new JButton("View All Inventory");
		btnViewAllInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Products;");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
					
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnViewAllInventory.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane.setViewportView(table);
		
		
		SearchNameField = new JTextField();
		SearchNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SearchNameField.setColumns(10);
		
		JButton btnSearchName = new JButton("Search Product Name");
		btnSearchName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnSearchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					SearchName = SearchNameField.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Products Where Product_Name like '%" + SearchName + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
					
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		
		SearchPriceField = new JTextField();
		SearchPriceField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SearchPriceField.setColumns(10);
		
		JButton btnSearchPrice = new JButton("Search by Price");
		btnSearchPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchPrice = SearchPriceField.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Products Where Product_Price like '%" + SearchPrice + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
					
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
				
			}
		});
		btnSearchPrice.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		SearchTypeField = new JTextField();
		SearchTypeField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		SearchTypeField.setColumns(10);
		
		JButton btnSearchType = new JButton("Search by Type");
		btnSearchType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchType = SearchTypeField.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Products Where Product_Type like '%" + SearchType + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
					
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnSearchType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(SearchNameField, 263, 263, 263)
							.addComponent(btnViewAllInventory, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSearchName)
						.addComponent(SearchPriceField, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchPrice, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(SearchTypeField, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchType, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnViewAllInventory, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SearchNameField, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchName, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SearchPriceField, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearchPrice, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(SearchTypeField, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearchType, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(411))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE))
					.addGap(16))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
