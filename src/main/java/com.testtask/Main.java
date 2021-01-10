package com.testtask;

import java.io.IOException;

import static com.testtask.ClassFinder.findClasses;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Result: " + findClasses(args[0], args[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Two arguments required (<filename>, <pattern>)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
