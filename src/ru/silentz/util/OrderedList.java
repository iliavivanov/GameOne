package ru.silentz.util;

import java.util.LinkedList;
import java.util.ListIterator;

public class OrderedList<T extends Comparable<? super T>> extends LinkedList<T> {

    public boolean orderedAdd(T element) {
        ListIterator<T> itr = listIterator();
        while (true) {
            if (!itr.hasNext()) {
                itr.add(element);
                return true;
            }

            T elementInList = itr.next();
            if (elementInList.compareTo(element) > 0) {
                itr.previous();
                itr.add(element);
                return true;
            }
        }
    }
}
