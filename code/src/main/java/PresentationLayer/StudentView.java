package PresentationLayer;

import DataLayer.model.Student;

import javax.swing.*;

public class StudentView {

    StudentController studentController;

    private JPanel panel;
    private JTextField nameTextField;
    private JButton editPersonalInformationButton;
    private JButton viewEnrolledButton;
    private JTextField icnTextField;
    private JTextField cnpTextField;
    private JTextField addressTextField;
    private JTextField inTextField;
    private JTextField groupTextField;
    private JButton finishUpdateButton;

    public StudentView(StudentController studentController) {

        this.studentController = studentController;

        JFrame frame = new JFrame("Student");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Student student = studentController.getStudent();

        nameTextField.setText(student.getName());
        icnTextField.setText(student.getIcnumber());
        cnpTextField.setText(student.getCnp());
        addressTextField.setText(student.getAddress());
        inTextField.setText(student.getIdentificationnumber());
        groupTextField.setText(student.getGroup());

        nameTextField.setEditable(false);
        icnTextField.setEditable(false);
        cnpTextField.setEditable(false);
        addressTextField.setEditable(false);
        inTextField.setEditable(false);
        groupTextField.setEditable(false);

        viewEnrolledButton.addActionListener(e -> {

        });
        editPersonalInformationButton.addActionListener(e -> {

        });
    }
}
