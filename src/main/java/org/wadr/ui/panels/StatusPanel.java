package org.wadr.ui.panels;

import org.wadr.service.TaskManager;
import javax.swing.*;
import java.awt.*;

/**
 * Panel de estado ubicado en la parte inferior de la aplicación.
 * Muestra métricas en tiempo real sobre el total de tareas, tareas pendientes 
 * y tareas completadas, utilizando colores distintivos para cada categoría.
 */
public class StatusPanel extends JPanel {
    private JLabel totalLabel;
    private JLabel pendingLabel;
    private JLabel completedLabel;
    
    /**
     * Constructor del panel de estado.
     * Inicializa componentes, configura el layout y realiza la primera carga de datos.
     */
    public StatusPanel() {
        initComponents();
        setupLayout();
        updateStats(); // Carga inicial de estadísticas
    }
    
    /**
     * Instancia las etiquetas y define los estilos de fuente.
     */
    private void initComponents() {
        totalLabel = new JLabel();
        pendingLabel = new JLabel();
        completedLabel = new JLabel();
        
        // Estilo Segoe UI para una apariencia moderna
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        pendingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        completedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    }
    
    /**
     * Organiza el panel en una rejilla de 1 fila y 3 columnas.
     * Cada columna representa una métrica diferente.
     */
    private void setupLayout() {
        // GridLayout(filas, columnas, hgap, vgap)
        setLayout(new GridLayout(1, 3, 10, 0));
        setBorder(BorderFactory.createTitledBorder("Estadísticas"));
        
        // Agregar sub-paneles con sus colores temáticos
        add(createStatPanel("Total", totalLabel, Color.BLUE));
        add(createStatPanel("Pendientes", pendingLabel, Color.ORANGE));
        add(createStatPanel("Completadas", completedLabel, new Color(0, 128, 0))); // Verde oscuro
    }
    
    /**
     * Método auxiliar para crear una tarjeta de estadística individual.
     * * @param title      Texto descriptivo de la métrica.
     * @param valueLabel Referencia a la etiqueta que mostrará el número.
     * @param color      Color representativo para el valor numérico.
     * @return Un JPanel configurado con título y valor.
     */
    private JPanel createStatPanel(String title, JLabel valueLabel, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        titleLabel.setForeground(Color.GRAY);
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setForeground(color);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Sincroniza las etiquetas visuales con los datos actuales del TaskManager.
     * Este método debe llamarse cada vez que se agregue, elimine o modifique una tarea.
     */
    public void updateStats() {
        // Obtiene la instancia única del gestor de tareas (Singleton)
        TaskManager manager = TaskManager.getInstance();
        
        totalLabel.setText(String.valueOf(manager.getTotalTasks()));
        pendingLabel.setText(String.valueOf(manager.getPendingTasks().size()));
        completedLabel.setText(String.valueOf(manager.getCompletedCount()));
    }
}