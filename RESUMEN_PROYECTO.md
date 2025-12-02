# 📋 Resumen Completo del Proyecto: Ordenamiento Concurrente

## 🎯 Visión General

Este proyecto implementa un sistema de comparación de rendimiento entre seis algoritmos de ordenamiento clásicos ejecutándose de forma **concurrente** (en paralelo) en Java. Cada algoritmo corre en su propio hilo, ordenando las mismas colecciones de datos, y al final se comparan los resultados para determinar cuál es más eficiente.

---

## 📁 Estructura de Archivos y Relaciones

### 🔷 **PAQUETE `util/`** - Utilidades Personalizadas

Estas clases reemplazan las clases de `java.util` que están prohibidas en el proyecto.

#### 1. **`MyList.java`**
- **Propósito**: Lista genérica simple que almacena objetos de cualquier tipo
- **Funcionalidad**: 
  - Almacenamiento dinámico con redimensionamiento automático
  - Métodos básicos: `add()`, `get()`, `set()`, `size()`, `clear()`
- **Usado por**: 
  - `MyArrayList` (hereda la funcionalidad base)
  - `ConcurrentSortingTest` (almacena tareas y resultados)
  - `DataGenerator` (almacena colecciones generadas)
  - `SortingTask` (almacena colecciones de trabajo)

#### 2. **`MyArrayList.java`**
- **Propósito**: Implementación propia de `ArrayList<Integer>` sin usar `java.util`
- **Funcionalidad**:
  - Almacena enteros (`Integer`)
  - Métodos: `add()`, `get()`, `set()`, `size()`, `remove()`
  - Redimensionamiento automático
- **Usado por**:
  - Todos los algoritmos de ordenamiento (método `sort(MyArrayList list)`)
  - `DataGenerator` (genera listas de prueba)
  - `SortingTask` (maneja colecciones ArrayList)

#### 3. **`MyRandom.java`**
- **Propósito**: Generador de números aleatorios sin usar `java.util.Random`
- **Funcionalidad**:
  - Implementa un generador lineal congruencial (LCG)
  - Métodos: `nextInt()`, `nextInt(int bound)`
- **Usado por**:
  - `DataGenerator` (genera números aleatorios para las colecciones de prueba)

#### 4. **`MyStack.java`**
- **Propósito**: Implementación propia de `Stack` sin usar `java.util`
- **Funcionalidad**:
  - Pila LIFO (Last In, First Out)
  - Métodos: `push()`, `pop()`, `peek()`, `isEmpty()`, `size()`
- **Usado por**:
  - `QuickSort` (implementación iterativa para evitar `StackOverflowError`)

---

### 🔷 **PAQUETE `sorting/`** - Algoritmos de Ordenamiento

#### 1. **`SortAlgorithm.java`** (Interfaz)
- **Propósito**: Define el contrato que todos los algoritmos deben cumplir
- **Métodos requeridos**:
  - `void sort(int[] arr)` - Ordena un arreglo primitivo
  - `void sort(MyArrayList list)` - Ordena una MyArrayList
  - `String getName()` - Retorna el nombre del algoritmo
- **Implementado por**: Los 6 algoritmos de ordenamiento

#### 2. **`BubbleSort.java`**
- **Propósito**: Implementa Bubble Sort (complejidad O(n²))
- **Funcionalidad**: Compara elementos adyacentes e intercambia si están en orden incorrecto
- **Relaciones**: Implementa `SortAlgorithm`, usado por `SortingTask`

#### 3. **`InsertionSort.java`**
- **Propósito**: Implementa Insertion Sort (complejidad O(n²))
- **Funcionalidad**: Construye el arreglo ordenado insertando elementos uno por uno
- **Relaciones**: Implementa `SortAlgorithm`, usado por `SortingTask`

#### 4. **`SelectionSort.java`**
- **Propósito**: Implementa Selection Sort (complejidad O(n²))
- **Funcionalidad**: Encuentra el elemento mínimo y lo coloca en su posición correcta
- **Relaciones**: Implementa `SortAlgorithm`, usado por `SortingTask`

#### 5. **`MergeSort.java`**
- **Propósito**: Implementa Merge Sort (complejidad O(n log n))
- **Funcionalidad**: Divide el arreglo en mitades, ordena recursivamente y combina
- **Relaciones**: Implementa `SortAlgorithm`, usado por `SortingTask`

