package courses.labs;

class MyQueue {

    private MyLinkedList.Element firstPos;
    private MyLinkedList.Element lastPos;
	
	private void elementCheck(Object element) throws LinkedListException {
        if (element == null) throw new MyLinkedList.LinkedListException("Element is null");
    }
	
	private void queueCheck() throws LinkedListException {
        if (firstPos == null) throw new MyLinkedList.LinkedListException("Queue is empty");
    }

    public void offer(Integer element) throws MyLinkedList.LinkedListException {
        elementCheck(element);
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

    public Integer peek() throws MyLinkedList.LinkedListException {
        queueCheck();
        return firstPos.getElement();
    }

    Integer poll() throws MyLinkedList.LinkedListException {
        queueCheck();
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

    public void printQueue() throws MyLinkedList.LinkedListException {
        queueCheck();
        MyLinkedList.Element currentElement = firstPos;
        while (currentElement.hasNext())
        {
            System.out.print(currentElement.getElement()+" ");
            currentElement = currentElement.getNext();
        }
        System.out.println(currentElement.getElement());
    }

}