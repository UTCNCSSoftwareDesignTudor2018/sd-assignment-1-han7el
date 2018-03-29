package PresentationLayer;

import BusinessLogicLayer.bll.EnrolmentBLL;
import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.bll.TeacherBLL;
import BusinessLogicLayer.bll.UserBLL;
import DataLayer.model.EnrolledCourse;
import DataLayer.model.Student;
import DataLayer.model.Teacher;
import DataLayer.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentController {

    private StudentBLL studentBLL;
    private UserBLL userBLL;
    private EnrolmentBLL enrolmentBLL;

    private Student student;

    public StudentController(User user){
        this.enrolmentBLL = new EnrolmentBLL();
        this.studentBLL = new StudentBLL();
        this.userBLL = new UserBLL();
        this.student = studentBLL.getSpecificStudent(user);
    }

    public void updateStudent(Student student){
        try{
            studentBLL.updateStudent(student);
        }catch (IllegalArgumentException iae){
            JOptionPane.showMessageDialog(null,iae.getMessage());
        }

    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesOfStudent(Student student){
        return enrolmentBLL.getEnrolledCoursesForStudent(student);
    }

    public Student getStudent() {
        updateStudentInformation();
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void updateStudentInformation(){
        this.student = studentBLL.getSpecificStudent(student);
    }
}
