package com.chloe.Test;

import com.chloe.other.User;

import java.util.ArrayList;
import java.util.Random;

public class TEST2 {
    public static void main(String[] args) {
         ArrayList<User> allUser=new ArrayList<User>();
         {
            allUser.add(new User("admin","password123"));
            allUser.add(new User("chloe","password111"));
        }
        User user1=new User("admin","password123");
        boolean flag=false;
        for (int i = 0; i < allUser.size(); i++) {
            if((user1.getUserName()==allUser.get(i).getUserName())&&(user1.getPassword()==allUser.get(i).getPassword())){
                flag= true;
            }
        }

        System.out.println(flag);
    }
}
