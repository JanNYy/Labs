package courses.labs;

public class MyCollections {

    //LinkedList

    public static void sort(MyLinkedList list) throws MyLinkedList.LinkedListException {
        for (int i = 0; i < list.size()-1; i++)
            for (int j = i; j < list.size(); j++)
                if (list.getElementAtIndex(i) > list.getElementAtIndex(j))
                    swap(list,i,j);
    }

    public static void swap(MyLinkedList list, int i, int j) throws MyLinkedList.LinkedListException {
        Integer tmpElement = list.getElementAtIndex(i);
        list.setElement(i, list.getElementAtIndex(j));
        list.setElement(j, tmpElement);
    }

    public static void copy(MyLinkedList dest, MyLinkedList src) throws MyLinkedList.LinkedListException {
        for (int i = 0; i < src.size(); i++)
            dest.addElement(src.getElementAtIndex(i));
    }

    public static void reverse(MyLinkedList list) throws MyLinkedList.LinkedListException {
        for (int i = 0; i < list.size()/2; i++)
            swap(list, i, list.size()-i-1);
    }

    //ArrayList

    public static void sort(MyArrayList list) {
        quickSort(list,0,list.size()-1);
    }

    private static void quickSort(MyArrayList list, int firstIndex, int lastIndex) {
        int i = firstIndex;
        int j = lastIndex;
        Integer centralElement = (Integer)list.get(firstIndex+(lastIndex-firstIndex)/2);
        while (i <= j)
        {
            while ((Integer)list.get(i) < centralElement)
                i += 1;
            while ((Integer)list.get(j) > centralElement)
                j -= 1;
            if (i <= j)
            {
                swap(list,i,j);
                i += 1;
                j -= 1;
            }
        }
        if (firstIndex < j)
            quickSort(list, firstIndex, j);
        if (lastIndex > i)
            quickSort(list,i,lastIndex);
    }

    public static void swap(MyArrayList list, int i, int j) {
        Integer tmpElement = (Integer)list.get(i);
        list.set(i,(Integer)list.get(j));
        list.set(j,tmpElement);
    }

    public static void copy(MyArrayList dest, MyArrayList src) {
        for (int i = 0; i < src.size(); i++)
            dest.add(src.get(i));
    }

    public static void reverse(MyArrayList list) {
        for (int i = 0; i < list.size()/2; i++)
            swap(list, i, list.size()-i-1);
    }

    public static int search(MyArrayList list, Integer key) {
        return binarySearch(list,key,0,list.size());
    }

    private static int binarySearch (MyArrayList list, Integer key, int leftIndex, int rightIndex) {
        int centralIndex = leftIndex+(rightIndex-leftIndex)/2;
        if (leftIndex >= rightIndex)
            return -(1 + leftIndex);
        if ((Integer)list.get(leftIndex) == key)
            return leftIndex;
        if ((Integer)list.get(centralIndex) == key)
        {
            if (centralIndex == leftIndex + 1)
                return centralIndex;
            else
                return binarySearch(list,key,leftIndex,centralIndex+1);
        }
        //if ((Integer)list.get(centralIndex) == key)
            //return centralIndex;
        if ((Integer)list.get(centralIndex) > key)
            return binarySearch(list,key,leftIndex,centralIndex);
        return binarySearch(list,key,centralIndex+1,rightIndex);
    }

}

