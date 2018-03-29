package PresentationLayer;

import DataLayer.model.Enrolment;
import DataLayer.model.Student;

import javax.swing.*;

public class EnrolmentStudentView {

    StudentController studentController;

    private JTable coursesTable;
    private JPanel panel;

    public EnrolmentStudentView(StudentController studentController){
        
        this.studentController = studentController;

        JFrame frame = new JFrame("Enrolment");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
