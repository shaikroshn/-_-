import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordCounter extends JFrame implements ActionListener {
    JTextArea textArea;
    JButton countButton;
    JLabel imageLabel;
    ImageIcon originalImageIcon;
    public WordCounter() {
        setTitle("Word Counter Tool");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        setLocationRelativeTo(null);  
        getContentPane().setBackground(new Color(0xF0F8FF)); 
        originalImageIcon = new ImageIcon("D:/JavaProjects/word.jpg");  
        imageLabel = new JLabel(originalImageIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Border imageOuterBorder = BorderFactory.createLineBorder(new Color(0xADD8E6), 5);  // Light blue border
        imageLabel.setBorder(BorderFactory.createCompoundBorder(imageOuterBorder, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setBackground(new Color(0xE6E6FA));
        add(imagePanel); 
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(0xF0F8FF));
        textArea = new JTextArea(5, 20);
        textArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0x20B2AA), 4), "Enter text"));  // Light sea green border
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textArea.setBackground(new Color(0xD3D3D3));  
        rightPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
        countButton = new JButton("Count Words & Characters");
        countButton.setFont(new Font("Arial", Font.BOLD, 14));
        countButton.setBackground(new Color(0x5F9EA0));
        countButton.setForeground(Color.WHITE);
        countButton.setFocusPainted(false);
        countButton.setBorder(BorderFactory.createLineBorder(new Color(0x20B2AA), 3, true));  
        countButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        countButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(countButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(rightPanel);

        setVisible(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeImage();
            }
        });
    }
    private void resizeImage() {
        int width = imageLabel.getWidth();
        int height = imageLabel.getHeight();
        Image scaledImage = originalImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = textArea.getText();
        int wordCount = 0, charCount = 0, spaceCount = 0;

        if (!text.isEmpty()) {
            String[] words = text.trim().split("\\s+");
            wordCount = words.length;
            charCount = text.replace(" ", "").length();
            spaceCount = text.length() - charCount;  
        }
        JDialog resultDialog = new JDialog(this, "Results", true);
        resultDialog.setSize(300, 150);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.getContentPane().setBackground(new Color(0xE0FFFF)); 
        
        JLabel resultLabel = new JLabel("<html><center>Word Count: " + wordCount + "<br>Character Count: " + charCount + "<br>Space Count: " + spaceCount + "</center></html>", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultDialog.add(resultLabel);
        resultDialog.setVisible(true);
    }
    public static void main(String[] args) {
        new WordCounter();
    }
}
