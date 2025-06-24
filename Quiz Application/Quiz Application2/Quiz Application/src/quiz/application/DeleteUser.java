package quiz.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteUser extends JFrame implements ActionListener {
    JButton delete;
    JButton back;
    JTextField usernameField;

    DeleteUser() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setLayout((LayoutManager)null);
        
        JLabel heading = new JLabel("Delete User");
        heading.setBounds(50, 20, 300, 30);
        heading.setFont(new Font("Viner Hand ITC", 1, 28));
        heading.setForeground(new Color(30, 144, 254));
        this.add(heading);
        
        JLabel usernameLabel = new JLabel("Enter username to delete:");
        usernameLabel.setBounds(50, 100, 200, 30);
        usernameLabel.setFont(new Font("Tahoma", 0, 16));
        this.add(usernameLabel);
        
        usernameField = new JTextField();
        usernameField.setBounds(250, 100, 300, 30);
        usernameField.setFont(new Font("Tahoma", 0, 16));
        this.add(usernameField);
        
        this.delete = new JButton("Delete");
        this.delete.setBounds(150, 200, 150, 30);
        this.delete.setBackground(new Color(30, 144, 254));
        this.delete.setForeground(Color.WHITE);
        this.delete.addActionListener(this);
        this.add(this.delete);
        
        this.back = new JButton("Back");
        this.back.setBounds(350, 200, 150, 30);
        this.back.setBackground(new Color(30, 144, 254));
        this.back.setForeground(Color.WHITE);
        this.back.addActionListener(this);
        this.add(this.back);
        
        this.setSize(600, 300);
        this.setLocation(400, 200);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.delete) {
            String username = usernameField.getText().trim();
            if (!username.isEmpty()) {
                deleteUser(username);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a username");
            }
        } else if (ae.getSource() == this.back) {
            this.setVisible(false);
            new Login();
        }
    }

    private void deleteUser(String usernameToDelete) {
        try {
            File inputFile = new File("users.txt");
            File tempFile = new File("temp_users.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            FileWriter writer = new FileWriter(tempFile);

            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                String[] parts = currentLine.split(",");
                if (parts.length > 0 && parts[0].equalsIgnoreCase(usernameToDelete)) {
                    found = true;
                    continue; // skip this line (don't write to temp file)
                }
                writer.write(currentLine + "\n");
            }
            writer.close();
            reader.close();

            if (found) {
                if (inputFile.delete()) {
                    if (!tempFile.renameTo(inputFile)) {
                        throw new IOException("Could not rename temp file");
                    }
                    JOptionPane.showMessageDialog(this, "User '" + usernameToDelete + "' deleted successfully");
                } else {
                    throw new IOException("Could not delete original file");
                }
            } else {
                JOptionPane.showMessageDialog(this, "User '" + usernameToDelete + "' not found");
                tempFile.delete(); // delete the temp file as no changes were made
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new DeleteUser();
    }
}