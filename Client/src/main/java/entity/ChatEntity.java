package entity;

import Client.Client;
import controller.ChatSingleController;
import view.ChatSingleView;

public class ChatEntity {
    private ChatSingleView chatSingleView;
    private ChatSingleController chatSingleController;
    private String name;
    public ChatEntity(String name,String sender, Client client){
        chatSingleView = new ChatSingleView(name,sender,client);
        chatSingleController = new ChatSingleController(chatSingleView);
        this.name = name;
    }
    public ChatEntity(){
        chatSingleView = null;
        chatSingleController = null;
    }

    public ChatSingleView getChatSingleView() {
        return chatSingleView;
    }

    public ChatSingleController getChatSingleController() {
        return chatSingleController;
    }

    public String getName() {
        return name;
    }
}
