package util;

/**
 * Lista simple genérica para reemplazar java.util.ArrayList.
 * Almacena objetos de cualquier tipo.
 */
public class MyList {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    public MyList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    public MyList(int initialCapacity) {
        this.data = new Object[initialCapacity];
        this.size = 0;
    }
    
    public void add(Object value) {
        if (size >= data.length) {
            resize();
        }
        data[size++] = value;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) data[index];
    }
    
    public void set(int index, Object value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        data[index] = value;
    }
    
    public int size() {
        return size;
    }
    
    private void resize() {
        Object[] newData = new Object[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    
    public void clear() {
        size = 0;
    }
    
    // Iterador simple para usar en for-each
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }
}

