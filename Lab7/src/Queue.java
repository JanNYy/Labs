package courses.labs;

interface Queue {

    void offer(Object element) throws LinkedListException;
    Object peek() throws LinkedListException;
    Object poll() throws LinkedListException;

}