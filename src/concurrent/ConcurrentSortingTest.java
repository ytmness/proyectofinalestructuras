package concurrent;

import java.util.Scanner;
import sorting.*;
import util.MyList;

/**
 * Clase principal que coordina la ejecución concurrente de los seis algoritmos
 * de ordenamiento y presenta los resultados.
 * 
 * El programa ejecuta todos los algoritmos simultáneamente durante un tiempo
 * límite especificado y luego muestra un ranking de eficiencia basado en las
 * métricas de rendimiento.
 */
public class ConcurrentSortingTest {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int timeLimitSeconds = 10;
        
        // Si se pasa como argumento, usarlo; si no, preguntar al usuario
        if (args.length > 0) {
            try {
                timeLimitSeconds = Integer.parseInt(args[0]);
                if (timeLimitSeconds <= 0) {
                    System.out.println("El tiempo límite debe ser positivo. Solicitando entrada al usuario...\n");
                    timeLimitSeconds = askUserForTimeLimit(scanner);
                }
            } catch (NumberFormatException e) {
                System.out.println("Argumento inválido. Solicitando entrada al usuario...\n");
                timeLimitSeconds = askUserForTimeLimit(scanner);
            }
        } else {
            timeLimitSeconds = askUserForTimeLimit(scanner);
        }
        
        scanner.close();
        
        System.out.println("\n================================================");
        System.out.println("  ORDENAMIENTO CONCURRENTE - PRUEBA DE RENDIMIENTO");
        System.out.println("================================================");
        System.out.println("Tiempo limite configurado: " + timeLimitSeconds + " segundos");
        System.out.println("El programa trabajara durante " + timeLimitSeconds + " segundos\n");
        
        // Generar datos de prueba
        DataGenerator generator = new DataGenerator();
        MyList collections = generator.generateAllCollections();
        
        // Crear instancias de los algoritmos
        SortAlgorithm[] algorithms = {
            new BubbleSort(),
            new InsertionSort(),
            new SelectionSort(),
            new MergeSort(),
            new QuickSort(),
            new HeapSort()
        };
        
        // Calcular tiempo de finalización
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (timeLimitSeconds * 1000L);
        
        // Crear tareas para cada algoritmo
        MyList tasks = new MyList();
        Thread[] threads = new Thread[algorithms.length];
        
        for (int i = 0; i < algorithms.length; i++) {
            SortAlgorithm algorithm = algorithms[i];
            SortingTask task = new SortingTask(algorithm, collections, endTime);
            tasks.add(task);
            threads[i] = new Thread(task, algorithm.getName());
        }
        
        System.out.println("Generando datos de prueba y iniciando ejecucion concurrente...\n");
        
