package courses.labs;

import java.util.ArrayList;
import java.util.Collection;

public class Generics {

    private static void checkArg(Object arg) {
        if (arg == null) throw new IllegalArgumentException("Argument is null");
    }

    public static <T> void fromArrayToCollection(T[] sourceArray, Collection<T> destCollection) {
        checkArg(sourceArray);
        checkArg(destCollection);
        for (T element : sourceArray) {
            destCollection.add(element);
        }
    }

    public static <T extends V, V> void copyAll(Collection<T> sourceCollection, Collection<V> destCollection) {
        checkArg(sourceCollection);
        checkArg(destCollection);
        destCollection.addAll(sourceCollection);
        /*for (T element : sourceCollection) { //Generics.copyAll(rapp, rapp) -> java.util.ConcurrentModificationException
            destCollection.add(element);
        }*/
    }

    public static <T extends Comparable<T>> Collection<T> greaterThanSpecified(Collection<T> sourceCollection, T greaterThan) {
        checkArg(sourceCollection);
        checkArg(greaterThan);
        Collection<T> resultCollection = new ArrayList<>();
        for (T element : sourceCollection) {
            if(element.compareTo(greaterThan)>0){
                resultCollection.add(element);
            }
        }
        return resultCollection;
    }

}