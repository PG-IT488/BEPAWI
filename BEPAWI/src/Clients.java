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
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;

public class Clients extends JFrame {

	private JPanel contentPane;
	private JTextField ClientID;
	private JTextField FirstName;
	private JTextField LastName;
	private JTextField CompanyName;
	private JTextField Address;
	private JTextField City;
	private JTextField Region;
	private JTextField PostalCode;
	private JTextField Country;
	private JTextField PhoneNumber;
	private JTextField Email;
	private JTable table;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int resultSetInt;
	public String ClID = null;
	public String FName = null;
	public String LName = null;
	public String CompName = null;
	public String Adr = null;
	public String Cty = null;
	public String Regn = null;
	public String PostC = null;
	public String Ctry = null;
	public String PhNum = null;
	public String Ema = null;
	private final JScrollPane scrollPane_1 = new JScrollPane();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clients frame = new Clients();
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
	public Clients() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 1200);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("BEPAWI");
		//label.setIcon(new ImageIcon(Clients.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
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
		
		JButton btnSearchClientInfo = new JButton("Search Client Info");
		btnSearchClientInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClID = ClientID.getText();
					FName = FirstName.getText();
					LName = LastName.getText();
					CompName = CompanyName.getText();
					Adr = Address.getText();
					Cty = City.getText();
					Regn = Region.getText();
					PostC = PostalCode.getText();
					Ctry = Country.getText();
					PhNum = PhoneNumber.getText();
					Ema = Email.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Select * From dbo.Client Where Client_ID like '%" + ClID + "%' and First_Name like '%" + FName 
					+ "%' and Last_Name like '%" + LName + "%' and Company_Name like '%" + CompName + "%' and Street_Address like '%" + Adr + "%' and City like '%" 
					+ Cty + "%' and Client_Zip like '%" + PostC + "%' and Country like '%" + Ctry + "%' and Phone_Number like '%" + PhNum + "%' and Email like '%" + Ema + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnSearchClientInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClID = ClientID.getText();
					FName = FirstName.getText();
					LName = LastName.getText();
					CompName = CompanyName.getText();
					Adr = Address.getText();
					Cty = City.getText();
					Regn = Region.getText();
					PostC = PostalCode.getText();
					Ctry = Country.getText();
					PhNum = PhoneNumber.getText();
					Ema = Email.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSet = statement.executeQuery("Insert into client(First_Name, Last_Name, Company_Name, Street_Address, city, State_Region, Client_Zip, Country, Phone_Number, Email)" + 
							"Values ('" + FName + "', '" + LName + "', '" + CompName + "', '" + Adr + "', '" + Cty + "', '" + Regn + "', '" + PostC + "', '" + Ctry + "', '" + PhNum + "','" + Ema + "');");
					
					resultSet = statement.executeQuery("Select * From dbo.Client;"); 
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnAddClient.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnUpdateClientInfo = new JButton("Update Client Info");
		btnUpdateClientInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ClID = ClientID.getText();
					FName = FirstName.getText();
					LName = LastName.getText();
					CompName = CompanyName.getText();
					Adr = Address.getText();
					Cty = City.getText();
					Regn = Region.getText();
					PostC = PostalCode.getText();
					Ctry = Country.getText();
					PhNum = PhoneNumber.getText();
					Ema = Email.getText();
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					if (ClientID.getText().length() > 0) {
						if(FirstName.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET First_Name = '" + FName + "' WHERE Client_ID = '" + ClID + "';");
						if(LastName.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Last_Name = '" + LName + "' WHERE Client_ID = '" + ClID + "';");
						if(CompanyName.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Company_Name = '" + CompName + "' WHERE Client_ID = '" + ClID + "';");
						if(Address.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Street_Address = '" + Adr + "' WHERE Client_ID = '" + ClID + "';");
						if(City.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET City = '" + Cty + "' WHERE Client_ID = '" + ClID + "';");
						if(Region.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET State_Region = '" + Regn + "' WHERE Client_ID = '" + ClID + "';");
						if(PostalCode.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Client_Zip = '" + PostC + "' WHERE Client_ID = '" + ClID + "';");
						if(Country.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Country = '" + Ctry + "' WHERE Client_ID = '" + ClID + "';");
						if(PhoneNumber.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Phone_Number = '" + PhNum + "' WHERE Client_ID = '" + ClID + "';");
						if(Email.getText().length() > 0)
							resultSetInt = statement.executeUpdate("UPDATE Client SET Email = '" + Ema + "' WHERE Client_ID = '" + ClID + "';");
					}
					resultSet = statement.executeQuery("Select * from Client Where Client_ID = '" + ClID + "';");
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		btnUpdateClientInfo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel lblCompanyName = new JLabel("First Name");
		
		FirstName = new JTextField();
		FirstName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		FirstName.setColumns(10);
		
		JLabel lblContactName = new JLabel("Last Name");
		
		LastName = new JTextField();
		LastName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		LastName.setColumns(10);
		
		JLabel lblContactTitle = new JLabel("Company Name");
		
		CompanyName = new JTextField();
		CompanyName.setFont(new Font("Tahoma", Font.PLAIN, 24));
		CompanyName.setColumns(10);
		
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
		
		JLabel lblFaxNumber = new JLabel("Email Address");
		
		Country = new JTextField();
		Country.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Country.setColumns(10);
		
		PhoneNumber = new JTextField();
		PhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 24));
		PhoneNumber.setColumns(10);
		
		Email = new JTextField();
		Email.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Email.setColumns(10);
		
			
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			    ClientID.setText(" ");
			    FirstName.setText(" ");
			    LastName.setText(" ");
			    CompanyName.setText(" ");
			    Address.setText(" ");
			    City.setText(" ");
			    Region.setText(" ");
			    PostalCode.setText(" ");
			    Country.setText(" ");
			    PhoneNumber.setText(" ");
			    Email.setText(" ");
			   }
			  });
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 24));
		 	
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(label, GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblClientId)
						.addComponent(ClientID, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblCompanyName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(FirstName, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblContactName, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(LastName, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblContactTitle, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(CompanyName, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblStreetAddress, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Address, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(City, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblStateOrRegion, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Region, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(lblPostalCode, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(PostalCode, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearchClientInfo, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCountry, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(Country, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpdateClientInfo, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(PhoneNumber, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addGap(90)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Email, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
						.addComponent(lblFaxNumber, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddClient, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(81))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblClientId, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ClientID, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCompanyName, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(FirstName, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblContactName, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(LastName, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblContactTitle, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(CompanyName, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblStreetAddress, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Address, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblCity, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(City, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblStateOrRegion, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Region, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblPostalCode, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PostalCode, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
							.addGap(1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
							.addGap(9)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCountry, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
						.addComponent(lblPhoneNumber)
						.addComponent(lblFaxNumber))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Country, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(PhoneNumber, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(9))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(Email, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
							.addGap(9)))
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSearchClientInfo, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
						.addComponent(btnAddClient, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
						.addComponent(btnUpdateClientInfo, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		  		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane.setViewportView(table);
		scrollPane.setColumnHeaderView(scrollPane_1);
		contentPane.setLayout(gl_contentPane);
		table.setRowHeight(1, 30);
	}
}
