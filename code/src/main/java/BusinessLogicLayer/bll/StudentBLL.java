package BusinessLogicLayer.bll;

import BusinessLogicLayer.validators.GnValidator;
import BusinessLogicLayer.validators.InValidator;
import BusinessLogicLayer.validators.Validator;
import DataLayer.dao.StudentDAO;
import DataLayer.model.Student;
import DataLayer.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boros on 3/27/2018.
 */
public class StudentBLL {

    private StudentDAO studentDAO;
    private UserBLL userBLL;
    private List<Validator<Student>> validators;

    public StudentBLL(){
        studentDAO = new StudentDAO();
        userBLL = new UserBLL();
        validators = new ArrayList<Validator<Student>>();
        validators.add(new InValidator());
        validators.add(new GnValidator());
    }

    public void insertStudent(Student student){
        User inserteduser = userBLL.insertUser((User)student);
        for(Validator<Student> sv: validators){
            sv.validate(student);
        }
        studentDAO.insertStudent((Student)inserteduser);
    }

    public void deleteStudent(Student student){
        userBLL.deleteUser((User)student);
        studentDAO.deleteStudent(student);
    }

    public void updateStudent(Student student){
        studentDAO.updateStudent(student);
    }

    public ArrayList<Student> getAllStudents(){
       return studentDAO.getAllStudents();
    }
}
