package PresentationLayer;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNewStudentView {

    TeacherController teacherController;

    private JPanel panel;
    private JTextField nameTextField;
    private JTextField icnTextField;
    private JTextField cnpTextField;
    private JTextField addressTextField;
    private JTextField inTextField;
    private JTextField groupTextField;
    private JButton addStudentButton;
    private JTextField usernameTextField;
    private JTextField passwordTestField;

    public AddNewStudentView(TeacherController teacherController) {

        this.teacherController = teacherController;

        JFrame frame = new JFrame("Add new student\n");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student newStudent = new Student();

                newStudent.setUsertype(Function.STUDENT_TYPE);
                newStudent.setUsername(usernameTextField.getText());
                newStudent.setPassword(passwordTestField.getText());
                newStudent.setName(nameTextField.getText());
                newStudent.setIcnumber(icnTextField.getText());
                newStudent.setCnp(cnpTextField.getText());
                newStudent.setIdentificationnumber(inTextField.getText());
                newStudent.setAddress(addressTextField.getText());
                newStudent.setIdentificationnumber(inTextField.getText());
                newStudent.setGroup(groupTextField.getText());

                teacherController.addNewStudent(newStudent);
            }
        });
    }
}
