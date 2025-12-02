package util;

/**
 * Implementación propia de ArrayList para reemplazar java.util.ArrayList.
 * Almacena elementos de tipo Integer.
 */
public class MyArrayList {
    private Integer[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    
    public MyArrayList() {
        this.data = new Integer[DEFAULT_CAPACITY];
        this.size = 0;
    }
    
    public MyArrayList(int initialCapacity) {
        this.data = new Integer[initialCapacity];
        this.size = 0;
    }
    
    public void add(Integer value) {
        if (size >= data.length) {
            resize();
        }
        data[size++] = value;
    }
    
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }
    
    public void set(int index, Integer value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        data[index] = value;
    }
    
    public int size() {
        return size;
    }
    
    private void resize() {
        Integer[] newData = new Integer[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    
    public void clear() {
        size = 0;
    }
    
    public Integer[] toArray() {
        Integer[] result = new Integer[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }
}

