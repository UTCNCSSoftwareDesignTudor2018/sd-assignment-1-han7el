package PresentationLayer.views;

import DataLayer.model.*;
import PresentationLayer.controllers.TeacherController;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                if(coursesForEnrolmentTable.getSelectedRow()==-1){
                    JOptionPane.showMessageDialog(null,"Course not selected!\n");
                }

                else{
                    Course newCourse = allCourses.get(coursesForEnrolmentTable.getSelectedRow());

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate localDate = LocalDate.parse(dateTextField.getText(), formatter);
                        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

                    Enrolment newEnrolment = new Enrolment();
                    newEnrolment.setStudent(enrolment.getStudent());
                    newEnrolment.addCourse(newCourse, Integer.valueOf(gradeTextField.getText()), sqlDate);
                    teacherController.insertEnrolment(newEnrolment);

                    enrolment.setEnrolledCourses(teacherController.getEnrolledCoursesOfStudent(enrolment.getStudent()));
                    updateCoursesTable();
                    }catch (DateTimeParseException ex){
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
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
