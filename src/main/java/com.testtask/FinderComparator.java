package com.testtask;

import java.util.Comparator;

public class FinderComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        o1 = o1.substring(o1.lastIndexOf(".") + 1);
        o2 = o2.substring(o2.lastIndexOf(".") + 1);
        return o1.compareTo(o2);
    }
}
