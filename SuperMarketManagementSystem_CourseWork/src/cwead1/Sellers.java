package cwead1;


import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sellers extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldSID;
    private JTextField textFieldName;
    private JTextField textFieldPassword;
    private JTextField textFieldTelephone;
    private JComboBox<String> comboBoxGender;
    private JTable table; // Add JTable to display sellers
    private DefaultTableModel tableModel; // To manage table data

    private static final String URL = "jdbc:mysql://localhost:3306/supermarketdatabase";
    private static final String USER = "root"; // Replace with your username
    private static final String PASS = ""; // Replace with your password

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Sellers frame = new Sellers();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Sellers() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 630, 504);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(0, 0, 154, 439);
        contentPane.add(panel_1);

        JButton btnNewButton = new JButton("Main menu");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainMenu MainMenuFrame = new MainMenu(); 
                MainMenuFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setBackground(new Color(255, 128, 64));
        btnNewButton.setBounds(27, 34, 89, 23);
        panel_1.add(btnNewButton);

        JButton btnBill = new JButton("Bill");
        btnBill.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Bill BillFrame = new Bill(); 
                BillFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnBill.setForeground(Color.WHITE);
        btnBill.setBackground(new Color(255, 128, 64));
        btnBill.setBounds(27, 84, 89, 23);
        panel_1.add(btnBill);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Sellers.class.getResource("/images/3916624.png")));
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setBounds(10, 262, 134, 128);
        panel_1.add(lblNewLabel);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(153, 0, 461, 439);
        contentPane.add(panel_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("Sellers");
        lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(157, 34, 129, 29);
        panel_1_1.add(lblNewLabel_1_1);

        // Labels and text fields for seller information
        JLabel lblSid = new JLabel("SID");
        lblSid.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblSid.setBounds(43, 96, 85, 21);
        panel_1_1.add(lblSid);

        textFieldSID = new JTextField();
        textFieldSID.setBackground(new Color(255, 255, 255));
        textFieldSID.setColumns(10);
        textFieldSID.setBounds(125, 97, 118, 20);
        panel_1_1.add(textFieldSID);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblName.setBounds(43, 141, 85, 21);
        panel_1_1.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBackground(new Color(255, 255, 255));
        textFieldName.setColumns(10);
        textFieldName.setBounds(125, 142, 118, 20);
        panel_1_1.add(textFieldName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPassword.setBounds(253, 96, 85, 21);
        panel_1_1.add(lblPassword);

        textFieldPassword = new JTextField();
        textFieldPassword.setBackground(new Color(255, 255, 255));
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(335, 97, 118, 20);
        panel_1_1.add(textFieldPassword);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblGender.setBounds(253, 141, 85, 21);
        panel_1_1.add(lblGender);

        comboBoxGender = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        comboBoxGender.setBackground(new Color(255, 255, 128));
        comboBoxGender.setBounds(335, 142, 118, 22);
        panel_1_1.add(comboBoxGender);

        JLabel lblTelephone = new JLabel("Telephone");
        lblTelephone.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblTelephone.setBounds(43, 186, 85, 21);
        panel_1_1.add(lblTelephone);

        textFieldTelephone = new JTextField();
        textFieldTelephone.setColumns(10);
        textFieldTelephone.setBackground(new Color(255, 255, 255));
        textFieldTelephone.setBounds(125, 187, 118, 20);
        panel_1_1.add(textFieldTelephone);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(Color.YELLOW);
        btnAdd.setBounds(33, 238, 89, 23);
        panel_1_1.add(btnAdd);
        btnAdd.addActionListener(e -> addSeller());

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBackground(Color.YELLOW);
        btnEdit.setBounds(135, 238, 89, 23);
        panel_1_1.add(btnEdit);
        btnEdit.addActionListener(e -> editSeller());

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.YELLOW);
        btnDelete.setBounds(234, 238, 89, 23);
        panel_1_1.add(btnDelete);
        btnDelete.addActionListener(e -> deleteSeller());

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(Color.YELLOW);
        btnClear.setBounds(336, 238, 89, 23);
        panel_1_1.add(btnClear);
        btnClear.addActionListener(e -> clearFields());

        // Set up JTable
        tableModel = new DefaultTableModel(new Object[]{"SID", "Name", "Password", "Gender", "Telephone"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 270, 410, 150); // Set the position and size of the scroll pane
        panel_1_1.add(scrollPane);

        loadSellers(); // Load seller data when the application starts

        // Add mouse listener to the table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) { // Check if a row is selected
                    // Populate fields with selected seller's data
                    textFieldSID.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    textFieldName.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    textFieldPassword.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    comboBoxGender.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
                    textFieldTelephone.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });
    }

    private void loadSellers() {
        // Clear the table before loading new data
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM sellers");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // Add each seller's data to the table
                String sid = rs.getString("sid");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                String telephone = rs.getString("telephone");
                tableModel.addRow(new Object[]{sid, name, password, gender, telephone});
            }
        } catch (SQLException e) {
            showError("Failed to load sellers: " + e.getMessage());
        }
    }

    private void addSeller() {
        if (!validateInputs()) return; // Validate inputs before proceeding

        String sid = textFieldSID.getText();
        String name = textFieldName.getText();
        String password = textFieldPassword.getText();
        String gender = (String) comboBoxGender.getSelectedItem();
        String telephone = textFieldTelephone.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO sellers (sid, name, password, gender, telephone) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, sid);
            pstmt.setString(2, name);
            pstmt.setString(3, password);
            pstmt.setString(4, gender);
            pstmt.setString(5, telephone);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Seller added successfully!");
            clearFields(); // Clear fields after adding
            loadSellers(); // Reload the sellers to update the table
        } catch (SQLException e) {
            showError("Failed to add seller: " + e.getMessage());
        }
    }

    private void editSeller() {
        if (!validateInputs()) return; // Validate inputs before proceeding

        String sid = textFieldSID.getText();
        String name = textFieldName.getText();
        String password = textFieldPassword.getText();
        String gender = (String) comboBoxGender.getSelectedItem();
        String telephone = textFieldTelephone.getText();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE sellers SET name = ?, password = ?, gender = ?, telephone = ? WHERE sid = ?")) {

            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, gender);
            pstmt.setString(4, telephone);
            pstmt.setString(5, sid);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Seller updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Seller not found!");
            }
            clearFields(); // Clear fields after editing
            loadSellers(); // Reload the sellers to update the table
        } catch (SQLException e) {
            showError("Failed to edit seller: " + e.getMessage());
        }
    }

    private void deleteSeller() {
        String sid = textFieldSID.getText();

        if (sid.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Seller ID to delete.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM sellers WHERE sid = ?")) {

            pstmt.setString(1, sid);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Seller deleted successfully!");
                clearFields(); // Clear fields after deletion
            } else {
                JOptionPane.showMessageDialog(this, "Seller not found!");
            }
            loadSellers(); // Reload the sellers to update the table
        } catch (SQLException e) {
            showError("Failed to delete seller: " + e.getMessage());
        }
    }

    private boolean validateInputs() {
        if (textFieldSID.getText().isEmpty() || textFieldName.getText().isEmpty() ||
            textFieldPassword.getText().isEmpty() || textFieldTelephone.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.");
            return false;
        }
        return true;
    }

    private void clearFields() {
        textFieldSID.setText("");
        textFieldName.setText("");
        textFieldPassword.setText("");
        textFieldTelephone.setText("");
        comboBoxGender.setSelectedIndex(0); // Reset to the first item
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
