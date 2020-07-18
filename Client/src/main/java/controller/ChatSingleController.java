package controller;

import view.ChatSingleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChatSingleController {
    private ChatSingleView chatSingleView;
    public ChatSingleController(ChatSingleView view){
        chatSingleView = view;
        chatSingleView.setVisible(true);
        chatSingleView.addSendBtnListener(new sendBtnListener());
        chatSingleView.addemojiiconListener(new emojiiconBtnListener());
        try {
            chatSingleView.getClient().getOutput().writeUTF("CHATSINGLE%&"+chatSingleView.getReceiver()+"%&CONNECT SUCCESS!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class sendBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(chatSingleView.checkInput()){
                try {
                    chatSingleView.getClient().getOutput().writeUTF("CHATSINGLE%&"+chatSingleView.getReceiver()+"%&"+chatSingleView.getInput());
                    chatSingleView.appenTextera("bạn: "+chatSingleView.getInput()+"\n");
                    chatSingleView.clearInput();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }else{
                    chatSingleView.showMessage("Nothing to send");
            }
        }
    }
    class emojiiconBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            chatSingleView.showEmoji();
        }
    }
}
