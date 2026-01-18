package org.wadr.ui;

import org.wadr.model.Task;
import org.wadr.service.TaskManager;
import org.wadr.ui.components.TaskListRenderer;
import org.wadr.ui.dialogs.TaskDetailDialog;
import org.wadr.ui.panels.ControlPanel;
import org.wadr.ui.panels.StatusPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Ventana principal de la aplicación.
 * Define la estructura visual (Layout), la barra de menús y la gestión
 * de eventos de usuario para el control de tareas.
 */
public class MainFrame extends JFrame {
    private TaskManager taskManager;
    private DefaultListModel<Task> listModel;
    private JList<Task> taskList;
    private ControlPanel controlPanel;
    private StatusPanel statusPanel;
    
    /**
     * Constructor que inicializa el gestor de datos y configura la interfaz.
     */
    public MainFrame() {
        taskManager = TaskManager.getInstance();
        initComponents();
        setupFrame();
        setupListeners();
        loadTasks();
    }
    
    /**
     * Instancia y organiza los componentes visuales básicos.
     * Utiliza un BorderLayout para dividir la aplicación en zonas (Control, Lista, Estado).
     */
    private void initComponents() {
        // Configuración del modelo de datos para la JList
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        
        // Se asigna el renderizador personalizado para cambiar la apariencia de las celdas
        taskList.setCellRenderer(new TaskListRenderer());
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        controlPanel = new ControlPanel();
        statusPanel = new StatusPanel();
        
        setLayout(new BorderLayout(10, 10));
        
        // Implementación de scroll para la lista de tareas
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Tareas"));
        
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Define las propiedades básicas del JFrame (tamaño, cierre, título).
     */
    private void setupFrame() {
        setTitle("Gestor de Tareas - Proyecto Base Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Centra la ventana en pantalla
        
        setupMenuBar();
    }
    
    /**
     * Configura la barra de menú superior, sus mnemónicos (atajos Alt) 
     * y aceleradores (Ctrl+Key).
     */
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // --- Menú Archivo ---
        JMenu fileMenu = new JMenu("Archivo");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        
        JMenuItem newItem = new JMenuItem("Nueva Tarea");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        JMenuItem clearAllItem = new JMenuItem("Limpiar todas las tareas");
        clearAllItem.addActionListener(e -> clearAllTasks());
        
        JMenuItem exitItem = new JMenuItem("Salir");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        fileMenu.add(newItem);
        fileMenu.add(clearAllItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // --- Menú Tareas ---
        JMenu taskMenu = new JMenu("Tareas");
        taskMenu.setMnemonic(KeyEvent.VK_T);
        
        JMenuItem completeItem = new JMenuItem("Marcar como completada");
        completeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        JMenuItem deleteItem = new JMenuItem("Eliminar tarea");
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        
        taskMenu.add(completeItem);
        taskMenu.add(deleteItem);
        
        // --- Menú Ayuda ---
        JMenu helpMenu = new JMenu("Ayuda");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        
        JMenuItem aboutItem = new JMenuItem("Acerca de");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(taskMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
        
        // Listeners del menú asociados a métodos específicos
        newItem.addActionListener(e -> showAddTaskDialog());
        exitItem.addActionListener(e -> System.exit(0));
        completeItem.addActionListener(e -> completeSelectedTask());
        deleteItem.addActionListener(e -> deleteSelectedTask());
        aboutItem.addActionListener(e -> showAboutDialog());
    }
    
    /**
     * Conecta las acciones de los botones en los paneles con la lógica del MainFrame.
     */
    private void setupListeners() {
        controlPanel.getAddButton().addActionListener(e -> showAddTaskDialog());
        controlPanel.getCompleteButton().addActionListener(e -> completeSelectedTask());
        controlPanel.getDeleteButton().addActionListener(e -> deleteSelectedTask());
        controlPanel.getDetailsButton().addActionListener(e -> showTaskDetails());
        controlPanel.getRefreshButton().addActionListener(e -> refreshTaskList());
        
        // Manejo de eventos de ratón para facilitar la navegación
        taskList.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Doble clic para ver detalle
                    showTaskDetails();
                }
            }
        });
    }
    
    /**
     * Sincroniza los datos del TaskManager con el listModel de la interfaz gráfica.
     */
    private void loadTasks() {
        listModel.clear();
        for (Task task : taskManager.getAllTasks()) {
            listModel.addElement(task);
        }
        statusPanel.updateStats(); // Actualiza contadores en el pie de página
    }
    
    /**
     * Elimina todas las tareas previa confirmación del usuario mediante un diálogo.
     */
    private void clearAllTasks() {
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea eliminar todas las tareas?",
            "Confirmar Eliminación Total",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            taskManager.clearAllTasks();
            loadTasks();
            JOptionPane.showMessageDialog(this, "Todas las tareas han sido eliminadas", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Muestra un formulario modal para la creación de una nueva tarea.
     * Valida que el campo título no esté vacío antes de guardar.
     */
    private void showAddTaskDialog() {
        JTextField titleField = new JTextField(20);
        JTextArea descArea = new JTextArea(5, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Título:"));
        panel.add(titleField);
        panel.add(new JLabel("Descripción:"));
        panel.add(new JScrollPane(descArea));
        
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        container.add(panel, BorderLayout.CENTER);
        
        int result = JOptionPane.showConfirmDialog(
            this,
            container,
            "Nueva Tarea",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            String description = descArea.getText().trim();
            
            if (!title.isEmpty()) {
                Task task = new Task(title, description);
                taskManager.addTask(task);
                loadTasks();
            } else {
                JOptionPane.showMessageDialog(this, "El título es requerido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Marca la tarea seleccionada en la lista como completada.
     */
    private void completeSelectedTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            if (!selected.isCompleted()) {
                taskManager.markTaskCompleted(selected.getId());
                loadTasks();
            } else {
                JOptionPane.showMessageDialog(this, "La tarea ya está completada", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una tarea primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Elimina la tarea seleccionada tras confirmar con el usuario.
     */
    private void deleteSelectedTask() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar la tarea '" + selected.getTitle() + "'?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                if (taskManager.removeTask(selected.getId())) {
                    loadTasks();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la tarea", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una tarea primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Abre un diálogo de detalle para la tarea seleccionada.
     */
    private void showTaskDetails() {
        Task selected = taskList.getSelectedValue();
        if (selected != null) {
            TaskDetailDialog dialog = new TaskDetailDialog(this, selected);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una tarea primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Fuerza la recarga de los datos en la UI.
     */
    private void refreshTaskList() {
        loadTasks();
        JOptionPane.showMessageDialog(this, "Lista actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Muestra información sobre el software y sus versiones.
     */
    private void showAboutDialog() {
        String aboutText = "<html>" +
            "<h2>Gestor de Tareas v1.1</h2>" +
            "<p>Proyecto Java para gestión de tareas</p>" +
            "</html>";
        
        JOptionPane.showMessageDialog(this, aboutText, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Ejecuta la visibilidad del Frame de forma segura en el Event Dispatch Thread de Swing.
     */
    public void showFrame() {
        SwingUtilities.invokeLater(() -> {
            try {
                setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error crítico: " + e.getMessage());
            }
        });
    }
}