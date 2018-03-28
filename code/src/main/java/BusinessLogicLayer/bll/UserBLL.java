package BusinessLogicLayer.bll;

import BusinessLogicLayer.validators.*;
import DataLayer.dao.UserDAO;
import DataLayer.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boros on 3/27/2018.
 */
public class UserBLL {

    private UserDAO userDAO;
    private List<Validator<User>> validators;

    public UserBLL(){
        userDAO = new UserDAO();
        validators = new ArrayList<Validator<User>>();
        validators.add(new NameValidator());
        validators.add(new CnpValidator());
        validators.add(new IcnValidator());
        validators.add(new AddressValidator());
    }

    public User loginUser (User user)throws IllegalArgumentException{
        return userDAO.getLoginUser(user);
    }

    public User insertUser(User user){
        for(Validator<User> uv: validators){
            uv.validate(user);
        }
        user.setUserid(userDAO.insertUser(user));
        return user;
    }

    public void deleteUser(User user){
        userDAO.deletUser(user);
    }

    public User getSpecificUser(int userid){
        return userDAO.getSpecificUser(userid);
    }

}
