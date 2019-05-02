import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

public class Shipping extends JFrame {

	private JPanel contentPane;
	private JTextField ClientID;
	private JTextField OrderID;
	private JTextField ShippingID;
	private JTextField ShippingName;
	private JTextField Address;
	private JTextField City;
	private JTextField Region;
	private JTextField PostalCode;
	private JTable table;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int resultSetInt;
	public String ClID = null;
	public String OrID = null;
	public String ShID = null;
	public String Name = null;
	public String Adr = null;
	public String Cty = null;
	public String Regn = null;
	public String PostC = null;
	public String ShipMethod = null;
	public String TrackNum = null;
	private JTextField ShippingMethod;
	private JTextField TrackingNumber;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shipping frame = new Shipping();
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
	public Shipping() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 1100);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Clients.class.getResource("/images/BEPAWI LOGO.PNG")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblClientId = new JLabel("Client ID");
		lblClientId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		ClientID = new JTextField();
		ClientID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ClientID.setColumns(10);
		
		JButton btnSearchOrders = new JButton("Search Order Info");
		btnSearchOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClID = ClientID.getText();
					OrID = OrderID.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Orders Where Client_ID like '%" + ClID + "%' and Order_ID like '%" + OrID + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnSearchOrders.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnSearchShippingInfo = new JButton("Search Shipping Info");
		btnSearchShippingInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClID = ClientID.getText();
					OrID = OrderID.getText();
					ShID = ShippingID.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select orders.client_id, shipping_orders.Order_ID, shipping_orders.shipping_id, shipping_orders.shipped_date,"
					+ " shipping_orders.ship_name, shipping_orders.street_address, shipping_orders.city, shipping_orders.state_region, shipping_orders.postal_code, "
					+ "shipping_orders.shipping_method, shipping_orders.tracking_number from shipping_orders inner join orders on shipping_orders.Order_ID = orders.Order_ID"
					+ " where shipping_orders.Order_ID like '%" + OrID + "%' and Orders.Client_ID like '%" + ClID + "%' and Shipping_orders.Shipping_ID like '%" + ShID + "%';");
					
					table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
					table_1.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		
		JButton btnAddShipment = new JButton("Add Shipment");
		btnAddShipment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selRow = table.getSelectedRow();
	                int selOrID = (int)table.getValueAt(selRow, 1);
					if(selOrID > 0) { 
						LocalDate dateNow = LocalDate.now();
						Name = ShippingName.getText();
						Adr = Address.getText();
						Cty = City.getText();
						Regn = Region.getText();
						PostC = PostalCode.getText();
						ShipMethod = ShippingMethod.getText();
						TrackNum = TrackingNumber.getText();
						
						connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
						statement = connection.createStatement();
					
						resultSetInt = statement.executeUpdate("Insert into shipping_Orders(Order_ID, Shipped_date, Ship_Name, Street_Address, city, State_Region, postal_code, shipping_method, tracking_number)" + 
						"Values ('" + selOrID + "', '" + dateNow + "', '" + Name + "', '" + Adr + "', '" + Cty + "', '" + Regn + "', '" + PostC + "', '" + ShipMethod + "', '" + TrackNum + "');");
						
						resultSetInt = statement.executeUpdate("Update Orders Set Order_Status = 'Shipped' Where Order_ID = '" + selOrID + "';");
						
						resultSet = statement.executeQuery("Select * From dbo.Orders Where Client_ID like '%" + ClID + "%' and Order_ID like '%" + OrID + "%';");
						table.setModel(DbUtils.resultSetToTableModel(resultSet));
						table.setRowHeight(30);
						
						ClID = ClientID.getText();
						OrID = OrderID.getText();
						ShID = ShippingID.getText();
						resultSet = statement.executeQuery("Select orders.client_id, shipping_orders.Order_ID, shipping_orders.shipping_id, shipping_orders.shipped_date,"
						+ " shipping_orders.ship_name, shipping_orders.street_address, shipping_orders.city, shipping_orders.state_region, shipping_orders.postal_code, "
						+ "shipping_orders.shipping_method, shipping_orders.tracking_number from shipping_orders inner join orders on shipping_orders.Order_ID = orders.Order_ID"
						+ " where shipping_orders.Order_ID like '%" + OrID + "%' and Orders.Client_ID like '%" + ClID + "%' and Shipping_orders.Shipping_ID like '%" + ShID + "%';");
					
						table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
						table_1.setRowHeight(30);
					} else {
						JOptionPane.showMessageDialog(null, "Please select an Order from the upper table");
					}
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnAddShipment.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnUpdateShippingInfo = new JButton("Update Shipping Info");
		btnUpdateShippingInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int selRow = table_1.getSelectedRow();
	                int selShID = (int)table_1.getValueAt(selRow, 2);
					if(selShID > 0) { 
						Name = ShippingName.getText();
						Adr = Address.getText();
						Cty = City.getText();
						Regn = Region.getText();
						PostC = PostalCode.getText();
						ShipMethod = ShippingMethod.getText();
						TrackNum = TrackingNumber.getText();
					
						connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
						statement = connection.createStatement();
					
						if (ShippingID.getText().length() > 0) {
							if(ShippingName.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET Ship_Name = '" + Name + "' WHERE Shipping_ID = '" + ShID + "';");
							if(Address.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET Street_Address = '" + Adr + "' WHERE Shipping_ID = '" + ShID + "';");
							if(City.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET City = '" + Cty + "' WHERE Shipping_ID = '" + ShID + "';");
							if(Region.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET State_Region = '" + Regn + "' WHERE Shipping_ID = '" + ShID + "';");
							if(PostalCode.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET Postal_Code = '" + PostC + "' WHERE Shipping_ID = '" + ShID + "';");
							if(ShippingID.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET Shipping_Method = '" + ShipMethod + "' WHERE Shipping_ID = '" + ShID + "';");
							if(ShippingName.getText().length() > 0)
								resultSetInt = statement.executeUpdate("UPDATE Shipping_Orders SET Tracking_Number = '" + TrackNum + "' WHERE Shipping_ID = '" + ShID + "';");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Please select a Shipment from the lower table");
					}
					
					resultSet = statement.executeQuery("Select * From dbo.Orders Where Client_ID like '%" + ClID + "%' and Order_ID like '%" + OrID + "%';");
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
					
					ClID = ClientID.getText();
					OrID = OrderID.getText();
					ShID = ShippingID.getText();
					resultSet = statement.executeQuery("Select orders.client_id, shipping_orders.Order_ID, shipping_orders.shipping_id, shipping_orders.shipped_date,"
					+ " shipping_orders.ship_name, shipping_orders.street_address, shipping_orders.city, shipping_orders.state_region, shipping_orders.postal_code, "
					+ "shipping_orders.shipping_method, shipping_orders.tracking_number from shipping_orders inner join orders on shipping_orders.Order_ID = orders.Order_ID"
					+ " where shipping_orders.Order_ID like '%" + OrID + "%' and Orders.Client_ID like '%" + ClID + "%' and Shipping_orders.Shipping_ID like '%" + ShID + "%';");
					
					table_1.setModel(DbUtils.resultSetToTableModel(resultSet));
					table_1.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnUpdateShippingInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCompanyName = new JLabel("Order ID");
		
		OrderID = new JTextField();
		OrderID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		OrderID.setColumns(10);
		
		JLabel lblShippingID = new JLabel("Shipping ID");
		
		ShippingID = new JTextField();
		ShippingID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ShippingID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		
		ShippingName = new JTextField();
		ShippingName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ShippingName.setColumns(10);
		
		JLabel lblStreetAddress = new JLabel("Street Address");
		
		Address = new JTextField();
		Address.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Address.setColumns(10);
		
		JLabel lblCity = new JLabel("City");
		
		City = new JTextField();
		City.setFont(new Font("Tahoma", Font.PLAIN, 24));
		City.setColumns(10);
		
		JLabel lblStateOrRegion = new JLabel("State or Region");
		
		Region = new JTextField();
		Region.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Region.setColumns(10);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		
		PostalCode = new JTextField();
		PostalCode.setFont(new Font("Tahoma", Font.PLAIN, 24));
		PostalCode.setColumns(10);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClientID.setText("");
			    OrderID.setText("");
			    ShippingID.setText("");
			    ShippingName.setText("");
			    Address.setText("");
			    City.setText("");
			    Region.setText("");
			    PostalCode.setText("");
			    ShippingMethod.setText("");
			    TrackingNumber.setText("");
			}
		});
		btnClearForm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblShippingMethod = new JLabel("Shipping Method");
		
		ShippingMethod = new JTextField();
		ShippingMethod.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ShippingMethod.setColumns(10);
		
		TrackingNumber = new JTextField();
		TrackingNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
		TrackingNumber.setColumns(10);
		
		JLabel lblTrackingnumber = new JLabel("Tracking Number");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		
		btnSearchShippingInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClientId)
						.addComponent(ClientID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCompanyName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(OrderID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShippingID, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(ShippingID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(ShippingName, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStreetAddress, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Address, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(City, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateOrRegion, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Region, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPostalCode, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(PostalCode, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(ShippingMethod, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShippingMethod, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTrackingnumber, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(TrackingNumber, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(btnClearForm, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
									.addGap(29))))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnSearchShippingInfo, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnUpdateShippingInfo, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnSearchOrders, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
										.addGap(27)
										.addComponent(btnAddShipment, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblClientId, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ClientID, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCompanyName, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(OrderID, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblShippingID, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ShippingID, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ShippingName, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblStreetAddress, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Address, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(City, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblStateOrRegion, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Region, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblPostalCode, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PostalCode, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addGap(29))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(lblShippingMethod)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ShippingMethod, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchOrders, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddShipment, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTrackingnumber)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnClearForm, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdateShippingInfo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSearchShippingInfo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(TrackingNumber, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.setRowHeight(1, 30);
	}
}
