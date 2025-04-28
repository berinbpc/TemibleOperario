# Temible Operario del Recontra Espionaje

Trabajo práctico de **Programación III**: modelado y resolución de un problema de transmisión segura de mensajes en redes de espías, utilizando algoritmos de grafos y arquitectura **MVC**.

## Descripción

El objetivo del proyecto es encontrar un **Árbol Generador Mínimo** (AGM) que minimice la probabilidad máxima de intercepción entre espías.  
Se modeló una red de espías como un **grafo no dirigido**, donde los vértices representan espías y las aristas representan encuentros posibles, con un peso asociado a la probabilidad de intercepción.

El sistema permite:

- Carga de espías y conexiones manualmente o mediante archivo `.json`.
- Resolución del problema utilizando **Prim** o **Kruskal**.
- Visualización de conexiones y caminos mínimos generados.
- Comparación de tiempos de ejecución entre algoritmos.
- Recorrido BFS del AGM para definir el orden de los encuentros.

## Tecnologías usadas

- **Java**  
- **Swing** para la interfaz gráfica (GUI)  
- **Gson** para manipulación de archivos JSON  
- **JUnit** para pruebas unitarias

## Estructura del proyecto

El proyecto está estructurado bajo el patrón **MVC**:

- **Model**: lógica del negocio (grafos, algoritmos Prim y Kruskal, manejo de archivos JSON, recorrido BFS).
- **View**: interfaces gráficas para carga de datos, selección de algoritmos y visualización de resultados.
- **Controller**: intermediario entre la Vista y el Modelo.
- **Model.testing**: tests unitarios del modelo.

## Cómo correrlo

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
   ```
2. Abrir el proyecto en un IDE (recomendado: **Eclipse**)
3. Ejecutar la clase `InterfazMenu` (paquete `view`).

## Instrucciones de uso

- Desde el menú principal, elegir entre:
  - **Carga manual**: ingresar cantidad de espías, nombres y conexiones.
  - **Carga por archivo**: cargar un `.json` con los datos de espías y probabilidades.
- Seleccionar el algoritmo a utilizar (**Prim** o **Kruskal**).
- Visualizar las tablas de conexiones y del árbol generador mínimo, junto al tiempo de ejecución.

## Ejemplos de tiempos de ejecución

| Cantidad de vértices | Prim | Kruskal |
|----------------------|------|---------|
| 10                   | 0.003s | 0.004s |
| 50                   | 0.009s | 0.005s |
| 100                  | 0.027s | 0.005s |

*Nota*: los tiempos pueden variar según la máquina.

## Extras implementados

- Serialización y deserialización de datos en formato `.json`.
- Elección dinámica entre algoritmos Prim y Kruskal.
- Recorrido BFS del AGM para definir el orden óptimo de encuentros.
