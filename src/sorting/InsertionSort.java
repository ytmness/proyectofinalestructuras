package sorting;

import util.MyArrayList;

/**
 * Implementación del algoritmo Insertion Sort (Ordenamiento por inserción).
 * 
 * Complejidad: O(n²) en el caso promedio y peor caso.
 * 
 * El algoritmo construye la lista ordenada insertando cada elemento en su
 * posición correcta dentro de la parte ya ordenada. Es eficiente para listas
 * pequeñas o casi ordenadas.
 */
public class InsertionSort implements SortAlgorithm {
    
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // Desplazar elementos mayores que key una posición hacia adelante
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    @Override
    public void sort(MyArrayList list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Integer key = list.get(i);
            int j = i - 1;
            
            // Desplazar elementos mayores que key una posición hacia adelante
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
    
    @Override
    public String getName() {
        return "Insertion Sort";
    }
}

