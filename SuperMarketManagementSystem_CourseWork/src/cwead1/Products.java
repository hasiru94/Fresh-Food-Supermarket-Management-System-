package cwead1;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Products extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField, textField_1, textField_2, textField_3;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Object> comboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Products frame = new Products();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Products() {
        setTitle("Products");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 633, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Sidebar panel
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(0, 0, 154, 600);
        contentPane.add(panel_1);

        JButton btnMainMenu = new JButton("Main menu");
        btnMainMenu.addActionListener(e -> {
            MainMenu MainMenuFrame = new MainMenu(); 
            MainMenuFrame.setVisible(true);
            dispose(); 
        });
        btnMainMenu.setBackground(new Color(255, 128, 0));
        btnMainMenu.setForeground(Color.WHITE);
        btnMainMenu.setBounds(10, 35, 117, 23);
        panel_1.add(btnMainMenu);

        JButton btnCategories = new JButton("Categories");
        btnCategories.addActionListener(e -> {
            Categories CategoriesFrame = new Categories(); 
            CategoriesFrame.setVisible(true);
            dispose(); 
        });
        btnCategories.setForeground(Color.WHITE);
        btnCategories.setBackground(new Color(255, 128, 0));
        btnCategories.setBounds(10, 84, 117, 23);
        panel_1.add(btnCategories);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setIcon(new ImageIcon(Products.class.getResource("/images/3916624.png")));
        lblNewLabel.setBounds(10, 424, 134, 128);
        panel_1.add(lblNewLabel);

        // Main panel
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(153, 0, 480, 600);
        contentPane.add(panel_1_1);

        JLabel lblTitle = new JLabel("Products");
        lblTitle.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblTitle.setBounds(170, 34, 129, 29);
        panel_1_1.add(lblTitle);

        // Input Fields
        JLabel lblProductID = new JLabel("ProductID");
        lblProductID.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblProductID.setBounds(43, 96, 85, 21);
        panel_1_1.add(lblProductID);

        textField = new JTextField();
        textField.setBounds(135, 97, 118, 20);
        panel_1_1.add(textField);

        JLabel lblProductName = new JLabel("Product Name");
        lblProductName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblProductName.setBounds(43, 141, 95, 21);
        panel_1_1.add(lblProductName);

        textField_1 = new JTextField();
        textField_1.setBounds(135, 142, 118, 20);
        panel_1_1.add(textField_1);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblQuantity.setBounds(263, 96, 85, 21);
        panel_1_1.add(lblQuantity);

        textField_2 = new JTextField();
        textField_2.setBounds(335, 97, 118, 20);
        panel_1_1.add(textField_2);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPrice.setBounds(263, 141, 85, 21);
        panel_1_1.add(lblPrice);

        textField_3 = new JTextField();
        textField_3.setBounds(335, 142, 118, 20);
        panel_1_1.add(textField_3);

        JLabel lblCategories = new JLabel("Categories");
        lblCategories.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCategories.setBounds(125, 189, 85, 21);
        panel_1_1.add(lblCategories);

        comboBox = new JComboBox<Object>();
        comboBox.setBounds(213, 189, 118, 22);
        panel_1_1.add(comboBox);

        // Buttons
        JButton btnAdd = new JButton("Add");
        btnAdd.setBackground(Color.YELLOW);
        btnAdd.setBounds(33, 238, 89, 23);
        panel_1_1.add(btnAdd);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBackground(Color.YELLOW);
        btnEdit.setBounds(135, 238, 89, 23);
        panel_1_1.add(btnEdit);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBackground(Color.YELLOW);
        btnDelete.setBounds(234, 238, 89, 23);
        panel_1_1.add(btnDelete);

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(Color.YELLOW);
        btnClear.setBounds(335, 238, 89, 23);
        panel_1_1.add(btnClear);

        // Table setup
        String[] columnNames = {"ProductID", "ProductName", "Category", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(193, 234, 119));
        scrollPane.setBounds(37, 298, 400, 217);
        panel_1_1.add(scrollPane);

        JLabel lblProductList = new JLabel("Product List");
        lblProductList.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblProductList.setBounds(172, 266, 85, 21);
        panel_1_1.add(lblProductList);

        loadTableData(); // Load data when GUI initializes
        loadCategories(); // Load categories into the JComboBox

        // Button functionalities
        btnAdd.addActionListener(e -> addProduct());
        btnEdit.addActionListener(e -> editProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnClear.addActionListener(e -> clearFields());
    }

    private static final String URL = "jdbc:mysql://localhost:3306/supermarketdatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            handleDatabaseConnectionError(e);
            return null;
        }
    }

    private void handleDatabaseConnectionError(SQLException e) {
        String message = e.getMessage();
        String sqlState = e.getSQLState();

        switch (sqlState) {
            case "28000":
                JOptionPane.showMessageDialog(this, "Database connection failed: Invalid username or password", "Connection Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "08S01":
                JOptionPane.showMessageDialog(this, "Database connection failed: Cannot connect to database server", "Connection Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "42000":
                JOptionPane.showMessageDialog(this, "Database connection failed: Access denied or database does not exist", "Connection Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Database connection failed: " + message, "Connection Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        e.printStackTrace();
    }

    private void loadTableData() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            tableModel.setRowCount(0); // Clear existing data
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("Category"),
                    rs.getInt("Quantity"),
                    rs.getDouble("Price")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading product data: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void loadCategories() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM categories")) {
            comboBox.removeAllItems(); // Clear existing items
            while (rs.next()) {
                comboBox.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading categories: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void addProduct() {
        if (!validateInputs()) return;

        String productId = textField.getText();
        String productName = textField_1.getText();
        String quantityStr = textField_2.getText();
        String priceStr = textField_3.getText();
        String category = comboBox.getSelectedItem().toString();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (ProductID, ProductName, Category, Quantity, Price) VALUES (?, ?, ?, ?, ?)")) {
            pstmt.setInt(1, Integer.parseInt(productId));
            pstmt.setString(2, productName);
            pstmt.setString(3, category);
            pstmt.setInt(4, Integer.parseInt(quantityStr));
            pstmt.setDouble(5, Double.parseDouble(priceStr));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully");
            loadTableData(); // Refresh table data
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding product: " + e.getMessage(), "Add Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity and Price must be numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editProduct() {
        if (!validateInputs()) return;

        String productId = textField.getText();
        String productName = textField_1.getText();
        String quantityStr = textField_2.getText();
        String priceStr = textField_3.getText();
        String category = comboBox.getSelectedItem().toString();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET ProductName = ?, Category = ?, Quantity = ?, Price = ? WHERE ProductID = ?")) {
            pstmt.setString(1, productName);
            pstmt.setString(2, category);
            pstmt.setInt(3, Integer.parseInt(quantityStr));
            pstmt.setDouble(4, Double.parseDouble(priceStr));
            pstmt.setInt(5, Integer.parseInt(productId));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product updated successfully");
            loadTableData(); // Refresh table data
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating product: " + e.getMessage(), "Update Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantity and Price must be numbers", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct() {
        String productId = textField.getText();
        if (productId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Product ID to delete", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM products WHERE ProductID = ?")) {
            pstmt.setInt(1, Integer.parseInt(productId));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product deleted successfully");
            loadTableData(); // Refresh table data
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting product: " + e.getMessage(), "Delete Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Product ID must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        textField.setText("");
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        comboBox.setSelectedIndex(0);
    }

    private boolean validateInputs() {
        if (textField.getText().isEmpty() || textField_1.getText().isEmpty() ||
            textField_2.getText().isEmpty() || textField_3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
