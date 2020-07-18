package controller;

import view.HomeView;
import view.loginview;
import view.signupview;

import javax.security.auth.login.LoginContext;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class logincontroller {
    private loginview loginview;

    public logincontroller(loginview view) {
        this.loginview = view;
        loginview.addSignupListener(new signUpListener());
        loginview.addLoginListener(new logInListener());
        loginview.setVisible(true);
    }

    class signUpListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            loginview.setVisible(false);
            signupview siginupview = new signupview();
            signupcontroller signupcontroller = new signupcontroller(siginupview);
        }
    }

    class logInListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Thread sendMessage = new Thread(new Runnable() {
                public void run() {
                    DataOutputStream output = null;
                    DataInputStream input = null;
                    Socket socket = null;
                    try {
                        socket = new Socket("localhost", 3000);
                        output = new DataOutputStream(socket.getOutputStream());
                        input = new DataInputStream(socket.getInputStream());
                        output.writeUTF("CHECKLOGIN%&" + loginview.getUserPassword());
                        while (true) {
                            try {
                                String msg = input.readUTF();
                                StringTokenizer tokenizer = new StringTokenizer(msg, "%&");
                                String CMD = tokenizer.nextToken();
                                if (CMD.equals("CHECKLOGIN")) {
                                    CMD = tokenizer.nextToken();
                                    if (CMD.equals("FALSE")) {
                                        loginview.showMessage("Username or password is mot correct");
                                        output.writeUTF("LOGOUT");
                                    } else if (CMD.equals("TRUE")) {
                                        CMD = tokenizer.nextToken();
                                        HomeView view = new HomeView(CMD);
                                        HomeController homeController = new HomeController(view);
                                        output.writeUTF("LOGOUT");
                                        loginview.setVisible(false);
                                    }
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
//                        output.writeUTF("LOGOUT");
//                        signupview.setVisible(false);

//                        loginview loginview = new loginview();
//                        logincontroller logincontroller = new logincontroller(loginview);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            sendMessage.start();
        }
    }
}
