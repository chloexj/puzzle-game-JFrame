package com.chloe.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TestJFrame extends JFrame implements ActionListener {
    JButton button=new JButton("Press Me");
    JButton button2=new JButton("Second button");
public TestJFrame(){

    this.setSize(500,600);
    this.setDefaultCloseOperation(3);
    this.setTitle("Test Button Function");
    this.setLocationRelativeTo(null);
this.setLayout(null);

    //Practice button function

    button.setBounds(0,0,100,50);
    this.getContentPane().add(button);
    button.addActionListener(this);


    button2.setBounds(100,200,200,50);
this.getContentPane().add(button2);
button2.addActionListener(this);

    this.setVisible(true);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==button){
            System.out.println("first button");
        }else if(source==button2){
            Random r=new Random();
           button2.setLocation(r.nextInt(300),r.nextInt(550));
        }
    }
}
