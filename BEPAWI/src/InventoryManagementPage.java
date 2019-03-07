import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class InventoryManagementPage extends JFrame {

	private JPanel contentPane;
	private JTextField PcodeText;

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
		setBounds(100, 100, 800, 662);
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
		
		PcodeText = new JTextField();
		PcodeText.setColumns(10);
		
		JLabel lblProductCode = new JLabel("Product Code");
		lblProductCode.setFont(new Font("Tahoma", Font.BOLD, 24));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 774, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblProductCode)
							.addContainerGap(699, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(PcodeText, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(528, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblProductCode)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PcodeText, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(323, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
