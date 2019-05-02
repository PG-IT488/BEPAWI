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

public class Locations extends JFrame {

	private JPanel contentPane;
	private JTextField LocationID;
	private JTextField LocationName;
	private JTextField Address;
	private JTextField City;
	private JTextField Region;
	private JTextField PostalCode;
	private JTextField Country;
	private JTextField PhoneNumber;
	private JTextField ManagerName;
	private JTable table;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int resultSetInt;
	public String LocID = null;
	public String LocName = null;
	public String Adr = null;
	public String Cty = null;
	public String Regn = null;
	public String PostC = null;
	public String Ctry = null;
	public String PhNum = null;
	public String ManageName = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Locations frame = new Locations();
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
	public Locations() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 1200);
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
		
		JLabel lblClientId = new JLabel("Location ID");
		lblClientId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		LocationID = new JTextField();
		LocationID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		LocationID.setColumns(10);
		
		JButton btnSearchLocationInfo = new JButton("Search Location Info");
		btnSearchLocationInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LocID = LocationID.getText();
					LocName = LocationName.getText();
					Adr = Address.getText();
					Cty = City.getText();
					Regn = Region.getText();
					PostC = PostalCode.getText();
					Ctry = Country.getText();
					PhNum = PhoneNumber.getText();
					ManageName = ManagerName.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Locations Where Location_Code like '%" + LocID + "%' and Location_Name like '%" + LocName 
					+ "%' and Address like '%" + Adr + "%' and City like '%" + Cty + "%' and Postal_code like '%" + PostC + "%' and Country like '%" + Ctry + 
					"%' and Phone_Number like '%" + PhNum + "%' and Manager_Name like '%" + ManageName + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnSearchLocationInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnAddLocation = new JButton("Add Location");
		btnAddLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LocID = LocationID.getText();
					LocName = LocationName.getText();
					Adr = Address.getText();
					Cty = City.getText();
					Regn = Region.getText();
					PostC = PostalCode.getText();
					Ctry = Country.getText();
					PhNum = PhoneNumber.getText();
					ManageName = ManagerName.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSetInt = statement.executeUpdate("Insert into Locations(Location_Name, Address, city, State_Region, Postal_code, Country, Phone_Number, Manager_Name)" + 
					"Values ('" + LocName + "', '" + Adr + "', '" + Cty + "', '" + Regn + "', '" + PostC + "', '" + Ctry + "', '" + PhNum + "','" + ManageName + "');");
					
					resultSet = statement.executeQuery("Select * From dbo.Locations;"); 
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnAddLocation.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnUpdateLocationInfo = new JButton("Update Location Info");
		btnUpdateLocationInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LocID = LocationID.getText();
					LocName = LocationName.getText();
					Adr = Address.getText();
					Cty = City.getText();
					Regn = Region.getText();
					PostC = PostalCode.getText();
					Ctry = Country.getText();
					PhNum = PhoneNumber.getText();
					ManageName = ManagerName.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					if (LocationID.getText().length() > 0) {
						if(LocationName.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET Location_Name = '" + LocName + "' WHERE Location_Code = '" + LocID + "';");
						if(Address.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET Address = '" + Adr + "' WHERE Location_Code = '" + LocID + "';");
						if(City.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET City = '" + Cty + "' WHERE Location_Code = '" + LocID + "';");
						if(Region.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET State_Region = '" + Regn + "' WHERE Location_Code = '" + LocID + "';");
						if(PostalCode.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET Client_Zip = '" + PostC + "' WHERE Location_Code = '" + LocID + "';");
						if(Country.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET Country = '" + Ctry + "' WHERE Location_Code = '" + LocID + "';");
						if(PhoneNumber.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET Phone_Number = '" + PhNum + "' WHERE Location_Code = '" + LocID + "';");
						if(ManagerName.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Locations SET Manager_Name = '" + ManageName + "' WHERE Location_Code = '" + LocID + "';");
					}
					resultSet = statement.executeQuery("Select * from Locations Where Location_Code = '" + LocID + "';");
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnUpdateLocationInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCompanyName = new JLabel("Location Name");
		
		LocationName = new JTextField();
		LocationName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		LocationName.setColumns(10);
		
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
		
		JLabel lblCountry = new JLabel("Country");
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		
		JLabel lblManagerName = new JLabel("Manager Name");
		
		Country = new JTextField();
		Country.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Country.setColumns(10);
		
		PhoneNumber = new JTextField();
		PhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
		PhoneNumber.setColumns(10);
		
		ManagerName = new JTextField();
		ManagerName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		ManagerName.setColumns(10);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocationID.setText("");
			    LocationName.setText("");
			    Address.setText("");
			    City.setText("");
			    Region.setText("");
			    PostalCode.setText("");
			    Country.setText("");
			    PhoneNumber.setText("");
			    ManagerName.setText("");
			}
		});
		btnClearForm.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClientId)
						.addComponent(LocationID, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCompanyName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(LocationName, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStreetAddress, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Address, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(City, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStateOrRegion, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Region, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPostalCode, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(PostalCode, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCountry, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Country, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblManagerName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(ManagerName, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnSearchLocationInfo, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnUpdateLocationInfo, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddLocation, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnClearForm, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(PhoneNumber, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
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
							.addComponent(LocationID, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCompanyName, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LocationName, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblStreetAddress, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Address, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(City, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblStateOrRegion, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Region, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblPostalCode, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PostalCode, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCountry, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Country, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblManagerName)
							.addGap(8)
							.addComponent(ManagerName, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblPhoneNumber)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PhoneNumber, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSearchLocationInfo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUpdateLocationInfo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddLocation, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClearForm, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.setRowHeight(1, 30);
	}
}
