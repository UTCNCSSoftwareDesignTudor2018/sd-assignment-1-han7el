package PresentationLayer.controllers;

import BusinessLogicLayer.bll.EnrolmentBLL;
import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.bll.TeacherBLL;
import DataLayer.model.*;
import javax.swing.*;
import java.util.ArrayList;

public class TeacherController {

    private StudentBLL studentBLL;
    private TeacherBLL teacherBLL;
    private EnrolmentBLL enrolmentBLL;
    private Teacher teacher;

    public TeacherController(User user){
        this.enrolmentBLL = new EnrolmentBLL();
        this.teacherBLL = new TeacherBLL();
        this.studentBLL = new StudentBLL();
        this.enrolmentBLL = new EnrolmentBLL();
        this.teacher = teacherBLL.getSpecificTeacher(user);
    }

    public void updateTeacher(){

        try {
            teacherBLL.updateTeacher(teacher);
        }catch (IllegalArgumentException iae){
            JOptionPane.showMessageDialog(null,iae.getMessage());
        }
    }

    public ArrayList<Student> getAllStudents(){
        return studentBLL.getAllStudents();
    }

    public void insertEnrolment(Enrolment enrolment){
        try{
            enrolmentBLL.insertEnrolment(enrolment);
        }catch (IllegalArgumentException iae){
            JOptionPane.showMessageDialog(null, iae.getMessage());
        }
    }

    public Teacher getTeacher(){
        updateInformation();
        return this.teacher;
    }

    public void insertStudent(Student student) {
        try {
            studentBLL.insertStudent(student);
        }catch (IllegalArgumentException iae){
            JOptionPane.showMessageDialog(null,iae.getMessage());
        }
    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesOfStudent(Student student){
        return enrolmentBLL.getEnrolledCoursesForStudent(student);
    }

    public ArrayList<Course> getAllCourses(){
        return teacherBLL.getAllCourses();
    }

    public void updateInformation(){
        this.teacher = teacherBLL.getSpecificTeacher(teacher);
    }

    public void createReportForStudent(Student student){
        Enrolment enrolment = new Enrolment();
        enrolment.setStudent(student);
        enrolment.setEnrolledCourses(this.getEnrolledCoursesOfStudent(student));
        teacherBLL.createReport(enrolment);
    }
}
