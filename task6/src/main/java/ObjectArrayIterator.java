import java.util.Iterator;
import java.util.NoSuchElementException;

public class ObjectArrayIterator<T> implements Iterator<T> {
    private final T[] array;
    private int index = 0;

    public ObjectArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the array");
        }
        return array[index++];
    }
}
