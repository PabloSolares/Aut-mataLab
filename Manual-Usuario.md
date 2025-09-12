# Manual de Usuario - AutómataLab - 202400044

## Tabla de Contenidos
1. [Introducción](#introducción)
2. [Requisitos del Sistema](#requisitos-del-sistema)
3. [Instalación y Ejecución](#instalación-y-ejecución)
4. [Interfaz de Usuario](#interfaz-de-usuario)
5. [Definición de Autómatas](#definición-de-autómatas)
6. [Funcionalidades del Lenguaje](#funcionalidades-del-lenguaje)
7. [Generación de Reportes](#generación-de-reportes)
8. [Manejo de Errores](#manejo-de-errores)
9. [Ejemplos de Uso](#ejemplos-de-uso)

## Introducción

AutómataLab es una herramienta de software desarrollada para la creación, edición, validación y análisis de autómatas finitos deterministas (AFD) y autómatas de pila (AP). Esta aplicación permite definir autómatas mediante archivos de texto con extensión `.atm` o a través de un editor incorporado, ofreciendo diversas funcionalidades para su manipulación y visualización.

## Requisitos del Sistema

- Sistema operativo: Windows, Linux o macOS
- Java Runtime Environment (JRE) versión 8 o superior
- Mínimo 2 GB de RAM
- 500 MB de espacio libre en disco

## Instalación y Ejecución

1. Descargue el archivo JAR ejecutable desde el repositorio del proyecto
2. Asegúrese de tener Java instalado en su sistema ejecutando `java -version` en la terminal
3. Ejecute la aplicación con el comando: `java -jar AutómataLab.jar`

## Interfaz de Usuario

La interfaz de AutómataLab está organizada en las siguientes secciones:

### Menú Principal
- **Archivo**: Contiene las opciones para crear, abrir y guardar archivos
- **Reportes**: Permite generar diferentes tipos de reportes
- **Ejecutar**: Inicia el análisis del código fuente

### Áreas de Trabajo
- **Editor de código**: Zona principal para escribir o editar definiciones de autómatas
- **Panel de salida**: Muestra resultados de ejecuciones y validaciones
- **Panel de reportes**: Visualiza los diferentes reportes generados
<img width="835" height="635" alt="image" src="https://github.com/user-attachments/assets/0cc315aa-aa1e-4154-81d3-5264e975fc9e" />
 <img width="792" height="730" alt="image" src="https://github.com/user-attachments/assets/de68b4a2-754f-478a-ac8d-ceb9c9fb3ebf" />



## Definición de Autómatas

### Autómatas Finitos Deterministas (AFD)

Los AFD se definen entre las etiquetas `<AFD>` y `</AFD>` con la siguiente estructura:

```atm
<AFD Nombre="nombre_del_autómata">
  N = {estado1, estado2, estado3};    // Conjunto de estados
  T = {símbolo1, símbolo2};           // Alfabeto
  I = {estado_inicial};               // Estado inicial
  A = {estado_aceptación1, estado_aceptación2};  // Estados de aceptación

Transiciones:
  estado_origen -> símbolo, estado_destino | ... ;
</AFD>
```

### Autómatas de Pila (AP)

Los AP se definen entre las etiquetas `<AP>` y `</AP>` con la siguiente estructura:

```atm
<AP Nombre="nombre_del_autómata">
  N = {estado1, estado2, estado3};    // Conjunto de estados
  T = {símbolo1, símbolo2};           // Alfabeto
  P = {símbolo_pila1, símbolo_pila2}; // Símbolos de pila
  I = {estado_inicial};               // Estado inicial
  A = {estado_aceptación};            // Estados de aceptación

Transiciones:
  estado_origen (símbolo_entrada) -> (símbolo_extrae_pila), estado_destino : (símbolo_inserta_pila) | ... ;
</AP>
```

## Funcionalidades del Lenguaje

- **Listar autómatas**: Muestra un listado de los autómatas definidos en el sistema
  - `` verAutomatas(); ``
- **Descripción de autómata**: Muestra los elementos de un autómata específico
  - `` desc(nombreAutomata); ``
- **Validación de cadenas**: Permite comprobar si una cadena es aceptada por un autómata
  -  `` nombreAutomata(cadenaAValidar); ``

## Generación de Reportes

- **Reporte de autómatas**: Muestra la representación gráfica de los autómatas
- **Reporte de pasos (AFD)**: Muestra el recorrido paso a paso de la validación de una cadena
- **Reporte de pasos (AP)**: Muestra el proceso de validación con pila
- **Tabla de tokens**: Muestra los tokens reconocidos durante el análisis léxico
- **Reporte de errores**: Muestra los errores léxicos o sintácticos detectados

## Manejo de Errores

El sistema genera reportes de errores cuando detecta símbolos no válidos o transiciones incorrectas. Cada error incluye tipo, descripción, línea y columna.

## Ejemplos de Uso

```atm
<AFD Nombre="AFD_1">
  N = {S, A, B};
  T = {0, 1};
  I = {S};
  A = {B};
Transiciones:
  S -> 0, A | 1, B;
  A -> 0, A | 1, B;
  B -> 0, B | 1, B;
</AFD>

<AP Nombre="AP_1">
  N = {I, A, F};
  T = {a, b};
  P = {a, b, #};
  I = {I};
  A = {F};
Transiciones:
  I ($) -> ($), A : (#);
  A (a) -> ($), A : (a);
  A (b) -> (a), F : ($);
</AP>
```
