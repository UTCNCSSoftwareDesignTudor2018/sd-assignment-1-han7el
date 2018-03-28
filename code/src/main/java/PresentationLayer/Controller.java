package PresentationLayer;

import BusinessLogicLayer.bll.EnrolmentBLL;
import BusinessLogicLayer.bll.StudentBLL;
import BusinessLogicLayer.bll.TeacherBLL;
import BusinessLogicLayer.bll.UserBLL;
import BusinessLogicLayer.functions.Function;
import DataLayer.model.*;
import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * Created by Boros on 3/27/2018.
 */
public class Controller {

    private UserBLL userBLL;

    public Controller(){
        this.userBLL = new UserBLL();
    }

    public void loginUser(User user) {
        User logingUser = userBLL.loginUser(user);
        try {

            if (logingUser.getUsertype() == Function.TEACHER_TYPE) {
                new TeacherView(new TeacherController(logingUser));
            } else if (logingUser.getUsertype() == Function.STUDENT_TYPE) {
                new StudentView(new StudentController(logingUser));
            }
        }catch(IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
        catch(NullPointerException e){
            System.out.println("Wrong username or password (null pointer!\n");
        }
    }
}
