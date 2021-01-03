package com.testtask;

import java.io.*;
import java.util.ArrayList;

public class ClassFinder {

    public static ArrayList<String> findClasses(String filepath, String pattern) throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        InputStream in = new FileInputStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String className, classNameForSearch;
        while ((className = br.readLine()) != null) {
            classNameForSearch = (pattern.equals(pattern.toLowerCase())) ? className.toLowerCase() : className;
            classNameForSearch = classNameForSearch + ' ';

            int indexInWord = 0, indexInPattern = 0;
            char symbolOfWord, symbolOfPattern;
            while (indexInWord < classNameForSearch.length() && indexInPattern < pattern.length()) {
                symbolOfWord = classNameForSearch.charAt(indexInWord);
                symbolOfPattern = pattern.charAt(indexInPattern);

                if (symbolOfPattern == '*') {
                    indexInWord++;
                    indexInPattern++;
                    continue;
                }
                if (symbolOfWord == symbolOfPattern) {
                    indexInPattern++;
                }
                if (symbolOfPattern == ' ') {
                    break;
                }

                indexInWord++;
            }

            if (indexInPattern == pattern.length()) {
                findResult.add(className);
            }
        }
        FinderComparator finderComparator = new FinderComparator();
        findResult.sort(finderComparator);
        return findResult;
    }
}

