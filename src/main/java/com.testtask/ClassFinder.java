package com.testtask;

import java.io.*;
import java.util.ArrayList;

public class ClassFinder {

    /**
     * Метод осуществляет поиск наименований классов в соответствии с введенным шаблоном
     *
     * @param filepath файл, содержащий наименования классов
     * @param pattern  шаблон, по которому производится поиск
     * @return отсортированный список наименований класов, подходящих под шаблон
     */
    public static ArrayList<String> findClasses(String filepath, String pattern) throws IOException {
        ArrayList<String> findResult = new ArrayList<>();
        InputStream in = new FileInputStream(filepath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        //className - переменная, содержащая в себе оригинальное наименование класса из файла (для итогового вывода)
        //classNameForSearch - переменная для модификации наименования класса в процессе поиска
        String className, classNameForSearch;
        while ((className = br.readLine()) != null) {
            //если все символы шаблона в нижнем регистре, переводим символы наименования класса также в нижний регистр,
            //чтобы поиск был регистронезависимым
            classNameForSearch = (pattern.equals(pattern.toLowerCase())) ? className.toLowerCase() : className;
            //добавляем к наименованию класса ' ', чтобы количество совпадающих символов было равным, в случае
            //когда поиск завершается символом  ' '
            classNameForSearch = classNameForSearch + ' ';

            int indexInWord = 0, indexInPattern = 0;
            char symbolOfWord, symbolOfPattern;
            while (indexInWord < classNameForSearch.length() && indexInPattern < pattern.length()) {
                symbolOfWord = classNameForSearch.charAt(indexInWord);
                symbolOfPattern = pattern.charAt(indexInPattern);

                //если шаблон содержит '*', то переходим к следующеу символу в шаблоне и в слове, тем самым
                //гарантируя, что как минимум один символ в наименовании класса будет пропущен при поиске
                if (symbolOfPattern == '*') {
                    indexInWord++;
                    indexInPattern++;
                    continue;
                }
                //если символ в шаблоне и наименовании совпал - переходим к следующему символу шаблона
                if (symbolOfWord == symbolOfPattern) {
                    indexInPattern++;
                }
                //если в шаблоне содержится ' ' - прекращаем поиск
                if (symbolOfPattern == ' ') {
                    break;
                }

                //переходим к следующему символу в наименовании класса
                indexInWord++;
            }

            //если в ходе поиска были перебраны все символы шаблона - наименование включает в себя необходимый шаблон,
            //включаем его в результат
            if (indexInPattern == pattern.length()) {
                findResult.add(className);
            }
        }
        FinderComparator finderComparator = new FinderComparator();
        findResult.sort(finderComparator);
        return findResult;
    }
}

