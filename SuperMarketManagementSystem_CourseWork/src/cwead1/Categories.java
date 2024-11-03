package cwead1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Categories extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldCID, textFieldName, textFieldDescription;
    private JTable table;
    private DefaultTableModel tableModel;

    private static final String URL = "jdbc:mysql://localhost:3306/supermarketdatabase";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Categories frame = new Categories();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Categories() {
        setTitle("Categories");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 624, 492);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBackground(new Color(255, 255, 128));
        panelMenu.setBounds(0, 0, 154, 439);
        contentPane.add(panelMenu);

        JButton btnMainMenu = createMenuButton("Main menu", e -> {
            new MainMenu().setVisible(true);
            dispose();
        }, panelMenu, 34);

        JButton btnSellers = createMenuButton("Sellers", e -> {
            new Sellers().setVisible(true);
            dispose();
        }, panelMenu, 84);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Categories.class.getResource("/images/3916624.png")));
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setBounds(10, 246, 134, 128);
        panelMenu.add(lblNewLabel);

        JPanel panelMain = new JPanel();
        panelMain.setLayout(null);
        panelMain.setBackground(new Color(128, 255, 128));
        panelMain.setBounds(154, 0, 461, 439);
        contentPane.add(panelMain);

        JLabel lblCategories = new JLabel("Categories");
        lblCategories.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblCategories.setBounds(157, 34, 129, 29);
        panelMain.add(lblCategories);

        JLabel lblCid = new JLabel("CID");
        lblCid.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCid.setBounds(43, 96, 85, 21);
        panelMain.add(lblCid);

        textFieldCID = createTextField(panelMain, 125, 97);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblName.setBounds(43, 141, 85, 21);
        panelMain.add(lblName);

        textFieldName = createTextField(panelMain, 125, 142);

        JLabel lblDescription = new JLabel("Description");
        lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblDescription.setBounds(260, 96, 85, 21);
        panelMain.add(lblDescription);

        textFieldDescription = createTextField(panelMain, 335, 97);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 301, 436, 138);
        panelMain.add(scrollPane);

        tableModel = new DefaultTableModel(new String[]{"CID", "Name", "Description"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(Color.YELLOW);
        btnAdd.setBounds(23, 212, 89, 23);
        btnAdd.addActionListener(e -> addCategory());
        panelMain.add(btnAdd);
        
        JButton btnEdit = new JButton("Edit");
        btnEdit.setBackground(Color.YELLOW);
        btnEdit.setBounds(125, 212, 89, 23);
        btnEdit.addActionListener(e -> editCategory());
        panelMain.add(btnEdit);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.YELLOW);
        btnDelete.setBounds(224, 212, 89, 23);
        btnDelete.addActionListener(e -> deleteCategory());
        panelMain.add(btnDelete);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(Color.YELLOW);
        btnClear.setBounds(325, 212, 89, 23);
        btnClear.addActionListener(e -> clearFields());
        panelMain.add(btnClear);

        loadCategories();
    }

    private JButton createMenuButton(String text, ActionListener action, JPanel panel, int y) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(255, 128, 64));
        button.setBounds(27, y, 100, 23);
        button.addActionListener(action);
        panel.add(button);
        return button;
    }

    private JTextField createTextField(JPanel panel, int x, int y) {
        JTextField textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(x, y, 118, 20);
        panel.add(textField);
        return textField;
    }

    private void loadCategories() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categories")) {

            while (rs.next()) {
                int cid = rs.getInt("cid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                tableModel.addRow(new Object[]{cid, name, description});
            }
        } catch (SQLException e) {
            showError("Failed to load categories: " + e.getMessage());
        }
    }

    private void addCategory() {
        if (!validateInputs()) return;

        String name = textFieldName.getText();
        String description = textFieldDescription.getText();

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to add this category?", 
            "Confirm Addition", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement("INSERT INTO categories (name, description) VALUES (?, ?)")) {

                pstmt.setString(1, name);
                pstmt.setString(2, description);
                pstmt.executeUpdate();
                tableModel.addRow(new Object[]{getLastInsertedId(), name, description});
                clearFields();
                JOptionPane.showMessageDialog(this, "Category added successfully");
            } catch (SQLException e) {
                showError("Failed to add category: " + e.getMessage());
            }
        }
    }

    private int getLastInsertedId() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            showError("Failed to retrieve last inserted ID: " + e.getMessage());
        }
        return -1;
    }

    private void editCategory() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            showError("Please select a category to edit.");
            return;
        }

        if (!validateInputs()) return;

        int cid = (int) tableModel.getValueAt(selectedRow, 0);
        String name = textFieldName.getText();
        String description = textFieldDescription.getText();

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to edit this category?", 
            "Confirm Edit", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement("UPDATE categories SET name = ?, description = ? WHERE cid = ?")) {

                pstmt.setString(1, name);
                pstmt.setString(2, description);
                pstmt.setInt(3, cid);
                pstmt.executeUpdate();

                tableModel.setValueAt(name, selectedRow, 1);
                tableModel.setValueAt(description, selectedRow, 2);
                clearFields();
                JOptionPane.showMessageDialog(this, "Category edited successfully");
            } catch (SQLException e) {
                showError("Failed to edit category: " + e.getMessage());
            }
        }
    }

    private void deleteCategory() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            showError("Please select a category to delete.");
            return;
        }

        int cid = (int) tableModel.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this category?", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM categories WHERE cid = ?")) {

                pstmt.setInt(1, cid);
                pstmt.executeUpdate();
                tableModel.removeRow(selectedRow);
                clearFields();
                JOptionPane.showMessageDialog(this, "Category deleted successfully");
            } catch (SQLException e) {
                showError("Failed to delete category: " + e.getMessage());
            }
        }
    }

    private boolean validateInputs() {
        String name = textFieldName.getText();
        String description = textFieldDescription.getText();

        if (name.trim().isEmpty()) {
            showError("Category name cannot be empty.");
            return false;
        }

        if (description.trim().isEmpty()) {
            showError("Description cannot be empty.");
            return false;
        }

        return true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFields() {
        textFieldCID.setText("");
        textFieldName.setText("");
        textFieldDescription.setText("");
        table.clearSelection();
    }
}
