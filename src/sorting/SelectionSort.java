package sorting;

import util.MyArrayList;

/**
 * Implementación del algoritmo Selection Sort (Ordenamiento por selección).
 * 
 * Complejidad: O(n²) en todos los casos.
 * 
 * El algoritmo selecciona repetidamente el elemento mínimo (o máximo) de la
 * parte no ordenada y lo coloca al principio de la parte ordenada.
 */
public class SelectionSort implements SortAlgorithm {
    
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del elemento mínimo en la parte no ordenada
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            
            // Intercambiar el elemento mínimo con el primer elemento no ordenado
            if (minIdx != i) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
            }
        }
    }
    
    @Override
    public void sort(MyArrayList list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del elemento mínimo en la parte no ordenada
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIdx)) {
                    minIdx = j;
                }
            }
            
            // Intercambiar el elemento mínimo con el primer elemento no ordenado
            if (minIdx != i) {
                Integer temp = list.get(minIdx);
                list.set(minIdx, list.get(i));
                list.set(i, temp);
            }
        }
    }
    
    @Override
    public String getName() {
        return "Selection Sort";
    }
}

