package PresentationLayer;

import BusinessLogicLayer.bll.EnrolmentBLL;
import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.bll.TeacherBLL;
import BusinessLogicLayer.bll.UserBLL;
import DataLayer.model.*;

import java.lang.reflect.Array;
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

    public void insertTeacher(){
        teacherBLL.insertTeacher(teacher);
    }

    public void updateTeacher(){
        teacherBLL.updateTeacher(teacher);
    }

    public void deleteTeacher(){
        teacherBLL.deleteTeacher(teacher);
    }

    public ArrayList<Student> getAllStudents(){
        return studentBLL.getAllStudents();
    }

    public void insertEnrolment(Enrolment enrolment){
        enrolmentBLL.insertEnrolment(enrolment);
    }

    public Teacher getTeacher(){
       return this.teacher;
    }

    public void addNewStudent(Student student) {
        studentBLL.insertStudent(student);
    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesOfStudent(Student student){
        return enrolmentBLL.getEnrolledCoursesForStudent(student);
    }

    public ArrayList<Course> getAllCourses(){
        return teacherBLL.getAllCourses();
    }
}
