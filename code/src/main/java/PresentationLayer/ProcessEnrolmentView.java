package PresentationLayer;

import DataLayer.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProcessEnrolmentView {

    TeacherController teacherController;

    private JTable coursesForEnrolmentTable;
    private JTextField gradeTextField;
    private JTextField dateTextField;
    private JPanel panel;
    private JButton enrollCourseButton;
    private Enrolment enrolment;
    private ArrayList<Course> allCourses;

    public ProcessEnrolmentView(TeacherController teacherController,Student student) {

       this.teacherController = teacherController;
       enrolment = new Enrolment();
       enrolment.setStudent(student);
       enrolment.setEnrolledCourses(teacherController.getEnrolledCoursesOfStudent(student));

       JFrame frame = new JFrame("Process Enrolment");
       frame.setContentPane(panel);
       frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       frame.pack();
       frame.setVisible(true);
       frame.setLocationRelativeTo(null);

       updateCoursesTable();

       coursesForEnrolmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

       enrollCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(coursesForEnrolmentTable.getSelectedRow()!=-1) {
                    Course newCourse = allCourses.get(coursesForEnrolmentTable.getSelectedRow());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(dateTextField.getText(), formatter);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

                    Enrolment newEnrolment = new Enrolment();
                    newEnrolment.setStudent(enrolment.getStudent());
                    newEnrolment.addCourse(newCourse, Integer.valueOf(gradeTextField.getText()), sqlDate);
                    teacherController.insertEnrolment(newEnrolment);

                    enrolment.setEnrolledCourses(teacherController.getEnrolledCoursesOfStudent(enrolment.getStudent()));
                    updateCoursesTable();
                }
            }
        });
    }

    public void updateCoursesTable(){
        allCourses = teacherController.getAllCourses();
        String s[][] = new String[allCourses.size()][2];
        for (int i = 0; i < allCourses.size(); i++) {
            s[i][0]=allCourses.get(i).getName();
            for(EnrolledCourse enrolledCourse: enrolment.getEnrolledCourses()){
                if(enrolledCourse.getCourse().equals(allCourses.get(i))){
                    s[i][1]="ENROLLED";
                }
            }
        }
        coursesForEnrolmentTable.setModel(new DefaultTableModel(s,new String[]{"Name","Enrolled"}));
    }
}
