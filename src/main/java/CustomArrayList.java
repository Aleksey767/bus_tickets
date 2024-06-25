@SuppressWarnings("unchecked")
public class CustomArrayList<E> {

    private int size;
    private int capacity;
    private E[] elements;

    public CustomArrayList() {
        capacity = 10;
        size = 0;
        elements = (E[]) new Object[capacity];
    }

    public void add(E element) {
        if (size == capacity) {
            grow();
        }
        elements[size] = element;
        size++;
    }

    public E get(int index) {
        if (index >= 0 && index < size)
            return elements[index];
        else
            return null;
    }

    public E remove(int index) {

        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                elements[i] = i + 1 == size ? null : elements[i + 1];
            }
            size--;
            E result = (E) Integer.valueOf(index);
            return result;
        } else return null;
    }

    public boolean remove(E value) {
        if (value != null) {
            int index = 0;
            for (E element : elements) {
                if (element != null && element.equals(value)) {
                    remove(index);
                    return true;
                }
                index++;
            }
        }
        return false;
    }

    private void grow() {
        this.capacity *= 1.5;
        E[] temp = (E[]) new Object[capacity];
        System.arraycopy(elements, 0, temp, 0, elements.length);
        this.elements = temp;
    }
}