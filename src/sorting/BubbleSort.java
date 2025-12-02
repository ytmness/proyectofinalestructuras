package sorting;

import util.MyArrayList;

/**
 * Implementación del algoritmo Bubble Sort (Ordenamiento de burbuja).
 * 
 * Complejidad: O(n²) en el caso promedio y peor caso.
 * 
 * El algoritmo recorre repetidamente la lista, comparando elementos adyacentes
 * e intercambiándolos si están en el orden incorrecto. Este proceso se repite
 * hasta que no se requieren más intercambios.
 */
public class BubbleSort implements SortAlgorithm {
    
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambiar elementos adyacentes
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // Optimización: si no hubo intercambios, el array ya está ordenado
            if (!swapped) {
                break;
            }
        }
    }
    
    @Override
    public void sort(MyArrayList list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // Intercambiar elementos adyacentes
                    Integer temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
    
    @Override
    public String getName() {
        return "Bubble Sort";
    }
}

