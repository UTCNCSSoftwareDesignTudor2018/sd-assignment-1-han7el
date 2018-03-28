package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.User;

/**
 * Created by Boros on 3/27/2018.
 */
public class IcnValidator implements Validator<User> {

    public void validate(User user) {
        if(!Function.isNumeric(user.getIcnumber())
                && !Function.isWellFormed(user.getIcnumber(),Function.ICN_LENGTH,Function.STRONG)){
            throw new IllegalArgumentException("Identity card number is invalid (exactly 6 characters) for: " + user.getName());
        }
    }
}
