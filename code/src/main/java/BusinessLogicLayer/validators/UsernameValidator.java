package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.User;

/**
 * Created by Boros on 3/27/2018.
 */
public class UsernameValidator implements Validator<User> {
    public void validate(User user) {
        if(!Function.isWellFormed(user.getUsername(),Function.ADDRESS_NAME_LENGTH,Function.WEAK)){
            throw new IllegalArgumentException("Name is invalid (<50 characters) for: " + user.getName());
        }
    }
}
