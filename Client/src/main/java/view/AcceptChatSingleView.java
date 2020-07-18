package view;

import Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AcceptChatSingleView extends JFrame {
    private JLabel titleLabel;
    private JButton cancelBtn;
    private JButton acceptBtn;
    private Client client;

    public AcceptChatSingleView(String sender, String reviever, Client client) {
        this.client = client;
        initComponents(reviever,sender);
    }

    private void initComponents(String receiver,String sender) {
        titleLabel = new JLabel("<html><span style='color:red; font-size:13px'>"+sender+" mon gui tin nhan cho ban"+"</span></html>");
        cancelBtn = new JButton("Từ chối");
        cancelBtn.setPreferredSize(new Dimension(100, 30));
        acceptBtn = new JButton("Chấp nhận");
        acceptBtn.setPreferredSize(new Dimension(100, 30));

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 150);
        panel.setLayout(layout);

        panel.add(titleLabel);
        panel.add(cancelBtn);
        panel.add(acceptBtn);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, cancelBtn, 70, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, cancelBtn, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, acceptBtn, 120, SpringLayout.WEST, cancelBtn);
        layout.putConstraint(SpringLayout.NORTH, acceptBtn, 0, SpringLayout.NORTH, cancelBtn);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setTitle(receiver);
        this.setSize(400, 150);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
    }

    public void addCancelListenter(ActionListener listener){
        cancelBtn.addActionListener(listener);
    }

    public void addAcceptListenter(ActionListener listener){
        acceptBtn.addActionListener(listener);
    }
}
