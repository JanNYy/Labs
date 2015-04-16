package courses.labs;

class MyQueue {

    private MyLinkedList.Element firstPos;
    private MyLinkedList.Element lastPos;

    void offer(Integer element) throws MyLinkedList.LinkedListException {
        if (element == null) throw new MyLinkedList.LinkedListException("New element is null");
        MyLinkedList.Element newElement = new MyLinkedList().new Element();
        newElement.setElement(element);
        if (firstPos == null)
        {
            firstPos = newElement;
            lastPos = firstPos;
        }
        else
        {
            lastPos.setNext(newElement);
            lastPos = newElement;
        }
    }

    Integer peek() throws MyLinkedList.LinkedListException {
        if (firstPos == null) throw new MyLinkedList.LinkedListException("Queue is empty");
        return firstPos.getElement();
    }

    Integer poll() throws MyLinkedList.LinkedListException {
        if (firstPos == null) throw new MyLinkedList.LinkedListException("Queue is empty");
        MyLinkedList.Element removedElement = firstPos;
        if (firstPos != lastPos)
            firstPos = firstPos.getNext();
        else
        {
            firstPos = null;
            lastPos = null;
        }
        return removedElement.getElement();
    }

    void printQueue() throws MyLinkedList.LinkedListException {
        if (firstPos == null) throw new MyLinkedList.LinkedListException("Queue is empty");
        MyLinkedList.Element currentElement = firstPos;
        while (currentElement.hasNext())
        {
            System.out.print(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement());
    }

}