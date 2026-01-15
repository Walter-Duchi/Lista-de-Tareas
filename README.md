# 📝 Gestor de Tareas - Java

¡Hola! 👋 Bienvenido al **Gestor de Tareas**, una aplicación super fácil de usar para organizar tus tareas diarias. ¡Es perfecta para estudiantes, profesionales, o cualquier persona que quiera mantenerse organizada!

---

## ✨ ¿Qué es este proyecto?

Imagina que tienes una **lista de cosas por hacer** en tu cabeza: "estudiar para el examen", "comprar leche", "llamar a mamá". ¡Esta aplicación te ayuda a anotarlas todas para que no se te olvide nada!

### 🎯 ¿Qué hace esta aplicación?
- ✅ **Agregar nuevas tareas** (con título y descripción)
- ✅ **Marcar tareas como completadas** (¡tacharlas cuando las termines!)
- ✅ **Ver detalles de cada tarea**
- ✅ **Eliminar tareas** que ya no necesites
- ✅ **Ver estadísticas** (cuántas tareas tienes, cuántas completadas)
- ✅ **Interfaz bonita y fácil de usar** (se ve como los programas de tu computadora)

---

## 🚀 ¿Cómo empiezo a usarla?

### Para usuarios normales (quiero usar el programa):
1. **Descarga el programa** (busca el archivo .jar)
2. **Haz doble clic** en el archivo (como cualquier programa)
3. ¡Listo! Ya puedes empezar a agregar tus tareas

### Para desarrolladores (quiero ver cómo está hecho):
Si eres curioso y quieres ver cómo funciona por dentro:

#### Requisitos:
- **Java 11** o superior (puedes descargarlo gratis)
- **Maven** (una herramienta para construir programas en Java)

#### Pasos:
1. **Descarga o clona este proyecto**
2. **Abre una terminal** en la carpeta del proyecto
3. **Ejecuta:** `mvn clean compile`
4. **Luego ejecuta:** `mvn exec:java -Dexec.mainClass="org.wadr.App"`
5. ¡La aplicación debería aparecer! 🎉

---

## 📂 ¿Cómo está organizado el proyecto?

El proyecto está dividido en carpetas, como los cajones de un escritorio:

```
GestorDeTareas/
├── 📁 src/main/java/org/wadr/
│   ├── 📄 App.java              # ⚡ ¡Aquí empieza todo! El motor principal
│   ├── 📁 model/                # 📦 Aquí están las "cosas" (las tareas)
│   │   └── Task.java           # 📝 La plantilla de una tarea
│   ├── 📁 service/              # 🛠️ El cerebro de la aplicación
│   │   └── TaskManager.java    # 🧠 Maneja todas las tareas
│   ├── 📁 ui/                   # 🎨 Todo lo que se ve en pantalla
│   │   ├── MainFrame.java      # 🪟 La ventana principal
│   │   ├── 📁 components/      # 🔧 Piezas pequeñas de la interfaz
│   │   ├── 📁 dialogs/         # 🪟 Ventanas emergentes
│   │   └── 📁 panels/          # 🧩 Secciones de la ventana
│   └── 📁 utils/                # 🧰 Herramientas útiles
│       └── FormatterUtil.java  # ✨ Da formato bonito a las fechas
└── 📄 pom.xml                   # 📦 Instrucciones para construir el proyecto
```

---

## 🎮 ¿Cómo uso la aplicación?

Es tan fácil como 1-2-3:

### 1. **Agregar una nueva tarea**
   - Haz clic en el botón **"＋ Nueva Tarea"**
   - Escribe un título (ej: "Comprar pan")
   - Agrega una descripción si quieres (ej: "En la panadería de la esquina")
   - ¡Listo! Aparecerá en tu lista

### 2. **Marcar una tarea como completada**
   - Selecciona una tarea de la lista
   - Haz clic en **"✓ Completar"**
   - ¡La tarea se marcará con un check! ✅

### 3. **Ver detalles de una tarea**
   - Selecciona una tarea
   - Haz clic en **"🔍 Detalles"**
   - O simplemente **haz doble clic** en la tarea
   - ¡Verás toda la información!

### 4. **Eliminar una tarea**
   - Selecciona la tarea que quieres eliminar
   - Haz clic en **"✗ Eliminar"**
   - Confirma que quieres eliminarla
   - ¡Desaparecerá de tu lista!

---

## 🤝 ¡Quiero ayudar a mejorar el proyecto!

¡Me encantaría que colaboraras! 🎉

### ¿Cómo puedo ayudar?
1. **Reportando errores** 🐛
   - ¿Encontraste algo que no funciona? ¡Cuéntame!
   - Ve a la sección de "Issues" y crea uno nuevo

2. **Sugiriendo mejoras** 💡
   - ¿Tienes una idea para hacerlo mejor? ¡Compártela!
   - ¿Qué función te gustaría que tuviera?

3. **Mejorando el código** 🔧
   - Si sabes programar en Java, puedes ayudar directamente

### Pasos para colaborar en el código:
1. **Haz un "fork"** (copia) de este proyecto
2. **Crea una rama** con tu mejora: `git checkout -b mi-mejora`
3. **Haz tus cambios** y prueba que funcionen
4. **Envía un "Pull Request"** (solicitud de cambios)
5. ¡Revisaré tu contribución con gusto! ❤️

---

## 📝 Algunas ideas para empezar a colaborar

### Para principiantes:
- ✨ **Mejorar los mensajes** de la aplicación
- 🎨 **Cambiar colores** de la interfaz
- 🔤 **Corregir errores de ortografía**

### Para intermedios:
- 💾 **Agregar guardado automático** (que las tareas no se pierdan)
- 🔍 **Agregar búsqueda** (encontrar tareas rápidamente)
- 📱 **Hacer la interfaz responsive** (que se vea bien en cualquier pantalla)

### Para avanzados:
- 🌐 **Agregar sincronización en la nube**
- 📊 **Agregar gráficos de progreso**
- 🤖 **Agregar recordatorios automáticos**

---

## ❓ Preguntas frecuentes

### ¿Necesito saber programar para usar la aplicación?
¡**NO!** La aplicación está hecha para que cualquiera pueda usarla, sin importar si sabes programar o no.

### ¿La aplicación es gratis?
¡**SÍ!** Es completamente gratuita y de código abierto.

### ¿Guarda mis tareas si cierro la aplicación?
Actualmente no, pero ¡esa es una gran idea para implementar! ¿Quieres ayudar a agregar esta función?

### ¿Funciona en Mac/Windows/Linux?
¡Sí! Funciona en cualquier computadora que tenga Java instalado.

---

## 📞 ¿Necesitas ayuda o tienes preguntas?

- 📧 **Puedes abrir un "Issue"** en GitHub
- 🤔 **Pregunta en la sección de discusiones**
- ⭐ **¡Dale una estrella al proyecto si te gusta!**

---

## 🙏 Agradecimientos

¡Gracias por interesarte en este proyecto! Cada persona que lo usa, prueba, sugiere cambios o colabora hace que esta aplicación sea mejor día a día.

**Recuerda:** Este proyecto fue creado con mucho cariño para ayudar a las personas a organizarse mejor. ¡Tú también puedes ser parte de esta misión!

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible para que todos lo usen, modifiquen y compartan libremente. ¡El conocimiento debe ser libre!
