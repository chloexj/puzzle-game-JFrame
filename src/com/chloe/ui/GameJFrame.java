package com.chloe.ui;

import com.chloe.other.FunctionClass;
import com.chloe.other.GameInfo;
import com.chloe.other.User;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Properties;
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
    JMenu loadGame = new JMenu("Load Game");
    JMenu saveGame = new JMenu("Save Game");
    JMenuItem playAg = new JMenuItem("Play Again");
    JMenuItem logInAg = new JMenuItem("Log in Again");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem infoMe = new JMenuItem("Information");
    JMenuItem cat = new JMenuItem("Cat");
    JMenu changePic = new JMenu("Change Picture");
    JMenuItem vancouver = new JMenuItem("Vancouver");
    JMenuItem special = new JMenuItem("Special");
    JMenuItem re0 = new JMenuItem("Save Record 0 (empty)");
    JMenuItem re1 = new JMenuItem("Save Record 1 (empty)");
    JMenuItem re2 = new JMenuItem("Save Record 2 (empty)");
    JMenuItem re3 = new JMenuItem("Save Record 3 (empty)");
    JMenuItem re4 = new JMenuItem("Save Record 4 (empty)");
    JMenuItem lRe0 = new JMenuItem("Record 0 (empty)");
    JMenuItem lRe1 = new JMenuItem("Record 1 (empty)");
    JMenuItem lRe2 = new JMenuItem("Record 2 (empty)");
    JMenuItem lRe3 = new JMenuItem("Record 3 (empty)");
    JMenuItem lRe4 = new JMenuItem("Record 4 (empty)");
    Random r = new Random();
    String[] picBankMain = {"Cat", "Vancouver", "Special"};
    String[][] picBank = {
            {"Cat1", "Cat2", "Cat3", "Cat4"},//cat
            {"Vancouver1", "Vancouver2", "Vancouver3"},//Vancouver
            {"Special1"}
    };
    //this index is used to lead the picture

    //if I want special account show special photo
    //1. 载入账户，如果账户等于载入账户，那么index的随机范围增加
    // 也就是说inpPicBankMain和inpPicBank的随机index要加一个数组
    //这个步骤要在哪里实现呢？ 在无参构造里面？
    //但，如果在无参构造里实现，那initalImage就无法引用到inp...这两个参数。
    //2. JMenu 要加一个item
    //
    //首先，确认一下，哪里用到了inpBank...
    //1. initial image肯定有用到，2. 各种点击事件，因为重新开始之类的操作需要得到数据 3. 输出存档。
    //也就是说这个参数必须放在成员变量里创建。并且改动后也不能变了就。
    //这样的话可以在无参构造里再给他赋值，然后储存？
//    可以试一下哈
    //顺带一提，突然想起来基本数据类型传进方法里要要求形参，但是数组可以自由穿梭，然后把string 改成string[]就成功了。
    //最后一步，在加载special 的时候对文件解密，加载完后再加密。
    int index1 = 0;
    int index2 = 0;
    String[]inpPicArr=new String[2];
