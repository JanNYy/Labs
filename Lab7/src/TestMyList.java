package courses.labs;

public class TestMyList {

    public static void main(String[] args) throws LinkedListException {

        System.out.println("Linked List");
        MyLinkedList testLinkedList = new MyLinkedList();
        for (int i = 1; i <= 5; i++) testLinkedList.addElement((Integer)i);

        System.out.print("Elements: ");
        testLinkedList.printList();
        System.out.println("Size: " + testLinkedList.size());

        System.out.print("Add 6 at index 2: ");
        testLinkedList.addElement(2, (Integer) 6);
        testLinkedList.printList();

        System.out.print("Add 7 at index 5: ");
        testLinkedList.addElement(5, (Integer) 7);
        testLinkedList.printList();

        System.out.println("Size: " + testLinkedList.size());

        System.out.print("Element at index 2: ");
        System.out.println(testLinkedList.getElementAtIndex(2));

        System.out.print("Remove element at index 2: ");
        testLinkedList.remove(2);
        testLinkedList.printList();

        System.out.println("Size: " + testLinkedList.size());

        System.out.print("Remove element at index 5: ");
        testLinkedList.remove(5);
        testLinkedList.printList();

        System.out.print("Add 10,11,12: ");
        Object[] testArray = new Object[] {10,11,12};
        testLinkedList.addAll(testArray);
        System.out.print("Elements: ");
        testLinkedList.printList();

        System.out.print("Add 13,14,15 at index 3: ");
        testArray = new Object[] {13,14,15};
        testLinkedList.addAll(3, testArray);
        System.out.print("Elements: ");
        testLinkedList.printList();

        System.out.print("Push 15: ");
        testLinkedList.push((Integer) 15);
        System.out.print("Elements: ");
        testLinkedList.printList();

        System.out.print("Pop: ");
        testLinkedList.pop();
        System.out.print("Elements: ");
        testLinkedList.printList();

        System.out.print("Offer 16: ");
        testLinkedList.offer((Integer) 16);
        System.out.print("Elements: ");
        testLinkedList.printList();

        System.out.print("Poll: ");
        testLinkedList.poll();
        System.out.print("Elements: ");
        testLinkedList.printList();

        testArray = testLinkedList.toArray();

        System.out.print("Sort desc: ");
        MyCollections.sort(testLinkedList, true);
        testLinkedList.printList();

        System.out.print("Sort asc: ");
        MyCollections.sort(testLinkedList, false);
        testLinkedList.printList();

        System.out.println("Copy list 1 to list 2");
        MyLinkedList testLinkedList2 = new MyLinkedList();
        MyCollections.copy(testLinkedList2, testLinkedList);
        System.out.print("Elements of list 2: ");
        testLinkedList2.printList();

        System.out.println("Reverse list 2");
        MyCollections.reverse(testLinkedList2);
        System.out.print("Elements of list 2: ");
        testLinkedList2.printList();

        System.out.println();

        System.out.println("Array List");
        MyArrayList testArrayList = new MyArrayList();
        testArrayList.addAll(testArray);
        System.out.print("Elements: ");
        testArrayList.printList();

        System.out.print("Sort desc: ");
        MyCollections.sort(testArrayList, true, new ListComparator());
        testArrayList.printList();

        System.out.print("Sort asc: ");
        MyCollections.sort(testArrayList, false, new ListComparator());
        testArrayList.printList();
    }

}
