package start;

import PresentationLayer.controllers.Controller;
import PresentationLayer.views.LoginView;

public class Start {

    public static void main(String[] args) {

        LoginView loginView = new LoginView(new Controller());
    }
}
