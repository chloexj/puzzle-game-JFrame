package com.chloe.Test;

import com.chloe.other.FunctionClass;
import com.chloe.other.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws IOException {

//        TestJFrame test=new TestJFrame();
//        TestJFrameMouse test1=new TestJFrameMouse();
//        TestJFrameKeyB test2=new TestJFrameKeyB();
//        ArrayList<User>all=new ArrayList<>();
//        all.add(new User("aaa","bbb"));
//        User u1=new User("aaa","ccc");
//        User u2=new User("aaa","bbb");
//        User u3=new User("abb","bbb");
//        System.out.println(new FunctionClass().existOrNot(all, u1));
//        System.out.println(new FunctionClass().existOrNot(all, u2));
//        System.out.println(new FunctionClass().existOrNot(all, u3));
//        File account = new File("Record\\Account.txt");
//        new FunctionClass().codeFile(account);
        User inputA=new User("chloe","123");

        File directory =new File("Record\\"+inputA.getUsername());
        directory.mkdirs();
        System.out.println(new FunctionClass().getFolderSize(directory));
        //        System.out.println(directory.getAbsolutePath());
//        System.out.println(directory.getCanonicalPath());
//        System.out.println(directory.getPath());
//        System.out.println(directory.getName());
    }
}
