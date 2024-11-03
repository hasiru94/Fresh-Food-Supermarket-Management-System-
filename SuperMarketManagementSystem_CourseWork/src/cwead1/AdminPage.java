package cwead1;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    private JPanel contentPane;

    public AdminPage() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 629, 483);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(128, 255, 0));
        sidePanel.setBounds(0, 0, 154, 439);
        contentPane.add(sidePanel);

        JLabel lblEvey = new JLabel("Evey");
        lblEvey.setForeground(Color.RED);
        lblEvey.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblEvey.setBounds(38, 32, 86, 61);
        sidePanel.add(lblEvey);

        JLabel lblProduct = new JLabel("Product");
        lblProduct.setForeground(Color.RED);
        lblProduct.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblProduct.setBounds(38, 75, 106, 61);
        sidePanel.add(lblProduct);

        JLabel lblNice = new JLabel("Nice");
        lblNice.setForeground(Color.RED);
        lblNice.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNice.setBounds(38, 117, 86, 61);
        sidePanel.add(lblNice);

        JLabel lblService = new JLabel("Service");
        lblService.setForeground(Color.RED);
        lblService.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblService.setBounds(38, 164, 86, 61);
        sidePanel.add(lblService);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBounds(152, 0, 461, 439);
        contentPane.add(mainPanel);

        JLabel lblTitle = new JLabel("Admin Dashboard");
        lblTitle.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblTitle.setBounds(100, 34, 300, 30);
        mainPanel.add(lblTitle);

        JButton btnAddProduct = new JButton("Add Product");
        btnAddProduct.setBackground(new Color(255, 255, 0));
        btnAddProduct.setBounds(150, 100, 160, 30);
        mainPanel.add(btnAddProduct);

        JButton btnEditProduct = new JButton("Edit Product");
        btnEditProduct.setBackground(new Color(255, 255, 0));
        btnEditProduct.setBounds(150, 150, 160, 30);
        mainPanel.add(btnEditProduct);

        JButton btnDeleteProduct = new JButton("Delete Product");
        btnDeleteProduct.setBackground(new Color(255, 255, 0));
        btnDeleteProduct.setBounds(150, 200, 160, 30);
        mainPanel.add(btnDeleteProduct);
    }
}
