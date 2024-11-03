package cwead1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;

public class Send extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea messageArea;
    private JTextField recipientField;
    private JTextArea billArea; // New JTextArea for bill details

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Send frame = new Send();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Send() {
        setTitle("Send Email");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 631, 480);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 255, 128));
        panel_1.setBounds(0, 0, 154, 439);
        contentPane.add(panel_1);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Send.class.getResource("/images/3916632.png")));
        lblNewLabel.setForeground(new Color(255, 128, 0));
        lblNewLabel.setBounds(10, 23, 134, 128);
        panel_1.add(lblNewLabel);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(128, 255, 128));
        panel_1_1.setBounds(147, 0, 483, 450);
        contentPane.add(panel_1_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Send Email");
        lblNewLabel_1_1.setFont(new Font("Wide Latin", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(157, 11, 176, 29);
        panel_1_1.add(lblNewLabel_1_1);
        
        JLabel lblRecipient = new JLabel("Recipient Email:");
        lblRecipient.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblRecipient.setBounds(20, 50, 120, 25);
        panel_1_1.add(lblRecipient);
        
        recipientField = new JTextField();
        recipientField.setBounds(150, 50, 310, 25);
        panel_1_1.add(recipientField);
        
        messageArea = new JTextArea();
        messageArea.setBounds(20, 90, 440, 100);
        panel_1_1.add(messageArea);
        
        billArea = new JTextArea(); // Initialize billArea
        billArea.setBounds(20, 200, 440, 150);
        billArea.setEditable(false); // Make it non-editable
        billArea.setBackground(new Color(240, 240, 240)); // Light background
        panel_1_1.add(billArea); // Add to panel

        JButton sendButton = new JButton("Send Email");
        sendButton.setBackground(new Color(255, 255, 0));
        sendButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        sendButton.setBounds(150, 370, 150, 30);
        panel_1_1.add(sendButton);
        
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipientEmail = recipientField.getText().trim();
                String messageText = messageArea.getText().trim();
                if (validateInput(recipientEmail, messageText)) {
                    sendEmail(recipientEmail, messageText);
                }
            }
        });
    }

    private boolean validateInput(String recipientEmail, String messageText) {
        if (recipientEmail.isEmpty() || !isValidEmail(recipientEmail)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (messageText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a message before sending.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,3}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private void sendEmail(String recipientEmail, String messageText) {
        String host = "smtp.gmail.com";
        final String user = "jhasirulakshan1998@gmail.com"; // your email
        final String password = "eqsb lfkf cdgo yqqa"; // your app password

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Bill Amount Notification");
            message.setText(messageText);

            Transport.send(message);
            JOptionPane.showMessageDialog(this, "Email Sent Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(this, "Failed to send email: " + e.getMessage(), "Email Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to set bill details in the JTextArea
    public void setBillDetails(String billDetails) {
        billArea.setText(billDetails);
    }
}
