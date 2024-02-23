package com.chloe.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestJFrameKeyB extends JFrame implements KeyListener {
    JButton button1=new JButton("Key Test Button");
    public TestJFrameKeyB(){
        this.setSize(500,600);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Test Keyboard function");

        button1.setBounds(0,0,150,100);
this.getContentPane().add(button1);
button1.addKeyListener(this);

        this.setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keep press");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65) {
            System.out.println("you press A");
        }
    }
}
