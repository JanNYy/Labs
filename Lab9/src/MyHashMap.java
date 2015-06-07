package courses.labs;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K,V> implements MyMap<K,V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private float loadFactor;
    private int size;
    //Number of elements after which capacity of hash table will increase
    private int threshold;
    private SimpleEntry<K,V>[] hashTable;

    private static class SimpleEntry<K,V> implements MyMap.Entry<K,V> {

        final K key;
        V value;
        SimpleEntry<K,V> next;
        int hashCode;

        public SimpleEntry(K key, int hashCode, V value, SimpleEntry<K,V> next) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
            this.next = next;
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
        public boolean equals(Object o) {
            if (!(o instanceof SimpleEntry)) return false;
            SimpleEntry entry = (SimpleEntry)o;
            Object key1 = getKey();
            Object key2 = entry.getKey();
            if (key1 == key2 || (key1 != null && key1.equals(key2))) {
                Object value1 = getValue();
                Object value2 = entry.getValue();
                if (value1 == value2 || (value1 != null && value1.equals(value2)))
                    return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode())^(value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return key + " \"" + value+"\"";
        }
    }

    //constructs an empty HashMap with the default initial capacity (16) and the default load factor (0.75).
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY,DEFAULT_LOAD_FACTOR);
    }

    //constructs an empty HashMap with the specified initial capacity and the default load factor (0.75).
    //Throws: IllegalArgumentException - if the initial capacity is negative.
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    //constructs an empty HashMap with the specified initial capacity and load factor.
    //Throws: IllegalArgumentException - if the initial capacity is negative or the load factor is nonpositive.
    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) throw new IllegalArgumentException("Illegal initial capacity: "+initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) throw new IllegalArgumentException("Illegal load factor: "+loadFactor);
        this.loadFactor = loadFactor;
        hashTable = new SimpleEntry[initialCapacity];
        threshold = Math.round(initialCapacity * loadFactor);;
    }

    //returns index for hash code keyHashCode
    private int indexFor(int keyHashCode, int hashTableLength) {
        return keyHashCode & (hashTableLength - 1);
    }

    private void resize(int newCapacity) {
        threshold = Math.round(newCapacity*loadFactor);
        SimpleEntry<K,V>[] newHashTable = new SimpleEntry[newCapacity];
        SimpleEntry<K,V> next;
        for (SimpleEntry<K,V> entry : hashTable)
        {
            while (entry != null)
            {
                next = entry.next;
                int index = indexFor(entry.hashCode, newCapacity);
                entry.next = newHashTable[index];
                newHashTable[index] = entry;
                entry = next;
            }
        }
        hashTable = newHashTable;
    }

    @Override
    public void clear() {
        Arrays.fill(hashTable, null);
        //System.gc();
        size = 0;
    }

    private SimpleEntry<K,V> getSimpleEntry(K key) {
        int desiredHash = (key == null) ? 0 : key.hashCode();
        SimpleEntry<K,V> entry = hashTable[indexFor(desiredHash, hashTable.length)];
        while (entry != null)
        {
            if ( (entry.hashCode == desiredHash) && ((entry.key == key) || ((key != null) && key.equals(entry.key))) )
                return entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return getSimpleEntry(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null)
            return containsNullValue();
        for (int i = 0; i < hashTable.length; i++)
            for (SimpleEntry<K,V> entry = hashTable[i]; entry != null; entry = entry.next)
                if (value.equals(entry.value))
                    return true;
        return false;
    }

    private boolean containsNullValue() {
        for (int i = 0; i < hashTable.length; i++)
            for (SimpleEntry<K,V> entry = hashTable[i]; entry != null; entry = entry.next)
                if (entry.value == null)
                    return true;
        return false;
    }

    @Override
    public V get(K key) {
        if (isEmpty())
            return null;

        if (key == null)
        {
            SimpleEntry<K,V> entry = hashTable[0];
            while(entry != null)
            {
                if (entry.getKey() == null)
                    return entry.getValue();
                entry = entry.next;
            }
            return null;
        }

        return getSimpleEntry(key) == null ? null : getSimpleEntry(key).value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null)
            return putNullKey(value);

        int hash = key.hashCode();
        int i = indexFor(hash, hashTable.length);
        for (SimpleEntry<K,V> entry = hashTable[i]; entry != null; entry = entry.next)
        {
            if ( (entry.hashCode == hash) && ((entry.key == key) || (key.equals(entry.key))) )
            {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        addSimpleEntry(key, hash, value, i);
        return null;
    }

    private V putNullKey(V value) {
        SimpleEntry<K,V> entry = hashTable[0];
        while(entry != null)
        {
            if (entry.key == null) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }
        addSimpleEntry(null, 0, value, 0);
        return null;
    }

    private void addSimpleEntry(K key, int hash, V value, int index) {
        if ((size >= threshold) && (null != hashTable[index]))
        {
            resize(2 * hashTable.length);
            hash = (null != key) ? key.hashCode() : 0;
            index = indexFor(hash, hashTable.length);
        }
        createSimpleEntry(key, hash, value, index);
    }

    private void createSimpleEntry(K key, int hash, V value, int index) {
        SimpleEntry<K,V> entry = hashTable[index];
        hashTable[index] = new SimpleEntry<K,V>(key, hash, value, entry);
        size +=1;
    }

    @Override
    public V remove(K key) {
        SimpleEntry<K,V> entry = removeSimpleEntry(key);
        return (entry == null ? null : entry.value);
    }

    private SimpleEntry<K,V> removeSimpleEntry(K key) {
        if (isEmpty())
        {
            return null;
        }

        int hash = (key == null) ? 0 : key.hashCode();
        int i = indexFor(hash, hashTable.length);
        SimpleEntry<K,V> previous = hashTable[i];
        SimpleEntry<K,V> entry = previous;

        while (entry != null)
        {
            SimpleEntry<K,V> next = entry.next;
            if ( (entry.hashCode == hash) && ((entry.key == key) || (key.equals(entry.key))) )
            {
                if (previous == entry)
                    hashTable[i] = next;
                else
                    previous.next = next;
                size -= 1;
                return entry;
            }
            previous = entry;
            entry = next;
        }

        return entry;
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
            int index; //current slot

            EntryIterator() {
                SimpleEntry<K,V>[] table = hashTable;
                while ((index < table.length) && (next == null))
                {
                    next = table[index];
                    index +=1;
                }
            }

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public SimpleEntry<K,V> next() {
                SimpleEntry<K,V> entry = next;
                if (entry == null)
                    throw new NoSuchElementException();
                if ((next = entry.next) == null)
                {
                    SimpleEntry<K,V>[] table = hashTable;
                    while ((index < table.length) && (next == null))
                    {
                        next = table[index];
                        index +=1;
                    }
                }
                return current = entry;
            }

            @Override
            public void remove() {
                if (current == null)
                    throw new IllegalStateException();
                K k = current.key;
                current = null;
                MyHashMap.this.remove(k);
            }

        }

        return new EntryIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof MyHashMap))
            return false;

        MyHashMap<?, ?> myHashMap = (MyHashMap<?, ?>) obj;

        if (Float.compare(myHashMap.loadFactor, loadFactor) != 0)
            return false;
        if (size != myHashMap.size)
            return false;

        return Arrays.equals(hashTable, myHashMap.hashTable);
    }

    @Override
    public int hashCode() {
        int result = (loadFactor != +0.0f ? Float.floatToIntBits(loadFactor) : 0);
        result = 31 * result + size;
        result = 31 * result + (hashTable != null ? Arrays.hashCode(hashTable) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyHashMap = {"+Arrays.toString(hashTable)+"}";
    }

}
