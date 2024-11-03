package cwead1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import cwead1.view.LoginPage;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainMenu frame = new MainMenu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 622, 478);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(0, 0, 154, 439);
        contentPane.add(panel_1);

        
        
        JButton btnCategories_1 = new JButton("Categories");
        btnCategories_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Categories CategoriesFrame = new Categories(); // Open the categories page
                CategoriesFrame.setVisible(true);
                dispose(); // Close the MainMenu page
        	}
        });
        btnCategories_1.setForeground(Color.WHITE);
        btnCategories_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCategories_1.setBackground(new Color(255, 128, 0));
        btnCategories_1.setBounds(28, 57, 100, 23);
        panel_1.add(btnCategories_1);
        
        JButton btnSellers_1 = new JButton("sellers");
        btnSellers_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Sellers SellersFrame = new Sellers(); // Open the Sellers page
                SellersFrame.setVisible(true);
                dispose(); // Close the MainMenu page
        	}
        });
        btnSellers_1.setForeground(Color.WHITE);
        btnSellers_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSellers_1.setBackground(new Color(255, 128, 0));
        btnSellers_1.setBounds(28, 91, 100, 23);
        panel_1.add(btnSellers_1);
        
        JButton btnBill_1 = new JButton("Bill");
        btnBill_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Bill BillFrame = new Bill(); // Open the Bill page
                BillFrame.setVisible(true);
                dispose(); // Close the MainMenu page
        	}
        });
        btnBill_1.setForeground(Color.WHITE);
        btnBill_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBill_1.setBackground(new Color(255, 128, 0));
        btnBill_1.setBounds(28, 125, 100, 23);
        panel_1.add(btnBill_1);
        
        JButton btnReport_1 = new JButton("Report");
        btnReport_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Report ReportFrame = new Report(getTitle()); // Open the Report page
                ReportFrame.setVisible(true);
                dispose(); // Close the MainMenu page
        	}
        });
        btnReport_1.setForeground(Color.WHITE);
        btnReport_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnReport_1.setBackground(new Color(255, 128, 0));
        btnReport_1.setBounds(28, 159, 100, 23);
        panel_1.add(btnReport_1);
        
        JButton btnSend_1 = new JButton("Send");
        btnSend_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Send SendFrame = new Send(); // Open the Send page
                SendFrame.setVisible(true);
                dispose(); // Close the MainMenu page
        	}
        });
        btnSend_1.setForeground(Color.WHITE);
        btnSend_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSend_1.setBackground(new Color(255, 128, 0));
        btnSend_1.setBounds(28, 193, 100, 23);
        panel_1.add(btnSend_1);
        
        JButton btnExit_1 = new JButton("Exit");
        btnExit_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0); // Exit the application
        	}
        });
        btnExit_1.setForeground(Color.WHITE);
        btnExit_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnExit_1.setBackground(new Color(255, 128, 0));
        btnExit_1.setBounds(28, 227, 100, 23);
        panel_1.add(btnExit_1);
        
        JButton btnProducts = new JButton("Products");
        btnProducts.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Products ProductsFrame = new Products(); // Open the Products page
                ProductsFrame.setVisible(true);
                dispose(); // Close the MainMenu page
        	}
        });
        btnProducts.setForeground(Color.WHITE);
        btnProducts.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnProducts.setBackground(new Color(255, 128, 0));
        btnProducts.setBounds(28, 23, 100, 23);
        panel_1.add(btnProducts);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/images/3916624.png")));
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setBounds(10, 300, 134, 128);
        panel_1.add(lblNewLabel);

        // Panel for Main Content
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(146, 0, 483, 439);
        contentPane.add(panel_1_1);

        JLabel lblTitle = new JLabel("Main Menu");
        lblTitle.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblTitle.setBounds(157, 34, 176, 29);
        panel_1_1.add(lblTitle);

        JLabel lblClose = new JLabel("X");
        lblClose.setForeground(Color.RED);
        lblClose.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblClose.setBounds(444, 0, 17, 21);
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
            }
        });
        panel_1_1.add(lblClose);

        JLabel lblImage = new JLabel("");
        lblImage.setIcon(new ImageIcon(MainMenu.class.getResource("/images/images (1).jpeg")));
        lblImage.setBounds(80, 105, 334, 242);
        panel_1_1.add(lblImage);
    }

    // Method to set up button properties
    private void setupButton(JButton button, int yPosition, JPanel panel) {
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 11));
        button.setBackground(Color.RED);
        button.setBounds(28, yPosition, 100, 23);
        panel.add(button);
    }

    // Method to open a new frame
    private void openFrame(JFrame frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(this);
    }
}
