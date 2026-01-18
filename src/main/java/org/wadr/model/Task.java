package org.wadr.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa una tarea dentro del sistema.
 * Esta clase maneja la información básica de las actividades, incluyendo
 * su estado de finalización y marca de tiempo de creación.
 */
public class Task {
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean completed;
    
    /**
     * Constructor por defecto.
     * Inicializa la fecha de creación al momento actual y genera un ID único.
     */
    public Task() {
        this.createdAt = LocalDateTime.now();
        this.id = generateId();
    }
    
    /**
     * Constructor con parámetros básicos.
     * * @param title El título o nombre de la tarea.
     * @param description Detalle extenso de lo que se debe realizar.
     */
    public Task(String title, String description) {
        this(); // Llama al constructor por defecto para setear el ID y la fecha
        this.title = title;
        this.description = description;
    }
    
    /**
     * Genera un identificador único basado en UUID.
     * Se toma solo una parte del UUID para mantener el ID legible.
     * * @return Una cadena con el prefijo TASK- seguido de 8 caracteres aleatorios.
     */
    private String generateId() {
        return "TASK-" + UUID.randomUUID().toString().substring(0, 8);
    }
    
    // --- Getters y Setters ---

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    /**
     * Representación textual de la tarea.
     * Muestra el título y un símbolo de verificación si está completada.
     */
    @Override
    public String toString() {
        return title + (completed ? " ✓" : "");
    }
    
    /**
     * Compara dos tareas basándose únicamente en su ID único.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return id != null && id.equals(task.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}