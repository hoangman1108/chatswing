package view;

import contains.EmojiContance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PanelEmoji extends JPanel {
    private JPanel rootpanel;
    private JPanel pnlPeople;

    /**
     * Create the panel.
     */
    public PanelEmoji() {
        setLayout(null);

        rootpanel = new JPanel();
        rootpanel.setBounds(0, 0, 315, 300);
        add(rootpanel);
        rootpanel.setLayout(null);

        JTabbedPane tabEmoji = new JTabbedPane(JTabbedPane.TOP);
        tabEmoji.setBounds(0, 0, 315, 300);
        rootpanel.add(tabEmoji);

        List<String[]> emoji = EmojiContance.getAllEmojisSortedByCategory();

        for (int i = 0; i <emoji.size() ; i++) {
            pnlPeople = new JPanel();
            pnlPeople.setPreferredSize(new Dimension(310, (emoji.get(i).length/8)*37));
            tabEmoji.setFont(new Font("", Font.BOLD, 20));
            pnlPeople.setLayout(null);
            initPeople(emoji.get(i));
            JScrollPane scrollPane = new JScrollPane(pnlPeople, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            tabEmoji.addTab(emoji.get(i)[1], null, scrollPane, null);
        }



    }

    public void initPeople(String []emoji) {
        int k = 0;
        for (int i = 0; i < emoji.length / 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel panel = new JPanel();
                panel.setLayout(null);
                final JLabel icon = new JLabel(emoji[k++]);
                icon.setHorizontalAlignment(SwingConstants.CENTER);
                icon.setFont(new Font("", Font.PLAIN, 25));
                icon.setBounds(0,0,25,25);
                panel.add(icon);
                panel.setBounds(10 + 35 * j, 11 + 36 * i, 25, 25);

                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String msg = ChatSingleView.inputField.getText();
                        ChatSingleView.inputField.setText(msg+" "+ icon.getText());
//                        String msg = ChatView.txtMessage.getText();
//                        ChatView.txtMessage.setText(msg+" "+ icon.getText());
                    }
                });

                pnlPeople.add(panel);
            }


        }
    }
}