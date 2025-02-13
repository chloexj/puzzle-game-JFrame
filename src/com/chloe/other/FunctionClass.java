package com.chloe.other;

import java.io.*;
import java.util.ArrayList;

public class FunctionClass {
    public FunctionClass() {
    }
    public void codeDirectory(File f) throws IOException {
        File[] arr = f.listFiles();
        for (File file : arr) {
            codeFile(file);
        }
    }
public void codeFile(File f) throws IOException {
    FileInputStream fis=new FileInputStream(f);

    int len;
    ArrayList<Integer> lenArr=new ArrayList<>();
    while((len=fis.read())!=-1){
        lenArr.add(len);
    }
    fis.close();
        FileOutputStream fos=new FileOutputStream(f);
    for (Integer i : lenArr) {
        fos.write(i^20);
    }
    fos.close();

}
    public int existOrNot(ArrayList<User> userAccount, User input) {
        for (int i = 0; i < userAccount.size(); i++) {
            if (input.getUsername().equalsIgnoreCase(userAccount.get(i).getUsername())) {
                return i;
            }
        }
        return -1;
    }
    public void readUserInfo(File account,ArrayList<User>userAccount) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(account));
        String line;
        while ((line = br.readLine()) != null) {
            if(!line.equals("")){
            //username=chloe&password=123&5
            String[] userAndPass = line.split("&");
            //username=chloe
            String userName = userAndPass[0].split("=")[1];
            String password = userAndPass[1].split("=")[1];
            User newUser = new User(userName, password);
            newUser.setTryTime(Integer.parseInt(userAndPass[2]));
            userAccount.add(newUser);
            }
        }
        br.close();
//        System.out.println(userAccount);
    }
    public long getFolderSize(File f){
        File[] arr = f.listFiles();
long size=0;
        for (File file : arr) {
            if(file.isFile()){
                //是文件
            size=size+file.length();
            }else {
                //不是文件
                size=size+getFolderSize(file);
            }
        }
    return size;
    }

}
