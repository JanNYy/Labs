package courses.labs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

//Visualization
//http://www.cs.usfca.edu/~galles/visualization/RedBlack.html

public class MyTreeMap<K extends Comparable,V> implements MyMap<K,V> {

    private SimpleEntry<K,V> root;
    private int size;
    private final Comparator<K> nodeComparator;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class SimpleEntry<K,V> implements MyMap.Entry<K,V> {

        K key;
        V value;
        SimpleEntry<K,V> parent;
        SimpleEntry<K,V> left;
        SimpleEntry<K,V> right;
        boolean color = BLACK;

        SimpleEntry(K key, V value, SimpleEntry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof SimpleEntry)) return false;

            SimpleEntry<K,V> other = (SimpleEntry<K,V>) obj;

            if (key != null ? !key.equals(other.key) : other.key != null) return false;
            return !(value != null ? !value.equals(other.value) : other.value != null);
        }

        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return key + " \"" + value+"\"";
        }
    }

    //constructs a new, empty tree map, using the natural ordering of its keys.
    public MyTreeMap() {
        nodeComparator = null;
    }

    //constructs a new, empty tree map, ordered according to the given comparator.
    public MyTreeMap(Comparator<K> comparator) {
        nodeComparator = comparator;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private void checkKey(K key) {
        if (key == null) throw new IllegalArgumentException("Key is null");
    }

    private SimpleEntry<K,V> getLowestSimpleEntry() {
        SimpleEntry<K,V> entry = root;
        if (!(isEmpty()))
            while (entry.left != null)
                entry = entry.left;
        return entry;
    }

    private SimpleEntry<K,V> getHighestSimpleEntry() {
        SimpleEntry<K,V> entry = root;
        if (!(isEmpty()))
            while (entry.right != null)
                entry = entry.right;
        return entry;
    }

    private SimpleEntry<K,V> getSuccessor(SimpleEntry<K,V> entry) {
        if (entry == null)
            return null;
        else
        if (entry.right != null)
        {
            entry = entry.right;
            while (entry.left != null)
                entry = entry.left;
            return entry;
        }
        else
        {
            SimpleEntry<K,V> parentEntry = entry.parent;
            while ( (parentEntry != null) && (entry == parentEntry.right) )
            {
                entry = parentEntry;
                parentEntry = entry.parent;
            }
            return parentEntry;
        }
    }

    private SimpleEntry<K,V> getPredecessor(SimpleEntry<K,V> entry) {
        if (entry == null)
            return null;
        else
        if (entry.left != null)
        {
            entry = entry.left;
            while (entry.right != null)
                entry = entry.right;
            return entry;
        }
        else
        {
            SimpleEntry<K,V> parentEntry = entry.parent;
            while ( (parentEntry != null) && (entry == parentEntry.left) )
            {
                entry = parentEntry;
                parentEntry = entry.parent;
            }
            return parentEntry;
        }
    }

    private boolean getColor(SimpleEntry<K,V> entry) {
        return (entry == null ? BLACK : entry.color);
    }

    private void setColor(SimpleEntry<K,V> entry, boolean color) {
        if (entry != null)
            entry.color = color;
    }

    private void rotateLeft(SimpleEntry<K,V> entry) {
        if (entry == null) return;

        SimpleEntry<K,V> rightEntry = entry.right;
        //Create node - right link
        entry.right = rightEntry.left;
        if (rightEntry.left != null)
            rightEntry.left.parent = entry;
        //Create right - parent link
        rightEntry.parent = entry.parent;
        if (entry.parent != null)
        {
            if (entry == entry.parent.left)
                entry.parent.left = rightEntry;
            else
                entry.parent.right = rightEntry;
        }
        else
        {
            root = rightEntry;
        }

        rightEntry.left = entry;
        entry.parent = rightEntry;
    }

    private void rotateRight(SimpleEntry<K,V> entry) {
        if (entry == null) return;

        SimpleEntry<K,V> leftEntry = entry.left;
        //Create node - left link
        entry.left = leftEntry.right;
        if (leftEntry.right != null)
            leftEntry.right.parent = entry;
        //Create left - parent link
        leftEntry.parent = entry.parent;
        if (entry.parent != null)
        {
            if (entry == entry.parent.right)
                entry.parent.right = leftEntry;
            else
                entry.parent.left = leftEntry;
        }
        else
        {
            root = leftEntry;
        }

        leftEntry.right = entry;
        entry.parent = leftEntry;
    }

    private SimpleEntry<K,V> getSimpleEntry(K key) {
        if (nodeComparator != null)
            return getSimpleEntryWithComparator(key);

        SimpleEntry<K,V> entry = root;
        while (entry != null)
        {
            int compareResult = key.compareTo(entry.key);
            if (compareResult < 0)
                entry = entry.left;
            else
            if (compareResult > 0)
                entry = entry.right;
            else
                return entry;
        }
        return null;
    }

    private SimpleEntry<K,V> getSimpleEntryWithComparator(K key) {
        if (nodeComparator != null)
        {
            SimpleEntry<K,V> entry = root;
            while (entry != null)
            {
                int compareResult = nodeComparator.compare(key, entry.key);
                if (compareResult < 0)
                    entry = entry.left;
                else
                if (compareResult > 0)
                    entry = entry.right;
                else
                    return entry;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        checkKey(key);
        return getSimpleEntry(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        SimpleEntry<K,V> entry = getLowestSimpleEntry();
        while (entry != null)
        {
            if (value.equals(entry.value))
                return true;
            entry = getSuccessor(entry);
        }
        return false;
    }

    @Override
    public V get(K key) {
        checkKey(key);
        SimpleEntry<K,V> entry = getSimpleEntry(key);
        return (entry == null ? null : entry.value);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        SimpleEntry<K,V> entry = root;
        if (entry == null) {
            root = new SimpleEntry<K,V>(key, value, null);
            size = 1;
            return root.value;
        }
        int compareEntryResult = 0;
        SimpleEntry<K,V> tempEntry = null;
        if (nodeComparator != null)
        {
            while (entry != null)
            {
                tempEntry = entry;
                compareEntryResult = nodeComparator.compare(key, entry.key);
                if (compareEntryResult < 0)
                    entry = entry.left;
                else
                if (compareEntryResult > 0)
                    entry = entry.right;
                else
                    return entry.setValue(value);
            }
        }
        else
        {
            while (entry != null)
            {
                tempEntry = entry;
                compareEntryResult = key.compareTo(entry.key);
                if (compareEntryResult < 0)
                    entry = entry.left;
                else if (compareEntryResult > 0)
                    entry = entry.right;
                else
                    return entry.setValue(value);
            }
        }
        entry = new SimpleEntry<K,V>(key, value, tempEntry);
        if (compareEntryResult < 0)
            tempEntry.left = entry;
        else
            tempEntry.right = entry;
        balanceAfterPut(entry);
        size += 1;
        return null;
    }

    private void balanceAfterPut(SimpleEntry<K,V> entry) {
        //New entry is RED at first
        entry.color = RED;

        SimpleEntry<K, V> uncleEntry;

        while ((entry != null) && (entry != root) && (getColor(entry.parent) == RED)) {
            if (entry.parent == entry.parent.parent.left)
            {
                uncleEntry = entry.parent.parent.right;
                //if uncle entry is RED
                if (getColor(uncleEntry) == RED) {
                    setColor(entry.parent, BLACK);
                    setColor(uncleEntry, BLACK);
                    setColor(entry.parent.parent, RED);
                    entry = entry.parent.parent;
                }
                //if uncle entry is BLACK
                else {
                    if (entry == entry.parent.right)
                    {
                        entry = entry.parent;
                        rotateLeft(entry);
                    }
                    setColor(entry.parent, BLACK);
                    setColor(entry.parent.parent, RED);
                    rotateRight(entry.parent.parent);
                }
            }
            //if entry in right subtree of grandfather
            else {
                uncleEntry = entry.parent.parent.left;
                //if uncle entry is RED
                if (getColor(uncleEntry) == RED)
                {
                    setColor(entry.parent, BLACK);
                    setColor(uncleEntry, BLACK);
                    setColor(entry.parent.parent, RED);
                    entry = entry.parent.parent;
                }
                //if uncle entry is BLACK
                else {
                    if (entry == entry.parent.left)
                    {
                        entry = entry.parent;
                        rotateRight(entry);
                    }
                    setColor(entry.parent, BLACK);
                    setColor(entry.parent.parent, RED);
                    rotateLeft(entry.parent.parent);
                }
            }
        }

        //Root is BLACK
        root.color = BLACK;
    }

    @Override
    public V remove(K key) {
        checkKey(key);
        SimpleEntry<K,V> entry = getSimpleEntry(key);
        if (entry == null)
            return null;
        V oldValue = entry.value;
        removeSimpleEntry(entry);
        return oldValue;
    }

    private void removeSimpleEntry(SimpleEntry<K,V> entry) {

        if ( (entry.left != null) && (entry.right != null) )
        {
            SimpleEntry<K,V> successorEntry = getSuccessor(entry);
            entry.key = successorEntry.key;
            entry.value = successorEntry.value;
            entry = successorEntry;
        }

        SimpleEntry<K,V> entryForReplacement;
        if (entry.left != null)
            entryForReplacement = entry.left;
        else
            entryForReplacement = entry.right;

        if (entryForReplacement != null)
        {
            entryForReplacement.parent = entry.parent;
            if (entry.parent == null)
                root = entryForReplacement;
            else
            if (entry == entry.parent.left)
                entry.parent.left  = entryForReplacement;
            else
                entry.parent.right = entryForReplacement;

            entry.left = null;
            entry.right = null;
            entry.parent = null;

            if (entry.color == BLACK)
                balanceAfterRemove(entryForReplacement);
        }
        else
        if (entry.parent != null)
        {
            if (entry.color == BLACK)
                balanceAfterRemove(entry);
            if (entry.parent != null)
            {
                if (entry == entry.parent.left)
                    entry.parent.left = null;
                else
                if (entry == entry.parent.right)
                    entry.parent.right = null;
                entry.parent = null;
            }
        }
        else
        {
            root = null;
        }

        size -= 1;
    }

    private void balanceAfterRemove(SimpleEntry<K,V> entry) {
        SimpleEntry<K,V> uncleEntry;

        while ( (entry != root) && (getColor(entry) == BLACK) )
        {
            if (entry == entry.parent.left)
            {
                uncleEntry = entry.parent.right;

                if (getColor(uncleEntry) == RED)
                {
                    setColor(uncleEntry,BLACK);
                    setColor(entry.parent,RED);
                    rotateLeft(entry.parent);
                    uncleEntry = entry.parent.right;
                }

                if ( (getColor(uncleEntry.left) == BLACK) && (getColor(uncleEntry.right) ) == BLACK)
                {
                    setColor(uncleEntry,RED);
                    entry = entry.parent;
                }
                else
                {
                    if (getColor(uncleEntry.right) == BLACK)
                    {
                        setColor(uncleEntry.left,BLACK);
                        setColor(uncleEntry,RED);
                        rotateRight(uncleEntry);
                        uncleEntry = entry.parent.right;
                    }
                    setColor(uncleEntry,getColor(entry.parent));
                    setColor(entry.parent,BLACK);
                    setColor(uncleEntry.right,BLACK);
                    rotateLeft(entry.parent);
                    entry = root;
                }
            }
            else
            {
                uncleEntry = entry.parent.left;

                if (getColor(uncleEntry) == RED)
                {
                    setColor(uncleEntry,BLACK);
                    setColor(entry.parent,RED);
                    rotateRight(entry.parent);
                    uncleEntry = entry.parent.left;
                }

                if ( (getColor(uncleEntry.right) == BLACK) && (getColor(uncleEntry.left) == BLACK) )
                {
                    setColor(uncleEntry,RED);
                    entry = entry.parent;
                }
                else
                {
                    if (getColor(uncleEntry.left) == BLACK)
                    {
                        setColor(uncleEntry.right,BLACK);
                        setColor(uncleEntry,RED);
                        rotateLeft(uncleEntry);
                        uncleEntry = entry.parent.left;
                    }
                    setColor(uncleEntry,getColor(entry.parent));
                    setColor(entry.parent,BLACK);
                    setColor(uncleEntry.left,BLACK);
                    rotateRight(entry.parent);
                    entry = root;
                }
            }
        }
        setColor(entry, BLACK);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator entryIterator() {

        class EntryIterator implements Iterator {

            SimpleEntry<K,V> next;
            SimpleEntry<K,V> current;

            EntryIterator() {
                next = getLowestSimpleEntry();
            }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Object next() {
                SimpleEntry<K,V> entry = next;
                if (entry == null)
                    throw new NoSuchElementException();
                next = getSuccessor(entry);
                return current = entry;
            }

            @Override
            public void remove() {
                if (current == null)
                    throw new IllegalStateException();
                K k = current.key;
                current = null;
                MyTreeMap.this.remove(k);
            }
        }

        return new EntryIterator();
    }

    private String formTreeInString (SimpleEntry<K,V> entry, StringBuilder treeToStr) {
        if (entry == null)
            return "";
        formTreeInString(entry.left, treeToStr);
        treeToStr = treeToStr.append((root == entry) ? "{Root} = " : "").append(entry).append(" (color ").append(entry.color ? "BLACK" : "RED").append("), ");
        formTreeInString(entry.right, treeToStr);
        return treeToStr.toString();
    }

    @Override
    public String toString() {
        StringBuilder treeToString = new StringBuilder("");
        return formTreeInString(root,treeToString);
    }
}
