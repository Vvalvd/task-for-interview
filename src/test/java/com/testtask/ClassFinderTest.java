package com.testtask;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.testtask.ClassFinder.findClasses;
import static org.testng.Assert.assertEquals;

public class ClassFinderTest {

    @DataProvider(name = "test-class-finder")
    Object[][] dataProviderTest() {
        String filepath = "./src/test/resources/classes.txt";
        return new Object[][]{
                //camel case in pattern (positive case)
                {filepath, "FoBa", new ArrayList<>(Arrays.asList("c.d.FooBar", "a.b.FooBarBaz"))},
                //upper case in pattern
                {filepath, "BF", new ArrayList<>()},
                //lower case in pattern (case insensitive)
                {filepath, "fbb", new ArrayList<>(Collections.singletonList("a.b.FooBarBaz"))},
                //camel case in pattern (negative case)
                {filepath, "fBb", new ArrayList<>()},
                //pattern ends with ' '
                {filepath, "FBar ", new ArrayList<>(Collections.singletonList("c.d.FooBar"))},
                //pattern with wildcard character (*)
                {filepath, "B*rBaz", new ArrayList<>(Collections.singletonList("a.b.FooBarBaz"))},
                //check of sort in result list
                {"./src/test/resources/classesForSortCheck.txt", "FooBar",
                        new ArrayList<>(Arrays.asList("e.f.aFooBar", "c.d.bFooBar", "a.b.bFooBar", "a.b.zFooBarBaz"))}
        };
    }

    //case with incorrect file path
    @Test(expectedExceptions = IOException.class)
    public void checkIncorrectFile() throws IOException {
        findClasses("test.txt", "FoBa");
    }

    @Test(dataProvider = "test-class-finder")
    public void checkClassFinder(String filepath, String pattern, ArrayList<String> findResult) throws IOException {
        assertEquals(findClasses(filepath, pattern), findResult);
    }
}