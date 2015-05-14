package courses.labs;

public class MyLinkedList implements MyList, Queue, Stack {

    private static class Element implements Comparable{

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

        public int compareTo(Object o) {
            Element other = (Element)o;
            return (Integer)this.getElement() - (Integer)other.getElement();
        }

    }

    private Element head;

    private void indexCheck (int index) throws IllegalArgumentException, LinkedListException {
        if (index > size()-1) throw new LinkedListException("Index value is greater than the number of elements");
        if (index < 0) throw new LinkedListException("Index value is less than zero");
    }

    private void elementCheck(Object element) throws LinkedListException {
        if (element == null) throw new LinkedListException("Element is null");
    }

    private void listCheck() throws LinkedListException {
        if (isEmpty()) throw new LinkedListException("List is empty");
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printList() throws LinkedListException {
        listCheck();
        Element currentElement = head;
        while (currentElement.hasNext())
        {
            System.out.print(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement());
    }

    private void addFirst(Object element) throws LinkedListException {
        elementCheck(element);
        Element newElement = new Element();
        newElement.setElement(element);
        newElement.setNext(head);
        head = newElement;
    }

    public void addElement(Object element) throws LinkedListException {
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

    public void addAll(Object[] c) throws LinkedListException {
        for (int i = 0; i < c.length; i++)
        {
            addElement(c[i]);
        }
    }

    public void addAll(int index, Object[] c) throws LinkedListException {
        for (int i = 0; i < c.length; i++)
        {
            addElement(index++,c[i]);
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

    public void set(int index, Object element) throws LinkedListException {
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

    public int indexOf(Object element) throws LinkedListException {
        elementCheck(element);
        listCheck();
        Element indexElement = head;
        int i = 0;
        while (i < size())
        {
            if ((indexElement.getElement()).equals(element)) return i;
            indexElement = indexElement.getNext();
            i += 1;
        }
        return -1;
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

    public Object[] toArray() throws LinkedListException {
        Object[] array = new Object[size()];
        Element listElement = head;
        for (int i = 0; i < size(); i++) {
            array[i] = listElement.getElement();
            listElement = listElement.getNext();
        }
        return array;
    }

    //Queue

    public void offer(Object element) throws LinkedListException {
        elementCheck(element);
        Element newElement = new Element();
        newElement.setElement(element);
        if (isEmpty()) addFirst(element);
        else addElement(element);
    }

    public Object peek() throws LinkedListException {
        listCheck();
        return head.getElement();
    }

    public Object poll() throws LinkedListException {
        listCheck();
        Element removedElement = head;
        head = head.getNext();
        return removedElement;
    }

    //Stack

    public void push(Object element) throws LinkedListException {
        elementCheck(element);
        Element newElement = new Element();
        newElement.setElement(element);
        newElement.setNext(head);
        head = newElement;
    }

    public Object pop() throws LinkedListException {
        listCheck();
        Element popElement = head;
        head = head.getNext();
        return popElement.getElement();
    }

}