package cwead1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;

public class SignInPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUserId;
    private JTextField textFieldPassword;

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/supermarketdatabase";
    private static final String USER = "root"; 
    private static final String PASS = ""; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SignInPage frame = new SignInPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SignInPage() {
        setTitle("Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 629, 483);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(0, 0, 154, 439);
        contentPane.add(panel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Evey");
        lblNewLabel_2.setForeground(Color.RED);
        lblNewLabel_2.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNewLabel_2.setBounds(38, 73, 86, 61);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Product");
        lblNewLabel_2_1.setForeground(Color.RED);
        lblNewLabel_2_1.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNewLabel_2_1.setBounds(38, 116, 106, 61);
        panel_1.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Nice");
        lblNewLabel_2_2.setForeground(Color.RED);
        lblNewLabel_2_2.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNewLabel_2_2.setBounds(38, 158, 86, 61);
        panel_1.add(lblNewLabel_2_2);
        
        JLabel lblNewLabel_2_3 = new JLabel("Service");
        lblNewLabel_2_3.setForeground(Color.RED);
        lblNewLabel_2_3.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNewLabel_2_3.setBounds(38, 205, 86, 61);
        panel_1.add(lblNewLabel_2_3);
        
        JLabel lblNewLabel = new JLabel();
        lblNewLabel.setIcon(new ImageIcon(SignInPage.class.getResource("/images/3916624.png")));
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setBounds(10, 300, 134, 128);
        panel_1.add(lblNewLabel);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(152, 0, 461, 439);
        contentPane.add(panel_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("Sign In");
        lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(157, 34, 129, 29);
        panel_1_1.add(lblNewLabel_1_1);

        JLabel lblUserId = new JLabel("User ID");
        lblUserId.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblUserId.setBounds(82, 133, 85, 21);
        panel_1_1.add(lblUserId);

        textFieldUserId = new JTextField();
        textFieldUserId.setBounds(215, 133, 118, 20);
        panel_1_1.add(textFieldUserId);
        textFieldUserId.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPassword.setBounds(82, 188, 85, 21);
        panel_1_1.add(lblPassword);

        textFieldPassword = new JTextField();
        textFieldPassword.setBounds(215, 188, 118, 20);
        panel_1_1.add(textFieldPassword);
        textFieldPassword.setColumns(10);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearFields());
        btnClear.setBackground(new Color(255, 255, 0));
        btnClear.setBounds(210, 305, 89, 23);
        panel_1_1.add(btnClear);

        // Add Save button
        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(e -> saveUser());
        btnSave.setBackground(new Color(255, 255, 0));
        btnSave.setBounds(99, 305, 89, 23); // Adjust the bounds as needed
        panel_1_1.add(btnSave);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Login_Page Login_PageFrame = new Login_Page(); 
                Login_Page login_Page = new Login_Page();
				login_Page.setVisible(true);
                dispose(); 
        	}
        });
        btnLogin.setBackground(Color.YELLOW);
        btnLogin.setBounds(319, 305, 89, 23);
        panel_1_1.add(btnLogin);
    }

    private void signIn() {
        String userId = textFieldUserId.getText().trim();
        String password = textFieldPassword.getText().trim();

        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both User ID and Password.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ? AND password = ?")) {
             
            stmt.setString(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Sign in successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Proceed to the next page or perform additional actions
            } else {
                JOptionPane.showMessageDialog(this, "Invalid User ID or Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void clearFields() {
        textFieldUserId.setText(""); // Clear the User ID field
        textFieldPassword.setText(""); // Clear the Password field
    }

    private void saveUser() {
        String userId = textFieldUserId.getText().trim();
        String password = textFieldPassword.getText().trim();

        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both User ID and Password.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SQL to insert a new user into the users table
        String insertQuery = "INSERT INTO users (user_id, password) VALUES (?, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            
            stmt.setString(1, userId);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields(); // Clear fields after saving
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save user.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