//    String inpPicBankMain = "";
//    String inpPicBank = "";
//    int index1 = r.nextInt(picBankMain.length);
//    int index2 = r.nextInt(picBank[index1].length);
//    String inpPicBankMain = picBankMain[index1];
//    String inpPicBank = picBank[index1][index2];

    String path = "..\\PuzzleGame\\Photos\\";
    User inputA = new User();

    public GameJFrame(User inputAccount) throws IOException {
        inputA = inputAccount;
        if (inputA.getUsername().equals("chloe")) {
            index1 = r.nextInt(picBankMain.length);
            changePic.add(special);
            special.addActionListener(this);
            File f=new File("Record\\forSpecial.txt");
            BufferedReader br=new BufferedReader(new FileReader(f));
            int flag = Integer.parseInt(br.readLine());
            br.close();
            if(flag==0) {
                new FunctionClass().codeDirectory(new File("Photos\\Special\\Special1"));
                BufferedWriter bw=new BufferedWriter(new FileWriter(f));
                bw.write("1");
                bw.close();
            }
        } else {
            index1 = r.nextInt(picBankMain.length - 1);
        }

        index2 = r.nextInt(picBank[index1].length);
        inpPicArr[0] = picBankMain[index1];
        inpPicArr[1] = picBank[index1][index2];
        System.out.println("Jframe"+inpPicArr[0] );
        System.out.println("Jframe"+inpPicArr[1]);

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
        setIconImage(Toolkit.getDefaultToolkit().getImage(path + "myIcon.jpg"));
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


    private void initImage() throws IOException {

        //when first time run this code, there is nothing in screen, so removing all doesn't effect anything.
        //but second time it is used, it means user move(because I use this code in KeyListener)
        //clean all photos to refresh the new screen after user movement.
        System.out.println("Inital Image" + inpPicArr[1] );
        System.out.println("Inital Image" + inpPicArr[0] );
        //此处做一个解密,这样做会贼卡。。。因为每一次移动都要重新刷新界面 加密解密。。。

        this.getContentPane().removeAll();
        if (winOrNot()) {
            JLabel winLogo = new JLabel(new ImageIcon(path + "Win.png"));
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
                System.out.println(path + inpPicArr[0]  + "\\" + inpPicArr[1]  + "\\" + arrTwo[a][i] + ".jpg");
                JLabel puzzle = new JLabel(new ImageIcon(path + inpPicArr[0]  + "\\" + inpPicArr[1]  + "\\" + arrTwo[a][i] + ".jpg"));
                //initial the position and size of the picture
                puzzle.setBounds(125 * i + 68, 125 * a + 157, 125, 125);
                puzzle.setBorder(new EtchedBorder(1));
                this.getContentPane().add(puzzle);
//        this.add(jLabel);
//get the new screen after user movement.
                this.getContentPane().repaint();
            }
        }

        JLabel background = new JLabel(new ImageIcon(path + "Background-USE.png"));
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


        //add the mouse click action to the menu item

        playAg.addActionListener(this);
        logInAg.addActionListener(this);
        exit.addActionListener(this);
        infoMe.addActionListener(this);
        playAg.addActionListener(this);
        cat.addActionListener(this);
        vancouver.addActionListener(this);
        re1.addActionListener(this);
        re2.addActionListener(this);
        re3.addActionListener(this);
        re4.addActionListener(this);
        re0.addActionListener(this);
        lRe1.addActionListener(this);
        lRe2.addActionListener(this);
        lRe3.addActionListener(this);
        lRe4.addActionListener(this);
        lRe0.addActionListener(this);
        //add menu item to menu, and add menu to menu bar
        saveGame.add(re0);
        saveGame.add(re1);
        saveGame.add(re2);
        saveGame.add(re3);
        saveGame.add(re4);

        loadGame.add(lRe0);
        loadGame.add(lRe1);
        loadGame.add(lRe2);
        loadGame.add(lRe3);
        loadGame.add(lRe4);

        function.add(changePic);
        function.add(playAg);
        function.add(saveGame);
        function.add(loadGame);
        function.add(logInAg);
        function.add(exit);
        about.add(infoMe);
        changePic.add(cat);
        changePic.add(vancouver);

        jMenuBar.add(function);
        jMenuBar.add(about);
        getGameInfo();
        //set menu to frame
        this.setJMenuBar(jMenuBar);

    }

    public void getGameInfo() {
        //read every data in file which's name is start with "save", get that name
        //and get index, get the step
        //setTxt
        File f = new File("Record\\" + inputA.getUsername());
        f.mkdirs();
        long folderSize = new FunctionClass().getFolderSize(f);
        if (folderSize != 0) {
            File[] arr = f.listFiles();
            for (File file : arr) {
                if (file.getName().startsWith("save")) {
                    int index = 0;
                    GameInfo gi = null;
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                        index = file.getName().charAt(4) - '0';
                        gi = (GameInfo) ois.readObject();
                        ois.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    int stepCount1 = gi.getStepCount();
                    saveGame.getItem(index).setText("Save Record " + index + " (Step:" + stepCount1 + ")");
                    loadGame.getItem(index).setText("Record " + index + " (Step:" + stepCount1 + ")");
                }
            }
        }
    }

    public void setTip() {
        JLabel tip = new JLabel("Press [↑,←,↓,→] to move");
        JLabel tip2 = new JLabel("Press 'A' to see full picture");
        tip.setBounds(15, 10, 200, 30);
        tip2.setBounds(15, 40, 200, 30);
        this.getContentPane().add(tip);
        this.getContentPane().add(tip2);
    }

    private void reStart() throws IOException {
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
            JLabel puzAll = new JLabel(new ImageIcon(path + inpPicArr[0]  + "\\" + inpPicArr[1]  + "\\" + inpPicArr[1]  + ".jpg"));
            puzAll.setBounds(68, 157, 500, 500);
            this.getContentPane().add(puzAll);
//add background
            JLabel bg = new JLabel(new ImageIcon(path + "Background-USE.png"));
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
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

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
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
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
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (code == 65) {
            try {
                initImage();//A
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (code == 67) {
            //C show win
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
//82 R

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAg) {
            try {
                reStart();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == logInAg) {
            //hide the game frame
            this.setVisible(false);
            //open the log in frame
            try {
                new LogInJFrame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == infoMe) {
            Properties prop = new Properties();
            try {
                FileInputStream fis = new FileInputStream(new File("src\\game.properties"));
                prop.load(fis);
                fis.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String add = (String) prop.get("add");
            System.out.println(add);

            //create a JDialog, which is similar to JFrame
            JDialog jDialog = new JDialog();
            JLabel myInfo = new JLabel(new ImageIcon(add));
            myInfo.setBounds(0, 0, 500, 420);
            jDialog.getContentPane().add(myInfo);
            jDialog.setSize(550, 460);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //if the jDialog didn't be closed, user cannot use other this game function.
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (e.getSource() == cat) {
            stepCount = 0;
            inpPicArr[0]  = "Cat";
            inpPicArr[1]  = picBank[0][r.nextInt(picBank[0].length)];
            initData();
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == vancouver) {
            stepCount = 0;
            inpPicArr[0]  = "Vancouver";
            inpPicArr[1]  = picBank[1][r.nextInt(picBank[1].length)];
            initData();
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == special) {
            stepCount = 0;
            inpPicArr[0]  = "Special";
            inpPicArr[1]  = picBank[2][r.nextInt(picBank[2].length)];
            initData();
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == re1 || e.getSource() == re2 || e.getSource() == re3 || e.getSource() == re4 || e.getSource() == re0) {
            JMenuItem item = (JMenuItem) e.getSource();
            int num = Integer.parseInt(item.getText().split(" ")[2]);
//            System.out.println(item.getText());
//Save Record 4 (empty)");

            GameInfo gameInfo = new GameInfo(arrTwo, x, y, stepCount, inpPicArr[0] , inpPicArr[1] );
            try {
                File directory = new File("Record\\" + inputA.getUsername());
                directory.mkdirs();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(directory.getPath() + "\\save" + num + ".data"));
                //here I need add a directory, the name is from input account.
                System.out.println(inputA.getUsername());
                oos.writeObject(gameInfo);
                oos.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            item.setText("Save Record " + num + " (Step:" + stepCount + ")");
            loadGame.getItem(num).setText("Record " + num + " (Step:" + stepCount + ")");
            System.out.println("record successfully");

        } else if (e.getSource() == lRe1 || e.getSource() == lRe2 || e.getSource() == lRe3 || e.getSource() == lRe0) {
            JMenuItem item = (JMenuItem) e.getSource();
//        System.out.println(item.getText().split(" ")[1]);
//        System.out.println(item.getText().split(" ")[1].split("\\(")[0]);
            //   "Record 0 (empty)");
            int num = Integer.parseInt(item.getText().split(" ")[1]);
//        System.out.println(num);
            GameInfo gameInfo = null;
            try {
                File directory = new File("Record\\" + inputA.getUsername());
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directory.getPath() + "\\save" + num + ".data"));
                gameInfo = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            x = gameInfo.getX();
            y = gameInfo.getY();
            arrTwo = gameInfo.getArrTwo();
            stepCount = gameInfo.getStepCount();

            inpPicArr[0]  = gameInfo.getInpPicBankMain();
            inpPicArr[1]  = gameInfo.getInpPicBank();
            System.out.println(gameInfo);
            try {
                initImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
