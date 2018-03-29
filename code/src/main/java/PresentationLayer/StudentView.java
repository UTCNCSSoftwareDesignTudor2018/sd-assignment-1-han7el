package PresentationLayer;

import DataLayer.model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentView {

    StudentController studentController;

    private JPanel panel;
    private JButton editPersonalInformationButton;
    private JButton viewEnrolledButton;
    private JButton finishUpdateButton;
    private JTextField nameTextField;
    private JTextField icnTextField;
    private JTextField cnpTextField;
    private JTextField addressTextField;
    private JTextField inTextField;
    private JTextField groupTextField;

    Student student;

    public StudentView(StudentController studentController) {

        this.studentController = studentController;

        JFrame frame = new JFrame("Student");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        updatePersonalInformation();

        setTextFields(false);

        finishUpdateButton.setEnabled(false);

        viewEnrolledButton.addActionListener(e -> {
            new EnrolmentStudentView(studentController);
        });
        editPersonalInformationButton.addActionListener(e -> {
            setTextFields(true);

            finishUpdateButton.setEnabled(true);
            editPersonalInformationButton.setEnabled(false);
        });
        finishUpdateButton.addActionListener(e -> {
            finishUpdateButton.setEnabled(false);
            editPersonalInformationButton.setEnabled(true);
            Student editedStudent = studentController.getStudent();
            editedStudent.setName(nameTextField.getText());
            editedStudent.setIcnumber(icnTextField.getText());
            editedStudent.setCnp(cnpTextField.getText());
            editedStudent.setAddress(addressTextField.getText());
            editedStudent.setGroup(groupTextField.getText());
            editedStudent.setIdentificationnumber(inTextField.getText());
            studentController.updateStudent(editedStudent);

            updatePersonalInformation();
            setTextFields(false);
        });
    }

    public void updatePersonalInformation(){
        student = studentController.getStudent();
        nameTextField.setText(student.getName());
        icnTextField.setText(student.getIcnumber());
        cnpTextField.setText(student.getCnp());
        addressTextField.setText(student.getAddress());
        inTextField.setText(student.getIdentificationnumber());
        groupTextField.setText(student.getGroup());
    }

    public void setTextFields(boolean bol){
        nameTextField.setEditable(bol);
        icnTextField.setEditable(bol);
        cnpTextField.setEditable(bol);
        addressTextField.setEditable(bol);
        inTextField.setEditable(bol);
        groupTextField.setEditable(bol);
    }
}
