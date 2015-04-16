package courses.labs;

class MyStack {

    private MyLinkedList.Element head;

    void push(Integer element) throws MyLinkedList.LinkedListException {
        if (element == null) throw new MyLinkedList.LinkedListException("New element is null");
        MyLinkedList.Element newElement = new MyLinkedList().new Element();
        newElement.setElement(element);
        newElement.setNext(head);
        head = newElement;
    }

    Integer pop() throws MyLinkedList.LinkedListException {
        if (head == null) throw new MyLinkedList.LinkedListException("Stack is empty");
        MyLinkedList.Element popElement = head;
        head = head.getNext();
        return popElement.getElement();
    }

    void printStack() throws MyLinkedList.LinkedListException {
        if (head == null) throw new MyLinkedList.LinkedListException("Stack is empty");
        MyLinkedList.Element currentElement = head;
        while (currentElement.hasNext())
        {
            System.out.print(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement());
    }

}