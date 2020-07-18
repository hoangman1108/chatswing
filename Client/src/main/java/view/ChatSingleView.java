package view;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.StringTokenizer;

public class ChatSingleView extends JFrame {
    private JLabel receiverLabel;
    private JTextArea textArea;
    private JScrollPane jScrollPane;
    public static JTextField inputField;
    private JButton sendBtn;
    private JButton emojiicon;
    private String receiver = null;
    private Client client = null;
    private PanelEmoji panelEmoji;
    public ChatSingleView(String receiver,String sender, Client client) {
        this.receiver = receiver;
        this.client = client;
        initComponents(receiver, sender);
    }

    private void initComponents(String receiver, String sender) {


        receiverLabel = new JLabel("<html><span style='font-size:14; color: blue'>" + receiver + "</span></html>");
        textArea = new JTextArea(22, 30);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setForeground(Color.BLACK);
        jScrollPane = new JScrollPane(textArea);
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 40));
        sendBtn = new JButton("SEND");
        sendBtn.setPreferredSize(new Dimension(70, 40));
        emojiicon = new JButton("FILE");
        emojiicon.setPreferredSize(new Dimension(70, 40));


        panelEmoji = new PanelEmoji();
        panelEmoji.setVisible(false);
        panelEmoji.setPreferredSize(new Dimension(315, 300));

        JPanel paneltemp = new JPanel();;
        paneltemp.setSize(new Dimension(300,200));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);

        textArea.setEditable(false);
        panel.add(panelEmoji);
        panel.add(receiverLabel);
        panel.add(inputField);
        panel.add(jScrollPane);
        panel.add(inputField);
        panel.add(sendBtn);
        panel.add(emojiicon);

        layout.putConstraint(SpringLayout.WEST, receiverLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, receiverLabel, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPane, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPane, 30, SpringLayout.NORTH,receiverLabel);

        layout.putConstraint(SpringLayout.WEST,inputField,10, SpringLayout.WEST,panel);
        layout.putConstraint(SpringLayout.NORTH,inputField,380,SpringLayout.NORTH,jScrollPane);

        layout.putConstraint(SpringLayout.WEST, emojiicon, 200, SpringLayout.WEST,inputField);
        layout.putConstraint(SpringLayout.NORTH, emojiicon, 0, SpringLayout.NORTH,inputField);

        layout.putConstraint(SpringLayout.WEST, sendBtn, 70, SpringLayout.WEST, emojiicon);
        layout.putConstraint(SpringLayout.NORTH, sendBtn, 0, SpringLayout.NORTH, emojiicon);
//
        layout.putConstraint(SpringLayout.WEST,panelEmoji, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, panelEmoji, 120, SpringLayout.WEST, panel);
        this.add(panel);
        this.pack();
        this.setTitle(sender);
        this.setSize(380, 500);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
        RecieveMessage();
        setVisible(true);
    }

    public void  RecieveMessage(){
        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        String msg =client.getInput().readUTF();
                        System.out.println("chatsingle: "+ msg);
                        StringTokenizer tokenizer = new StringTokenizer(msg, "%&");
                        String CMD = tokenizer.nextToken().trim();
                        if (CMD.equals("CHATSINGLE")) {
                            String name = tokenizer.nextToken().trim();
                            CMD = tokenizer.nextToken().trim();
                            appenTextera(name+" : "+CMD+"\n");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        readMessage.start();
    }

    public boolean checkInput(){
        if(inputField.getText().isEmpty()) return false;
        return true;
    }
    public void showMessage(String messge) {
        JOptionPane.showMessageDialog(this, messge);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public String getInput() {
        return inputField.getText();
    }

    public Client getClient() {
        return client;
    }

    public void clearInput() {
        inputField.setText("");
    }

    public String getReceiver() {
        return receiver;
    }
    public void appenTextera(String str){
        textArea.setText(textArea.getText()+str);
    }

    public void addSendBtnListener(ActionListener listener){
        sendBtn.addActionListener(listener);
    }
    public void addemojiiconListener(ActionListener listener){
        emojiicon.addActionListener(listener);
    }

    boolean checkvisitble = true;
    public void showEmoji(){
       panelEmoji.setVisible(checkvisitble);
       checkvisitble=!checkvisitble;
    }
}
