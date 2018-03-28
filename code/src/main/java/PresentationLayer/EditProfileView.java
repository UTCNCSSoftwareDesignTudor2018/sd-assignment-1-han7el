package PresentationLayer;

import javax.swing.*;

/**
 * Created by Boros on 3/27/2018.
 */
public class EditProfileView {

    private Controller controller;

    private JTextField nameTextField;
    private JTextField icnTextField;
    private JTextField cnpTextField;
    private JTextField addressTextField;
    private JButton finishUpdateButton;
    private JPanel panel;

    public EditProfileView(Controller controller) {

        this.controller = controller;

        JFrame frame = new JFrame("Edit Profile");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        finishUpdateButton.addActionListener(e -> {

        });

    }
}
