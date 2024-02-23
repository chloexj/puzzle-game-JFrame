package com.chloe.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //member variable, because we need use it in method
    //generate a two-dimensional array
    int[][] arrTwo = new int[4][4];
    int arrWin[][] = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int x;
    int y;
    int stepCount = 0;



    //menu item, move them here, so that other methord can use them.
    JMenuItem playAg = new JMenuItem("Play Again");
    JMenuItem logInAg = new JMenuItem("Log in Again");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem infoMe = new JMenuItem("Information");
    JMenuItem cat=new JMenuItem("Cat");
    JMenuItem vancouver=new JMenuItem("Vancouver");
    Random r = new Random();
    String []picBankMain={"Cat","Vancouver"};
    String[][]picBank={
            {"Cat1","Cat2","Cat3","Cat4"},//cat
            {"Vancouver1","Vancouver2","Vancouver3"}//Vancouver
    };
    //this index is used to lead the picture
    int index1=r.nextInt(picBankMain.length);
    int index2=r.nextInt(picBank[index1].length);
    String inpPicBankMain=picBankMain[index1];
    String inpPicBank=picBank[index1][index2];

    String path="..\\PuzzleGame\\Photos\\";
    public GameJFrame() {

        //initial frame
        initJFrame();
        //initial menu
        menuBar();
        //shuffle function
        initData();
        //initial image
        initImage();
        // add a keyboard linster to check the user's operation
        this.addKeyListener(this);

        //show the frame
        this.setVisible(true);

    }

    private void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //shuffle the data

        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int index = r.nextInt(arr.length);
            arr[i] = arr[index];
            arr[index] = temp;
        }


        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                //get the 0 postion- x,y.
                x = i / 4;
                y = i % 4;
            }
            arrTwo[i / 4][i % 4] = arr[i];

        }


    }


    private void initImage() {

        //when first time run this code, there is nothing in screen, so removing all doesn't effect anything.
        //but second time it is used, it means user move(because I use this code in KeyListener)
        //clean all photos to refresh the new screen after user movement.
        this.getContentPane().removeAll();
        if (winOrNot()) {
            JLabel winLogo = new JLabel(new ImageIcon(path+"Win.png"));
            winLogo.setBounds(225, 330, 198, 100);
            this.getContentPane().add(winLogo);
        }
        setTip();


        JLabel step = new JLabel("Step: " + stepCount);
        step.setBounds(90, 100, 100, 30);
        this.getContentPane().add(step);
        for (int a = 0; a < 4; a++) {
            //loop 4 times to get y-axis pictures
            for (int i = 0; i < 4; i++) {
                //loop 4 times to get x-axis pictures
                //add picture to program
                // String path="..\\PuzzleGame\\Photos\\";  Cat\\Cat1\\1.jpg
                //E:\000-整理-个人文件夹\学习相关\Java\PuzzleGame\Photos\Cat\Cat1\1.jpg
                JLabel puzzle = new JLabel(new ImageIcon(path +inpPicBankMain+"\\"+inpPicBank+"\\"+arrTwo[a][i] + ".jpg"));
                //initial the position and size of the picture
                puzzle.setBounds(125 * i + 68, 125 * a + 157, 125, 125);
                puzzle.setBorder(new EtchedBorder(1));
                this.getContentPane().add(puzzle);
//        this.add(jLabel);
//get the new screen after user movement.
                this.getContentPane().repaint();
            }
        }
        JLabel background = new JLabel(new ImageIcon(path+"Background-USE.png"));
        background.setBounds(18, 5, 600, 700);
        this.getContentPane().add(background);
    }

    private void initJFrame() {
        this.setSize(650, 800);
        this.setTitle("Puzzle Game--24.2_Chloe");
//        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        //cancel the automatically center
        this.setLayout(null);
    }

    private void menuBar() {
        //menu bar
        JMenuBar jMenuBar = new JMenuBar();
        //menu
        JMenu function = new JMenu("Function");
        JMenu about = new JMenu("About");
        JMenu changePic=new JMenu("Change Picture");


        //add the mouse click action to the menu item
        playAg.addActionListener(this);
        logInAg.addActionListener(this);
        exit.addActionListener(this);
        infoMe.addActionListener(this);
        playAg.addActionListener(this);
        cat.addActionListener(this);
        vancouver.addActionListener(this);
        //add menu item to menu, and add menu to menu bar
        function.add(changePic);
        function.add(playAg);
        function.add(logInAg);
        function.add(exit);
        about.add(infoMe);
        changePic.add(cat);
        changePic.add(vancouver);
        jMenuBar.add(function);
        jMenuBar.add(about);
        //set menu to frame
        this.setJMenuBar(jMenuBar);

    }
