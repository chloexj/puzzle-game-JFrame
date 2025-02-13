package com.chloe.other;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class GameInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 29702578657591563L;
    private int[][]arrTwo;
    private int x;
    private int y;
    private int stepCount;
    private String path;
    private String inpPicBankMain;
    private String  inpPicBank;

    public GameInfo(int[][] arrTwo, int x, int y, int stepCount) {
    }

    public GameInfo(int[][] arrTwo, int x, int y, int stepCount, String inpPicBankMain, String inpPicBank) {
        this.arrTwo = arrTwo;
        this.x = x;
        this.y = y;
        this.stepCount = stepCount;
        this.inpPicBankMain = inpPicBankMain;
        this.inpPicBank = inpPicBank;
    }

    public int[][] getArrTwo() {
        return arrTwo;
    }

    public void setArrTwo(int[][] arrTwo) {
        this.arrTwo = arrTwo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }


    public String getInpPicBankMain() {
        return inpPicBankMain;
    }

    public void setInpPicBankMain(String inpPicBankMain) {
        this.inpPicBankMain = inpPicBankMain;
    }

    public String getInpPicBank() {
        return inpPicBank;
    }

    public void setInpPicBank(String inpPicBank) {
        this.inpPicBank = inpPicBank;
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "arrTwo=" + Arrays.toString(arrTwo) +
                ", x=" + x +
                ", y=" + y +
                ", stepCount=" + stepCount +
                ", inpPicBankMain='" + inpPicBankMain + '\'' +
                ", inpPicBank='" + inpPicBank + '\'' +
                '}';
    }
}
