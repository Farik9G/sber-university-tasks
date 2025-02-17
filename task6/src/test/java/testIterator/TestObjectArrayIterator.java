package testIterator;

import iterator.ObjectArrayIterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestObjectArrayIterator {
    @Test
    void testIteratorHasNext() {
        String[] data = {"tip", "clip", "cat"};
        Iterator<String> iterator = new ObjectArrayIterator<>(data);

        assertTrue(iterator.hasNext());
        assertEquals("tip", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("clip", iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals("cat", iterator.next());

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorThrowsExceptionWhenNoMoreElements() {
        String[] data = {"stick"};
        Iterator<String> iterator = new ObjectArrayIterator<>(data);

        assertEquals("stick", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
