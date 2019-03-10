import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class InventoryManagementPage extends JFrame {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	private JPanel contentPane;
	public static final String DATABASE_URL = "jdbc:sqlserver://bepawidatabase.csnb6xcefqki.us-east-1.rds.amazonaws.com:1433;database=BEPAWI";
	public static String UserName = null;
	public static String Password = null;
	
	
	private JTextField UpdatePcodeText;
	private JTextField AddPcodeText;
	private JTextField UpdatePnameText;
	private JTextField UpdtPdescText;
	private JTextField AddPnameText;
	private JTextField AddPdescText;
	private JTextField UpdatePtypeText;
	private JTextField AddPtypeText;
	private JTextField UpdtPpackquantityText;
	private JTextField AddPpackquantityText;
	private JTextField UpdtPpriceText;
	private JTextField AddPpriceText;
	
	

	public PreparedStatement pst = null;
	public ResultSet rs = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagementPage frame = new InventoryManagementPage();
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
	public InventoryManagementPage() {
		setType(Type.POPUP);
		setTitle("Inventory Management Page");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1027, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("BEPAWI");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		
		UpdatePcodeText = new JTextField();
		UpdatePcodeText.setColumns(10);
		
		JLabel lblUpdateProductCode = new JLabel("Code:");
		lblUpdateProductCode.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblUpdateProduct = new JLabel("Update Product");
		lblUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblUpdateAddNewItem = new JLabel("Add New Item");
		lblUpdateAddNewItem.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		AddPcodeText = new JTextField();
		AddPcodeText.setColumns(10);
		
		JLabel lblAddProductCode = new JLabel("Code:");
		lblAddProductCode.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblUpdateProductName = new JLabel("Name:");
		lblUpdateProductName.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		UpdatePnameText = new JTextField();
		UpdatePnameText.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnUpdatePrdct = new JButton("Enter");
		btnUpdatePrdct.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnAddNewPrdct = new JButton("Enter");
		btnAddNewPrdct.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblProductDescription = new JLabel("Description:");
		lblProductDescription.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		UpdtPdescText = new JTextField();
		UpdtPdescText.setColumns(10);
		
		AddPnameText = new JTextField();
		AddPnameText.setColumns(10);
		
		AddPdescText = new JTextField();
		AddPdescText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Description:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Picture:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_3 = new JLabel("Picture:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_4 = new JLabel("Type:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		UpdatePtypeText = new JTextField();
		UpdatePtypeText.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Type:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		AddPtypeText = new JTextField();
		AddPtypeText.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Pack Qnty:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_7 = new JLabel("Pack Qnty:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblNewLabel_8 = new JLabel("Price:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		UpdtPpackquantityText = new JTextField();
		UpdtPpackquantityText.setColumns(10);
		
		AddPpackquantityText = new JTextField();
		AddPpackquantityText.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Price:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		UpdtPpriceText = new JTextField();
		UpdtPpriceText.setColumns(10);
		
		AddPpriceText = new JTextField();
		AddPpriceText.setColumns(10);
		
		JButton btnViewInvt = new JButton("View Inventory");
		btnViewInvt.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		JComboBox LocationcomboBox = new JComboBox();
		 {
			 
			 
			 
			 String sql ="Select Location_Name FROM Product_location";
			 
			 try 
			 {
				 connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
				 Statement s= connection.prepareStatement(sql);
				 resultSet = rs=s.executeQuery(sql);
				 
				 while(rs.next())
				{
					 String name=rs.getString(1);
					 LocationcomboBox.addItem(name);
				}
				 
				 //return LocationcomboBox;
			 }
			 catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
		 }
		 
		 /*LocationcomboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					//String sql="select * from Product_Location where Location_Name=?";
					
					
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					statement = connection.createStatement();
					resultSet = statement.executeQuery("select * from Product_Location"); 
					
									
					while (resultSet.next()) {
						String name=resultSet.getString("Location_Name");
						LocationcomboBox.addItem(resultSet.getString("name"));
					}
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
			
			public void updateCombo() {
				String sql = "select * from Product_Location";
				try {
					pst = connection.prepareStatement (sql);
					rs = pst.executeQuery();
					while (rs.next()) {
						LocationcomboBox.addItem(rs.getString("Location_Name"));
					}
					}
					catch (Exception e) {
						
				}
			}
			
		}); */
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(AddPcodeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblAddProductCode, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
									.addGap(21)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(AddPnameText, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(AddPdescText)
										.addComponent(lblNewLabel_1)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblUpdateAddNewItem, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(UpdatePcodeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblUpdateProductName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
												.addComponent(UpdatePnameText, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblProductDescription)
										.addComponent(UpdtPdescText, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblUpdateProductCode, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(UpdatePtypeText)
									.addComponent(lblNewLabel_5)
									.addComponent(AddPtypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_4))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(AddPpackquantityText, 132, 132, 132)
									.addComponent(UpdtPpackquantityText, Alignment.TRAILING, 132, 132, 132)
									.addComponent(lblNewLabel_7))
								.addComponent(lblNewLabel_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_9)
								.addComponent(btnUpdatePrdct, 0, 0, Short.MAX_VALUE)
								.addComponent(UpdtPpriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(1)
									.addComponent(AddPpriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnAddNewPrdct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(lblUpdateProduct, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLocation)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(LocationcomboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnViewInvt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLocation)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(LocationcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(btnViewInvt, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblUpdateProduct, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpdateProductCode)
						.addComponent(lblUpdateProductName, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProductDescription, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_8))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(UpdatePcodeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UpdatePnameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UpdtPdescText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UpdatePtypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UpdtPpackquantityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UpdtPpriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addComponent(lblUpdateAddNewItem, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnUpdatePrdct, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddProductCode, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_9)
						.addComponent(lblNewLabel_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(AddPcodeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddPnameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddPdescText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddPtypeText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddPpriceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddPpackquantityText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddNewPrdct, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
