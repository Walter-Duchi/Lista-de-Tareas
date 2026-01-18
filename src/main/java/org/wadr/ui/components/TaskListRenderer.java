package org.wadr.ui.components;

import org.wadr.model.Task;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Renderizador personalizado para los elementos de la lista de tareas.
 * Hereda de JPanel para permitir una disposición compleja de múltiples etiquetas
 * (título, estado y fecha) dentro de una sola celda de la JList.
 */
public class TaskListRenderer extends JPanel implements ListCellRenderer<Task> {
    private JLabel titleLabel;
    private JLabel statusLabel;
    private JLabel dateLabel;
    
    /**
     * Constructor del renderizador.
     * Configura el diseño visual (Layout) y los estilos de fuente iniciales.
     */
    public TaskListRenderer() {
        // Espaciado interno de 10px horizontal y 5px vertical
        setLayout(new BorderLayout(10, 5));
        setBorder(new EmptyBorder(8, 10, 8, 10));
        
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Negrita para el título
        
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dateLabel.setForeground(Color.GRAY);
        
        // Panel interno para organizar el título arriba y el estado debajo
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false); // Permite que se vea el fondo del panel principal
        infoPanel.add(titleLabel, BorderLayout.NORTH);
        infoPanel.add(statusLabel, BorderLayout.CENTER);
        
        add(infoPanel, BorderLayout.CENTER);
        add(dateLabel, BorderLayout.EAST);
    }
    
    /**
     * Configura y retorna el componente que se dibujará en la celda de la lista.
     * Este método se ejecuta cada vez que la lista necesita repintar un elemento.
     *
     * @param list La JList que estamos renderizando.
     * @param task El objeto Task asociado a la celda actual.
     * @param index El índice de la celda.
     * @param isSelected Indica si el usuario ha seleccionado la celda.
     * @param cellHasFocus Indica si la celda tiene el foco del teclado.
     * @return El propio panel (this) configurado con los datos de la tarea.
     */
    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task task, 
                                                   int index, boolean isSelected, boolean cellHasFocus) {
        
        // 1. Cargar datos básicos
        titleLabel.setText(task.getTitle());
        dateLabel.setText(org.wadr.utils.FormatterUtil.formatDateTime(task.getCreatedAt()));
        
        // 2. Aplicar lógica visual según el estado de la tarea (Completada vs Pendiente)
        if (task.isCompleted()) {
            statusLabel.setText("✓ Completada");
            statusLabel.setForeground(new Color(0, 128, 0)); // Verde oscuro
            titleLabel.setForeground(Color.GRAY);
        } else {
            statusLabel.setText("⏳ Pendiente");
            statusLabel.setForeground(new Color(200, 100, 0)); // Naranja/Marrón
            titleLabel.setForeground(Color.BLACK);
        }
        
        // 3. Gestionar colores de fondo y selección
        if (isSelected) {
            setBackground(new Color(220, 235, 255)); // Azul claro de selección
        } else {
            // Efecto de filas cebra (alternar colores para mejorar legibilidad)
            setBackground(index % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
        }
        
        // 4. Gestionar el borde de foco
        if (cellHasFocus) {
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 255), 1),
                new EmptyBorder(7, 9, 7, 9) // Ajuste para mantener el padding con el borde
            ));
        } else {
            setBorder(new EmptyBorder(8, 10, 8, 10));
        }
        
        setOpaque(true); // Necesario para que el color de fondo sea visible
        return this;
    }
}