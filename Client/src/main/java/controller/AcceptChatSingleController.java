package controller;

import view.AcceptChatSingleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AcceptChatSingleController {
    private AcceptChatSingleView acceptChatSingleView;
    public AcceptChatSingleController(AcceptChatSingleView view){
        this.acceptChatSingleView = view;
        acceptChatSingleView.setVisible(true);
        acceptChatSingleView.addCancelListenter(new cacelListener());
        acceptChatSingleView.addAcceptListenter(new acceptListener());
    }

    class cacelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            acceptChatSingleView.dispose();
        }
    }

    class acceptListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
