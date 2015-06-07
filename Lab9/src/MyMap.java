package courses.labs;

import java.util.Iterator;

public interface MyMap<K,V> {

    interface Entry<K,V> {

        //ñompares the specified object with this entry for equality.
        boolean equals(Object o);

        //returns the hash code value for this map entry.
        int hashCode();

        //returns the key corresponding to this entry.
        K getKey();

        //returns the value corresponding to this entry.
        V getValue();

        //replaces the value corresponding to this entry with the specified value.
        V setValue(V value);

    }

    //removes all of the mappings from this map.
    void clear();

    //returns true if this map contains a mapping for the specified key.
    boolean containsKey(K key);

    //returns true if this map maps one or more keys to the specified value.
    boolean containsValue(V value);

    //returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    V get(K key);

    //returns true if this map contains no key-value mappings.
    boolean isEmpty();

    //associates the specified value with the specified key in this map.
    V put(K key, V value);

    //removes the mapping for the specified key from this map if present.
    V remove(K key);

    //returns the number of key-value mappings in this map.
    int size();

    //returns an iterator over the elements (MyMap.Entry) in proper sequence
    Iterator entryIterator();

}
