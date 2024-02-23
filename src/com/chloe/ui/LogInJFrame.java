package com.chloe.ui;
import com.chloe.other.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class LogInJFrame extends JFrame implements MouseListener {
    static ArrayList<User> userAccount=new ArrayList<User>();
    static {
        userAccount.add(new User("admin","password123"));
        userAccount.add(new User("chloe","123"));
    }
    String path = "E:\\000-整理-个人文件夹\\学习相关\\Java\\PuzzleGame\\Photos\\";
    JButton changeVery = new JButton();
    JButton logInBut = new JButton();
    JButton signUpBut = new JButton();
    JLabel showVeryCode = new JLabel();
    String rightVery=getVery();

    JTextField inpUserName = new JTextField();
    JPasswordField inpPass = new JPasswordField();
    JTextField inpVery = new JTextField();


    public LogInJFrame() {


        setAllScreen();
        setJFrame();
        this.setVisible(true);

    }

    public void setJFrame(){
        this.setSize(500, 440);
        this.setTitle("Puzzle Game-Log in");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);

    }

    public void setAllScreen() {

        //get the input frame

        inpUserName.setBounds(200, 150, 170, 25);

        inpPass.setBounds(200, 200, 170, 25);

        inpVery.setBounds(200, 250, 80, 25);
        this.getContentPane().add(inpUserName);
        this.getContentPane().add(inpPass);
        this.getContentPane().add(inpVery);

        //get the logo and user information
        JLabel userName = new JLabel(new ImageIcon(path + "UserName.png"));
        userName.setBounds(82, 150, 100, 20);
        JLabel password = new JLabel(new ImageIcon(path + "PassWord.png"));
        password.setBounds(82, 200, 100, 20);
        JLabel veryCode = new JLabel(new ImageIcon(path + "Verification Code.png"));
        veryCode.setBounds(15, 250, 163, 20);
        this.getContentPane().add(veryCode);
        this.getContentPane().add(password);
        this.getContentPane().add(userName);


        //get verification code

        showVeryCode.setText(rightVery);
        showVeryCode.setBounds(300, 250, 45, 25);
        showVeryCode.addMouseListener(this);
        this.getContentPane().add(showVeryCode);

        //add an action listener to verification code, if you press, change verification code


        //get log in and sign up button

        logInBut.setBounds(90, 300, 140, 60);
        logInBut.setIcon(new ImageIcon(path +"LogIn_o.png"));
        logInBut.setBorderPainted(false);//move the boder
        logInBut.setContentAreaFilled(false);//move the background of th button.
        this.getContentPane().add(logInBut);
//sign up button

        signUpBut.setBounds(270, 300, 140, 60);
        signUpBut.setIcon(new ImageIcon(path + "SignUp_o.png"));
        signUpBut.setBorderPainted(false);//move the boder
        signUpBut.setContentAreaFilled(false);//move the background of th button.
        this.getContentPane().add(signUpBut);
        //add action listen for button
        logInBut.addMouseListener(this);
        signUpBut.addMouseListener(this);
        changeVery.addMouseListener(this);

        //get the log in screen
        JLabel screen = new JLabel(new ImageIcon(path + "LoginScreen.png"));
        screen.setBounds(0, 0, 470, 390);
        this.getContentPane().add(screen);

    }


    public String getVery() {
        //generate 4 letters+ 1number, number can show in any position.
        Random r = new Random();
        //create an array to keep A-Z 65-90 97-122
        ArrayList<Character> arry = new ArrayList<>();
        //put A-Z to this arr
        for (int i = 0; i < 26; i++) {
            arry.add((char) ('a' + i));
            arry.add((char) ('A' + i));
        }
        StringBuilder veryTemp = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(arry.size());
            veryTemp.append(arry.get(index));
        }
        veryTemp.append(r.nextInt(10));

        //shuffle the position.
        char[] arr = veryTemp.toString().toCharArray();
        int index = r.nextInt(arr.length);
        char temp = arr[index];
        arr[index] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        String very = new String(arr);
        return very;
    }
public boolean userExist(User user1,ArrayList<User>allUser){
    for (int i = 0; i < allUser.size(); i++) {
        if((user1.getUserName().equalsIgnoreCase(allUser.get(i).getUserName()))&&(user1.getPassword().equals(allUser.get(i).getPassword()))){
            return true;
        }
    }
    return false;
}
public void showjDialog(String warning){
JDialog showWarning=new JDialog();
showWarning.setSize(300,90);
showWarning.setLocationRelativeTo(null);
showWarning.setAlwaysOnTop(true);
showWarning.setModal(true);

JLabel warningInfo=new JLabel(warning);
warningInfo.setBounds(20,0,300,90);
showWarning.getContentPane().add(warningInfo);
showWarning.setVisible(true);
}
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == logInBut) {
            logInBut.setIcon(new ImageIcon(path+"LogIn_c.png"));
           }else if(e.getSource() == signUpBut){
            signUpBut.setIcon(new ImageIcon(path+"SignUp_c.png"));
        }
    }

        @Override
        public void mouseReleased (MouseEvent e){
          if (e.getSource() == logInBut) {
                logInBut.setIcon(new ImageIcon(path+"LogIn_o.png"));
                System.out.println("LOG IN");
                // real log in code
              String getUserName = inpUserName.getText();
              String getPass = inpPass.getText();
              String getVeryCode = inpVery.getText();
              User input = new User(getUserName, getPass);

//compare verification code
              if (getVeryCode.length() == 0) {
                  showjDialog("Verification code is empty");
              }else if((getUserName.length()==0)||(getPass.length()==0)){
                  showjDialog("Username of password cannot be empty");
              }
              else if (getVeryCode.equalsIgnoreCase(rightVery)){
                  if(userExist(input,userAccount)){
                      //log in successfully
                      this.setVisible(false);
                      new GameJFrame();
                  }else{
                      showjDialog("Username and password do not match");
                  }

              }else {
                  showjDialog("Verification code is wrong");
              }
            } else if(e.getSource() == signUpBut){
                signUpBut.setIcon(new ImageIcon(path+"SignUp_o.png"));
                showjDialog("username:admin  password:password123");
                System.out.println("sign up");
            } else if(e.getSource() == showVeryCode){
              rightVery=getVery();
                showVeryCode.setText(rightVery);
                System.out.println("change");
            }

        }

        @Override
        public void mouseEntered (MouseEvent e){
            if (e.getSource() == logInBut) {
                logInBut.setIcon(new ImageIcon(path+"LogIn_f.png"));

            }else if(e.getSource() == signUpBut){
                signUpBut.setIcon(new ImageIcon(path+"SignUp_f.png"));
            }

        }

        @Override
        public void mouseExited (MouseEvent e){
            if (e.getSource() == logInBut) {
                logInBut.setIcon(new ImageIcon(path+"LogIn_o.png"));
            }else if(e.getSource() == signUpBut){
                signUpBut.setIcon(new ImageIcon(path+"SignUp_o.png"));
            }
        }


    }