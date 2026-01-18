package org.wadr.utils;

import org.wadr.model.Task;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Clase utilitaria para el formateo de datos en la aplicación.
 * Proporciona métodos estáticos para transformar objetos de dominio y fechas
 * en representaciones textuales amigables para el usuario.
 */
public class FormatterUtil {
    
    /** * Formato estándar de fecha y hora: día/mes/año hora:minuto (ej. 18/01/2026 12:30).
     */
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /**
     * Convierte un objeto LocalDateTime a una cadena de texto formateada.
     * * @param dateTime La fecha y hora a formatear.
     * @return Representación en texto o "N/A" si la fecha es nula.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "N/A";
        return dateTime.format(DATE_FORMATTER);
    }
    
    /**
     * Genera un bloque de texto estructurado con toda la información de una tarea.
     * Ideal para ser mostrado en diálogos de detalles o logs.
     * * @param task El objeto Task a procesar.
     * @return Cadena multilínea con los atributos de la tarea.
     */
    public static String formatTaskDetails(Task task) {
        if (task == null) return "Tarea no encontrada";
        
        // Se utiliza StringBuilder para una concatenación eficiente de strings
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(task.getId()).append("\n");
        sb.append("Título: ").append(task.getTitle()).append("\n");
        sb.append("Descripción: ").append(task.getDescription()).append("\n");
        sb.append("Estado: ").append(task.isCompleted() ? "Completada" : "Pendiente").append("\n");
        sb.append("Creada: ").append(formatDateTime(task.getCreatedAt()));
        
        return sb.toString();
    }
    
    /**
     * Recorta un texto si excede la longitud máxima permitida, añadiendo puntos suspensivos.
     * Útil para vistas de lista donde el espacio es limitado.
     * * @param text El texto original.
     * @param maxLength Límite de caracteres permitido.
     * @return Texto original o texto truncado terminado en "...".
     */
    public static String truncateText(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) return text;
        
        // Se restan 3 a la longitud para compensar los puntos suspensivos
        return text.substring(0, maxLength - 3) + "...";
    }
}