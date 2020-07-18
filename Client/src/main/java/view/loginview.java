package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class loginview extends JFrame {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel titleLabel;
    private JButton signinBtn;
    private JButton signupBtn;
    private JTextField usernameField;
    private JTextField passwordField;

    public loginview() {
        initComponent();
    }

    private void initComponent() {
        usernameLabel = new JLabel("username: ");
        passwordLabel = new JLabel("password: ");
        titleLabel = new JLabel("<html><h3 style='color:blue;font-size: 20px'>Sign in</h3></html>");
        signinBtn = new JButton("Sign in");
        signupBtn = new JButton("Sign up");
        usernameField = new JTextField(15);
        passwordField = new JTextField(15);

        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 300);
        panel.setLayout(layout);

        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(titleLabel);
        panel.add(signinBtn);
        panel.add(signupBtn);
        panel.add(usernameField);
        panel.add(passwordField);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 140, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, usernameLabel, 60, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 70, SpringLayout.NORTH, titleLabel);

        layout.putConstraint(SpringLayout.WEST, usernameField, 80, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField, 0, SpringLayout.NORTH, usernameLabel);

        layout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 40, SpringLayout.NORTH, usernameLabel);

        layout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, usernameField);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, signinBtn, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, signinBtn, 60, SpringLayout.NORTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, signupBtn, 150, SpringLayout.WEST, signinBtn);
        layout.putConstraint(SpringLayout.NORTH, signupBtn, 0, SpringLayout.NORTH, signinBtn);

        this.add(panel);
        this.pack();
        this.setTitle("Sign in");
        this.setSize(400, 300);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
    }
    public void showMessage(String messge) {
        JOptionPane.showMessageDialog(this, messge);
    }
    public String getUserPassword(){
        return usernameField.getText().trim()+","+passwordField.getText().trim();
    }

    public void addSignupListener(ActionListener listener){
        signupBtn.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener){
        signinBtn.addActionListener(listener);
    }

}
