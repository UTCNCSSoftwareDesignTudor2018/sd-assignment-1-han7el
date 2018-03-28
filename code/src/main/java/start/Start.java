package start;

import PresentationLayer.Controller;
import PresentationLayer.LoginView;
import sun.rmi.runtime.Log;

public class Start {

    public static void main(String[] args) {

        LoginView loginView = new LoginView(new Controller());
    }
}
