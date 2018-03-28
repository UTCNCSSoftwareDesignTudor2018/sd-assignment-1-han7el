package PresentationLayer;

import DataLayer.model.Teacher;
import com.itextpdf.text.log.SysoCounter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherView {

    TeacherController teacherController;

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
        Teacher teacher = teacherController.getTeacher();

        JFrame frame = new JFrame("Teacher");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        nameTextField.setText(teacher.getName());
        icnTextField.setText(teacher.getIcnumber());
        cnpTextField.setText(teacher.getCnp());
        addressTextField.setText(teacher.getAddress());

        nameTextField.setEditable(false);
        icnTextField.setEditable(false);
        cnpTextField.setEditable(false);
        addressTextField.setEditable(false);

        finishUpdateButton.setEnabled(false);

        editPersonalInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setEditable(true);
                icnTextField.setEditable(true);
                cnpTextField.setEditable(true);
                addressTextField.setEditable(true);

                finishUpdateButton.setEnabled(true);
            }
        });

        finishUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishUpdateButton.setEnabled(false);

                Teacher editedTeacher = teacherController.getTeacher();
                editedTeacher.setName(nameTextField.getText());
                editedTeacher.setIcnumber(icnTextField.getText());
                editedTeacher.setCnp(cnpTextField.getText());
                editedTeacher.setAddress(addressTextField.getText());
                teacherController.updateTeacher();

                nameTextField.setEditable(false);
                icnTextField.setEditable(false);
                cnpTextField.setEditable(false);
                addressTextField.setEditable(false);
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
                new EnrolmentView(teacherController);
            }
        });
    }

}
