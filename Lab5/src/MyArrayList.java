package courses.labs;

public class MyArrayList {

    private Object[] data;
    private int currentIndex;

    private static final int INITIAL_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    public MyArrayList(int capacity) {
        data = new Object[capacity];
    }

    public int size() {
        //return data.length;
        return currentIndex;
    }

    private void ensureCapacity(int minCapacity) {
        Object[] newData = new Object[data.length+minCapacity];
        System.arraycopy(data,0,newData,0,data.length);
        data = newData;
    }

    private void indexCheck (int index) throws IllegalArgumentException {
        if ((index < 0) || (index >= currentIndex))
            throw new IllegalArgumentException();
    }

    public void add(Object element) {
        if (currentIndex == data.length)
            ensureCapacity(1);
        data[currentIndex] = element;
        currentIndex += 1;
    }

    public void add(int index, Object element) {
        indexCheck(index);
        if (currentIndex == data.length)
            ensureCapacity(1);
        System.arraycopy(data,index,data,index+1,currentIndex-index);
        data[index] = element;
        currentIndex += 1;
    }

    public void addAll(Object[] c) {
        if (data.length-currentIndex < c.length)
            ensureCapacity(c.length-(data.length-currentIndex));
        System.arraycopy(c,0,data,currentIndex,c.length);
        currentIndex += c.length;
    }

    public void addAll(int index, Object[] c) {
        if (data.length-currentIndex < c.length)
            ensureCapacity(c.length-(data.length-currentIndex));
        System.arraycopy(data,index,data,index+c.length,currentIndex-index);
        System.arraycopy(c,0,data,index,c.length);
        currentIndex += c.length;
    }

    public Object get(int index) {
        return data[index];
    }

    public void remove(int index) {
        indexCheck(index);
        System.arraycopy(data, index + 1, data, index, currentIndex-index);
        currentIndex -= 1;
    }

    public void set(int index, Object element) {
        indexCheck(index);
        data[index] = element;
    }

}