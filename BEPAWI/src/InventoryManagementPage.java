import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

public class InventoryManagementPage extends JFrame {
  
    private JPanel contentPane;
  
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    private JTable table1;
    private JTable table2;
    private JTable table1a;
    private JTable table2a;
  
    private JTextField UpdatePcodeText;
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
    private JTextField UpdtStocklevel1Text;
    private JTextField AddStocklevel1Text;
    private JTextField UpdtStocklevel2Text;
    private JTextField AddStocklevel2Text;
    private JTextField UpdtAmntStocklevelText;
    private JTextField AddAmntStocklevelText;
   
   
    int resultSetInt;
    public int UpdatePCode;
    public String UpdatePname = null;
    public String UpdtPdesc = null;
    public String UpdatePtype = null;
    public String UpdtPpackquantity = null;
    public BigDecimal UpdtPprice;
    public int UpdtStocklevel1;
    public int UpdtStocklevel2;
    public int UpdtAmntStocklevel;
    
    public String AddPname = null;
    public String AddPdesc = null;
    public String AddPtype = null;
    public String AddPpackquantity = null;
    public BigDecimal AddPprice;
    public int AddStocklevel1;
    public int AddStocklevel2;
    public int AddAmntStocklevel;
  
    String[] messageStrings = {"Select Location","Location 1", "Location 2"};
    JComboBox comboBox = new JComboBox(messageStrings);
    JLabel lblText = new JLabel();
   
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
        setBounds(100, 100, 1371, 1004);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLUE));
        setContentPane(contentPane);
      
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(InventoryManagementPage.class.getResource("/images/BEPAWI LOGO.PNG")));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Tahoma", Font.BOLD, 32));
        label.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(0, 0, 255)));
        label.setBackground(Color.WHITE);
        getContentPane().add(lblText);
       
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
       
        JPanel panel_2 = new JPanel();
       
        JPanel panel_3 = new JPanel();
        panel_3.setLayout(null);
       
         JLabel lblNewLabel_7 = new JLabel("Pack Qnty:");
         lblNewLabel_7.setBounds(556, 53, 109, 25);
         panel_3.add(lblNewLabel_7);
         lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 20));
        
          AddPpackquantityText = new JTextField();
          AddPpackquantityText.setBounds(556, 89, 132, 20);
          panel_3.add(AddPpackquantityText);
          AddPpackquantityText.setColumns(10);
         
           JLabel lblNewLabel_9 = new JLabel("Price:");
           lblNewLabel_9.setBounds(708, 53, 58, 25);
           panel_3.add(lblNewLabel_9);
           lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
          
            AddPpriceText = new JTextField();
            AddPpriceText.setBounds(707, 89, 86, 20);
            panel_3.add(AddPpriceText);
            AddPpriceText.setColumns(10);
           
             JLabel lblUpdateAddNewItem = new JLabel("Add New Item");
             lblUpdateAddNewItem.setBounds(10, 11, 201, 29);
             panel_3.add(lblUpdateAddNewItem);
             lblUpdateAddNewItem.setFont(new Font("Tahoma", Font.BOLD, 24));
              
                JLabel lblNewLabel = new JLabel("Name:");
                lblNewLabel.setBounds(98, 53, 65, 25);
                panel_3.add(lblNewLabel);
                lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
               
                 AddPnameText = new JTextField();
                 AddPnameText.setBounds(106, 91, 97, 20);
                 panel_3.add(AddPnameText);
                 AddPnameText.setColumns(10);
                
                  JLabel lblNewLabel_1 = new JLabel("Description:");
                  lblNewLabel_1.setBounds(215, 53, 122, 25);
                  panel_3.add(lblNewLabel_1);
                  lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
                 
                   AddPdescText = new JTextField();
                   AddPdescText.setBounds(215, 91, 122, 20);
                   panel_3.add(AddPdescText);
                   AddPdescText.setColumns(10);
                  
                    JLabel lblNewLabel_3 = new JLabel("Picture:");
                    lblNewLabel_3.setBounds(347, 53, 79, 25);
                    panel_3.add(lblNewLabel_3);
                    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
                   
                     JLabel lblNewLabel_5 = new JLabel("Type:");
                     lblNewLabel_5.setBounds(450, 53, 56, 25);
                     panel_3.add(lblNewLabel_5);
                     lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
                    
                      AddPtypeText = new JTextField();
                      AddPtypeText.setBounds(450, 91, 86, 20);
                      panel_3.add(AddPtypeText);
                      AddPtypeText.setColumns(10);
                     
                      JLabel lblStockLct1add = new JLabel("Stock Lct1");
                      lblStockLct1add.setBounds(806, 53, 111, 25);
                      panel_3.add(lblStockLct1add);
                      lblStockLct1add.setFont(new Font("Tahoma", Font.BOLD, 20));
                     
                      AddStocklevel1Text = new JTextField();
                      AddStocklevel1Text.setBounds(806, 89, 100, 20);
                      panel_3.add(AddStocklevel1Text);
                      AddStocklevel1Text.setColumns(10);
                     
                      JLabel lblStockLct2add = new JLabel("Stock Lct2");
                      lblStockLct2add.setBounds(927, 52, 111, 27);
                      panel_3.add(lblStockLct2add);
                      lblStockLct2add.setFont(new Font("Tahoma", Font.BOLD, 20));
                     
                      AddStocklevel2Text = new JTextField();
                      AddStocklevel2Text.setBounds(927, 89, 100, 20);
                      panel_3.add(AddStocklevel2Text);
                      AddStocklevel2Text.setColumns(10);
                     
                      JLabel lblStockLeveladd = new JLabel("Stock Level");
                      lblStockLeveladd.setBounds(1048, 52, 125, 27);
                      panel_3.add(lblStockLeveladd);
                      lblStockLeveladd.setFont(new Font("Tahoma", Font.BOLD, 20));
                     
                      AddAmntStocklevelText = new JTextField();
                      AddAmntStocklevelText.setBounds(1048, 89, 100, 20);
                      panel_3.add(AddAmntStocklevelText);
                      AddAmntStocklevelText.setColumns(10);
                     
                       JButton btnAddNewPrdct = new JButton("Add");
                       btnAddNewPrdct.setBounds(1183, 77, 109, 37);
                       panel_3.add(btnAddNewPrdct);
                       btnAddNewPrdct.addActionListener(new ActionListener() {
                           public void actionPerformed(ActionEvent arg0) {
                               try {
                                  
                                   
                                   AddPname = AddPnameText.getText();
                                   AddPdesc = AddPdescText.getText();
                                   AddPtype = AddPtypeText.getText();
                                   AddPpackquantity = AddPpackquantityText.getText();
                                   AddPprice = new BigDecimal (AddPpriceText.getText());
                                   AddStocklevel1 = Integer.parseInt(AddStocklevel1Text.getText());
                                   AddStocklevel2 = Integer.parseInt(AddStocklevel2Text.getText());
                                   AddAmntStocklevel = Integer.parseInt(AddAmntStocklevelText.getText());
                                  
                                   
                    connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                   
                    statement = connection.createStatement();
                   
                    resultSetInt = statement.executeUpdate("Insert into products(product_name, Product_Description, Product_Type, "
                            + "Packaging_Quantity, Product_Price, Location_1_Stock, Location_2_Stock, Stock_Level)" +
                    "Values ('" + AddPname + "', '" + AddPdesc + "', '" + AddPtype + "', '" + AddPpackquantity + "', '" + AddPprice + "', '" + AddStocklevel1 + "'"
                            + ", '" + AddStocklevel2 + "', '" + AddAmntStocklevel + "');");
                   
                    resultSet = statement.executeQuery("Select * From dbo.Products;");
                   
                    table1.setModel(DbUtils.resultSetToTableModel(resultSet));
                    table1.setRowHeight(30);
                    table2.setModel(DbUtils.resultSetToTableModel(resultSet));
                    table2.setRowHeight(30);
                }
                catch(SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "User does not have access to this table");
                }
                              
                           }
                       });
                       btnAddNewPrdct.setFont(new Font("Tahoma", Font.BOLD, 20));
       
           

                   
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addComponent(label, GroupLayout.DEFAULT_SIZE, 1484, Short.MAX_VALUE)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 1322, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(152, Short.MAX_VALUE))
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1322, GroupLayout.PREFERRED_SIZE)
                        .addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 1322, Short.MAX_VALUE))
                    .addGap(152))
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(label)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)
                    .addGap(29)
                    .addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        panel_2.setLayout(null);
       
         JLabel lblUpdateProduct = new JLabel("Update Product");
         lblUpdateProduct.setBounds(10, 11, 201, 29);
         panel_2.add(lblUpdateProduct);
         lblUpdateProduct.setFont(new Font("Tahoma", Font.BOLD, 24));
        
          JLabel lblUpdateProductCode = new JLabel("Code:");
          lblUpdateProductCode.setBounds(10, 51, 70, 25);
          panel_2.add(lblUpdateProductCode);
          lblUpdateProductCode.setFont(new Font("Tahoma", Font.BOLD, 20));
         
           UpdatePcodeText = new JTextField();
           UpdatePcodeText.setBounds(10, 87, 86, 20);
           panel_2.add(UpdatePcodeText);
           UpdatePcodeText.setColumns(10);
          
            JLabel lblUpdateProductName = new JLabel("Name:");
            lblUpdateProductName.setBounds(106, 51, 80, 27);
            panel_2.add(lblUpdateProductName);
            lblUpdateProductName.setFont(new Font("Tahoma", Font.BOLD, 20));
           
             UpdatePnameText = new JTextField();
             UpdatePnameText.setBounds(106, 87, 97, 20);
             panel_2.add(UpdatePnameText);
             UpdatePnameText.setColumns(10);
            
              JLabel lblProductDescription = new JLabel("Description:");
              lblProductDescription.setBounds(219, 51, 122, 25);
              panel_2.add(lblProductDescription);
              lblProductDescription.setFont(new Font("Tahoma", Font.BOLD, 20));
             
               UpdtPdescText = new JTextField();
               UpdtPdescText.setBounds(213, 87, 121, 20);
               panel_2.add(UpdtPdescText);
               UpdtPdescText.setColumns(10);
              
                JLabel lblNewLabel_2 = new JLabel("Picture:");
                lblNewLabel_2.setBounds(351, 49, 79, 27);
                panel_2.add(lblNewLabel_2);
                lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
               
                 JLabel lblNewLabel_4 = new JLabel("Type:");
                 lblNewLabel_4.setBounds(451, 51, 91, 25);
                 panel_2.add(lblNewLabel_4);
                 lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
                
                  UpdatePtypeText = new JTextField();
                  UpdatePtypeText.setBounds(451, 87, 86, 20);
                  panel_2.add(UpdatePtypeText);
                  UpdatePtypeText.setColumns(10);
                 
                   JLabel lblNewLabel_6 = new JLabel("Pack Qnty:");
                   lblNewLabel_6.setBounds(565, 44, 109, 38);
                   panel_2.add(lblNewLabel_6);
                   lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
                  
                    UpdtPpackquantityText = new JTextField();
                    UpdtPpackquantityText.setBounds(561, 87, 132, 20);
                    panel_2.add(UpdtPpackquantityText);
                    UpdtPpackquantityText.setColumns(10);
                   
                     JLabel lblNewLabel_8 = new JLabel("Price:");
                     lblNewLabel_8.setBounds(703, 51, 58, 25);
                     panel_2.add(lblNewLabel_8);
                     lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 20));
                    
                      UpdtPpriceText = new JTextField();
                      UpdtPpriceText.setBounds(703, 87, 86, 20);
                      panel_2.add(UpdtPpriceText);
                      UpdtPpriceText.setColumns(10);
                     
        JLabel lblStockLct1updt = new JLabel("Stock Lct1");
        lblStockLct1updt.setBounds(799, 51, 111, 25);
        panel_2.add(lblStockLct1updt);
        lblStockLct1updt.setFont(new Font("Tahoma", Font.BOLD, 20));
       
        UpdtStocklevel1Text = new JTextField();
        UpdtStocklevel1Text.setBounds(799, 87, 100, 20);
        panel_2.add(UpdtStocklevel1Text);
        UpdtStocklevel1Text.setColumns(10);
       
        JLabel lblStockLct2updt = new JLabel("Stock Lct2");
        lblStockLct2updt.setBounds(920, 50, 111, 27);
        panel_2.add(lblStockLct2updt);
        lblStockLct2updt.setFont(new Font("Tahoma", Font.BOLD, 20));
       
        UpdtStocklevel2Text = new JTextField();
        UpdtStocklevel2Text.setBounds(920, 87, 100, 20);
        panel_2.add(UpdtStocklevel2Text);
        UpdtStocklevel2Text.setColumns(10);
       
        JLabel lblStockLevelupdt = new JLabel("Stock Level");
        lblStockLevelupdt.setBounds(1041, 50, 125, 27);
        panel_2.add(lblStockLevelupdt);
        lblStockLevelupdt.setFont(new Font("Tahoma", Font.BOLD, 20));
       
        UpdtAmntStocklevelText = new JTextField();
        UpdtAmntStocklevelText.setBounds(1040, 87, 100, 20);
        panel_2.add(UpdtAmntStocklevelText);
        UpdtAmntStocklevelText.setColumns(10);
        
          JButton btnUpdatePrdct = new JButton("Update");
          btnUpdatePrdct.setBounds(1183, 75, 109, 37);
          panel_2.add(btnUpdatePrdct);
          btnUpdatePrdct.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                 
                try {
                    UpdatePCode = Integer.parseInt(UpdatePcodeText.getText());
                    UpdatePname = UpdatePnameText.getText();
                    UpdtPdesc = UpdtPdescText.getText();
                    UpdatePtype = UpdatePtypeText.getText();
                    UpdtPpackquantity = UpdtPpackquantityText.getText();
                    
                   
                    connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                   
                    statement = connection.createStatement();
                   
                    if (UpdatePcodeText.getText().length() > 0) {
                        if(UpdatePnameText.getText().length() > 0)
                            resultSetInt = statement.executeUpdate("UPDATE Products SET product_name = '" + UpdatePname + "' WHERE Product_Code = '" + UpdatePCode + "';");
                        if(UpdtPdescText.getText().length() > 0)
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Product_Description = '" + UpdtPdesc + "' WHERE Product_Code = '" + UpdatePCode + "';");
                        if(UpdatePtypeText.getText().length() > 0)
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Product_Type = '" + UpdatePtype + "' WHERE Product_Code = '" + UpdatePCode + "';");
                        if(UpdtPpackquantityText.getText().length() > 0)
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Packaging_Quantity = '" + UpdtPpackquantity + "' WHERE Product_Code = '" + UpdatePCode + "';");
                        if(UpdtPpriceText.getText().length() > 0) {
                        	UpdtPprice = new BigDecimal(UpdtPpriceText.getText());
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Product_Price = '" + UpdtPprice + "' WHERE Product_Code = '" + UpdatePCode + "';");}
                        if(UpdtStocklevel1Text.getText().length() > 0) {
                            UpdtStocklevel1 = Integer.parseInt(UpdtStocklevel1Text.getText());
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Location_1_Stock = '" + UpdtStocklevel1 + "' WHERE Product_Code = '" + UpdatePCode + "';");}
                        if(UpdtStocklevel2Text.getText().length() > 0) {
                        	UpdtStocklevel2 = Integer.parseInt(UpdtStocklevel2Text.getText());
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Location_2_Stock = '" + UpdtStocklevel2 + "' WHERE Product_Code = '" + UpdatePCode + "';");}
                        if(UpdtAmntStocklevelText.getText().length() > 0) {
                        	UpdtAmntStocklevel = Integer.parseInt(UpdtAmntStocklevelText.getText());
                            resultSetInt = statement.executeUpdate("UPDATE Products SET Stock_Level = '" + UpdtAmntStocklevel + "' WHERE Product_Code = '" + UpdatePCode + "';");}
                    }
                    resultSet = statement.executeQuery("Select * from Products Where Product_Code = '" + UpdatePCode + "';");
                    table1.setModel(DbUtils.resultSetToTableModel(resultSet));
                    table1.setRowHeight(30);
                    table2.setModel(DbUtils.resultSetToTableModel(resultSet));
                    table2.setRowHeight(30);
                }
                catch(SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "User does not have access to this table");
                }
                 
              }
          });
          btnUpdatePrdct.setFont(new Font("Tahoma", Font.BOLD, 20));
       
        JPanel panel1 = new JPanel();
        tabbedPane.addTab("Location 1", null, panel1, null);
        panel1.setLayout(null);
       
         JScrollPane scrollPane1 = new JScrollPane();
         scrollPane1.setBounds(10, 50, 1297, 290);
         panel1.add(scrollPane1);
        
          table1 = new JTable();
          table1.setFont(new Font("Tahoma", Font.PLAIN, 24));
          scrollPane1.setViewportView(table1);
         
           JButton btnViewInvt1 = new JButton("View");
           btnViewInvt1.setBounds(1183, 442, 109, 40);
           panel1.add(btnViewInvt1);
           btnViewInvt1.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent arg0) {
                   try {
                       connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                     
                       statement = connection.createStatement();
                     
                       resultSet = statement.executeQuery("Select * From dbo.Products;");
                     
                       table1.setModel(DbUtils.resultSetToTableModel(resultSet));
                       table1.setRowHeight(30);
                     
                   }
                   catch(SQLException sqlException) {
                       JOptionPane.showMessageDialog(null, "User does not have access to this table");
                   }
                   try {
                       connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                     
                       statement = connection.createStatement();
                     
                       resultSet = statement.executeQuery("SELECT (SELECT SUM(Location_1_Stock) FROM   Products) As \"Total Amount of Items\",\r\n" +
                               "        (SELECT sum(Location_1_Stock * Product_Price))As \"Total Value of Inventory\" FROM Products WHERE Location_1_Stock IS Not NULL;");
                     
                       table1a.setModel(DbUtils.resultSetToTableModel(resultSet));
                       table1a.setRowHeight(30);
                     
                   }
                   catch(SQLException sqlException) {
                       JOptionPane.showMessageDialog(null, "User does not have access to this table");
                   }
               }
           });
           btnViewInvt1.setFont(new Font("Tahoma", Font.BOLD, 20));
          
           JLabel lblLocation = new JLabel("Location 1, Inventory");
           lblLocation.setFont(new Font("Tahoma", Font.BOLD, 18));
           lblLocation.setBounds(10, 11, 200, 28);
           panel1.add(lblLocation);
          
           JScrollPane scrollPane1a = new JScrollPane();
           scrollPane1a.setBounds(830, 351, 477, 82);
           panel1.add(scrollPane1a);
          
           table1a = new JTable();
           table1a.setFont(new Font("Tahoma", Font.PLAIN, 24));
           scrollPane1a.setViewportView(table1a);
       
        JPanel panel2 = new JPanel();
        tabbedPane.addTab("Location 2", null, panel2, null);
        panel2.setLayout(null);
       
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(10, 50, 1297, 290);
        panel2.add(scrollPane2);
       
        table2 = new JTable();
        table2.setFont(new Font("Tahoma", Font.PLAIN, 24));
        scrollPane2.setViewportView(table2);
       
        JButton btnViewInvt2 = new JButton("View");
        btnViewInvt2.setBounds(1183, 442, 109, 40);
        //panel2.add(btnViewInvt2);
        btnViewInvt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                  
                    statement = connection.createStatement();
                  
                    resultSet = statement.executeQuery("Select * From dbo.Products;");
                  
                    table2.setModel(DbUtils.resultSetToTableModel(resultSet));
                    table2.setRowHeight(30);
                  
                }
                catch(SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "User does not have access to this table");
                }
               
                try {
                    connection = DriverManager.getConnection(AdminMenu.DATABASE_URL, AdminMenu.UserName, AdminMenu.Password);
                  
                    statement = connection.createStatement();
                  
                    resultSet = statement.executeQuery("SELECT (SELECT SUM(Location_2_Stock) FROM   Products) As \"Total Amount of Items\",\r\n" +
                            "        (SELECT sum(Location_2_Stock * Product_Price))As \"Total Value of Inventory\" FROM Products WHERE Location_2_Stock IS Not NULL;");
                  
                    table2a.setModel(DbUtils.resultSetToTableModel(resultSet));
                    table2a.setRowHeight(30);
                  
                }
                catch(SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "User does not have access to this table");
                }
            }
        });
       
       
        btnViewInvt2.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnViewInvt2.setBounds(1183, 442, 109, 40);
        panel2.add(btnViewInvt2);
       
        JLabel lblLocation_1 = new JLabel("Location 2, Inventory");
        lblLocation_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblLocation_1.setBounds(10, 11, 200, 28);
        panel2.add(lblLocation_1);
       
        JScrollPane scrollPane2a = new JScrollPane();
        scrollPane2a.setBounds(830, 351, 477, 82);
        panel2.add(scrollPane2a);
        contentPane.setLayout(gl_contentPane);
       
        table2a = new JTable();
        table2a.setFont(new Font("Tahoma", Font.PLAIN, 24));
        scrollPane2a.setViewportView(table2a);
    }
}