public void setTip(){
    JLabel tip=new JLabel("Press [↑,←,↓,→] to move");
    JLabel tip2=new JLabel("Press 'A' to see full picture");
    tip.setBounds(15,10,200,30);
    tip2.setBounds(15,40,200,30);
    this.getContentPane().add(tip);
    this.getContentPane().add(tip2);
}
    private void reStart() {
        initData();
        stepCount = 0;
        initImage();
    }

    public boolean winOrNot() {

        for (int i = 0; i < arrWin.length; i++) {
            for (int i1 = 0; i1 < arrWin[i].length; i1++) {
                if (arrWin[i][i1] != arrTwo[i][i1]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (winOrNot()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 65) {
            //show full photo
            System.out.println("A");
            this.getContentPane().removeAll();
            setTip();
            JLabel step = new JLabel("Step: " + stepCount);
            step.setBounds(90, 100, 100, 30);
            this.getContentPane().add(step);

//            add full photo
            JLabel puzAll = new JLabel(new ImageIcon(path+inpPicBankMain+"\\"+inpPicBank+"\\"+inpPicBank+".jpg"));
            puzAll.setBounds(68, 157, 500, 500);
            this.getContentPane().add(puzAll);
//add background
            JLabel bg = new JLabel(new ImageIcon(path+"Background-USE.png"));
            bg.setBounds(18, 5, 600, 700);

            this.getContentPane().add(bg);
            this.getContentPane().repaint();
        } else if (code == 67) {
            //Cheat, to win directly
            System.out.println("C");
            this.getContentPane().removeAll();
            arrTwo = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();

        }

    }
//↑↓←→ A C function
    @Override
    public void keyReleased(KeyEvent e) {
        if (winOrNot()) {
            return;
        }
//37 38 39 40
        //37-↑
        int code = e.getKeyCode();

        if (code == 37) {
            stepCount++;
            System.out.println("←");
            if (y == 3) {
                //if y=3, y+1=4, it means there is no photo on the right of 0, so cannot exchange. so need return to end this action.
                return;
            }
            //exchange the 0 postion with the left item(x+1,y)
            arrTwo[x][y] = arrTwo[x][y + 1];
            arrTwo[x][y + 1] = 0;
            //get the 0 new place.
            y++;
            initImage();
        } else if (code == 38) {
            stepCount++;
            //same as above.
            System.out.println("↑");
            if (x == 3) {
                return;
            }
            arrTwo[x][y] = arrTwo[x + 1][y];
            arrTwo[x + 1][y] = 0;
            x++;
            initImage();
        } else if (code == 39) {
            stepCount++;
            //same as above.
            System.out.println("→");
            if (y == 0) {
                return;
            }
            arrTwo[x][y] = arrTwo[x][y - 1];
            arrTwo[x][y - 1] = 0;
            y--;
            initImage();
        } else if (code == 40) {
            stepCount++;
            //same as above.
            System.out.println("↓");
            if (x == 0) {
                return;
            }
            arrTwo[x][y] = arrTwo[x - 1][y];
            arrTwo[x - 1][y] = 0;
            x--;
            initImage();
        } else if (code == 65) {
            initImage();//A
        } else if (code == 67) {
            //C show win
            initImage();
        }
//82 R

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAg) {
            reStart();
        } else if (e.getSource() == logInAg) {
            //hide the game frame
            this.setVisible(false);
            //open the log in frame
            new LogInJFrame();

        }else if(e.getSource()==exit){
            System.exit(0);
        }else if(e.getSource()==infoMe){
            //create a JDialog, which is similar to JFrame
        JDialog jDialog=new JDialog();
            JLabel myInfo=new JLabel(new ImageIcon(path+"MyInfor-USE.png"));
            myInfo.setBounds(0,0,500,420);
            jDialog.getContentPane().add(myInfo);
            jDialog.setSize(550,460);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //if the jDialog didn't be closed, user cannot use other this game function.
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else if(e.getSource()==cat){
            stepCount=0;
           inpPicBankMain="Cat";
           inpPicBank=picBank[0][r.nextInt(picBank[0].length)];
           initData();
           initImage();
        }else if(e.getSource()==vancouver){
            stepCount=0;
            inpPicBankMain="Vancouver";
            inpPicBank=picBank[1][r.nextInt(picBank[1].length)];
            initData();
            initImage();
        }

    }
}
