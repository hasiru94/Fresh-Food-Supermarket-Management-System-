package cwead1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Report extends JFrame {
    private JPanel contentPane;
    private JTextArea textAreaReport;

    public Report(String billContent) {
        setTitle("Bill Report");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());
        
        textAreaReport = new JTextArea();
        textAreaReport.setEditable(false);
        textAreaReport.setText(billContent);
        JScrollPane scrollPane = new JScrollPane(textAreaReport);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        JButton btnPrint = new JButton("Print Bill");
        btnPrint.setBackground(new Color(255, 255, 0));
        btnPrint.addActionListener(e -> printBill(billContent));
        contentPane.add(btnPrint, BorderLayout.SOUTH);
    }

    private void printBill(String billContent) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                
                contentStream.beginText();
                contentStream.newLineAtOffset(25, 750);

                String[] lines = billContent.split("\n");
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -15);
                }
                
                contentStream.endText();
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");
            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                document.save(fileToSave.getAbsolutePath() + ".pdf");
                JOptionPane.showMessageDialog(this, "Bill printed successfully!");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error printing bill: " + ex.getMessage());
        }
    }
}
