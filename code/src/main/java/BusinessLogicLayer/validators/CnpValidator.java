package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.User;

/**
 * Created by Boros on 3/27/2018.
 */
public class CnpValidator implements Validator<User> {

    public void validate(User user){
        if(!Function.isNumeric(user.getCnp())
                && !Function.isWellFormed(user.getCnp(),Function.CNP_LENGTH,Function.STRONG)){
            throw new IllegalArgumentException("CNP is invalid (exactly 13 characters) for: " + user.getName());
        }
    }
}
