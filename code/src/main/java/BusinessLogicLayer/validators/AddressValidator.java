package BusinessLogicLayer.validators;

import BusinessLogicLayer.functions.Function;
import DataLayer.model.User;

/**
 * Created by Boros on 3/27/2018.
 */
public class AddressValidator implements Validator<User> {
    public void validate(User user) {
        if(!Function.isWellFormed(user.getAddress(),Function.ADDRESS_NAME_LENGTH,Function.WEAK)){
            throw new IllegalArgumentException("Address is invalid (<50 characters) for: " + user.getName());
        }
    }
}
