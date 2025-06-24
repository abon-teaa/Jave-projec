package quiz.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserInfo extends JFrame implements ActionListener {
    JButton back;
    JTextArea userInfoArea;

    UserInfo() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout((LayoutManager)null);
        
        JLabel heading = new JLabel("Registered Users");
        heading.setBounds(50, 20, 300, 30);
        heading.setFont(new Font("Viner Hand ITC", 1, 28));
        heading.setForeground(new Color(30, 144, 254));
        this.add(heading);
        
        userInfoArea = new JTextArea();
        userInfoArea.setEditable(false);
        userInfoArea.setFont(new Font("Tahoma", 0, 16));
        
        JScrollPane scrollPane = new JScrollPane(userInfoArea);
        scrollPane.setBounds(50, 70, 650, 400);
        this.add(scrollPane);
        
        loadUserData();
        
        this.back = new JButton("Back");
        this.back.setBounds(300, 500, 150, 30);
        this.back.setBackground(new Color(30, 144, 254));
        this.back.setForeground(Color.WHITE);
        this.back.addActionListener(this);
        this.add(this.back);
        
        this.setSize(750, 600);
        this.setLocation(350, 100);
        this.setVisible(true);
    }

    private void loadUserData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    content.append("Username: ").append(parts[0])
                          .append("\nRegistered: ").append(parts[1])
                          .append("\n----------------------------\n");
                }
            }
            reader.close();
            userInfoArea.setText(content.toString());
        } catch (IOException e) {
            userInfoArea.setText("No user data found or error reading file.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.back) {
            this.setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new UserInfo();
    }
}