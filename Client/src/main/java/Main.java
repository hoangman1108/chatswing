import controller.logincontroller;
import view.loginview;

public class Main {
    public static void main(String[] args) {
        loginview view = new loginview();
        logincontroller logincontroller = new logincontroller(view);
    }
}