        // Iniciar todos los hilos casi simultáneamente
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Esperar a que todos los hilos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + thread.getName());
                e.printStackTrace();
            }
        }
        
        long actualDuration = System.currentTimeMillis() - startTime;
        System.out.println("\n================================================");
        System.out.println("Todas las tareas han finalizado.");
        System.out.println("Tiempo limite configurado: " + timeLimitSeconds + " segundos");
        System.out.println("Tiempo total transcurrido: " + (actualDuration / 1000.0) + " segundos");
        System.out.println("================================================\n");
        
        // Recopilar resultados
        MyList results = new MyList();
        for (int i = 0; i < tasks.size(); i++) {
            SortingTask task = tasks.get(i);
            results.add(task.getResult());
        }
        
        // Mostrar resultados individuales
        printResults(results);
        
        // Mostrar comparación entre arrays y ArrayLists
        printStructureComparison(results);
        
        // Generar y mostrar ranking
        printRanking(results);
    }
    
    /**
     * Imprime los resultados individuales de cada algoritmo de forma formateada.
     */
    private static void printResults(MyList results) {
        System.out.println("================================================");
        System.out.println("              RESULTADOS INDIVIDUALES");
        System.out.println("================================================");
        System.out.println();
        
        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            String status = result.isCompletedAll() ? "**COMPLETO**" : "**INCOMPLETO**";
            int iteracionesCompletas = result.getCollectionsSorted() / result.getTotalCollections();
            int coleccionesExtra = result.getCollectionsSorted() % result.getTotalCollections();
            
            String coleccionesInfo;
            if (iteracionesCompletas > 0) {
                coleccionesInfo = String.format("%d total (%d iteracion%s completa%s", 
                    result.getCollectionsSorted(),
                    iteracionesCompletas,
                    iteracionesCompletas > 1 ? "es" : "",
                    iteracionesCompletas > 1 ? "s" : "");
                if (coleccionesExtra > 0) {
                    coleccionesInfo += " + " + coleccionesExtra + " extra";
                }
                coleccionesInfo += ")";
            } else {
                coleccionesInfo = result.getCollectionsSorted() + " de " + result.getTotalCollections();
            }
            
            System.out.printf("%-18s | Colecciones: %-25s | Tiempo promedio: %8.2f ms | %s%n",
                result.getAlgorithmName(),
                coleccionesInfo,
                result.getAvgTime(),
                status);
        }
        
        System.out.println();
    }
    
    /**
     * Muestra la comparación de rendimiento entre arrays y ArrayLists.
     */
    private static void printStructureComparison(MyList results) {
        System.out.println("================================================");
        System.out.println("     COMPARACIÓN: ARRAYS vs ARRAYLISTS");
        System.out.println("================================================");
        System.out.println();
        
        for (int i = 0; i < results.size(); i++) {
            Result result = results.get(i);
            
            if (result.getArraysSorted() > 0 && result.getArrayListsSorted() > 0) {
                System.out.println(result.getAlgorithmName() + ":");
                System.out.printf("  Arrays ordenados:     %3d | Tiempo promedio: %8.2f ms%n",
                    result.getArraysSorted(), result.getAvgArrayTime());
                System.out.printf("  ArrayLists ordenados: %3d | Tiempo promedio: %8.2f ms%n",
                    result.getArrayListsSorted(), result.getAvgArrayListTime());
                
                // Determinar cuál es más rápido
                if (result.getAvgArrayTime() < result.getAvgArrayListTime()) {
                    double diferencia = result.getAvgArrayListTime() - result.getAvgArrayTime();
                    double porcentaje = (diferencia / result.getAvgArrayListTime()) * 100;
                    System.out.printf("  -> Arrays son %.1f%% mas rapidos (%.2f ms de diferencia)%n",
                        porcentaje, diferencia);
                } else if (result.getAvgArrayListTime() < result.getAvgArrayTime()) {
                    double diferencia = result.getAvgArrayTime() - result.getAvgArrayListTime();
                    double porcentaje = (diferencia / result.getAvgArrayTime()) * 100;
                    System.out.printf("  -> ArrayLists son %.1f%% mas rapidos (%.2f ms de diferencia)%n",
                        porcentaje, diferencia);
                } else {
                    System.out.println("  -> Rendimiento similar entre ambas estructuras");
                }
                System.out.println();
            } else {
                System.out.println(result.getAlgorithmName() + ":");
                if (result.getArraysSorted() > 0) {
                    System.out.printf("  Solo Arrays ordenados: %3d | Tiempo promedio: %8.2f ms%n",
                        result.getArraysSorted(), result.getAvgArrayTime());
                }
                if (result.getArrayListsSorted() > 0) {
                    System.out.printf("  Solo ArrayLists ordenados: %3d | Tiempo promedio: %8.2f ms%n",
                        result.getArrayListsSorted(), result.getAvgArrayListTime());
                }
                System.out.println();
            }
        }
    }
    
    /**
     * Genera y muestra el ranking de eficiencia de los algoritmos.
     */
    private static void printRanking(MyList results) {
        System.out.println("================================================");
        System.out.println("              RANKING DE EFICIENCIA");
        System.out.println("================================================");
        System.out.println();
        
        // Ordenar resultados: primero por cantidad de colecciones (descendente),
        // luego por tiempo promedio (ascendente) en caso de empate
        MyList sortedResults = sortResults(results);
        
        // Mostrar ranking
        String[] positions = {"1°", "2°", "3°", "4°", "5°", "6°"};
        for (int i = 0; i < sortedResults.size(); i++) {
            Result result = sortedResults.get(i);
            String description = generateRankingDescription(result, i, sortedResults);
            System.out.println(positions[i] + " " + result.getAlgorithmName() + " - " + description);
        }
        
        System.out.println();
        System.out.println("================================================");
        System.out.println("Criterio de ranking:");
        System.out.println("1. Cantidad de colecciones ordenadas (mayor es mejor)");
        System.out.println("2. Tiempo promedio por colección (menor es mejor, en caso de empate)");
        System.out.println("================================================");
    }
    
    /**
     * Ordena los resultados usando Selection Sort (simple y sin dependencias).
     */
    private static MyList sortResults(MyList results) {
        // Usar un array temporal para el ordenamiento
        Result[] resultArray = new Result[results.size()];
        for (int i = 0; i < results.size(); i++) {
            resultArray[i] = results.get(i);
        }
        
        // Selection Sort
        for (int i = 0; i < resultArray.length - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < resultArray.length; j++) {
                Result r1 = resultArray[maxIdx];
                Result r2 = resultArray[j];
                
                // Comparar por cantidad de colecciones ordenadas (mayor es mejor)
                int collectionsCompare = r2.getCollectionsSorted() - r1.getCollectionsSorted();
                if (collectionsCompare != 0) {
                    if (collectionsCompare > 0) {
                        maxIdx = j;
                    }
                } else {
                    // En caso de empate, comparar por tiempo promedio (menor es mejor)
                    if (r2.getAvgTime() < r1.getAvgTime()) {
                        maxIdx = j;
                    }
                }
            }
            
            // Intercambiar
            if (maxIdx != i) {
                Result temp = resultArray[i];
                resultArray[i] = resultArray[maxIdx];
                resultArray[maxIdx] = temp;
            }
        }
        
        // Crear nueva lista ordenada
        MyList sorted = new MyList(resultArray.length);
        for (Result r : resultArray) {
            sorted.add(r);
        }
        
        return sorted;
    }
    
    /**
     * Genera una descripción textual para el ranking de un algoritmo.
     */
    private static String generateRankingDescription(Result result, int position, MyList allResults) {
        StringBuilder desc = new StringBuilder();
        
        if (result.isCompletedAll()) {
            desc.append("Ordenó todas las colecciones");
            if (position == 0) {
                desc.append(" con el menor tiempo promedio");
            } else {
                Result first = allResults.get(0);
                if (Math.abs(result.getAvgTime() - first.getAvgTime()) < 1.0) {
                    desc.append(", rendimiento muy similar al primero");
                } else {
                    desc.append(", tiempo promedio ligeramente mayor");
                }
            }
        } else {
            desc.append("No completó todas las colecciones");
            if (position == allResults.size() - 1) {
                desc.append(", ordenó la menor cantidad");
            } else {
                desc.append(", pero logró ordenar más que otros algoritmos O(n²)");
            }
        }
        
        desc.append(".");
        return desc.toString();
    }
    
    /**
     * Solicita al usuario que ingrese el tiempo límite en segundos.
     * @param scanner Scanner para leer la entrada del usuario
     * @return Tiempo límite en segundos (valor por defecto 10 si la entrada es inválida)
     */
    private static int askUserForTimeLimit(Scanner scanner) {
        System.out.println("================================================");
        System.out.println("  CONFIGURACIÓN DE TIEMPO LÍMITE");
        System.out.println("================================================");
        System.out.print("Ingrese el tiempo límite en segundos (presione Enter para usar 10 segundos por defecto): ");
        
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            System.out.println("Usando valor por defecto: 10 segundos.");
            return 10;
        }
        
        try {
            int seconds = Integer.parseInt(input);
            if (seconds <= 0) {
                System.out.println("El tiempo debe ser un número positivo. Usando valor por defecto: 10 segundos.");
                return 10;
            }
            return seconds;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Debe ser un número entero. Usando valor por defecto: 10 segundos.");
            return 10;
        }
    }
}

