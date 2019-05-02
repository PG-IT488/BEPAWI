import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UserNameField;
	private JPasswordField passwordField;

	public static final String DATABASE_URL = "jdbc:sqlserver://bepawidatabase.csnb6xcefqki.us-east-1.rds.amazonaws.com;database=BEPAWI";
	public String UserName = null;
	public String Password = null;
	public int fails = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setMaximumSize(new Dimension(600, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel lblBepawi = new JLabel("");
		lblBepawi.setIcon(new ImageIcon(Login.class.getResource("/images/BEPAWI LOGO.PNG")));
		lblBepawi.setBorder(new MatteBorder(0, 0, 4, 0, (Color) Color.BLUE));
		lblBepawi.setHorizontalAlignment(SwingConstants.CENTER);
		lblBepawi.setForeground(new Color(0, 0, 0));
		lblBepawi.setBackground(Color.BLUE);
		lblBepawi.setFont(new Font("Tahoma", Font.BOLD, 32));
		
		UserNameField = new JTextField();
		UserNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		UserNameField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		passwordField = new JPasswordField();
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserName = UserNameField.getText();
					Password = passwordField.getText();
					AdminMenu.UserName = UserNameField.getText();
					AdminMenu.Password = passwordField.getText();
					if(UserName.length()< 20 && Password.length() < 20){
						connection = DriverManager.getConnection(DATABASE_URL, UserName, Password);
						AdminMenu aMenu = new AdminMenu();
						aMenu.setVisible(true);
						dispose();
					}else {
						if(UserName.length()> 20)
							JOptionPane.showMessageDialog(null, "Username input exceeds character limits");
						if(Password.length()> 20)
							JOptionPane.showMessageDialog(null, "Password input exceeds character limits");
					}
					
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid User Name and/or Password");
					fails ++;
					JOptionPane.showMessageDialog(null, "You have " + fails + " failed attempts. You have " + (3-fails) + " attempts remaining.");
	        		if (fails >2) {
	        			JOptionPane.showMessageDialog(null, "System will now close, goodbye");
	        			System.exit(0);
	        		}	
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null, "Do you really want to exit?","Exit", JOptionPane.YES_NO_OPTION);
				if(i==0) {
					System.exit(0);
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(lblBepawi, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(lblUserName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(384))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(UserNameField, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addGap(85))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
					.addGap(384))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(87)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
					.addGap(85))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblBepawi, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(119)
					.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(UserNameField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addGap(16)
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addGap(130))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
