package PresentationLayer.views;

import DataLayer.model.User;
import PresentationLayer.controllers.Controller;

import javax.swing.*;

public class LoginView {

    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Controller controller;


    public LoginView(Controller controller){

        this.controller = controller;

        JFrame frame = new JFrame("Login");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        loginButton.addActionListener(e -> {
            User user = new User();
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());
            controller.loginUser(user);
        });
    }
}
