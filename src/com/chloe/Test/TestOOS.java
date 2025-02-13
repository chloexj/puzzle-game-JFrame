package com.chloe.Test;

import com.chloe.other.GameInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestOOS {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GameInfo gameInfo = null;

        File directory = new File("Record\\chloe");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(directory.getPath() + "\\save2.data"));
        gameInfo = (GameInfo) ois.readObject();
        ois.close();
        System.out.println(gameInfo);
    }
}
