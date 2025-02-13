package com.chloe.Test;

import com.chloe.other.FunctionClass;

import java.io.File;
import java.io.IOException;

public class TestCode3Dir {
    public static void main(String[] args) throws IOException {
        new FunctionClass().codeDirectory(new File("Photos\\Special\\Special1"));
    }
}
