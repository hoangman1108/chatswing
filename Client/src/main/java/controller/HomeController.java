package controller;

import entity.ChatEntity;
import view.ChatSingleView;
import view.HomeView;
import view.loginview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeController {
    private HomeView homeView;
    public HomeController(HomeView view){
        homeView = view;
        homeView.setVisible(true);
        homeView.addLogoutListener(new logoutListener());
        homeView.addChatListener(new chatListener());
    }

    class logoutListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            try {
                homeView.getClient().getOutput().writeUTF("LOGOUTSUCCESS%&"+homeView.getNameuser());
                homeView.getClient().getOutput().writeUTF("LOGOUT");
                homeView.setVisible(false);
                loginview loginview = new loginview();
                logincontroller logincontroller = new logincontroller(loginview);

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    class chatListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String reciever = homeView.indexOfTable();
            if(reciever.equals("")){
                homeView.showMessage("Selected in online table to chat");
            }else{
                try {
                    homeView.getClient().getOutput().writeUTF("CONNECTCHATSINGLE%&"+homeView.indexOfTable());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                ChatEntity chatEntity = new ChatEntity(reciever,homeView.getNameuser(), homeView.getClient());
                homeView.getChatList().add(chatEntity);
            }
        }
    }
}
