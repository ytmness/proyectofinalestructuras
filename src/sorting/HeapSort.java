package sorting;

import util.MyArrayList;

/**
 * Implementación del algoritmo Heap Sort (Ordenamiento por montículos).
 * 
 * Complejidad: O(n log n) en todos los casos.
 * Espacio adicional: O(1)
 * 
 * El algoritmo convierte la lista en un heap (montículo máximo) y luego
 * extrae repetidamente el elemento máximo, colocándolo al final de la
 * lista ordenada.
 */
public class HeapSort implements SortAlgorithm {
    
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        
        // Construir el heap (reorganizar el array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Mover la raíz actual al final
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            // Llamar heapify en el heap reducido
            heapify(arr, i, 0);
        }
    }
    
    // Para heapificar un subárbol enraizado en el nodo i
    private void heapify(int[] arr, int n, int i) {
        int largest = i; // Inicializar el más grande como raíz
        int left = 2 * i + 1; // hijo izquierdo
        int right = 2 * i + 2; // hijo derecho
        
        // Si el hijo izquierdo es más grande que la raíz
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // Si el hijo derecho es más grande que el más grande hasta ahora
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // Si el más grande no es la raíz
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            // Heapificar recursivamente el subárbol afectado
            heapify(arr, n, largest);
        }
    }
    
    @Override
    public void sort(MyArrayList list) {
        int n = list.size();
        
        // Construir el heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }
        
        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            Integer temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);
            
            heapify(list, i, 0);
        }
    }
    
    private void heapify(MyArrayList list, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && list.get(left) > list.get(largest)) {
            largest = left;
        }
        
        if (right < n && list.get(right) > list.get(largest)) {
            largest = right;
        }
        
        if (largest != i) {
            Integer swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);
            
            heapify(list, n, largest);
        }
    }
    
    @Override
    public String getName() {
        return "Heap Sort";
    }
}

