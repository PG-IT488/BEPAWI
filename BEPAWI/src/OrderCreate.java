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
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class OrderCreate extends JFrame {

	private JPanel contentPane;
	private JTextField SearchPName;
	private JTextField SearchPType;
	private JTable table;
	private JTable table_1;

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int resultSetInt;
	public String SearchName = null;
	public String SearchType = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderCreate frame = new OrderCreate();
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
	public OrderCreate() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(OrderCreate.class.getResource("/images/BEPAWI LOGO.PNG")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		JButton btnViewAllProducts = new JButton("View All Products");
		btnViewAllProducts.addActionListener(new ActionListener() {
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
		btnViewAllProducts.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JScrollPane scrollPane = new JScrollPane();
		
		SearchPName = new JTextField();
		SearchPName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SearchPName.setColumns(10);
		
		JButton btnSearchProductName = new JButton("Search Product Name");
		btnSearchProductName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchName = SearchPName.getText();
					
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
		btnSearchProductName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		SearchPType = new JTextField();
		SearchPType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		SearchPType.setColumns(10);
		
		JButton btnSearchProductType = new JButton("Search Product Type");
		btnSearchProductType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SearchType = SearchPType.getText();
					
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
		btnSearchProductType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double OrderSum = 0;
				int OrderQuant = 0;
				try {
					resultSet = statement.executeQuery("Select Sum(Product_sum) as OrderSum from Invoice Where Order_ID  = '" + OrderForm.Order_ID + "';");
					int index = 1;
					while (resultSet.next())
					{
					   double OrderSum1 = resultSet.getDouble(index);
					   System.out.println(OrderSum1);
					   OrderSum = OrderSum1;
					}
					resultSet = statement.executeQuery("Select Sum(Product_Quantity) as OrderQuant from Invoice Where Order_ID  = '" + OrderForm.Order_ID + "';");
					while (resultSet.next())
					{
					   int OrderSum2 = resultSet.getInt(index);
					   System.out.println(OrderSum2);
					   OrderQuant = OrderSum2;
					}
					resultSetInt = statement.executeUpdate("Update Orders Set Order_Total = '" + OrderSum + "', Order_Quantity = '" + OrderQuant + "', "
					+ "Order_Status = 'Built' Where Order_ID = '" + OrderForm.Order_ID + "';");
					JOptionPane.showMessageDialog(null, "Quantity Total = " + OrderQuant);
					JOptionPane.showMessageDialog(null, "Order Price Total = " + OrderSum);
					JOptionPane.showMessageDialog(null, "Closing order creation window now");
					dispose();
				}
				catch(SQLException sqlException){
					JOptionPane.showMessageDialog(null, "User does not have access to this function");
				}
			}
		});
		btnSubmitOrder.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnAddSelectedProduct = new JButton("Add Product to Cart");
		btnAddSelectedProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selRow = table.getSelectedRow();
	                int selProID = (int)table.getValueAt(selRow, 0);
	                BigDecimal selProPrice = (BigDecimal)table.getValueAt(selRow, 6);
	                int selProQuantParse = Integer.parseInt(JOptionPane.showInputDialog(null, "Amount of product requested?"));
	                BigDecimal selProQuant = new BigDecimal(selProQuantParse);
	                BigDecimal selProSum = selProPrice.multiply(selProQuant);
	                Object[] possibilities = {"Location_1", "Location_2"};
	                String selProLoc = (String)JOptionPane.showInputDialog(null, "Select location of purchase","Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, null);
	                
	                resultSetInt = statement.executeUpdate("Insert into Invoice(Order_ID, Product_Code, Product_Quantity, Price_at_sale, Product_sum, Product_Location_Code) "
	                + "Values('" + OrderForm.Order_ID + "', '" + selProID + "', '" + selProQuant + "', '" + selProPrice + "', '" + selProSum + "', '" + selProLoc + "');");
	                
	                resultSetInt = statement.executeUpdate("Update Products Set " + selProLoc + "_Stock = " + selProLoc + "_Stock - " + selProQuant + " Where Product_Code = '" + selProID + "';");
	                
					resultSet = statement.executeQuery("Select Invoice.Invoice_code, Invoice.Product_code, Products.product_name, invoice.Product_Quantity, "
					+ "invoice.Price_at_sale, invoice.Product_Sum, invoice.Product_Location_Code From Invoice Inner Join Products on invoice.Product_Code = "
					+ "products.Product_Code Where Invoice.Order_ID = '" + OrderForm.Order_ID + "';");
					table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
					table_1.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnAddSelectedProduct.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnRemoveProductIn = new JButton("Remove Item in Cart");
		btnRemoveProductIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selCartRow = table_1.getSelectedRow();
					int selInvoID = (int)table_1.getValueAt(selCartRow, 0);
					int selProID = (int)table_1.getValueAt(selCartRow, 1);
					BigDecimal selProQuant = (BigDecimal)table_1.getValueAt(selCartRow, 3);
					String selProLoc = (String)table_1.getValueAt(selCartRow, 6);
					
					resultSetInt = statement.executeUpdate("Update Products Set " + selProLoc + "_Stock = " + selProLoc + "_Stock + " + selProQuant + " Where Product_Code = '" + selProID + "';");
					resultSetInt = statement.executeUpdate("Delete from Invoice Where Invoice_code = '" + selInvoID + "';");
					
					resultSet = statement.executeQuery("Select Invoice.Invoice_code, Invoice.Product_code, Products.product_name, invoice.Product_Quantity, "
					+ "invoice.Price_at_sale, invoice.Product_Sum, invoice.Product_Location_Code From Invoice Inner Join Products on invoice.Product_Code = "
					+ "products.Product_Code Where Invoice.Order_ID = '" + OrderForm.Order_ID + "';");
					table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
					table_1.setRowHeight(30);
				} 
				catch (SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this function");
				}
			}
		});
		btnRemoveProductIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnHoldOrder = new JButton("Hold Order");
		btnHoldOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					resultSetInt = statement.executeUpdate("Update Orders Set Order_Status = 'Pending' Where Order_ID = '" + OrderForm.Order_ID + "';");
					JOptionPane.showMessageDialog(null, "Closing order creation window now, use Update Order to continue building order");
					JOptionPane.showMessageDialog(null, "The Order ID number for this order is " + OrderForm.Order_ID);
					dispose();
				}
				catch (SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this function");
				}
			}
		});
		btnHoldOrder.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnViewAllProducts, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(SearchPName, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchProductName, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(SearchPType, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchProductType, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddSelectedProduct, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSubmitOrder, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRemoveProductIn, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnHoldOrder, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnViewAllProducts, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(SearchPName, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchProductName, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(SearchPType, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchProductType, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(btnAddSelectedProduct, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(9))
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
					.addGap(0)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRemoveProductIn, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnHoldOrder, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSubmitOrder, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE))
					.addGap(31))
		);
		
		table_1 = 
				new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
