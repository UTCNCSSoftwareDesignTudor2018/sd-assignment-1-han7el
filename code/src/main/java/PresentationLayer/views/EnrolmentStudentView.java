package PresentationLayer.views;

import DataLayer.model.EnrolledCourse;
import PresentationLayer.controllers.StudentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

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

        updateCoursesTable();
    }

    public void updateCoursesTable(){
        ArrayList<EnrolledCourse> enrolledCourses;
        enrolledCourses = studentController.getEnrolledCoursesOfStudent();
        String s[][] = new String[enrolledCourses.size()][3];
        for (int i = 0; i < enrolledCourses.size(); i++) {
            s[i][0]=enrolledCourses.get(i).getCourse().getName();
            s[i][1]=String.valueOf(enrolledCourses.get(i).getGrade());
            s[i][2]=enrolledCourses.get(i).getDate().toString();
        }
        coursesTable.setModel(new DefaultTableModel(s,new String[]{"Name","Grade","Date"}));
    }
}
