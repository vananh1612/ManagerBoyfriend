package client.views;

import javax.swing.*;
import java.awt.*;

public class LoginView {

    public JFrame loginFrame;
    public JTextField usernameField;
    public JPasswordField passwordField;
    public JButton loginButton;

    public LoginView() {
        init();
    }

    public void init() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(loginFrame, message);
    }

    public void displayFrame() {
        loginFrame.setVisible(true);
    }

    public void hideFrame() {
        loginFrame.setVisible(false);
    }

}
