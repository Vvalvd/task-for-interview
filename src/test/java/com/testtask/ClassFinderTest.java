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
                //символы в шаблоне в разном регистре (положительный сценарий)
                {filepath, "FoBa", new ArrayList<>(Arrays.asList("c.d.FooBar", "a.b.FooBarBaz"))},
                //символы в шаблоне в верхнем регистре в некорректном порядке
                {filepath, "BF", new ArrayList<>()},
                //символы в шаблоне в нижнем регистре (регистронезависимый поиск)
                {filepath, "fbb", new ArrayList<>(Collections.singletonList("a.b.FooBarBaz"))},
                //символы в шаблоне в разном регистре (негативный сценарий)
                {filepath, "fBb", new ArrayList<>()},
                //шаблон оканчивается символом ' '
                {filepath, "FBar ", new ArrayList<>(Collections.singletonList("c.d.FooBar"))},
                //шаблон подразумевает пропуск символов (*)
                {filepath, "B*rBaz", new ArrayList<>(Collections.singletonList("a.b.FooBarBaz"))},
                //проверка сортировки в результатирующем списке
                {"./src/test/resources/classesForSortCheck.txt", "FooBar",
                        new ArrayList<>(Arrays.asList("e.f.aFooBar", "c.d.bFooBar", "a.b.bFooBar", "a.b.zFooBarBaz"))}
        };
    }

    //проверка случая, когда введен некорректный путь к файлу
    @Test(expectedExceptions = IOException.class)
    public void checkIncorrectFile() throws IOException {
        findClasses("test.txt", "FoBa");
    }

    @Test(dataProvider = "test-class-finder")
    public void checkCamelCaseLetters(String filepath, String pattern, ArrayList<String> findResult) throws IOException {
        assertEquals(findResult, findClasses(filepath, pattern));
    }
}