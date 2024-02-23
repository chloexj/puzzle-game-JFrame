package com.chloe.Test;

import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestJFrameMouse extends JFrame implements MouseListener {
    JButton button1=new JButton("A Button");
public TestJFrameMouse(){
    this.setSize(500,500);
    this.setDefaultCloseOperation(3);
    this.setLayout(null);
    this.setTitle("Test Mouse Action");

    button1.setBounds(0,0,150,100);
this.getContentPane().add(button1);
button1.addMouseListener(this);

    this.setVisible(true);
}

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click one time");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("keep press");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("release the mouse");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("touch the button");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("leave the button");
    }
}


