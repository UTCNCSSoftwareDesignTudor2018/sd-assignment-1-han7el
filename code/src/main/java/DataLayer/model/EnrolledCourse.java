package DataLayer.model;

import java.sql.Date;

/**
 * Created by Boros on 3/26/2018.
 */
public class EnrolledCourse {

    private Course course;
    private int grade;
    private Date date;

    public EnrolledCourse(Course course){
        this.course = course;
    }

    public EnrolledCourse(Course course, int grade, Date date){
        setGrade(grade);
        setCourse(course);
        setDate(date);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
