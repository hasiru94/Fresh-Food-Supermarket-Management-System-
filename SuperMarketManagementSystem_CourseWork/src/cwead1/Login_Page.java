package cwead1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Login_Page extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Page frame = new Login_Page();
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
	public Login_Page() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 483);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(128, 255, 0));
		panel_1.setBounds(0, 0, 154, 439);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Evey \r\n");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Wide Latin", Font.BOLD, 12));
		lblNewLabel_2.setBackground(new Color(255, 0, 128));
		lblNewLabel_2.setBounds(38, 32, 86, 61);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Product");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setFont(new Font("Wide Latin", Font.BOLD, 12));
		lblNewLabel_2_1.setBackground(new Color(255, 0, 128));
		lblNewLabel_2_1.setBounds(38, 75, 106, 61);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Nice");
		lblNewLabel_2_2.setForeground(Color.RED);
		lblNewLabel_2_2.setFont(new Font("Wide Latin", Font.BOLD, 12));
		lblNewLabel_2_2.setBackground(new Color(255, 0, 128));
		lblNewLabel_2_2.setBounds(38, 117, 86, 61);
		panel_1.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Service");
		lblNewLabel_2_3.setForeground(Color.RED);
		lblNewLabel_2_3.setFont(new Font("Wide Latin", Font.BOLD, 12));
		lblNewLabel_2_3.setBackground(new Color(255, 0, 128));
		lblNewLabel_2_3.setBounds(38, 164, 86, 61);
		panel_1.add(lblNewLabel_2_3);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 255, 255));
		panel_1_1.setBounds(152, 0, 461, 439);
		contentPane.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Login");
		lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(157, 34, 129, 29);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("Seleted Roll");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(82, 133, 85, 21);
		panel_1_1.add(lblNewLabel);
		
		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUserId.setBounds(82, 188, 85, 21);
		panel_1_1.add(lblUserId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(215, 133, 118, 22);
		panel_1_1.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(215, 189, 118, 20);
		panel_1_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(255, 255, 0));
		btnNewButton.setBounds(78, 305, 89, 23);
		panel_1_1.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(255, 255, 0));
		btnClear.setBounds(242, 305, 89, 23);
		panel_1_1.add(btnClear);
		
		JLabel lblClose = new JLabel("X");
		lblClose.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblClose.setForeground(new Color(255, 0, 0));
		lblClose.setBounds(444, 0, 17, 21);
		panel_1_1.add(lblClose);
	}
}
