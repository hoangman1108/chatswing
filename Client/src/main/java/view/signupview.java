package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class signupview extends JFrame {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmpassLabel;
    private JLabel titleLabel;
    private JLabel fullnameLabel;
    private JButton signinBtn;
    private JButton registerBtn;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField fullnameField;
    private JPasswordField confirmpassField;
    public signupview() {
        initComponent();
    }

    private void initComponent() {
        usernameLabel = new JLabel("username: ");
        passwordLabel = new JLabel("password: ");
        confirmpassLabel = new JLabel("confirm pass: ");
        fullnameLabel = new JLabel("full name: ");
        titleLabel = new JLabel("<html><h3 style='color:blue;font-size: 20px'>Sign up</h3></html>");
        signinBtn = new JButton("Sign in");
        registerBtn = new JButton("Register");
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        fullnameField = new JTextField(15);
        confirmpassField = new JPasswordField(15);

        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 400);
        panel.setLayout(layout);

        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(fullnameLabel);
        panel.add(confirmpassLabel);
        panel.add(titleLabel);
        panel.add(signinBtn);
        panel.add(registerBtn);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(fullnameField);
        panel.add(confirmpassField);

        layout.putConstraint(SpringLayout.WEST, titleLabel, 140, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 30, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, fullnameLabel, 60, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fullnameLabel, 70, SpringLayout.NORTH, titleLabel);

        layout.putConstraint(SpringLayout.WEST, fullnameField, 80, SpringLayout.WEST, fullnameLabel);
        layout.putConstraint(SpringLayout.NORTH, fullnameField, 0, SpringLayout.NORTH, fullnameLabel);

        layout.putConstraint(SpringLayout.WEST, usernameLabel, 60, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, usernameLabel, 40, SpringLayout.NORTH, fullnameLabel);

        layout.putConstraint(SpringLayout.WEST, usernameField, 80, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, usernameField, 0, SpringLayout.NORTH, usernameLabel);

        layout.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, usernameLabel);
        layout.putConstraint(SpringLayout.NORTH, passwordLabel, 40, SpringLayout.NORTH, usernameLabel);

        layout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, usernameField);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 0, SpringLayout.NORTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, confirmpassLabel, 0, SpringLayout.WEST, passwordLabel);
        layout.putConstraint(SpringLayout.NORTH, confirmpassLabel,40, SpringLayout.NORTH, passwordLabel);

        layout.putConstraint(SpringLayout.WEST, confirmpassField, 0, SpringLayout.WEST, passwordField);
        layout.putConstraint(SpringLayout.NORTH, confirmpassField, 0, SpringLayout.NORTH, confirmpassLabel);

        layout.putConstraint(SpringLayout.WEST, registerBtn, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, registerBtn, 60, SpringLayout.NORTH, confirmpassField);

        layout.putConstraint(SpringLayout.WEST, signinBtn, 150, SpringLayout.WEST, registerBtn);
        layout.putConstraint(SpringLayout.NORTH, signinBtn, 0, SpringLayout.NORTH, registerBtn);

        this.add(panel);
        this.pack();
        this.setTitle("Sign in");
        this.setSize(400, 400);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);
        this.setResizable(false);
    }

    public void addRegisterListener(ActionListener listener){
        registerBtn.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener){
        signinBtn.addActionListener(listener);
    }
    public void showMessage(String messge) {
        JOptionPane.showMessageDialog(this, messge);
    }

    public String registerSendServer(){
        return usernameField.getText()+","+passwordField.getText()+","+fullnameField.getText();
    }
    public boolean CheckRegister(){
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmpassField.getText();
        String fullname = fullnameField.getText();
        if(fullname.equals("") || fullname.length() < 6){
            showMessage("Your name invalid!");
            return false;
        }
        if(username.equals("") || fullname.length()<6){
            showMessage("Username invalid or length lower 6 characters");
            return false;
        }

        if(password.equals("")){
            showMessage("Password is empty");
        }else{
            if(!password.equals(confirmPassword)){
                showMessage("Confirm password fail!");
                return false;
            }
        }

        return true;
    }
}
