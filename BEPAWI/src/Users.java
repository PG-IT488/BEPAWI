import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class Users extends JFrame {
   
   
   
    Connection connection = null;
    Statement statement = null;
    //ResultSet resultSet = null;
    int resultSetInt;
   

    private JTable table;
   
    private JPanel contentPane;
    private JTextField UsernametextField;
    private JTextField PasswordtextField;
    private JCheckBox chckbxProducts;
   
    public static final String DATABASE_URL = "jdbc:sqlserver://bepawidatabase.csnb6xcefqki.us-east-1.rds.amazonaws.com;database=BEPAWI";
   
    public String username = null;
    public String password = null;
   

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Users frame = new Users();
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
    public Users() {

        setTitle("Add a User");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 650);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       
        JPanel panel = new JPanel();
        panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 378, 594);
        contentPane.add(panel);
        panel.setLayout(null);
       
        UsernametextField = new JTextField();
        UsernametextField.setBounds(10, 206, 115, 25);
        panel.add(UsernametextField);
        UsernametextField.setColumns(10);
       
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUsername.setBounds(10, 166, 115, 25);
        panel.add(lblUsername);
       
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPassword.setBounds(10, 250, 115, 25);
        panel.add(lblPassword);
       
        PasswordtextField = new JTextField();
        PasswordtextField.setBounds(10, 290, 115, 25);
        panel.add(PasswordtextField);
        PasswordtextField.setColumns(10);
       
        JLabel lblUser = new JLabel("Add New User:");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblUser.setBounds(10, 117, 166, 25);
        panel.add(lblUser);
       
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 153, 378, 2);
        panel.add(separator);
       
       
        JCheckBox chckbxProducts = new JCheckBox("Products Table");
        chckbxProducts.setBounds(10, 386, 115, 23);
        panel.add(chckbxProducts);
       
        JCheckBox chckbxClients = new JCheckBox("Clients Table");
        chckbxClients.setBounds(10, 416, 115, 23);
        panel.add(chckbxClients);
       
        JCheckBox chckbxOrders = new JCheckBox("Orders Table");
        chckbxOrders.setBounds(10, 446, 115, 23);
        panel.add(chckbxOrders);
       
        JCheckBox chckbxInvoices = new JCheckBox("Invoices Table");
        chckbxInvoices.setBounds(10, 476, 115, 23);
        panel.add(chckbxInvoices);
       
        JLabel lblNewLabel = new JLabel("Table Access:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNewLabel.setBounds(10, 341, 166, 25);
        panel.add(lblNewLabel);
       
        JCheckBox chckbxShipping = new JCheckBox("Shipping Orders Table");
        chckbxShipping.setBounds(140, 386, 160, 23);
        panel.add(chckbxShipping);
       
        JCheckBox chckbxLocations = new JCheckBox("Locations Table");
        chckbxLocations.setBounds(140, 416, 160, 23);
        panel.add(chckbxLocations);
       
        JButton btnNewButton = new JButton("Enter");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               
                try {
           
                   
                    connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                    statement = connection.createStatement();
           
                    String username = "";
                    String password = "";
                    username = UsernametextField.getText().trim();
                    password = PasswordtextField.getText().trim();
                   
                   
                   
                    if (username.equals("")|| password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null," name or password is wrong","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                       
                       
                       
                        resultSetInt = statement.executeUpdate("CREATE LOGIN "+username+" WITH PASSWORD = '"+password+"'");
                        resultSetInt = statement.executeUpdate("IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'"+username+"') CREATE USER ["+username+"] FOR LOGIN ["+username+"] EXEC sp_addrolemember N'db_owner', N'"+username+"'");
                                               
                        //("CREATE LOGIN NewAdminName WITH PASSWORD = 'ABCD' GO USE BEPAWI GO IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'NewAdminName') BEGIN CREATE USER [NewAdminName] FOR LOGIN [NewAdminName]  EXEC sp_addrolemember N'db_owner', N'NewAdminName' END;) VALUES('"+username+"', '"+password+"');");
                   
                        String SMessage = "Record added for "+username;
                        
                       
                                           // create dialog ox which is print message
                            JOptionPane.showMessageDialog(null,SMessage,"Message",JOptionPane.PLAIN_MESSAGE);
                        //close connection
                           
                    }
           
                    //chckbxProducts.setSelected(true);
                   
                    //if(chckbxProducts != null)           
                    if (chckbxProducts.isSelected())
                    {
                        //System.out.println("Is selected");
                        resultSetInt = statement.executeUpdate("GRANT INSERT, SELECT ON dbo.Products TO ["+username+"]");
                        }
                    else {
                        //System.out.println("Is not Selected");
                        resultSetInt = statement.executeUpdate("DENY INSERT, SELECT ON dbo.Products TO ["+username+"]");
                    }
                   
                    if (chckbxClients.isSelected())
                    {
                        //System.out.println("Is selected");
                        resultSetInt = statement.executeUpdate("GRANT INSERT, SELECT ON dbo.Client TO ["+username+"]");
                        }
                    else {
                        //System.out.println("Is not Selected");
                        resultSetInt = statement.executeUpdate("DENY INSERT, SELECT ON dbo.Client TO ["+username+"]");
                    }
                   
                    if (chckbxOrders.isSelected())
                    {
                        //System.out.println("Is selected");
                        resultSetInt = statement.executeUpdate("GRANT INSERT, SELECT ON dbo.orders TO ["+username+"]");
                        }
                    else {
                        //System.out.println("Is not Selected");
                        resultSetInt = statement.executeUpdate("DENY INSERT, SELECT ON dbo.orders TO ["+username+"]");
                    }
                   
                    if (chckbxInvoices.isSelected())
                    {
                        //System.out.println("Is selected");
                        resultSetInt = statement.executeUpdate("GRANT INSERT, SELECT ON dbo.Invoice TO ["+username+"]");
                        }
                    else {
                        //System.out.println("Is not Selected");
                        resultSetInt = statement.executeUpdate("DENY INSERT, SELECT ON dbo.Invoice TO ["+username+"]");
                    }
                   
                    if (chckbxShipping.isSelected())
                    {
                        //System.out.println("Is selected");
                        resultSetInt = statement.executeUpdate("GRANT INSERT, SELECT ON dbo.shipping_orders TO ["+username+"]");
                        }
                    else {
                        //System.out.println("Is not Selected");
                        resultSetInt = statement.executeUpdate("DENY INSERT, SELECT ON dbo.shipping_orders TO ["+username+"]");
                    }
                   
                    if (chckbxLocations.isSelected())
                    {
                        //System.out.println("Is selected");
                        resultSetInt = statement.executeUpdate("GRANT INSERT, SELECT ON dbo.Locations TO ["+username+"]");
                        }
                    else {
                        //System.out.println("Is not Selected");
                        resultSetInt = statement.executeUpdate("DENY INSERT, SELECT ON dbo.Locations TO ["+username+"]");
                    }
                    dispose();

                    //((java.sql.Connection)connection).close();
                    }
                
               
                catch (SQLException se)
                {
                    //handle errors for JDBC
                	JOptionPane.showMessageDialog(null, "User does not have permission to perform this action.");
                    se.printStackTrace();
                }
                catch (Exception a) //catch block
                {
                    a.printStackTrace();
                }
                   
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(207, 526, 106, 33);
        panel.add(btnNewButton);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(Users.class.getResource("/images/BEPAWI LOGO.PNG")));
        label.setBounds(0, 0, 378, 111);
        panel.add(label);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Tahoma", Font.BOLD, 32));
        label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
        label.setBackground(Color.BLUE);
       
       
       
           
    }
}