package courses.labs;

import java.util.Arrays;

public class CircularBuffer {

    private final int bufferSize;
    private final Object[] bufferData;
    private int currentSize;
    private int indexPut;
    private int indexGet;

    public CircularBuffer(int buffSize) {
        bufferSize = buffSize;
        bufferData = new Object[bufferSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == bufferSize;
    }

    public synchronized void put(Object dataElement) {
        if (isFull()) throw new RuntimeException("Circular buffer is full");
        bufferData[indexPut++] = dataElement;
        if (indexPut == bufferSize)
            indexPut = 0;
        currentSize += 1;
    }

    public synchronized Object get() {
        if (isEmpty()) throw new RuntimeException("Circular buffer is empty");
        Object obj = bufferData[indexGet++];
        if (indexGet == bufferSize)
            indexGet = 0;
        currentSize -= 1;
        return obj;
    }

    @Override
    public String toString() {
        return "Circular buffer: size = " + currentSize + ", data = " + Arrays.toString(bufferData);
    }
}