#### 6. **`QuickSort.java`**
- **Propósito**: Implementa Quick Sort (complejidad O(n log n) promedio)
- **Funcionalidad**: 
  - **IMPORTANTE**: Usa implementación **iterativa** con `MyStack` para evitar `StackOverflowError`
  - Selecciona un pivote y particiona el arreglo
- **Relaciones**: 
  - Implementa `SortAlgorithm`
  - Usa `MyStack` para gestión manual de la pila de recursión
  - Usa `MyRandom` para selección aleatoria del pivote
  - Usado por `SortingTask`

#### 7. **`HeapSort.java`**
- **Propósito**: Implementa Heap Sort (complejidad O(n log n))
- **Funcionalidad**: Construye un heap y extrae elementos en orden
- **Relaciones**: Implementa `SortAlgorithm`, usado por `SortingTask`

---

### 🔷 **PAQUETE `concurrent/`** - Coordinación y Ejecución Concurrente

#### 1. **`ConcurrentSortingTest.java`** (Clase Principal - `main`)
- **Propósito**: Punto de entrada del programa, coordina toda la ejecución
- **Funcionalidad**:
  - Lee el tiempo límite desde argumentos de línea de comandos
  - Crea `DataGenerator` para generar colecciones de prueba
  - Instancia los 6 algoritmos de ordenamiento
  - Crea `SortingTask` para cada algoritmo
  - Crea y ejecuta `Thread`s para cada tarea (ejecución concurrente)
  - Espera a que todos los hilos terminen (`join()`)
  - Recolecta `Result`s de cada algoritmo
  - Muestra resultados individuales, comparación Arrays vs ArrayLists, y ranking
- **Relaciones**:
  - **Usa**: `DataGenerator`, `SortAlgorithm[]`, `SortingTask`, `Result`, `MyList`, `Thread`
  - **Crea**: Instancias de todos los algoritmos y tareas
  - **Coordina**: La ejecución concurrente completa

#### 2. **`DataGenerator.java`**
- **Propósito**: Genera todas las colecciones de prueba necesarias
- **Funcionalidad**:
  - Genera 6 tipos de colecciones:
    1. 100 elementos aleatorios
    2. 1,000 elementos aleatorios
    3. 10,000 elementos aleatorios
    4. 50,000 elementos aleatorios
    5. 100,000 elementos aleatorios
    6. 100,000 elementos aleatorios (rango 1-5)
  - Cada tipo se genera como `int[]` y como `MyArrayList`
  - Total: **12 colecciones** (6 tipos × 2 estructuras)
- **Relaciones**:
  - **Usa**: `MyRandom` (genera números aleatorios), `MyArrayList`, `MyList`
  - **Usado por**: `ConcurrentSortingTest` (genera datos iniciales)
  - **Retorna**: `MyList` de `SortingTask.CollectionItem`

#### 3. **`SortingTask.java`** (Implementa `Runnable`)
- **Propósito**: Tarea que ejecuta un algoritmo de ordenamiento en un hilo separado
- **Funcionalidad**:
  - Recibe un algoritmo y las colecciones a ordenar
  - Ejecuta en un bucle mientras no expire el tiempo límite
  - Para cada colección:
    - Crea una copia (para no modificar el original)
    - Mide el tiempo de ordenamiento
    - Verifica si terminó antes del tiempo límite
    - Actualiza el `Result` con las métricas
    - Muestra prints detallados durante la ejecución
  - Maneja descalificaciones cuando se excede el tiempo límite
- **Clase interna `CollectionItem`**:
  - Almacena una colección individual (puede ser `int[]` o `MyArrayList`)
  - Incluye descripción y flag `isArray`
  - Método `copy()` para crear copias profundas
- **Relaciones**:
  - **Usa**: `SortAlgorithm`, `Result`, `MyList`, `CollectionItem`
  - **Usado por**: `ConcurrentSortingTest` (crea instancias para cada algoritmo)
  - **Ejecutado por**: `Thread` (cada algoritmo en su propio hilo)

#### 4. **`Result.java`**
- **Propósito**: Almacena las métricas de rendimiento de un algoritmo
- **Funcionalidad**:
  - Almacena:
    - Nombre del algoritmo
    - Cantidad de colecciones ordenadas (total, Arrays, ArrayLists)
    - Tiempo total y promedio
    - Tiempos promedio separados por tipo de estructura
    - Si completó todas las colecciones
  - Método `addCollection(long timeMs, boolean isArray)` actualiza las métricas
