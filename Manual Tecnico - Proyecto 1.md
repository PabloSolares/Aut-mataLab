# Manual Técnico - AutómataLab

## Lenguaje y Herramientas Utilizadas

- **Lenguaje de programación:** Java (versión 8 o superior)
- **Entorno de desarrollo recomendado:** NetBeans / IntelliJ IDEA / Eclipse
- **Herramientas de análisis léxico y sintáctico:** JFlex y CUP
- **Generación de gráficos:** Graphviz (comando `dot`)
- **Ejecución:** Archivo `.jar` ejecutable mediante `java -jar AutómataLab.jar`

---

## Arquitectura General del Proyecto

El proyecto está organizado en **paquetes** principales:

- **Lexer/** → Contiene los analizadores léxicos y definiciones CUP.
- **Modelo/** → Contiene las clases base para la representación de autómatas (AFD, AP, etc.).
- **Controlador/** → Gestiona la comunicación entre la interfaz gráfica y los modelos de autómatas.
- **Vista/** → Contiene la interfaz gráfica con el editor de texto, panel de salida y reportes.
- **proyeto1compi/** → Contiene la clase principal (`main`) para iniciar la aplicación.

---

## Clases Principales

### Paquete `Modelo`

#### 1. `Automata` (Interfaz)
Define la estructura básica de un autómata.

#### 2. `AFD`
Implementación de un **Autómata Finito Determinista**.

- Métodos relevantes: `addEstado`, `addTransicion`, `validate`, `ConvertirADot`, `exportarGrafica`, `exportarGraficaValidar`.

#### 3. `AP`
Implementación de un **Autómata de Pila**.

- Métodos relevantes: `setTrancisiones`, `validate`, `generarDotAP`, `exportarGraficaAP`, `generarDotValidacion`.

#### 4. `Transicion`
Representa una transición de un autómata de pila.

- Atributos: `simboloEntrada`, `simboloExtrae`, `estadoDestino`, `simboloInserta`.
- Método sobrescrito: `toString()` para imprimir la transición.

#### 5. `ModeloMenu`
Administra listas globales de **tokens** y **errores**.

- Métodos: 
  - `ingresarToken(Yytoken token)` → Añade un token a la lista.
  - `getListaTokens()` y `getListaErrores()` → Devuelven listas acumuladas.

---

### Paquete `Controlador`

#### 1. `ControladorMenu`
Clase que conecta la vista con el modelo y gestiona eventos de la interfaz gráfica.

- **Responsabilidades principales:**
  - Inicializar la vista principal (`initMenu`).
  - Configurar acciones para abrir, guardar y procesar archivos `.atm`.
  - Integrar el analizador léxico (`Lexer`) y sintáctico (`LexerCup` + `sintax`).
  - Manejar la generación de reportes (`ReporteVista`, `ModeloReportes`).

- **Acciones importantes:**
  - `btnAbrir` → Abre un archivo `.atm` y carga su contenido en el editor.
  - `btnGuardarArchivo` → Guarda el código en un archivo `.atm`.
  - `btnProcesar` → Procesa el código fuente, ejecuta el parser y actualiza tokens y errores.
  - `btnVerReportes` → Muestra reportes de análisis.

#### 2. `ControladorReportes`
(No mostrado en detalle, pero se encarga de la lógica para la visualización de reportes).

---

### Paquete `proyeto1compi`

#### 1. `Proyeto1Compi`
Clase `main` que inicializa la aplicación.

- Flujo de ejecución:
  1. Crear instancia de `MenuVista` (interfaz gráfica).
  2. Crear `ModeloMenu` (almacenamiento de tokens y errores).
  3. Pasar ambos al `ControladorMenu`.
  4. Llamar a `initMenu()` para iniciar la aplicación.

---

## Dependencias Externas

- **Graphviz** → Para graficar los autómatas (DOT → PNG).
- **Java CUP y JFlex** → Construcción del parser y lexer.

---

## Métodos Críticos a Revisar en Futuras Modificaciones

- **AFD.validate(String cadena)** → Recorrido de cadenas en un AFD.
- **AP.validate(String cadena)** → Validación de cadenas en autómatas de pila con operaciones de la pila.
- **ControladorMenu.initMenu()** → Vincula la interfaz gráfica con la lógica del sistema.
- **exportarGrafica/exportarGraficaValidar** (AFD y AP) → Dependen de Graphviz instalado.
- **ModeloMenu.ListaTokens y ListaErrores** → Deben gestionarse cuidadosamente para evitar acumulación innecesaria de datos.

---

## Recomendaciones de Mantenimiento

1. **Extensibilidad:**  
   - Nuevos autómatas pueden implementarse extendiendo la interfaz `Automata`.
   - CUP y JFlex pueden modificarse para soportar más instrucciones.

2. **Visualización:**  
   - Actualmente se usa Graphviz externo. Se podría integrar una librería gráfica en Java para reducir dependencias externas.

3. **Gestión de errores:**  
   - Mejorar los reportes de errores semánticos y de ejecución.

4. **Documentación y pruebas:**  
   - Mantener este manual técnico junto con el manual de usuario y gramática BNF.
   - Desarrollar pruebas unitarias para validaciones de cadenas.

---
