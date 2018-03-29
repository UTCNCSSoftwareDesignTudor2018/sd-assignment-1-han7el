package PresentationLayer;

import DataLayer.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EnrolmentTeacherView {

    TeacherController teacherController;
    ArrayList<Student> students;
    ArrayList<EnrolledCourse> enrolledCourses;

    private JPanel panel;
    private JTable studentsTable;
    private JTable coursesTable;
    private JScrollPane studentsTableScroll;
    private JScrollPane coursesTableScroll;
    private JButton editStudentEnrolmentButton;
    private JButton viewCoursesButton;
    private JButton generateReportButton;


    public EnrolmentTeacherView(TeacherController teacherController) {

        this.teacherController = teacherController;

        JFrame frame = new JFrame("Enrolment");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


        studentsTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null,},
                },
                new String[]{
                        "Name","Group"
                }
        ));
        studentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //studentsTableScroll.setViewportView(studentsTable);


        coursesTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null,null},
                },
                new String[]{
                        "Name","Grade","Date"
                }
        ));
        coursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //coursesTableScroll.setViewportView(coursesTable);

        updateStudentsTable();

        editStudentEnrolmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentsTable.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "No student selected!");
                }
                else{
                    Student currentStudent = students.get(studentsTable.getSelectedRow());
                    new ProcessEnrolmentView(teacherController,currentStudent);
                }
            }
        });
        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(studentsTable.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "No student selected!");
                }
                else{
                    updateCoursesTable();
                }
            }
        });
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Enrolment enrolment = new Enrolment();
                teacherController.createReportForStudent(students.get(studentsTable.getSelectedRow()));
            }
        });
    }
    public void updateStudentsTable(){
        students = teacherController.getAllStudents();
        String s[][] = new String[students.size()][2];
        for (int i = 0; i < students.size(); i++) {
            s[i][0]=students.get(i).getName();
            s[i][1]=students.get(i).getGroup();
        }
        studentsTable.setModel(new DefaultTableModel(s,new String[]{"Name","Group"}));
    }

    public void updateCoursesTable(){
        Student student = students.get(studentsTable.getSelectedRow());
        enrolledCourses = teacherController.getEnrolledCoursesOfStudent(student);
        String s[][] = new String[enrolledCourses.size()][3];
        for (int i = 0; i < enrolledCourses.size(); i++) {
            s[i][0]=enrolledCourses.get(i).getCourse().getName();
            s[i][1]=String.valueOf(enrolledCourses.get(i).getGrade());
            s[i][2]=enrolledCourses.get(i).getDate().toString();
        }
        coursesTable.setModel(new DefaultTableModel(s,new String[]{"Name","Grade","Date"}));
    }


}
