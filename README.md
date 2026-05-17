# Gestor de Tareas — Java Swing

Aplicación de escritorio para gestión de tareas personales, desarrollada en Java con interfaz gráfica Swing. Implementa patrones de diseño clásicos (Singleton, MVC), separación en capas, renderizadores personalizados de listas y un conjunto de pruebas unitarias con JUnit 4. El proyecto está gestionado con Maven y compilado para Java 11.

---

## Tabla de contenidos

- [Descripción general](#descripción-general)
- [Tecnologías y patrones utilizados](#tecnologías-y-patrones-utilizados)
- [Arquitectura del proyecto](#arquitectura-del-proyecto)
- [Requisitos previos](#requisitos-previos)
- [Compilación y ejecución](#compilación-y-ejecución)
- [Ejecutar las pruebas](#ejecutar-las-pruebas)
- [Funcionalidades principales](#funcionalidades-principales)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Preguntas frecuentes](#preguntas-frecuentes)
- [Autor](#autor)

---

## Descripción general

Este proyecto es un gestor de tareas de escritorio que permite al usuario crear, visualizar, completar y eliminar tareas mediante una interfaz gráfica construida con Java Swing. La aplicación arranca con un conjunto de tareas de muestra precargadas y mantiene el estado en memoria durante la sesión.

El objetivo del proyecto fue aplicar de forma práctica principios de diseño orientado a objetos en Java: separación de responsabilidades en capas (modelo, servicio, UI), el patrón Singleton para la gestión centralizada del estado, y la escritura de pruebas unitarias que validan el comportamiento del núcleo de negocio de forma independiente a la interfaz gráfica.

---

## Tecnologías y patrones utilizados

### Tecnologías
| Tecnología | Versión | Propósito |
|---|---|---|
| Java | 11 | Lenguaje base de la aplicación |
| Java Swing + AWT | JDK 11 | Framework de interfaz gráfica de escritorio |
| Maven | 3.x | Gestión de dependencias, compilación y empaquetado |
| JUnit | 4.13.2 | Framework de pruebas unitarias |
| Hamcrest | 3.0 | Matchers expresivos para aserciones en tests |
| Apache Commons Lang | 3.20.0 | Utilidades de strings y objetos |

### Patrones de diseño aplicados
| Patrón | Dónde se aplica |
|---|---|
| Singleton | `TaskManager`: instancia única del gestor de tareas con acceso thread-safe mediante `synchronized` |
| MVC (Model-View-Controller) | Separación en paquetes `model`, `service` y `ui` |
| Renderer personalizado | `TaskListRenderer`: control visual de cada celda de la `JList` según el estado de la tarea |
| Event Dispatch Thread (EDT) | Toda la UI se inicializa dentro de `SwingUtilities.invokeLater()` para garantizar la seguridad de hilos de Swing |

---

## Arquitectura del proyecto

```
App.java (punto de entrada)
    │
    └── SwingUtilities.invokeLater()
              │
         MainFrame (JFrame)
              │
    ┌─────────┼──────────────┐
    │         │              │
ControlPanel  JList       StatusPanel
(inputs y     (TaskListRenderer)  (estadísticas)
 botones)          │
              TaskDetailDialog
              (diálogo de detalle)
                   │
              TaskManager (Singleton)
                   │
              List<Task> (en memoria)
```

La capa de servicio (`TaskManager`) es completamente independiente de la UI. Esto permite que las pruebas unitarias en `AppTest` ejerciten toda la lógica de negocio sin necesidad de instanciar ningún componente gráfico.

---

## Requisitos previos

- [Java JDK 11](https://adoptium.net/) o superior
- [Apache Maven](https://maven.apache.org/download.cgi) 3.6 o superior

Verificar las instalaciones:

```bash
java -version
mvn -version
```

---

## Compilación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/Walter-Duchi/Lista-de-Tareas.git
cd Lista-de-Tareas
```

### 2. Compilar el proyecto

```bash
mvn compile
```

### 3. Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass="org.wadr.App"
```

O compilar el JAR ejecutable y lanzarlo directamente:

```bash
mvn package
java -jar target/GCSW-P2-1.0-SNAPSHOT.jar
```

La ventana principal de la aplicación se abrirá con cuatro tareas de ejemplo precargadas.

---

## Ejecutar las pruebas

```bash
mvn test
```

Maven compilará el proyecto, ejecutará los tests en `AppTest.java` y generará el reporte en `target/surefire-reports/`. Las pruebas cubren tres operaciones críticas del `TaskManager`:

| Test | Qué verifica |
|---|---|
| `testAddTask` | Que agregar una tarea incrementa correctamente el contador total |
| `testMarkTaskCompleted` | Que una tarea puede marcarse como completada y el estado persiste |
| `testRemoveTask` | Que eliminar una tarea por ID la remueve del listado y el contador se actualiza |

Cada test parte desde un estado limpio gracias al método `@Before` que invoca `clearAllTasks()` antes de cada caso.

---

## Funcionalidades principales

- **Agregar tareas**: formulario con título y descripción. Se genera un ID único con el formato `TASK-XXXXXXXX` basado en UUID.
- **Completar tareas**: marcar una tarea seleccionada como terminada. El renderizador personalizado aplica un estilo visual diferenciado (tachado o color) para distinguir tareas completadas de pendientes.
- **Eliminar tareas**: eliminar la tarea seleccionada de la lista.
- **Ver detalles**: diálogo emergente (`TaskDetailDialog`) que muestra todos los atributos de una tarea: ID, título, descripción, estado y fecha/hora de creación formateada.
- **Panel de estadísticas**: barra inferior con contadores en tiempo real de tareas totales, pendientes y completadas.
- **Barra de menú**: acceso mediante menú `Archivo` y `Tareas` con atajos de teclado (`Ctrl+N` para nueva tarea, `Ctrl+Q` para salir).
- **Limpiar todo**: opción para eliminar todas las tareas de la sesión actual.

---

## Estructura del proyecto

```
Lista-de-Tareas/
├── src/
│   ├── main/
│   │   └── java/org/wadr/
│   │       ├── model/
│   │       │   └── Task.java              # Entidad de dominio con UUID, título, descripción y estado
│   │       ├── service/
│   │       │   └── TaskManager.java       # Singleton thread-safe con toda la lógica de negocio
│   │       ├── ui/
│   │       │   ├── components/
│   │       │   │   └── TaskListRenderer.java  # Renderizador personalizado de celdas JList
│   │       │   ├── dialogs/
│   │       │   │   └── TaskDetailDialog.java  # Diálogo de detalle de tarea
│   │       │   ├── panels/
│   │       │   │   ├── ControlPanel.java      # Panel de inputs y botones de acción
│   │       │   │   └── StatusPanel.java       # Panel de estadísticas con contadores
│   │       │   └── MainFrame.java             # JFrame principal con BorderLayout
│   │       ├── utils/
│   │       │   └── FormatterUtil.java         # Utilidades de formateo de fechas y textos
│   │       └── App.java                       # Punto de entrada — inicia en el EDT
│   └── test/
│       └── java/org/wadr/
│           └── AppTest.java                   # Pruebas unitarias JUnit 4 del TaskManager
└── pom.xml                                    # Configuración Maven con dependencias y plugin JAR
```

---

## Preguntas frecuentes

**¿Por qué se usa `synchronized` en el método `getInstance()` de `TaskManager`?**
Para garantizar que en un entorno multihilo solo se cree una instancia del Singleton. Aunque en esta aplicación Swing el acceso concurrente al gestor es mínimo, es una práctica correcta que demuestra conciencia sobre thread safety en Java.

**¿Por qué las pruebas unitarias no testean la interfaz gráfica?**
Porque `TaskManager` encapsula toda la lógica de negocio de forma completamente independiente de Swing. Esto es un beneficio directo de la separación en capas: los tests pueden verificar el comportamiento del núcleo de la aplicación sin necesidad de levantar ningún componente de UI, lo que los hace más rápidos y confiables.

**¿Los datos se persisten entre sesiones?**
No. El estado se mantiene únicamente en memoria (`List<Task>`) durante la ejecución. Al cerrar la aplicación, los datos se pierden. La extensión natural sería agregar una capa de persistencia con serialización a archivo o una base de datos embebida como H2 o SQLite.

**¿Cómo se distinguen visualmente las tareas completadas?**
El `TaskListRenderer` extiende `DefaultListCellRenderer` e intercepta el pintado de cada celda. Cuando una tarea tiene `isCompleted() == true`, aplica un estilo diferenciado (color de texto, decoración) para que el usuario identifique el estado de un vistazo sin necesidad de abrir el diálogo de detalle.

**¿Qué formato tiene el ID de cada tarea?**
Se genera con el prefijo `TASK-` seguido de los primeros 8 caracteres de un `UUID.randomUUID()`, resultando en identificadores como `TASK-a3f2b1c4`. Es suficientemente único para el alcance de la aplicación y más legible que un UUID completo.

**¿Por qué se usa `SwingUtilities.invokeLater()` en `App.java`?**
Swing no es thread-safe. Toda creación y modificación de componentes gráficos debe ejecutarse en el Event Dispatch Thread (EDT). `invokeLater()` encola la inicialización de `MainFrame` en ese hilo, siguiendo las buenas prácticas oficiales de la documentación de Java Swing.

---

## Autor

**Walter Alejandro Duchi Rivera**

Desarrollador Full Stack con experiencia en React, .NET y arquitecturas orientadas a eventos con WebSockets.

- GitHub: [@WalterDuchi](https://github.com/Walter-Duchi)
- LinkedIn: [linkedin.com/in/walter-duchi](https://www.linkedin.com/in/walter-duchi/)
- Portafolio Profesional: [Walter Duchi](https://portafolio-theta-ten-87.vercel.app/)
- Correo: [waltduchi@gmail.com](mailto:waltduchi@gmail.com)
- WhatsApp: [+593 993 516 268](https://wa.me/593993516268)

---

*Proyecto desarrollado como parte del portafolio profesional.*
