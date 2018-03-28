package DataLayer.model;

import java.util.ArrayList;
import java.sql.Date;

/**
 * Created by Boros on 3/26/2018.
 */
public class Enrolment {

    private Student student;
    private ArrayList<EnrolledCourse> enrolledCourses;

    public Enrolment(){
        enrolledCourses = new ArrayList<EnrolledCourse>();
    }

    public Enrolment(Student student)
    {
        this.student = student;
        enrolledCourses = new ArrayList<EnrolledCourse>();
    }

    public void addCourse(Course course,int grade,Date date)
    {
        EnrolledCourse newEnrolledCourse = new EnrolledCourse(course,grade,date);
        enrolledCourses.add(newEnrolledCourse);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student){
        this.student = student;
    }

    public ArrayList<EnrolledCourse> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<EnrolledCourse> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
