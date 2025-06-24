package quiz.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Login extends JFrame implements ActionListener {
    JButton rules, back, viewUsers, deleteUser;
    JTextField tfname;

    public Login() {
        setTitle("Quiz Application Login");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.jpeg.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        add(image);
        
        // Heading
        JLabel heading = new JLabel("Simple Minds");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        
        // Name Label
        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setBounds(810, 150, 300, 20);
        nameLabel.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        nameLabel.setForeground(new Color(30, 144, 254));
        add(nameLabel);
        
        // Text Field
        tfname = new JTextField();
        tfname.setBounds(735, 200, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);
        
        // Buttons
        rules = createButton("Rules", 735, 270, 120, 25);
        back = createButton("Back", 915, 270, 120, 25);
        viewUsers = createButton("View Users", 735, 320, 120, 25);
        deleteUser = createButton("Delete User", 915, 320, 120, 25);
        
        setSize(1200, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(30, 144, 254));
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        add(button);
        return button;
    }

    private void saveUserInfo(String name) {
        try (FileWriter writer = new FileWriter("users.txt", true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(name + "," + timestamp + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            String name = tfname.getText().trim();
            if (!name.isEmpty()) {
                saveUserInfo(name);
                setVisible(false);
                new Rules(name);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        } else if (ae.getSource() == viewUsers) {
            setVisible(false);
            new UserInfo();
        } else if (ae.getSource() == deleteUser) {
            setVisible(false);
            new DeleteUser();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}