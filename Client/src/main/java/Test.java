import Client.Client;
import controller.AcceptChatSingleController;
import view.AcceptChatSingleView;
import view.ChatSingleView;

public class Test {
    public static void main(String[] args) {
//        Client client = new Client("hoang amn");
//        ChatSingleView chatSingleView = new ChatSingleView("Nguyễn Hoàng Mẫn","Cong ly",client);

        AcceptChatSingleView accept = new AcceptChatSingleView("sender","recierer");
        AcceptChatSingleController controller = new AcceptChatSingleController(accept);
    }
}
