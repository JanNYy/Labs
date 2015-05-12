package courses.labs;

public class MyLinkedList {

    private class Element {

        private Object elValue;
        private Element next;

        public boolean hasNext() {
            return next != null;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element nextElement) {
            next = nextElement;
        }

        public Object getElement() {
            return elValue;
        }

        public void setElement(Object e) {
            elValue = e;
        }
    }

    public static class LinkedListException extends Exception {

        public LinkedListException(String message) {
            super(message);
        }

        public LinkedListException() {

        }
    }

    private Element head;

    private void indexCheck (int index) throws IllegalArgumentException, LinkedListException {
        if (index > size()+1) throw new LinkedListException("Index value is greater than the number of elements");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
    }

    private void elementCheck(Object element) throws LinkedListException {
        if (element == null) throw new LinkedListException("Element is null");
    }

    private void listCheck() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addElement(Object element) throws LinkedListException {
        addLast(element);
    }

    public void addElement(int index, Object element) throws LinkedListException {
        listCheck();
        elementCheck(element);
        indexCheck(index);
        if (index == 0) addFirst(element);
        else
        {
            Element precedingElement = head;
            int i = 0;
            while ((precedingElement.hasNext()) && (i < index-1))
            {
                precedingElement = precedingElement.getNext();
                i +=1;
            }
            Element newElement = new Element();
            newElement.setElement(element);
            newElement.setNext(precedingElement.getNext());
            precedingElement.setNext(newElement);
        }
    }

    public void addFirst(Object element) throws LinkedListException {
        elementCheck(element);
        Element newElement = new Element();
        newElement.setElement(element);
        newElement.setNext(head);
        head = newElement;
    }

    public void addLast(Object element) throws LinkedListException {
        elementCheck(element);
        if (isEmpty()) addFirst(element);
        else
        {
            Element lastElement = head;
            while (lastElement.hasNext())
            {
                lastElement = lastElement.getNext();
            }
            Element newElement = new Element();
            lastElement.setNext(newElement);
            newElement.setElement(element);
        }
    }

    public Object getElementAtIndex(int index) throws LinkedListException {
        indexCheck(index);
        int i = 0;
        Element indexElement = head;
        while (i < index)
        {
            indexElement = indexElement.getNext();
            i += 1;
        }
        return indexElement.getElement();
    }

    public Object getFirst() throws LinkedListException {
        listCheck();
        return head.getElement();
    }

    public Object getLast() throws LinkedListException {
        listCheck();
        Element lastElement = head;
        while (lastElement.hasNext())
        {
            lastElement = lastElement.getNext();
        }
        return lastElement.getElement();
    }

    public void remove(int index) throws LinkedListException {
        listCheck();
        indexCheck(index);
        int i = 0;
        Element precedingElement = head;
        while (i < index-1)
        {
            precedingElement = precedingElement.getNext();
            i += 1;
        }
        if (index == size()-1) precedingElement.setNext(null);
        else precedingElement.setNext((precedingElement.getNext()).getNext());
    }

    public void removeFirst() throws LinkedListException {
        listCheck();
        head = head.getNext();
    }

    public void removeLast() throws LinkedListException {
        listCheck();
        if (size() == 1) head = null;
        else
        {
            int i = 0;
            Element precedingElement = head;
            while (i < size()-2)
            {
                precedingElement = precedingElement.getNext();
                i += 1;
            }
            precedingElement.setNext(null);
        }
    }

    public void setElement(int index, Object element) throws LinkedListException {
        elementCheck(element);
        listCheck();
        indexCheck(index);
        int i = 0;
        Element indexElement = head;
        while ((i < index) && (indexElement.hasNext()))
        {
            indexElement = indexElement.getNext();
            i += 1;
        }
        indexElement.setElement(element);
    }

    public int size() throws LinkedListException {
        listCheck();
        int listSize = 1;
        Element lastElement = head;
        while (lastElement.hasNext())
        {
            lastElement = lastElement.getNext();
            listSize += 1;
        }
        return listSize;
    }

    public int indexOf(Object element) throws LinkedListException {
        elementCheck(element);
        listCheck();
        Element indexElement = head;
        int i = 0;
        while (i<size())
        {
            if ((indexElement.getElement()).equals(element)) return i;
            indexElement = indexElement.getNext();
            i += 1;
        }
        return -1;
    }

    public void printList() throws LinkedListException {
        listCheck();
        Element currentElement = head;
        while (currentElement.hasNext())
        {
            System.out.println(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement().toString());
    }

}