package courses.labs;

public class MyLinkedList {

    public class Element {

        private Integer elValue;
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

        public Integer getElement() {
            return elValue;
        }

        public void setElement(Integer e) {
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

    boolean isEmpty() {
        return head == null;
    }

    void addElement(Integer element) throws LinkedListException {
        addLast(element);
    }

    void addElement(int index, Integer element) throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        if (index > size()-1) throw new LinkedListException("Index value is greater than the number of elements");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
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

    void addFirst(Integer element) throws LinkedListException {
        if (element == null) throw new LinkedListException("New element is null");
        Element newElement = new Element();
        newElement.setElement(element);
        newElement.setNext(head);
        head = newElement;
    }

    void addLast(Integer element) throws LinkedListException {
        if (element == null) throw new LinkedListException("New element is null");
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

    Integer getElementAtIndex(int index) throws LinkedListException {
        if (index > size()-1) throw new LinkedListException("Index value is greater than the number of elements");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
        int i = 0;
        Element indexElement = head;
        while (i < index)
        {
            indexElement = indexElement.getNext();
            i += 1;
        }
        return indexElement.getElement();
    }

    Integer getFirst() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        return head.getElement();
    }

    Integer getLast() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        Element lastElement = head;
        while (lastElement.hasNext())
        {
            lastElement = lastElement.getNext();
        }
        return lastElement.getElement();
    }

    void remove(int index) throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        if (index > size()-1) throw new LinkedListException("Index value is greater than the number of elements");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
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

    void removeFirst() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        head = head.getNext();
    }

    void removeLast() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
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

    void setElement(int index, Integer element) throws LinkedListException {
        if (element == null) throw new LinkedListException("New element is null");
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        if (index > size()-1) throw new LinkedListException("Index value is greater than the last index in list");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
        int i = 0;
        Element indexElement = head;
        while ((i <= index) && (indexElement.hasNext()))
        {
            indexElement = indexElement.getNext();
            i += 1;
        }
        indexElement.setElement(element);
    }

    int size() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        int listSize = 1;
        Element lastElement = head;
        while (lastElement.hasNext())
        {
            lastElement = lastElement.getNext();
            listSize += 1;
        }
        return listSize;
    }

    int indexOf(Integer element) throws LinkedListException {
        if (element == null) throw new LinkedListException("Required element is null");
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
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

    void printList() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("Linked list is empty");
        Element currentElement = head;
        while (currentElement.hasNext())
        {
            System.out.print(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement());
    }

}