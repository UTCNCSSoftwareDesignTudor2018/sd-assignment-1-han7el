package PresentationLayer.controllers;

import BusinessLogicLayer.bll.EnrolmentBLL;
import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.bll.UserBLL;
import DataLayer.model.*;

import javax.swing.*;

import java.util.ArrayList;

public class StudentController {

    private StudentBLL studentBLL;
    private UserBLL userBLL;
    private EnrolmentBLL enrolmentBLL;

    private Enrolment enrolment;

    public StudentController(User user){
        this.enrolmentBLL = new EnrolmentBLL();
        this.studentBLL = new StudentBLL();
        this.userBLL = new UserBLL();
        this.enrolment = new Enrolment();
        this.enrolment.setStudent(studentBLL.getSpecificStudent(user));
        this.enrolment.setEnrolledCourses(enrolmentBLL.getEnrolledCoursesForStudent(enrolment.getStudent()));
    }

    public void updateStudent(Student student){
        try{
            studentBLL.updateStudent(student);
        }catch (IllegalArgumentException iae){
            JOptionPane.showMessageDialog(null,iae.getMessage());
        }
    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesOfStudent(){
        return enrolmentBLL.getEnrolledCoursesForStudent(enrolment.getStudent());
    }

    public Enrolment getEnrolment() {
        updateStudentInformation();
        return enrolment;
    }

    public void setStudent(Student student) {
        this.enrolment.setStudent(student);
    }

    public Student getStudent(){
        updateStudentInformation();
        return enrolment.getStudent();
    }

    public void updateStudentInformation(){
        this.enrolment.setStudent(studentBLL.getSpecificStudent(enrolment.getStudent()));
        this.enrolment.setEnrolledCourses(enrolmentBLL.getEnrolledCoursesForStudent(enrolment.getStudent()));
    }
}
