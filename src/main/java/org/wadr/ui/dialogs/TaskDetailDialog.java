package org.wadr.ui.dialogs;

import org.wadr.model.Task;
import org.wadr.utils.FormatterUtil;
import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal para la visualización detallada de una tarea.
 * Se utiliza para mostrar toda la información de un objeto Task, 
 * incluyendo ID, fecha de creación y descripción completa.
 */
public class TaskDetailDialog extends JDialog {
    
    /**
     * Constructor del diálogo.
     * * @param parent El Frame principal que actúa como dueño de este diálogo.
     * @param task   El objeto Task cuyos detalles se desean visualizar.
     */
    public TaskDetailDialog(Frame parent, Task task) {
        // 'true' indica que el diálogo es modal (bloquea la ventana principal)
        super(parent, "Detalles de Tarea", true);
        initComponents(task);
    }
    
    /**
     * Inicializa y organiza los componentes visuales del diálogo.
     * * @param task Tarea de la cual se extraerá la información formateada.
     */
    private void initComponents(Task task) {
        setLayout(new BorderLayout(10, 10));
        setSize(400, 300);
        // Centra el diálogo respecto a la ventana que lo invocó
        setLocationRelativeTo(getParent());
        
        // --- Panel Principal con margen ---
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // --- Área de Texto para Detalles ---
        // Se utiliza el método estático de FormatterUtil para obtener el texto estructurado
        JTextArea detailsArea = new JTextArea(FormatterUtil.formatTaskDetails(task));
        detailsArea.setEditable(false); // Evita que el usuario modifique el texto
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fuente tipo consola para alineación
        detailsArea.setLineWrap(true);   // Ajuste automático de línea
        detailsArea.setWrapStyleWord(true);
        
        // Implementación de Scroll por si la descripción es muy extensa
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Información de la Tarea"));
        
        // --- Panel de Botones (Acciones) ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Cerrar");
        
        // dispose() cierra la ventana y libera los recursos de memoria
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        
        // Montaje final de los componentes en el panel principal
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
}