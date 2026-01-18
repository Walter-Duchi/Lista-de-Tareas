package org.wadr.service;

import org.wadr.model.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase de servicio encargada de la gestión y persistencia en memoria de las tareas.
 * Implementa el patrón Singleton para asegurar que solo exista una instancia
 * del gestor de tareas en toda la aplicación.
 */
public class TaskManager {
    private List<Task> tasks;
    private static TaskManager instance;
    
    /**
     * Constructor privado para evitar instanciación externa.
     * Inicializa la lista de tareas y carga datos de prueba.
     */
    private TaskManager() {
        tasks = new ArrayList<>();
        initializeSampleTasks();
    }
    
    /**
     * Obtiene la instancia única de TaskManager.
     * Utiliza 'synchronized' para garantizar la seguridad entre hilos (thread-safe).
     * * @return La instancia única de TaskManager.
     */
    public static synchronized TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }
    
    /**
     * Carga tareas iniciales para propósitos de prueba y demostración.
     * Incluye pausas breves para asegurar marcas de tiempo diferenciadas.
     */
    private void initializeSampleTasks() {
        addTask(new Task("Configurar proyecto", "Configurar estructura del proyecto Java"));
        
        try {
            Thread.sleep(10); // Pausa para evitar colisiones de timestamp
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        addTask(new Task("Diseñar interfaz", "Crear interfaces gráficas con JFrame"));
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        addTask(new Task("Implementar lógica", "Desarrollar funcionalidad principal"));
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        addTask(new Task("Probar aplicación", "Realizar pruebas de funcionalidad"));
    }
    
    /**
     * Agrega una nueva tarea al listado general.
     * @param task Objeto Task a ser almacenado.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    /**
     * Elimina una tarea mediante su identificador único.
     * * @param taskId El ID de la tarea a eliminar.
     * @return true si la tarea fue encontrada y eliminada, false de lo contrario.
     */
    public boolean removeTask(String taskId) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId().equals(taskId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Recupera una copia de todas las tareas registradas.
     * @return Una nueva lista que contiene todas las tareas.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * Filtra y retorna únicamente las tareas que no han sido completadas.
     * @return Lista de tareas pendientes.
     */
    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        return pending;
    }
    
    /**
     * Filtra y retorna únicamente las tareas marcadas como completadas.
     * @return Lista de tareas terminadas.
     */
    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed.add(task);
            }
        }
        return completed;
    }
    
    /**
     * Busca una tarea específica por su ID.
     * @param id Identificador único de la tarea.
     * @return El objeto Task encontrado o null si no existe.
     */
    public Task getTaskById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }
    
    /**
     * Actualiza el estado de una tarea a 'completada'.
     * @param taskId ID de la tarea a marcar.
     */
    public void markTaskCompleted(String taskId) {
        Task task = getTaskById(taskId);
        if (task != null) {
            task.setCompleted(true);
        }
    }
    
    /**
     * @return Cantidad total de tareas en la lista.
     */
    public int getTotalTasks() {
        return tasks.size();
    }
    
    /**
     * Calcula cuántas tareas han sido finalizadas hasta el momento.
     * @return Conteo de tareas completadas.
     */
    public int getCompletedCount() {
        int count = 0;
        for (Task task : tasks) {
            if (task.isCompleted()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Elimina permanentemente todas las tareas de la memoria.
     */
    public void clearAllTasks() {
        tasks.clear();
    }
}