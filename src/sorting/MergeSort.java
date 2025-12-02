package sorting;

import util.MyArrayList;

/**
 * Implementación del algoritmo Merge Sort (Ordenamiento por mezcla).
 * 
 * Complejidad: O(n log n) en el peor caso y caso promedio.
 * Espacio adicional: O(n)
 * 
 * Algoritmo divide y vencerás que divide la lista en mitades, las ordena
 * recursivamente y luego las mezcla de forma ordenada.
 */
public class MergeSort implements SortAlgorithm {
    
    @Override
    public void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }
    
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Ordenar las dos mitades
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // Mezclar las mitades ordenadas
            merge(arr, left, mid, right);
        }
    }
    
    private void merge(int[] arr, int left, int mid, int right) {
        // Crear arreglos temporales para las dos mitades
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Copiar datos a los arreglos temporales
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        // Mezclar los arreglos temporales de vuelta al arreglo original
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copiar los elementos restantes
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    @Override
    public void sort(MyArrayList list) {
        mergeSort(list, 0, list.size() - 1);
    }
    
    private void mergeSort(MyArrayList list, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            
            merge(list, left, mid, right);
        }
    }
    
    private void merge(MyArrayList list, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        MyArrayList leftList = new MyArrayList(n1);
        MyArrayList rightList = new MyArrayList(n2);
        
        for (int i = 0; i < n1; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightList.add(list.get(mid + 1 + j));
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftList.get(i) <= rightList.get(j)) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
    
    @Override
    public String getName() {
        return "Merge Sort";
    }
}

