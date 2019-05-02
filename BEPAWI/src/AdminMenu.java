import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	public static final String DATABASE_URL = "jdbc:sqlserver://bepawidatabase.csnb6xcefqki.us-east-1.rds.amazonaws.com;database=BEPAWI";
	public static String UserName = null;
	public static String Password = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	 * @throws IOException 
	 */
	public AdminMenu()  {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 32));
		label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
		label.setBackground(Color.BLUE);
		label.setIcon(new ImageIcon(AdminMenu.class.getResource("/images/BEPAWI LOGO.PNG")));
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inventory inv = new Inventory();
				inv.setVisible(true);
			}
		});
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnInvoices = new JButton("Invoices");
		btnInvoices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Invoices invo = new Invoices();
				invo.setVisible(true);
			}
		});
		btnInvoices.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnOrders = new JButton("Orders");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderForm orfo = new OrderForm();
				orfo.setVisible(true);
			}
		});
		btnOrders.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clients cli = new Clients();
				cli.setVisible(true);
			}
		});
		btnClients.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnMngInv = new JButton("Inv. Mng. ");
        btnMngInv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                InventoryManagementPage invmp = new InventoryManagementPage();
                invmp.setVisible(true);
            }
        });
        btnMngInv.setFont(new Font("Tahoma", Font.PLAIN, 24));
        
        JButton btnReports = new JButton("Reports");
        btnReports.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Reports rpt = new Reports();
        		rpt.setVisible(true);
        	}
        });
        btnReports.setFont(new Font("Tahoma", Font.PLAIN, 24));
        
        JButton btnShipping = new JButton("Shipping");
        btnShipping.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Shipping spg = new Shipping();
        		spg.setVisible(true);
        	}
        });
        btnShipping.setFont(new Font("Tahoma", Font.PLAIN, 24));
        
        JButton btnPayment = new JButton("Payment");
        btnPayment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Payment pyt = new Payment();
        		pyt.setVisible(true);
        	}
        });
        btnPayment.setFont(new Font("Tahoma", Font.PLAIN, 24));
        
        JButton btnUsers = new JButton("Users");
        btnUsers.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Users urs = new Users();
        		urs.setVisible(true);
        	}
        });
        btnUsers.setFont(new Font("Tahoma", Font.PLAIN, 24));
        
        JButton btnLocations = new JButton("Locations");
        btnLocations.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Locations loc = new Locations();
        		loc.setVisible(true);
        	}
        });
        btnLocations.setFont(new Font("Tahoma", Font.PLAIN, 24));
       
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addComponent(label, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(57)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        					.addComponent(btnShipping, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnMngInv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(btnOrders, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
        					.addComponent(btnInventory, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(btnUsers, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnLocations, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnClients, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnReports, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnInvoices, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
        			.addGap(65))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
        			.addGap(44)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnInventory, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnInvoices, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
        			.addGap(43)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnOrders, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnClients, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
        			.addGap(36)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnMngInv, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnReports, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        			.addGap(33)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnShipping, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnPayment, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        			.addGap(32)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(btnUsers, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnLocations, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        			.addGap(64))
        );
        gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {btnInventory, btnInvoices, btnOrders, btnClients, btnMngInv, btnReports, btnShipping, btnPayment});
        gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {btnInventory, btnInvoices, btnOrders, btnClients, btnMngInv, btnReports, btnShipping, btnPayment});
        contentPane.setLayout(gl_contentPane);
    }
}