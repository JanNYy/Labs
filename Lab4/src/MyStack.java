package courses.labs;

class MyStack {

    private MyLinkedList.Element head;
	
	private void elementCheck(Object element) throws LinkedListException {
        if (element == null) throw new MyLinkedList.LinkedListException("Element is null");
    }
	
	private void stackCheck() throws LinkedListException {
        if (head == null) throw new MyLinkedList.LinkedListException("Stack is empty");
    }

    public void push(Integer element) throws MyLinkedList.LinkedListException {
        elementCheck(element);
        MyLinkedList.Element newElement = new MyLinkedList().new Element();
        newElement.setElement(element);
        newElement.setNext(head);
        head = newElement;
    }

    public Integer pop() throws MyLinkedList.LinkedListException {
        stackCheck();
        MyLinkedList.Element popElement = head;
        head = head.getNext();
        return popElement.getElement();
    }

    public void printStack() throws MyLinkedList.LinkedListException {
        stackCheck();
        MyLinkedList.Element currentElement = head;
        while (currentElement.hasNext())
        {
            System.out.print(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement());
    }

}