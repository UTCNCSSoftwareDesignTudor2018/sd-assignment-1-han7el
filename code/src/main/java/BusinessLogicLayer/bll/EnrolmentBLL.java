package BusinessLogicLayer.bll;

import BusinessLogicLayer.validators.GrValidator;
import BusinessLogicLayer.validators.Validator;
import DataLayer.dao.EnrolmentDAO;
import DataLayer.model.EnrolledCourse;
import DataLayer.model.Enrolment;
import DataLayer.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boros on 3/27/2018.
 */
public class EnrolmentBLL {

    private EnrolmentDAO enrolmentDAO;
    private List<Validator<EnrolledCourse>> validators;

    public EnrolmentBLL(){
        enrolmentDAO = new EnrolmentDAO();
        validators = new ArrayList<Validator<EnrolledCourse>>();
        validators.add(new GrValidator());
    }

    public void insertEnrolment(Enrolment enrolment){
        for(EnrolledCourse enrolledCourse: enrolment.getEnrolledCourses()){
            for(Validator<EnrolledCourse> ecv: validators){
                ecv.validate(enrolledCourse);
            }
        }

        for(EnrolledCourse enrolledCourse: enrolment.getEnrolledCourses()){
            enrolmentDAO.insertEnrolment(enrolledCourse,enrolment.getStudent());
        }
    }

    public ArrayList<EnrolledCourse> getEnrolledCoursesForStudent(Student student){
        return enrolmentDAO.getEnrolledCoursesForStudent(student);
    }
}
