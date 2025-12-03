# Proyecto: Ordenamiento Concurrente con Seis Algoritmos Clásicos

Este proyecto implementa una comparación de rendimiento entre seis algoritmos de ordenamiento clásicos ejecutándose de forma concurrente en Java.

## Descripción

El proyecto ejecuta simultáneamente seis algoritmos de ordenamiento (Bubble Sort, Insertion Sort, Selection Sort, Merge Sort, Quick Sort y Heap Sort) durante un tiempo límite especificado. Cada algoritmo corre en su propio hilo de ejecución, ordenando diferentes colecciones de datos aleatorios tanto en arreglos primitivos (`int[]`) como en `ArrayList<Integer>`.

## Algoritmos Implementados

1. **Bubble Sort** - Complejidad O(n²)
2. **Insertion Sort** - Complejidad O(n²)
3. **Selection Sort** - Complejidad O(n²)
4. **Merge Sort** - Complejidad O(n log n)
5. **Quick Sort** - Complejidad O(n log n) promedio
6. **Heap Sort** - Complejidad O(n log n)

## Datos de Prueba

El proyecto utiliza seis tipos de colecciones de prueba:
- 100 elementos aleatorios
- 1,000 elementos aleatorios
- 10,000 elementos aleatorios
- 50,000 elementos aleatorios
- 100,000 elementos aleatorios
- 100,000 elementos aleatorios entre 1 y 5

Cada colección se prueba tanto como arreglo primitivo (`int[]`) como `MyArrayList<Integer>`, resultando en **12 colecciones** (6 tipos × 2 estructuras) por algoritmo.

## Estructura del Proyecto

```
src/
├── util/
│   ├── MyArrayList.java            (Implementación propia de ArrayList)
│   ├── MyStack.java                (Implementación propia de Stack)
│   ├── MyRandom.java               (Implementación propia de Random)
│   └── MyList.java                 (Lista genérica simple)
├── sorting/
│   ├── SortAlgorithm.java          (Interfaz para algoritmos)
│   ├── BubbleSort.java
│   ├── InsertionSort.java
│   ├── SelectionSort.java
│   ├── MergeSort.java
│   ├── QuickSort.java
│   └── HeapSort.java
└── concurrent/
    ├── ConcurrentSortingTest.java  (Clase principal)
    ├── SortingTask.java            (Tarea concurrente)
    ├── Result.java                 (Almacenamiento de resultados)
    └── DataGenerator.java          (Generación de datos)
```

## Compilación

Para compilar el proyecto, ejecuta desde la raíz del proyecto:

**En Linux/Mac:**
```bash
mkdir -p bin
javac -d bin -encoding UTF-8 src/util/*.java src/sorting/*.java src/concurrent/*.java
```

**En Windows:**
```bash
if not exist bin mkdir bin
javac -d bin -encoding UTF-8 src\util\*.java src\sorting\*.java src\concurrent\*.java
```

**Nota:** Asegúrate de tener Java JDK 8 o superior instalado y en tu PATH.

## Ejecución

Para ejecutar el programa, usa la clase principal `ConcurrentSortingTest`:

```bash
java -cp bin concurrent.ConcurrentSortingTest [segundos]
```

**Ejemplos:**

```bash
# Ejecutar con tiempo límite por defecto (10 segundos)
java -cp bin concurrent.ConcurrentSortingTest

# Ejecutar con 5 segundos de tiempo límite
java -cp bin concurrent.ConcurrentSortingTest 5

# Ejecutar con 30 segundos de tiempo límite
java -cp bin concurrent.ConcurrentSortingTest 30
```

Si no se especifica un tiempo límite, el programa usará 10 segundos por defecto.

## Limpieza

Para eliminar los archivos compilados y empezar desde cero:

**En Linux/Mac:**
```bash
rm -rf bin
```

**En Windows:**
```bash
rmdir /s /q bin
```

## Salida del Programa

El programa muestra:

