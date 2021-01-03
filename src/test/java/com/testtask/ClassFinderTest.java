package com.testtask;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.testtask.ClassFinder.findClasses;
import static org.junit.Assert.assertEquals;

public class ClassFinderTest {

    private final String FILEPATH = "./src/test/resources/classes.txt";

    @Test(expected = IOException.class)
    public void checkIncorrectFile() throws IOException {
        findClasses("test.txt", "FoBa");
    }

    @Test
    public void checkCamelCaseLetters() throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        findResult.add("c.d.FooBar");
        findResult.add("a.b.FooBarBaz");
        assertEquals(findResult, findClasses(FILEPATH, "FoBa"));
    }

    @Test
    public void checkUpperCaseWrongOrder() throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        assertEquals(findResult, findClasses(FILEPATH, "BF"));
    }

    @Test
    public void checkCaseInsensitive() throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        findResult.add("a.b.FooBarBaz");
        assertEquals(findResult, findClasses(FILEPATH, "fbb"));
    }

    @Test
    public void checkCaseSensitive() throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        assertEquals(findResult, findClasses(FILEPATH, "fBb"));
    }

    @Test
    public void checkEndsWithSpace() throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        findResult.add("c.d.FooBar");
        assertEquals(findResult, findClasses(FILEPATH, "FBar "));
    }

    @Test
    public void checkMissingLetters() throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        findResult.add("a.b.FooBarBaz");
        assertEquals(findResult, findClasses(FILEPATH, "B*rBaz"));
    }

    @Test
    public void checkSort() throws Exception {
        ArrayList<String> findResult = new ArrayList<>();
        findResult.add("e.f.aFooBar");
        findResult.add("c.d.bFooBar");
        findResult.add("a.b.bFooBar");
        findResult.add("a.b.zFooBarBaz");
        assertEquals(findResult, findClasses("./src/test/resources/classesForSortCheck.txt", "FooBar"));
    }
}