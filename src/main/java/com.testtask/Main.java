package com.testtask;

import java.io.IOException;

import static com.testtask.ClassFinder.findClasses;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Результат: " + findClasses(args[0], args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
