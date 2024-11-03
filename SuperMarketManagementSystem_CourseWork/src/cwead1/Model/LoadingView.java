package cwead1.Model;

import javax.swing.*;

import cwead1.Loading_Page;

import java.awt.*;

public class LoadingView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JProgressBar progressBar;
    private JLabel lblPercentage;

    public LoadingView() {
    	setTitle("Loading_Page");
        setBackground(new Color(192, 192, 192));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 642, 499);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(null);

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(10, 10, 154, 439);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("START");
        lblNewLabel_2.setFont(new Font("Wide Latin", Font.BOLD, 12));
        lblNewLabel_2.setBackground(new Color(255, 0, 128));
        lblNewLabel_2.setForeground(new Color(255, 0, 0));
        lblNewLabel_2.setBounds(38, 196, 86, 26);
        panel_1.add(lblNewLabel_2);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(163, 10, 453, 439);
        contentPane.add(panel_1_1);
        panel_1_1.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("FRESH  FOOD   SUPERMARKET");
        lblNewLabel_1.setForeground(new Color(0, 64, 0));
        lblNewLabel_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 11, 433, 29);
        panel_1_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("MANAGEMENT   SYSTEM");
        lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(59, 51, 338, 29);
        panel_1_1.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Loading_Page.class.getResource("/images/images (2).jpeg")));
        lblNewLabel.setBounds(131, 119, 174, 150);
        panel_1_1.add(lblNewLabel);
        
        JLabel lblNewLabel_3 = new JLabel("Loading.....");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel_3.setBounds(139, 333, 84, 22);
        panel_1_1.add(lblNewLabel_3);
        
        lblPercentage = new JLabel("0%");
        lblPercentage.setForeground(new Color(0, 128, 0));
        lblPercentage.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblPercentage.setBounds(228, 339, 46, 14);
        panel_1_1.add(lblPercentage);
        
        JLabel lblClose = new JLabel("X");
        lblClose.setForeground(Color.RED);
        lblClose.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblClose.setBounds(436, 0, 17, 21);
        panel_1_1.add(lblClose);
        
        progressBar = new JProgressBar();
        progressBar.setForeground(new Color(255, 0, 0)); // Set the color of the progress bar fill to red

        progressBar.setBackground(new Color(255, 255, 255));
        progressBar.setBounds(10, 446, 616, 14);
        progressBar.setStringPainted(true); // Show progress percentage directly on the bar
        contentPane.add(progressBar);
    }


    public void setProgress(int progress) {
        progressBar.setValue(progress);
        lblPercentage.setText(progress + "%");
    }
}