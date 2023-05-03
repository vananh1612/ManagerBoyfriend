package client.controlles;

import client.models.Client;
import client.views.LoginView;
import shared.models.Notification;
import shared.models.User;

import java.util.Arrays;

public class LoginController {
    public Client client;
    private LoginView loginView;

    public LoginController() {
        client = new Client();
    }

    public void login() {
        loginView = new LoginView();
        loginView.displayFrame();
        loginView.loginButton.addActionListener(e -> {
            String username = loginView.usernameField.getText();
            String password = loginView.passwordField.getText();
            try {
                Notification<User> notification = client.login(username, password);
                loginView.showMessage(notification.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void main(String[] args) {
        new LoginController().login();
    }
}