- **Relaciones**:
  - **Usado por**: `SortingTask` (actualiza métricas durante ejecución)
  - **Usado por**: `ConcurrentSortingTest` (muestra resultados y genera ranking)

---

## 🔄 Flujo de Ejecución del Programa

```
1. ConcurrentSortingTest.main()
   │
   ├─> Lee argumentos (tiempo límite)
   │
   ├─> Crea DataGenerator
   │   └─> Genera 12 colecciones (6 tipos × 2 estructuras)
   │       └─> Usa MyRandom para números aleatorios
   │
   ├─> Crea 6 algoritmos (BubbleSort, InsertionSort, etc.)
   │
   ├─> Para cada algoritmo:
   │   ├─> Crea SortingTask
   │   ├─> Crea Thread
   │   └─> Inicia el hilo (ejecución concurrente)
   │
   ├─> Espera a que todos los hilos terminen (join())
   │
   └─> Muestra resultados:
       ├─> Resultados individuales
       ├─> Comparación Arrays vs ArrayLists
       └─> Ranking de eficiencia
```

### Durante la ejecución de cada `SortingTask`:

```
SortingTask.run() (en su propio hilo)
│
├─> Mientras no expire el tiempo límite:
│   │
│   ├─> Para cada colección:
│   │   ├─> Crea copia (CollectionItem.copy())
│   │   ├─> Mide tiempo de inicio
│   │   ├─> Ejecuta algoritmo.sort() (array o list)
│   │   ├─> Mide tiempo de fin
│   │   ├─> Si terminó a tiempo:
│   │   │   └─> Result.addCollection() (actualiza métricas)
│   │   └─> Si excedió tiempo:
│   │       └─> Muestra mensaje DESCALIFICADO y termina
│   │
│   └─> Prepara nuevas copias para siguiente iteración
│
└─> Al finalizar: muestra resumen (Arrays: X, ArrayLists: Y)
```

---

## 🔗 Dependencias entre Paquetes

```
concurrent/
  ├─> usa sorting/ (algoritmos)
  ├─> usa util/ (MyList, MyArrayList, MyRandom)
  └─> coordina todo

sorting/
  ├─> usa util/MyArrayList (para ordenar listas)
  └─> QuickSort también usa util/MyStack y util/MyRandom

util/
  └─> Clases independientes (no dependen de otros paquetes)
```

---

## 📊 Resumen por Categoría

### **Clases de Utilidad (util/)**
- `MyList`: Lista genérica base
- `MyArrayList`: Lista de enteros (reemplaza ArrayList)
- `MyRandom`: Generador aleatorio (reemplaza Random)
- `MyStack`: Pila (reemplaza Stack, usado por QuickSort)

### **Algoritmos (sorting/)**
- `SortAlgorithm`: Interfaz común
- `BubbleSort`, `InsertionSort`, `SelectionSort`: O(n²)
- `MergeSort`, `QuickSort`, `HeapSort`: O(n log n)

### **Coordinación (concurrent/)**
- `ConcurrentSortingTest`: Orquestador principal
- `DataGenerator`: Genera datos de prueba
- `SortingTask`: Ejecuta algoritmo en hilo separado
- `Result`: Almacena métricas de rendimiento

---

## 🎯 Características Clave del Diseño

1. **Sin `java.util`**: Todas las clases de utilidad son implementaciones propias
2. **Concurrencia**: Cada algoritmo corre en su propio `Thread`
3. **Medición precisa**: Tiempos medidos con `System.currentTimeMillis()`
4. **Control de tiempo**: Los hilos verifican periódicamente el tiempo límite
5. **Comparación justa**: Todos los algoritmos ordenan las mismas colecciones
6. **Dos estructuras**: Soporte para `int[]` y `MyArrayList<Integer>`
7. **Métricas detalladas**: Separación de tiempos por tipo de estructura

---

## 📝 Archivos de Scripts (Raíz del Proyecto)

- **`compile.bat`**: Compila el proyecto
- **`run.bat`**: Ejecuta el programa
- **`clean-compile.bat`**: Limpia y recompila desde cero
- **`upload-to-github.bat`**: Sube el proyecto a GitHub
- **`.gitignore`**: Excluye archivos compilados del repositorio
- **`README.md`**: Documentación del proyecto

---

Este resumen proporciona una visión completa de cómo está estructurado el proyecto y cómo interactúan todos los componentes entre sí.

