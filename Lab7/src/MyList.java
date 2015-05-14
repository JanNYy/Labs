package courses.labs;

interface MyList {

    void addElement(Object element) throws LinkedListException;
    void addElement(int index, Object element) throws LinkedListException;
    void addAll(Object[] c) throws LinkedListException;
    void addAll(int index, Object[] c) throws LinkedListException;
    Object getElementAtIndex(int index) throws LinkedListException;
    void remove(int index) throws LinkedListException;
    void set(int index, Object element) throws LinkedListException;
    int indexOf(Object element) throws LinkedListException;
    int size() throws LinkedListException;
    Object[] toArray() throws LinkedListException;

}
