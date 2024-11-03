package cwead1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class Bill extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBoxProducts;
    private JComboBox<String> comboBoxCategories;
    private JTextField textFieldQuantity;
    private JTextField textFieldBillID;
    private JTextField textFieldFinalAmount;
    private JTextField textFieldBillDate;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea textAreaBillReport;

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/supermarketdatabase";
    private static final String USER = "root"; 
    private static final String PASS = ""; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Bill frame = new Bill();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Bill() {
        setTitle("Bill");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 611);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Sidebar Panel
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(0, 0, 154, 572);
        contentPane.add(panel_1);
        
        JButton btnNewButton = new JButton("Report");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Report ReportFrame = new Report(); 
                ReportFrame.setVisible(true);
                dispose(); 
        		
        	}
        });
        btnNewButton.setBackground(new Color(255, 128, 64));
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 11));
        btnNewButton.setBounds(34, 55, 89, 23);
        panel_1.add(btnNewButton);
        
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Send SendFrame = new Send(); 
                SendFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnSend.setFont(new Font("Times New Roman", Font.BOLD, 11));
        btnSend.setBackground(new Color(255, 128, 64));
        btnSend.setBounds(34, 104, 89, 23);
        panel_1.add(btnSend);
        
        JButton btnLogIn = new JButton("LOGIN");
        btnLogIn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Login_Page LoginPageFrame = new Login_Page(); 
                LoginPageFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnLogIn.setFont(new Font("Times New Roman", Font.BOLD, 11));
        btnLogIn.setBackground(new Color(255, 128, 64));
        btnLogIn.setBounds(34, 153, 89, 23);
        panel_1.add(btnLogIn);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Bill.class.getResource("/images/3916624.png")));
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setBounds(10, 400, 134, 128);
        panel_1.add(lblNewLabel);
        
        JButton btnMainmenu = new JButton("MainMenu");
        btnMainmenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainMenu MainMenuFrame = new MainMenu(); 
                MainMenuFrame.setVisible(true);
                dispose(); 
        	}
        });
        btnMainmenu.setFont(new Font("Times New Roman", Font.BOLD, 11));
        btnMainmenu.setBackground(new Color(255, 128, 64));
        btnMainmenu.setBounds(34, 201, 89, 23);
        panel_1.add(btnMainmenu);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(154, 0, 530, 561);
        contentPane.add(panel_1_1);

        JLabel lblNewLabel_1_1 = new JLabel("Bill Point");
        lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(161, 16, 129, 29);
        panel_1_1.add(lblNewLabel_1_1);

        JLabel lblProduct = new JLabel("Product");
        lblProduct.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblProduct.setBounds(43, 96, 85, 21);
        panel_1_1.add(lblProduct);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblQuantity.setBounds(43, 141, 85, 21);
        panel_1_1.add(lblQuantity);

        // Add Date Label
        JLabel lblBillDate = new JLabel("Bill Date (YYYY-MM-DD)");
        lblBillDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblBillDate.setBounds(43, 176, 181, 21);
        panel_1_1.add(lblBillDate);

        comboBoxProducts = new JComboBox<>();
        comboBoxProducts.setBackground(new Color(255, 255, 128));
        comboBoxProducts.setBounds(125, 97, 118, 20);
        panel_1_1.add(comboBoxProducts);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setBackground(new Color(255, 255, 255));
        textFieldQuantity.setColumns(10);
        textFieldQuantity.setBounds(125, 142, 118, 20);
        panel_1_1.add(textFieldQuantity);

        textFieldBillDate = new JTextField();
        textFieldBillDate.setBackground(new Color(255, 255, 255));
        textFieldBillDate.setColumns(10);
        textFieldBillDate.setBounds(210, 177, 200, 20);
        panel_1_1.add(textFieldBillDate);

        JButton btnAddBill = new JButton("Add Bill");
        btnAddBill.setBackground(Color.YELLOW);
        btnAddBill.setBounds(33, 238, 89, 23);
        panel_1_1.add(btnAddBill);

        JButton btnClear = new JButton("Clear");
        btnClear.setBackground(Color.YELLOW);
        btnClear.setBounds(135, 238, 89, 23);
        panel_1_1.add(btnClear);

        textFieldBillID = new JTextField();
        textFieldBillID.setColumns(10);
        textFieldBillID.setBackground(new Color(255, 255, 255));
        textFieldBillID.setBounds(125, 56, 118, 20);
        panel_1_1.add(textFieldBillID);

        JLabel lblBillID = new JLabel("Bill ID");
        lblBillID.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblBillID.setBounds(43, 55, 85, 21);
        panel_1_1.add(lblBillID);

        textFieldFinalAmount = new JTextField();
        textFieldFinalAmount.setColumns(10);
        textFieldFinalAmount.setBackground(new Color(193, 234, 119));
        textFieldFinalAmount.setBounds(210, 375, 86, 20);
        panel_1_1.add(textFieldFinalAmount);

        JLabel lblFinalAmount = new JLabel("Final Amount (Rs.)");
        lblFinalAmount.setBounds(58, 378, 150, 14);
        panel_1_1.add(lblFinalAmount);

        JButton btnPrintBill = new JButton("Print Bill");
        btnPrintBill.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnPrintBill.setForeground(new Color(255, 0, 128));
        btnPrintBill.setBackground(Color.YELLOW);
        btnPrintBill.setBounds(321, 374, 89, 23);
        panel_1_1.add(btnPrintBill);

        JButton btnFilter = new JButton("Filter");
        btnFilter.setBackground(Color.YELLOW);
        btnFilter.setBounds(253, 205, 89, 23);
        panel_1_1.add(btnFilter);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBackground(Color.YELLOW);
        btnRefresh.setBounds(353, 205, 89, 23);
        panel_1_1.add(btnRefresh);

        // JTable setup
        String[] columnNames = {"Product", "Quantity", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 270, 400, 76);
        panel_1_1.add(scrollPane);
        
        textAreaBillReport = new JTextArea();
        textAreaBillReport.setBounds(24, 408, 400, 150);
        panel_1_1.add(textAreaBillReport);
        textAreaBillReport.setEditable(false);
        textAreaBillReport.setBackground(new Color(240, 240, 240));
        textAreaBillReport.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JLabel lblCategory = new JLabel("Category");
        lblCategory.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCategory.setBounds(263, 96, 85, 21);
        panel_1_1.add(lblCategory);
        
        comboBoxCategories = new JComboBox<>();
        comboBoxCategories.setBackground(new Color(255, 255, 128));
        comboBoxCategories.setBounds(345, 97, 118, 20);
        panel_1_1.add(comboBoxCategories);
        
        btnAddBill.addActionListener(e -> addBill());
        btnPrintBill.addActionListener(e -> printBill());
        btnFilter.addActionListener(e -> filterProducts());
        btnRefresh.addActionListener(e -> loadProducts());
        btnClear.addActionListener(e -> clearFields());
        
        loadCategories();
        loadProducts();
        comboBoxCategories.addActionListener(e -> filterProducts());
    }

    private void loadCategories() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM categories")) {
            while (rs.next()) {
                comboBoxCategories.addItem(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading categories: " + e.getMessage());
        }
    }

    private void loadProducts() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT ProductName FROM products")) {
            comboBoxProducts.removeAllItems();
            while (rs.next()) {
                comboBoxProducts.addItem(rs.getString("ProductName"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading products: " + e.getMessage());
        }
    }

    private void filterProducts() {
        String selectedCategory = (String) comboBoxCategories.getSelectedItem();
        if (selectedCategory == null) {
            JOptionPane.showMessageDialog(null, "Please select a category to filter.");
            return;
        }

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT ProductName FROM products WHERE Category = ?")) {
            stmt.setString(1, selectedCategory);
            ResultSet rs = stmt.executeQuery();

            comboBoxProducts.removeAllItems();
            while (rs.next()) {
                comboBoxProducts.addItem(rs.getString("ProductName"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error filtering products: " + e.getMessage());
        }
    }

    

    private void addBill() {
        String productName = (String) comboBoxProducts.getSelectedItem();
        int quantity = Integer.parseInt(textFieldQuantity.getText());
        int price = getProductPrice(productName);

        if (price == -1) {
            JOptionPane.showMessageDialog(null, "Error retrieving product price.");
            return;
        }

        int amount = quantity * price;
        tableModel.addRow(new Object[]{productName, quantity, amount});
        updateFinalAmount();

        // Add the bill to the database
        addBillToDatabase(productName, quantity, amount);
    }

    private int getProductPrice(String productName) {
        int price = -1;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT Price FROM products WHERE ProductName = ?")) {
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                price = rs.getInt("Price");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error retrieving product price: " + e.getMessage());
        }
        return price;
    }

    private void updateFinalAmount() {
        int sum = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int amount = (int) tableModel.getValueAt(i, 2);
            sum += amount;
        }
        textFieldFinalAmount.setText(String.valueOf(sum));
    }

    private void addBillToDatabase(String productName, int quantity, int amount) {
        String insertQuery = "INSERT INTO bills (product_name, quantity, amount, bill_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, productName);
            stmt.setInt(2, quantity);
            stmt.setBigDecimal(3, BigDecimal.valueOf(amount)); // Use BigDecimal for decimal type
            stmt.setDate(4, new Date(System.currentTimeMillis())); // Set the current date

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Bill added successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add the bill.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding bill to database: " + e.getMessage());
        }
    }


    private void clearFields() {
        textFieldBillID.setText("");
        textFieldQuantity.setText("");
        textFieldBillDate.setText("");
        comboBoxProducts.setSelectedIndex(-1);
        comboBoxCategories.setSelectedIndex(-1);
        tableModel.setRowCount(0);
        textFieldFinalAmount.setText("");
        textAreaBillReport.setText("");
    }

 // Inside your Bill class

    private void printBill() {
        StringBuilder bill = new StringBuilder();
        bill.append("Bill ID: ").append(textFieldBillID.getText()).append("\n");
        bill.append("Date: ").append(textFieldBillDate.getText()).append("\n\n");
        bill.append("Product\tQuantity\tAmount\n");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String product = (String) tableModel.getValueAt(i, 0);
            int quantity = (int) tableModel.getValueAt(i, 1);
            int amount = (int) tableModel.getValueAt(i, 2);
            bill.append(product).append("\t").append(quantity).append("\t").append(amount).append("\n");
        }

        bill.append("\nFinal Amount: Rs. ").append(textFieldFinalAmount.getText());
        
        // Open the report interface and pass the bill content
        Report report = new Report(); // Pass the bill content
        report.setVisible(true);
    }

    // Separate class for Report
    public class Report extends JFrame {
        private JTextArea textAreaReport;

        public Report() {
            setTitle("Bill Report");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textAreaReport = new JTextArea();
            textAreaReport.setEditable(false);
            textAreaReport.setText(PASS);
            JScrollPane scrollPane = new JScrollPane(textAreaReport);
            add(scrollPane);
        }
    }
}

