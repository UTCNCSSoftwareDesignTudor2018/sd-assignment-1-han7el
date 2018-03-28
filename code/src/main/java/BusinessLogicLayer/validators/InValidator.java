package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.Student;

/**
 * Created by Boros on 3/27/2018.
 */
public class InValidator implements Validator<Student> {
    public void validate(Student student) {
        if(!Function.isNumeric(student.getIdentificationnumber())
                && !Function.isWellFormed(student.getIdentificationnumber(),Function.IN_LENGTH,Function.STRONG)){
            throw new IllegalArgumentException("Identification number is invalid for: " + student.getName());
        }
    }
}
