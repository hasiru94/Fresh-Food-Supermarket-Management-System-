package cwead1.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cwead1.AdminPage;
import cwead1.Bill;
import cwead1.MainMenu;
import cwead1.MemberPage;
import cwead1.SignInPage;
import javax.swing.ImageIcon;

public class LoginPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUserId;
    private JTextField textFieldPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginPage frame = new LoginPage();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginPage() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 629, 483);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBackground(new Color(255, 255, 128));
        panelLeft.setBounds(0, 0, 154, 439);
        contentPane.add(panelLeft);

        JLabel lblBrand1 = new JLabel("Evey");
        lblBrand1.setForeground(Color.RED);
        lblBrand1.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblBrand1.setBounds(38, 32, 86, 61);
        panelLeft.add(lblBrand1);

        JLabel lblBrand2 = new JLabel("Product");
        lblBrand2.setForeground(Color.RED);
        lblBrand2.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblBrand2.setBounds(38, 75, 106, 61);
        panelLeft.add(lblBrand2);
        
        JLabel lblNice = new JLabel("Nice");
        lblNice.setForeground(Color.RED);
        lblNice.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNice.setBounds(38, 123, 86, 61);
        panelLeft.add(lblNice);
        
        JLabel lblService = new JLabel("Service");
        lblService.setForeground(Color.RED);
        lblService.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblService.setBounds(38, 166, 106, 61);
        panelLeft.add(lblService);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.setBackground(new Color(128, 255, 128));
        panelMain.setBounds(152, 0, 461, 439);
        contentPane.add(panelMain);

        JLabel lblLoginTitle = new JLabel("Login");
        lblLoginTitle.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblLoginTitle.setBounds(157, 34, 129, 29);
        panelMain.add(lblLoginTitle);

        JLabel lblRole = new JLabel("Selected Role");
        lblRole.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblRole.setBounds(82, 133, 85, 21);
        panelMain.add(lblRole);

        JLabel lblUserId = new JLabel("User ID");
        lblUserId.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblUserId.setBounds(82, 188, 85, 21);
        panelMain.add(lblUserId);

        JComboBox<String> comboBoxRole = new JComboBox<>(new String[] {"Admin", "Member"});
        comboBoxRole.setBackground(new Color(0, 255, 0));
        comboBoxRole.setBounds(215, 133, 118, 22);
        panelMain.add(comboBoxRole);

        textFieldUserId = new JTextField();
        textFieldUserId.setBackground(new Color(255, 255, 255));
        textFieldUserId.setBounds(215, 189, 118, 20);
        panelMain.add(textFieldUserId);
        textFieldUserId.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPassword.setBounds(82, 233, 85, 21);
        panelMain.add(lblPassword);

        textFieldPassword = new JTextField();
        textFieldPassword.setBackground(new Color(255, 255, 255));
        textFieldPassword.setBounds(215, 234, 118, 20);
        panelMain.add(textFieldPassword);
        textFieldPassword.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin(comboBoxRole.getSelectedItem().toString(), textFieldUserId.getText(), textFieldPassword.getText());
            }
        });
        btnLogin.setBackground(new Color(255, 255, 0));
        btnLogin.setBounds(78, 305, 89, 23);
        panelMain.add(btnLogin);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> {
            textFieldUserId.setText("");
            textFieldPassword.setText("");
            comboBoxRole.setSelectedIndex(0);
        });
        btnClear.setBackground(new Color(255, 255, 0));
        btnClear.setBounds(242, 305, 89, 23);
        panelMain.add(btnClear);

        JButton btnClose = new JButton("X");
        btnClose.addActionListener(e -> dispose());
        btnClose.setBackground(new Color(255, 255, 255));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnClose.setForeground(Color.RED);
        btnClose.setBounds(415, 0, 46, 34);
        panelMain.add(btnClose);

        JButton btnSignIn = new JButton("Sign In");
        btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSignIn.setForeground(new Color(255, 0, 0));
        btnSignIn.addActionListener(e -> {
            new SignInPage().setVisible(true);
            dispose();
        });
        btnSignIn.setBackground(new Color(255, 255, 255));
        btnSignIn.setBounds(335, 394, 89, 23);
        panelMain.add(btnSignIn);
    }

    private void handleLogin(String role, String userId, String password) {
        if (userId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "User ID and Password cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarketdatabase", "root", "");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ? AND password = ? AND role = ?")) {
            
            stmt.setString(1, userId);
            stmt.setString(2, password);
            stmt.setString(3, role);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if ("Admin".equals(role)) {
                    new MainMenu().setVisible(true);
                } else if ("Member".equals(role)) {
                    new Bill().setVisible(true);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials or role. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database connection failed. Please check your setup.", "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
