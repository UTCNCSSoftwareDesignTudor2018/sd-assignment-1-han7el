package PresentationLayer;

import BusinessLogicLayer.bll.EnrolmentBLL;
import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.bll.TeacherBLL;
import BusinessLogicLayer.bll.UserBLL;
import DataLayer.model.EnrolledCourse;
import DataLayer.model.Student;
import DataLayer.model.Teacher;
import DataLayer.model.User;

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

    public void insertStudent(Student student){
        studentBLL.insertStudent(student);
    }

    public void updateStudent(Student student){
        studentBLL.updateStudent(student);
    }

    public void deleteStudent(Student student){
        studentBLL.deleteStudent(student);
    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesOfStudent(Student student){
        return enrolmentBLL.getEnrolledCoursesForStudent(student);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
