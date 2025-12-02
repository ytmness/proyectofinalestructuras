package util;

/**
 * Implementación propia de Stack para reemplazar java.util.Stack.
 * Almacena elementos de tipo Integer.
 */
public class MyStack {
    private Integer[] data;
    private int top;
    private static final int DEFAULT_CAPACITY = 100;
    
    public MyStack() {
        this.data = new Integer[DEFAULT_CAPACITY];
        this.top = -1;
    }
    
    public MyStack(int initialCapacity) {
        this.data = new Integer[initialCapacity];
        this.top = -1;
    }
    
    public void push(Integer value) {
        if (top >= data.length - 1) {
            resize();
        }
        data[++top] = value;
    }
    
    public Integer pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data[top--];
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
    
    private void resize() {
        Integer[] newData = new Integer[data.length * 2];
        for (int i = 0; i <= top; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}

