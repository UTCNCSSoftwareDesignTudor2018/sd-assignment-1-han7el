package BusinessLogicLayer.bll;

import DataLayer.dao.TeacherDAO;
import DataLayer.model.Teacher;
import DataLayer.model.User;

import java.util.ArrayList;

/**
 * Created by Boros on 3/27/2018.
 */
public class TeacherBLL {

    private TeacherDAO teacherDAO;
    private UserBLL userBLL;

    public TeacherBLL(){
        teacherDAO = new TeacherDAO();
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
        teacherDAO.updateTeacher(teacher);
    }

    public ArrayList<Teacher> getAllTeachers(){
        return teacherDAO.getAllTeachers();
    }
}
