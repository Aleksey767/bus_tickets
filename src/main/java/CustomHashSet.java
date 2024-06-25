import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomHashSet<E> implements Iterable<E> {

    private Entry[] buckets;
    private Entry<E> nullEntry;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    public CustomHashSet() {
        buckets = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public boolean add(E element) {
        if (element == null) {
            return putForNullKey();
        }

        if (size >= buckets.length * LOAD_FACTOR) {
            resize(2 * buckets.length);
        }

        int hash = generateHash(element.hashCode());
        int index = indexFor(hash, buckets.length);
        Entry<E> current = buckets[index];

        while (current != null) {
            if (hash == generateHash(current.key.hashCode())) {
                if (current.key.equals(element)) {
                    return false;
                }
                current = current.next;
            }
        }
        Entry<E> entry = new Entry<>(element, buckets[index]);
        buckets[index] = entry;
        size++;
        return true;
    }

    public boolean remove(E element) {

        if (element == null) {
            nullEntry = null;
            return true;
        }
        int hash = generateHash(element.hashCode());
        int index = indexFor(hash, buckets.length);
        Entry previous = null;
        Entry current = buckets[index];
        while (current != null) {
            if (generateHash(current.key.hashCode()) == hash) {
                if (element.equals(current.key)) {
                    if (previous == null) {
                        buckets[index] = current.next;
                        size--;
                        return true;
                    } else {
                        previous.next = current.next;
                        size--;
                        return true;
                    }
                }
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean contains(E element) {
        if (element == null) {
            return nullEntry != null;
        }
        int hash = generateHash(element.hashCode());
        int index = indexFor(hash, buckets.length);
        Entry<E> current = buckets[index];
        while (current != null) {
            if (hash == generateHash(current.key.hashCode())) {
                if (current.key.equals(element)) {
                    return true;
                }
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }

    private int generateHash(int hashCode) {
        hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12);
        return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private boolean putForNullKey() {
        if (nullEntry == null) {
            nullEntry = new Entry<>(null, null);
            size++;
            return true;
        }
        return false;
    }

    private void resize(int newCapacity) {
        Entry<E>[] newBuckets = new Entry[newCapacity];
        for (Entry<E> bucket : buckets) {
            Entry<E> current = bucket;
            while (current != null) {
                Entry<E> next = current.next;
                int hash = generateHash(current.key.hashCode());
                int index = indexFor(hash, newCapacity);
                current.next = newBuckets[index];
                newBuckets[index] = current;
                current = next;
            }
        }
        buckets = newBuckets;
    }

    private class CustomIterator implements Iterator<E> {

        private int currentBucket;
        private Entry<E> currentEntry;
        private boolean nullEntryReturned;

        CustomIterator() {
            currentBucket = 0;
            currentEntry = null;
            nullEntryReturned = false;
        }

        @Override
        public boolean hasNext() {
            if (!nullEntryReturned && nullEntry != null) {
                return true;
            }
            if (currentEntry != null && currentEntry.next != null) {
                return true;
            }
            for (int i = currentBucket; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public E next() {
            if (!nullEntryReturned && nullEntry != null) {
                nullEntryReturned = true;
                return nullEntry.key;
            }
            if (currentEntry != null && currentEntry.next != null) {
                currentEntry = currentEntry.next;
                return currentEntry.key;
            }
            while (currentBucket < buckets.length) {
                if (buckets[currentBucket] != null) {
                    currentEntry = buckets[currentBucket];
                    currentBucket++;
                    return currentEntry.key;
                }
                currentBucket++;
            }
            throw new NoSuchElementException();
        }
    }
    private static class Entry<E> {

        E key;
        Entry<E> next;

        Entry(E key, Entry<E> next) {
            this.key = key;
            this.next = next;
        }
    }
}