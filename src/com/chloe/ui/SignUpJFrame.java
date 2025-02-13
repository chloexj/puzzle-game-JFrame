package com.chloe.ui;

import com.chloe.other.FunctionClass;
import com.chloe.other.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;

public class SignUpJFrame extends JFrame implements MouseListener {
    String path = "E:\\000-整理-个人文件夹\\学习相关\\Java\\PuzzleGame\\Photos\\";
    File account = new File("Record\\Account.txt");
    JButton resetBut = new JButton();
    JButton signUpBut = new JButton();


    JTextField inpUserName = new JTextField();
    JPasswordField inpPass = new JPasswordField();
    JPasswordField inpPass2 = new JPasswordField();
    JTextField inpVery = new JTextField();
    ArrayList<User> userAccount = new ArrayList<>();

    public SignUpJFrame() throws IOException {
//        this.setSize(350, 450);
//        this.setTitle("Puzzle Game--Sign up");
//        this.setDefaultCloseOperation(3);
//        this.setLocationRelativeTo(null);
//        setIconImage(Toolkit.getDefaultToolkit().getImage(path + "myIcon.jpg"));
//        this.setVisible(true);
        new FunctionClass().codeFile(account);
        new FunctionClass().readUserInfo(account, userAccount);
        System.out.println(userAccount);
        new FunctionClass().codeFile(account);
        setAllScreen();
        setJFrame();
        setIconImage(Toolkit.getDefaultToolkit().getImage(path + "myIcon.jpg"));
        this.setVisible(true);


    }

    public void setAllScreen() {

        //get the input frame

        inpUserName.setBounds(200, 150, 170, 25);
        inpPass.setBounds(200, 200, 170, 25);
        inpPass2.setBounds(200, 250, 170, 25);
        this.getContentPane().add(inpUserName);
        this.getContentPane().add(inpPass);
        this.getContentPane().add(inpPass2);


        //get the logo and user information
        JLabel RUserName = new JLabel(new ImageIcon(path + "Register username.png"));
        RUserName.setBounds(30, 155, 163, 20);
        JLabel RPassword = new JLabel(new ImageIcon(path + "Register Password.png"));
        RPassword.setBounds(30, 205, 163, 20);
        JLabel RPassword2 = new JLabel(new ImageIcon(path + "Repeate Password.png"));
        RPassword2.setBounds(30, 255, 163, 20);
        this.getContentPane().add(RPassword2);
        this.getContentPane().add(RPassword);
        this.getContentPane().add(RUserName);


        //get log in and sign up button

        resetBut.setBounds(270, 300, 140, 60);
        resetBut.setIcon(new ImageIcon(path + "Reset_o.png"));
        resetBut.setBorderPainted(false);//move the boder
        resetBut.setContentAreaFilled(false);//move the background of th button.
        this.getContentPane().add(resetBut);
//sign up button
//90, 300, 140, 60
        signUpBut.setBounds(90, 300, 140, 60);
        signUpBut.setIcon(new ImageIcon(path + "SignUp_o.png"));
        signUpBut.setBorderPainted(false);//move the boder
        signUpBut.setContentAreaFilled(false);//move the background of th button.
        this.getContentPane().add(signUpBut);
        //add action listen for button
        resetBut.addMouseListener(this);
        signUpBut.addMouseListener(this);

        //get the log in screen
        JLabel screen = new JLabel(new ImageIcon(path + "LoginScreen.png"));
        screen.setBounds(0, 0, 470, 390);
        this.getContentPane().add(screen);

    }

    public void setJFrame() {
        this.setSize(500, 440);
        this.setTitle("Puzzle Game-Sign up");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == resetBut) {
            resetBut.setIcon(new ImageIcon(path + "Reset_c.png"));
        } else if (e.getSource() == signUpBut) {
            signUpBut.setIcon(new ImageIcon(path + "SignUp_c.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == resetBut) {
            resetBut.setIcon(new ImageIcon(path + "Reset_o.png"));
            System.out.println("reset");

            //real function:
            inpUserName.setText("");
            inpPass.setText("");
            inpPass2.setText("");
        } else if (e.getSource() == signUpBut) {
            signUpBut.setIcon(new ImageIcon(path + "SignUp_o.png"));

            //belowing is the real function of click signupBut

            //1.username and pass cannot be empty
            String username = inpUserName.getText();
            String pass1 = inpPass.getText();
            String pass2 = inpPass2.getText();
            if (username.length() == 0 || pass1.length() == 0 || pass2.length() == 0) {
                showjDialog("Username and password cannot be empty");
                return;
            }
            //2. 2 times passwords are the same or not
            if (!pass1.equals(pass2)) {
                showjDialog("The passwords entered twice do not match");
                return;
            }
            //3.  format is correct or not
//username: a-Z 0-9  {4,16}
            if (!username.matches("[a-zA-Z0-9]{4,16}")) {
                showjDialog("Username should be letters or digits,no less than 4, no more than 16");
                return;
            }
            //pass:at least 1 number,1 letter 6-16
            if (!pass1.matches("[a-zA-Z0-9]{6,16}")) {
                showjDialog("Password should be letters or digits,no less than 6, no more than 16");
                return;
            }
            //4. username exist or not
            User input = new User(username, pass1);
            if (new FunctionClass().existOrNot(userAccount, input) != -1) {
                //exist
                showjDialog("Sorry, username exists, please change another.");
            } else {
                //5. add new user
                userAccount.add(input);
                input.setTryTime(0);
                //6. write in account

                BufferedWriter bw = null;

                try {
                    new FunctionClass().codeFile(account);
                    bw = new BufferedWriter(new FileWriter(account, true));
                    bw.newLine();
                    bw.write(input.toString() + "&" + input.getTryTime());
                    bw.newLine();
                    bw.close();

                    new FunctionClass().codeFile(account);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                //7. successfully sign up
                showjDialog("Sign up successfully.");
                //if successfully sign up, find step:

                try {
                    new LogInJFrame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                this.setVisible(false);
                System.out.println("sign up");
            }
        }
    }

    private void showjDialog(String warning) {
        JDialog showWarning = new JDialog();
        showWarning.setSize(500, 90);
        showWarning.setLocationRelativeTo(null);
        showWarning.setAlwaysOnTop(true);
        showWarning.setModal(true);

        JLabel warningInfo = new JLabel(warning);
        warningInfo.setBounds(20, 0, 500, 90);
        showWarning.getContentPane().add(warningInfo);
        showWarning.setVisible(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == resetBut) {
            resetBut.setIcon(new ImageIcon(path + "Reset_f.png"));

        } else if (e.getSource() == signUpBut) {
            signUpBut.setIcon(new ImageIcon(path + "SignUp_f.png"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == resetBut) {
            resetBut.setIcon(new ImageIcon(path + "Reset_o.png"));
        } else if (e.getSource() == signUpBut) {
            signUpBut.setIcon(new ImageIcon(path + "SignUp_o.png"));
        }
    }
}