1. **Información de inicio**: Tiempo límite configurado y generación de datos
2. **Progreso detallado en tiempo real**: 
   - Cada algoritmo muestra qué estructura está ordenando (ARRAY o ArrayList)
   - Tiempo de ejecución de cada ordenamiento individual
   - Mensajes de descalificación cuando se excede el tiempo límite
3. **Resumen al finalizar**: Cada algoritmo muestra cuántas colecciones ordenó (total, Arrays, ArrayLists)
4. **Resultados individuales**: Para cada algoritmo muestra:
   - Cantidad de colecciones ordenadas
   - Tiempo promedio por colección
   - Si completó todas las tareas (COMPLETO/INCOMPLETO)
5. **Comparación Arrays vs ArrayLists**: 
   - Cantidad de colecciones ordenadas por tipo de estructura
   - Tiempo promedio para cada estructura
   - Qué estructura fue más rápida y por cuánto porcentaje
6. **Ranking de eficiencia**: Ordenamiento de los algoritmos del más eficiente al menos eficiente

### Ejemplo de salida:

```
================================================
  ORDENAMIENTO CONCURRENTE - PRUEBA DE RENDIMIENTO
================================================
Tiempo límite: 10 segundos

Generando datos de prueba...
Generando 50,000 elementos...
Generando 100,000 elementos...
Generando 100,000 elementos (rango 1-5)...
Datos de prueba generados exitosamente.

Iniciando ejecución concurrente de los 6 algoritmos...

Iniciando Bubble Sort en hilo Bubble Sort
Iniciando Insertion Sort en hilo Insertion Sort
...
[Resultados individuales]
...
================================================
              RANKING DE EFICIENCIA
================================================

1° Quick Sort – Ordenó todas las colecciones con el menor tiempo promedio.
2° Merge Sort – Ordenó todas las colecciones, tiempo promedio ligeramente mayor.
...
```

## Métricas de Rendimiento

El programa mide y compara:

- **Cantidad de colecciones ordenadas**: Cuántas colecciones completas logró ordenar cada algoritmo dentro del tiempo límite
- **Tiempo promedio por colección**: Tiempo promedio que tarda cada algoritmo en ordenar una colección
- **Completitud**: Si el algoritmo logró ordenar todas las colecciones de prueba en el tiempo dado
- **Comparación de estructuras**: Rendimiento comparativo entre `int[]` y `MyArrayList<Integer>` para cada algoritmo
- **Tiempo por estructura**: Tiempo promedio separado para Arrays y ArrayLists

## Criterios de Ranking

Los algoritmos se ordenan por:
1. **Cantidad de colecciones ordenadas** (mayor es mejor)
2. **Tiempo promedio por colección** (menor es mejor, en caso de empate)

## Requisitos

- Java JDK 8 o superior
- Sistema operativo con soporte para múltiples hilos

## Características de Implementación

- ✅ Ejecución concurrente segura (sin condiciones de carrera)
- ✅ Detención cooperativa de hilos (sin métodos deprecados)
- ✅ Implementación manual de todos los algoritmos (sin usar `Arrays.sort()` ni `Collections.sort()`)
- ✅ **Sin dependencias de `java.util`**: Implementaciones propias de ArrayList, Stack y Random
- ✅ Soporte para ambas estructuras de datos (arreglos y MyArrayList)
- ✅ Medición precisa de tiempos con `System.currentTimeMillis()`
- ✅ Código bien comentado y estructurado

## Notas Técnicas

- Cada algoritmo se ejecuta en su propio hilo independiente
- Los hilos verifican periódicamente el tiempo límite y se detienen cooperativamente
- Los datos de prueba se generan una vez y se copian para cada algoritmo, asegurando comparaciones justas
- El programa espera a que todos los hilos terminen antes de mostrar los resultados

## Autor

Proyecto desarrollado para demostrar diferencias de rendimiento entre algoritmos de ordenamiento clásicos en un entorno concurrente.

