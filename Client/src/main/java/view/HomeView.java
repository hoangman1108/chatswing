package view;

import Client.Client;
import controller.ChatSingleController;
import entity.ChatEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

public class HomeView extends JFrame {
    private String nameuser;
    private JLabel nameLabel;
    private JButton chatBtn;
    private JButton logoutBtn;
    private JScrollPane jScrollonline;
    private JTable onlineTable;
    private Client client;
    public static List<ChatEntity> chatList = new ArrayList<ChatEntity>();
    private String[] columnNames = new String[]{
            "Online"
    };
    private Object data = new Object[][]{};

    public HomeView(String name) {
        nameuser = name;
        System.out.println("yourname: "+nameuser);
        initComponents(name);
    }

    private void initComponents(String name) {
        client = new Client(name);

        nameLabel = new JLabel("<html><span style='font-size:18px;color:blue'>" + name + "</span></html>");
        chatBtn = new JButton("Chat");
        logoutBtn = new JButton("Logout");
        jScrollonline = new JScrollPane();
        onlineTable = new JTable();
        onlineTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollonline.setViewportView(onlineTable);
        jScrollonline.setPreferredSize(new Dimension(200, 300));

        chatBtn.setPreferredSize(new Dimension(100, 30));
        logoutBtn.setPreferredSize(new Dimension(100, 30));
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 400);
        panel.setLayout(layout);
        panel.add(nameLabel);
        panel.add(chatBtn);
        panel.add(logoutBtn);
        panel.add(jScrollonline);

        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, chatBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, chatBtn, 80, SpringLayout.NORTH, nameLabel);

        layout.putConstraint(SpringLayout.WEST, logoutBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, logoutBtn, 80, SpringLayout.NORTH, chatBtn);

        layout.putConstraint(SpringLayout.WEST, jScrollonline, 170, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollonline, 50, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Chat");
        this.setSize(400, 400);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
        TableOnline(name);
    }

    public Client getClient() {
        return client;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void TableOnline(final String fullname) {
        try {
            client.getOutput().writeUTF("LOGINSUCCESS%&"+fullname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread readMessage = new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 0;
                    Object[][] online = new Object[100][1];
                    while (true) {
                        String msg = client.getInput().readUTF();
                        System.out.println("home: "+msg);
                        StringTokenizer tokenizer = new StringTokenizer(msg, "%&");
                        String CMD = tokenizer.nextToken().trim();
                        if (CMD.equals("USERONLINE")){
                            CMD = tokenizer.nextToken();

                           if(!CMD.equals(fullname)){
                               online[i++][0] = CMD;
                               onlineTable.setModel(new DefaultTableModel(online, columnNames));
                           }
                        }else if(CMD.equals("USEROFFLINE")){
                            CMD=tokenizer.nextToken().trim();
                            for (int j = 0; j < i; j++) {
                                if (online[j][0].equals(CMD)) {
                                    if (j == i - 1) {
                                        online[j][0] = null;
                                    } else {
                                        for (int k = j; k < i - 1; k++) {
                                            online[k][0] = online[k + 1][0];
                                        }
                                        online[i - 1] = null;
                                    }
                                }
                            }
                            onlineTable.setModel(new DefaultTableModel(online, columnNames));
                            i--;
                        }else if(CMD.equals("CONNECTEDCHATSINGLE")){
                            CMD=tokenizer.nextToken().trim();
                            ChatEntity chatEntity = new ChatEntity(CMD,nameuser, client);
                            chatList.add(chatEntity);
                        }else if(CMD.equals("CHATSINGLE")){
                            CMD=tokenizer.nextToken().trim();
                            for(ChatEntity chatEntity:chatList){
                                if(chatEntity.getName().equals(CMD)){
                                    String message = tokenizer.nextToken().trim();
                                    chatEntity.getChatSingleView().appenTextera(CMD+" : "+message+"\n");
                                    break;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readMessage.start();
    }
    public void showMessage(String messge) {
        JOptionPane.showMessageDialog(this, messge);
    }
    public String indexOfTable(){
        if(onlineTable.getSelectedRow()== -1){
            return "";
        }
        return onlineTable.getValueAt(onlineTable.getSelectedRow(),onlineTable.getSelectedColumn()).toString();
    }

    public List<ChatEntity> getChatList() {
        return chatList;
    }

    public void addLogoutListener(ActionListener listener){
        logoutBtn.addActionListener(listener);
    }
    public void addChatListener(ActionListener listener){
        chatBtn.addActionListener(listener);
    }
}