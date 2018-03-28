package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.EnrolledCourse;
import DataLayer.model.Enrolment;
import DataLayer.model.User;

/**
 * Created by Boros on 3/27/2018.
 */
public class GrValidator implements Validator<EnrolledCourse> {

    public void validate(EnrolledCourse enrolledCourse) {
        if(enrolledCourse.getGrade()<1 && enrolledCourse.getGrade() >10){
            throw new IllegalArgumentException("Grade is invalid (between 1 and 10)!\n");
        }
    }
}
