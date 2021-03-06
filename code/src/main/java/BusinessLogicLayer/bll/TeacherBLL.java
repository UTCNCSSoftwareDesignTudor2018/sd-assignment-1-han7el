package BusinessLogicLayer.bll;

import BusinessLogicLayer.validators.*;
import DataLayer.dao.CourseDAO;
import DataLayer.dao.TeacherDAO;
import DataLayer.model.Course;
import DataLayer.model.Enrolment;
import DataLayer.model.Teacher;
import DataLayer.model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boros on 3/27/2018.
 */
public class TeacherBLL {

    private TeacherDAO teacherDAO;
    private CourseDAO courseDAO;
    private UserBLL userBLL;

    public TeacherBLL(){
        teacherDAO = new TeacherDAO();
        courseDAO = new CourseDAO();
        userBLL = new UserBLL();
    }

    public void insertTeacher(Teacher teacher){
        User inserteduser = userBLL.insertUser((User)teacher);
        teacherDAO.insertTeacher((Teacher)inserteduser);
    }

    public void deleteTeacher(Teacher teacher){
        userBLL.deleteUser((User)teacher);
        teacherDAO.deleteTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher){
        for(Validator<User> sv: userBLL.getValidators()){
            sv.validate(teacher);
        }
        teacherDAO.updateTeacher(teacher);
    }

    public ArrayList<Teacher> getAllTeachers(){
        return teacherDAO.getAllTeachers();
    }

    public Teacher getSpecificTeacher(User user){
        return teacherDAO.getSpecificTeacher(user);
    }

    public ArrayList<Course> getAllCourses(){
        return courseDAO.getAllCourses();
    }

    public void createReport(Enrolment enrolment){
        ReportCreator.createReport(enrolment);
    }
}
