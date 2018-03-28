package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.Student;

/**
 * Created by Boros on 3/27/2018.
 */
public class GnValidator implements Validator<Student> {
    public void validate(Student student) {
        if(!Function.isNumeric(student.getGroup())
                && !Function.isWellFormed(student.getGroup(),Function.GN_LENGTH,Function.STRONG)){
            throw new IllegalArgumentException("Group number is invalid for: " + student.getName());
        }
    }
}
