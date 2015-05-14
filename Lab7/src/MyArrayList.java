package courses.labs;

import java.util.RandomAccess;

public class MyArrayList implements MyList, RandomAccess {

    private Object[] data;
    private int currentIndex;

    private static final int INITIAL_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    public MyArrayList(int capacity) {
        data = new Object[capacity];
    }

    private void indexCheck(int index) throws LinkedListException {
        if (index >= currentIndex) throw new LinkedListException("Index value is greater than the number of elements");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
    }

    private void ensureCapacity(int minCapacity) {
        Object[] newData = new Object[data.length+minCapacity];
        System.arraycopy(data,0,newData,0,data.length);
        data = newData;
    }

    public void addElement(Object element) throws LinkedListException {
        if (currentIndex == data.length)
            ensureCapacity(1);
        data[currentIndex] = element;
        currentIndex += 1;
    }

    public void addElement(int index, Object element) throws LinkedListException {
        indexCheck(index);
        if (currentIndex == data.length)
            ensureCapacity(1);
        System.arraycopy(data,index,data,index+1,currentIndex-index);
        data[index] = element;
        currentIndex += 1;
    }

    public void addAll(Object[] c) throws LinkedListException {
        if (data.length-currentIndex < c.length)
            ensureCapacity(c.length-(data.length-currentIndex));
        System.arraycopy(c,0,data,currentIndex,c.length);
        currentIndex += c.length;
    }

    public void addAll(int index, Object[] c) throws LinkedListException {
        indexCheck(index);
        if (data.length-currentIndex < c.length)
            ensureCapacity(c.length-(data.length-currentIndex));
        System.arraycopy(data,index,data,index+c.length,currentIndex-index);
        System.arraycopy(c,0,data,index,c.length);
        currentIndex += c.length;
    }

    public Object getElementAtIndex(int index) throws LinkedListException {
        indexCheck(index);
        return data[index];
    }

    public void remove(int index) throws LinkedListException {
        indexCheck(index);
        System.arraycopy(data, index + 1, data, index, currentIndex-index);
        currentIndex -= 1;
    }

    public void set(int index, Object element) throws LinkedListException {
        indexCheck(index);
        data[index] = element;
    }

    public int indexOf(Object element) throws LinkedListException {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(element)) return i;
        }
        return -1;
    }

    public int size() throws LinkedListException {
        return currentIndex;
    }

    public Object[] toArray() throws LinkedListException {
        Object[] array = new Object[size()];
        System.arraycopy(data,0,array,0,size());
        return array;
    }

    public void printList() {
        for (int i = 0; i < currentIndex; i++ )
            System.out.print(data[i]+ " ");
        System.out.println();
    }

}