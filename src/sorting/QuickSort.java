package sorting;

import util.MyArrayList;
import util.MyRandom;
import util.MyStack;

/**
 * Implementación del algoritmo Quick Sort (Ordenamiento rápido).
 * 
 * Complejidad: O(n log n) en promedio, O(n²) en el peor caso.
 * 
 * Algoritmo divide y vencerás que elige un pivote, particiona la lista en
 * elementos menores y mayores que el pivote, y ordena recursivamente las
 * sublistas. Usa selección aleatoria de pivote para evitar el peor caso.
 * Implementación iterativa para evitar StackOverflowError con arreglos grandes.
 */
public class QuickSort implements SortAlgorithm {
    private MyRandom random = new MyRandom();
    
    @Override
    public void sort(int[] arr) {
        // Versión iterativa usando una pila para evitar StackOverflowError
        MyStack stack = new MyStack();
        stack.push(0);
        stack.push(arr.length - 1);
        
        while (!stack.isEmpty()) {
            int high = stack.pop();
            int low = stack.pop();
            
            if (low < high) {
                // Particionar y obtener el índice del pivote
                int pi = partition(arr, low, high);
                
                // Agregar las sublistas a la pila (primero la más grande para optimizar)
                if (pi - low > high - pi) {
                    stack.push(low);
                    stack.push(pi - 1);
                    stack.push(pi + 1);
                    stack.push(high);
                } else {
                    stack.push(pi + 1);
                    stack.push(high);
                    stack.push(low);
                    stack.push(pi - 1);
                }
            }
        }
    }
    
    private int partition(int[] arr, int low, int high) {
        // Selección aleatoria de pivote para evitar el peor caso
        int randomIndex = low + random.nextInt(high - low + 1);
        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;
        
        int pivot = arr[high];
        int i = low - 1; // Índice del elemento más pequeño
        
        for (int j = low; j < high; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot) {
                i++;
                // Intercambiar arr[i] y arr[j]
                int swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
            }
        }
        
        // Intercambiar arr[i+1] y arr[high] (pivote)
        int swap = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = swap;
        
        return i + 1;
    }
    
    @Override
    public void sort(MyArrayList list) {
        // Versión iterativa usando una pila para evitar StackOverflowError
        MyStack stack = new MyStack();
        stack.push(0);
        stack.push(list.size() - 1);
        
        while (!stack.isEmpty()) {
            int high = stack.pop();
            int low = stack.pop();
            
            if (low < high) {
                int pi = partition(list, low, high);
                
                // Agregar las sublistas a la pila (primero la más grande para optimizar)
                if (pi - low > high - pi) {
                    stack.push(low);
                    stack.push(pi - 1);
                    stack.push(pi + 1);
                    stack.push(high);
                } else {
                    stack.push(pi + 1);
                    stack.push(high);
                    stack.push(low);
                    stack.push(pi - 1);
                }
            }
        }
    }
    
    private int partition(MyArrayList list, int low, int high) {
        // Selección aleatoria de pivote
        int randomIndex = low + random.nextInt(high - low + 1);
        Integer temp = list.get(randomIndex);
        list.set(randomIndex, list.get(high));
        list.set(high, temp);
        
        Integer pivot = list.get(high);
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                Integer swap = list.get(i);
                list.set(i, list.get(j));
                list.set(j, swap);
            }
        }
        
        Integer swap = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, swap);
        
        return i + 1;
    }
    
    @Override
    public String getName() {
        return "Quick Sort";
    }
}

