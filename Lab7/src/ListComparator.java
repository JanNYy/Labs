package courses.labs;

import java.util.Comparator;

public class ListComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        return (Integer)o1-(Integer)o2;
    }

}