package controller;

import view.loginview;
import view.signupview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class signupcontroller {
    private signupview signupview;

    public signupcontroller(signupview view) {
        this.signupview = view;
        signupview.setVisible(true);
        signupview.addLoginListener(new loginListener());
        signupview.addRegisterListener(new registerListener());
    }

    class loginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            signupview.setVisible(false);
            loginview login = new loginview();
            logincontroller logincontroller = new logincontroller(login);
        }
    }

    class registerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            boolean check = signupview.CheckRegister();
            if (check) {
                Thread sendMessage = new Thread(new Runnable() {
                    public void run() {
                        DataOutputStream output = null;
                        Socket socket = null;
                        try {
                            socket = new Socket("localhost", 3000);
                            output = new DataOutputStream(socket.getOutputStream());
                            output.writeUTF("REGISTER%&" + signupview.registerSendServer());
                            output.writeUTF("LOGOUT");
                            signupview.setVisible(false);

                            loginview loginview = new loginview();
                            logincontroller logincontroller = new logincontroller(loginview);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                sendMessage.start();
            }
        }
    }
}

