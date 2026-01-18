package org.wadr.ui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de control que agrupa las acciones principales de la aplicación.
 * Proporciona botones para la gestión de tareas (crear, completar, eliminar, ver detalles y refrescar).
 * Se ubica usualmente en la parte superior (North) de la ventana principal.
 */
public class ControlPanel extends JPanel {
    private JButton addButton;
    private JButton completeButton;
    private JButton deleteButton;
    private JButton detailsButton;
    private JButton refreshButton;
    
    /**
     * Constructor del panel.
     * Inicializa los componentes y establece la disposición visual.
     */
    public ControlPanel() {
        initComponents();
        setupLayout();
    }
    
    /**
     * Instancia los botones y configura sus textos de ayuda (tooltips).
     * El uso de caracteres Unicode (＋, ✓, ✗) mejora la identificación visual rápida.
     */
    private void initComponents() {
        addButton = new JButton("＋ Nueva Tarea");
        addButton.setToolTipText("Agregar nueva tarea");
        
        completeButton = new JButton("✓ Completar");
        completeButton.setToolTipText("Marcar tarea como completada");
        
        deleteButton = new JButton("✗ Eliminar");
        deleteButton.setToolTipText("Eliminar tarea seleccionada");
        
        detailsButton = new JButton("🔍 Detalles");
        detailsButton.setToolTipText("Ver detalles de la tarea");
        
        refreshButton = new JButton("↻ Actualizar");
        refreshButton.setToolTipText("Actualizar lista de tareas");
    }
    
    /**
     * Configura el diseño del panel utilizando FlowLayout.
     * Se aplica un borde con título para delimitar visualmente la sección de acciones.
     */
    private void setupLayout() {
        // Alineación a la izquierda con separación de 10px
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Acciones"));
        
        add(addButton);
        add(completeButton);
        add(deleteButton);
        add(detailsButton);
        add(refreshButton);
    }
    
    // --- Getters ---
    /**
     * Los getters permiten que el MainFrame acceda a los botones para 
     * asignarles los escuchadores (ActionListeners) correspondientes.
     */
    public JButton getAddButton() { return addButton; }
    public JButton getCompleteButton() { return completeButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getDetailsButton() { return detailsButton; }
    public JButton getRefreshButton() { return refreshButton; }
}