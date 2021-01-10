package com.testtask;

import java.io.*;
import java.util.ArrayList;

public class ClassFinder {

    /**
     * Method implement class finder functionality in a similar way to the Intellij IDEA Ctrl+N search
     *
     * @param filepath file that contains class names separated by line breaks
     * @param pattern search pattern
     * @return sorted class names
     */
    public static ArrayList<String> findClasses(String filepath, String pattern) throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        InputStream in = new FileInputStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String className, classNameForSearch;
        while ((className = br.readLine()) != null) {
            if (className.isEmpty()) {
                continue;
            }
            //if the search pattern consists of only lower case characters then the search becomes case insensitive
            classNameForSearch = (pattern.equals(pattern.toLowerCase())) ? className.toLowerCase() : className;
            //add ' ' to class name, for case when pattern has ' ' in the end
            classNameForSearch = classNameForSearch + ' ';

            int indexInWord = 0, indexInPattern = 0;
            char symbolOfWord, symbolOfPattern;
            while (indexInWord < classNameForSearch.length() && indexInPattern < pattern.length()) {
                symbolOfWord = classNameForSearch.charAt(indexInWord);
                symbolOfPattern = pattern.charAt(indexInPattern);

                //if pattern contains '*', then go to next symbol in class name and pattern
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

