package PresentationLayer;

import DataLayer.model.Teacher;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherView {

    TeacherController teacherController;
    Teacher teacher;

    private JPanel panel;
    private JLabel personalInformationLabel;
    private JLabel nameLabel;
    private JLabel icnLabel;
    private JLabel cnpLabel;
    private JLabel addressLabel;
    private JTextField nameTextField;
    private JButton editPersonalInformationButton;
    private JButton addNewStudent;
    private JTextField icnTextField;
    private JTextField cnpTextField;
    private JTextField addressTextField;
    private JButton finishUpdateButton;
    private JButton processEnrolmentButton;

    public TeacherView(TeacherController teacherController){
        this.teacherController = teacherController;

        JFrame frame = new JFrame("Teacher");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        updatePersonalInformation();

        setTextFields(false);

        finishUpdateButton.setEnabled(false);

        editPersonalInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTextFields(true);

                finishUpdateButton.setEnabled(true);
                editPersonalInformationButton.setEnabled(false);
            }
        });

        finishUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishUpdateButton.setEnabled(false);
                editPersonalInformationButton.setEnabled(true);
                Teacher editedTeacher = teacherController.getTeacher();
                editedTeacher.setName(nameTextField.getText());
                editedTeacher.setIcnumber(icnTextField.getText());
                editedTeacher.setCnp(cnpTextField.getText());
                editedTeacher.setAddress(addressTextField.getText());
                teacherController.updateTeacher();

                updatePersonalInformation();
                setTextFields(false);
            }
        });
        addNewStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddNewStudentView(teacherController);
            }
        });
        processEnrolmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnrolmentTeacherView(teacherController);
            }
        });
    }

    public void updatePersonalInformation(){
        teacher = teacherController.getTeacher();
        nameTextField.setText(teacher.getName());
        icnTextField.setText(teacher.getIcnumber());
        cnpTextField.setText(teacher.getCnp());
        addressTextField.setText(teacher.getAddress());
    }

    public void setTextFields(boolean bol){
        nameTextField.setEditable(bol);
        icnTextField.setEditable(bol);
        cnpTextField.setEditable(bol);
        addressTextField.setEditable(bol);
    }

}
