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

public class Payment extends JFrame {

	private JPanel contentPane;
	private JTextField ClientID;
	private JTextField OrderID;
	private JTable table;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	int resultSetInt;
	public String ClID = null;
	public String OrID = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
	public Payment() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 1000);
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
		
		JButton btnConfirmPayment = new JButton("Confirm Payment");
		btnConfirmPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ClID = ClientID.getText();
					OrID = OrderID.getText();
					int selRow = table.getSelectedRow();
	                int selOrID = (int)table.getValueAt(selRow, 1);
	                Object[] possibilities = {"Debit", "Credit", "Cash"};
	                String PayType = (String)JOptionPane.showInputDialog(null, "Select method of payment","Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, possibilities, null);
	                LocalDate dateNow = LocalDate.now(); //can be altered later on to add in a date selection menu
	                
					connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
					
					statement = connection.createStatement();
					
					resultSetInt = statement.executeUpdate("Update Orders Set Payment_Type = '" + PayType + "', Paid_Date = '" + dateNow + "', Order_Status = 'Paid' Where Order_ID = '" + selOrID + "';");
					resultSet = statement.executeQuery("Select * From dbo.Orders Where Client_ID like '%" + ClID + "%' and Order_ID like '%" + OrID + "%';");
					
					table.setModel(DbUtils.resultSetToTableModel(resultSet));
					table.setRowHeight(30);
				}
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(null, "User does not have access to this table");
				}
			}
		});
		
		JLabel lblCompanyName = new JLabel("Order ID");
		
		OrderID = new JTextField();
		OrderID.setFont(new Font("Tahoma", Font.PLAIN, 24));
		OrderID.setColumns(10);
		
		
		btnConfirmPayment.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
						.addComponent(btnSearchOrders, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirmPayment, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
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
							.addComponent(ClientID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCompanyName, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(OrderID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearchOrders, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(89)
							.addComponent(btnConfirmPayment, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(565))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 833, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 24));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		table.setRowHeight(1, 30);
	}
}
