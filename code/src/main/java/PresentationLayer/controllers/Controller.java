package PresentationLayer.controllers;

import BusinessLogicLayer.bll.UserBLL;
import BusinessLogicLayer.functions.Function;
import DataLayer.model.*;
import PresentationLayer.views.StudentView;
import PresentationLayer.views.TeacherView;

import javax.swing.*;

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
            JOptionPane.showMessageDialog(null,"Invalid username or password");
            System.out.println("Wrong username or password!\n");
        }
    }
}
