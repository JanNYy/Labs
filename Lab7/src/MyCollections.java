package courses.labs;

import java.util.Comparator;
import java.util.RandomAccess;

public class MyCollections {

    public static void sort(MyList list, boolean desc) throws LinkedListException {
        if (list instanceof RandomAccess)
            quickSort(list,0,list.size()-1,desc);
        else
            bubbleSort(list, desc);
    }

    public static void sort(MyList list, boolean desc, Comparator c) throws LinkedListException {
        if (list instanceof RandomAccess)
            quickSort(list,0,list.size()-1,desc,c);
        else
            bubbleSort(list,desc,c);
    }

    private static void bubbleSort(MyList list, boolean desc) throws LinkedListException {
        if (list.getElementAtIndex(0) instanceof Comparable)
        {
            for (int i = 0; i < list.size()-1; i++)
                for (int j = i; j < list.size(); j++)
                    if ((((Comparable) list.getElementAtIndex(i)).compareTo(list.getElementAtIndex(j)) > 0)^desc)
                        swap(list, i, j);
        }
        else throw new LinkedListException("Comparator is required");
    }

    private static void bubbleSort(MyList list,  boolean desc, Comparator c) throws LinkedListException {
        if (c != null)
        {
            for (int i = 0; i < list.size()-1; i++)
                for (int j = i; j < list.size(); j++)
                    if ((c.compare(list.getElementAtIndex(i), list.getElementAtIndex(j)) > 0)^desc)
                        swap(list,i,j);
        }
        else throw new LinkedListException("Comparator is null");
    }

    private static void quickSort(MyList list, int firstIndex, int lastIndex, boolean desc) throws LinkedListException {
        if (list.getElementAtIndex(0) instanceof Comparable)
        {
            int i = firstIndex;
            int j = lastIndex;
            Object centralElement = list.getElementAtIndex(firstIndex+(lastIndex-firstIndex)/2);
            while (i <= j)
            {
                if (desc){
                    while (((Comparable) list.getElementAtIndex(i)).compareTo(centralElement) > 0)
                        i += 1;
                    while (((Comparable) list.getElementAtIndex(j)).compareTo(centralElement) < 0)
                        j -= 1;
                }
                else
                {
                    while (((Comparable) list.getElementAtIndex(i)).compareTo(centralElement) < 0)
                        i += 1;
                    while (((Comparable) list.getElementAtIndex(j)).compareTo(centralElement) > 0)
                        j -= 1;
                }
                if (i <= j)
                {
                    swap(list,i,j);
                    i += 1;
                    j -= 1;
                }
            }
            if (firstIndex < j)
                quickSort(list,firstIndex,j,desc);
            if (lastIndex > i)
                quickSort(list,i,lastIndex,desc);
        }
        else throw new LinkedListException("Comparator is required");
    }

    private static void quickSort(MyList list, int firstIndex, int lastIndex,  boolean desc, Comparator c) throws LinkedListException {
        if (c != null)
        {
            int i = firstIndex;
            int j = lastIndex;
            Object centralElement = list.getElementAtIndex(firstIndex+(lastIndex-firstIndex)/2);
            while (i <= j)
            {
                if (desc)
                {
                    while ((c.compare(list.getElementAtIndex(i), centralElement)) > 0)
                        i += 1;
                    while ((c.compare(list.getElementAtIndex(j), centralElement)) < 0)
                        j -= 1;
                }
                else
                {
                    while (((c.compare(list.getElementAtIndex(i), centralElement)) < 0))
                        i += 1;
                    while (((c.compare(list.getElementAtIndex(j), centralElement)) > 0))
                        j -= 1;
                }
                if (i <= j)
                {
                    swap(list,i,j);
                    i += 1;
                    j -= 1;
                }
            }
            if (firstIndex < j)
                quickSort(list,firstIndex,j,desc,c);
            if (lastIndex > i)
                quickSort(list,i,lastIndex,desc,c);
        }
        else throw new LinkedListException("Comparator is null");
    }

    public static void swap(MyList list, int i, int j) throws LinkedListException {
        Object tmpElement = list.getElementAtIndex(i);
        list.set(i, list.getElementAtIndex(j));
        list.set(j, tmpElement);
    }

    public static void copy(MyList dest, MyList src) throws LinkedListException {
        for (int i = 0; i < src.size(); i++)
            dest.addElement(src.getElementAtIndex(i));
    }

    public static void reverse(MyList list) throws LinkedListException {
        for (int i = 0; i < list.size()/2; i++)
            swap(list, i, list.size()-i-1);
    }

}