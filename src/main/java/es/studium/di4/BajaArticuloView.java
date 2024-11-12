package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BajaArticuloView extends JFrame {
    private JComboBox<String> choiceArticulos;
    Datos datos = new Datos(); // Instancia para manejar los datos

    public BajaArticuloView() {
        datos.conectar(); // Conectar a la base de datos o sistema de datos

        setTitle("Eliminar Artículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccioneArticulo = new JLabel("Seleccione Artículo a Eliminar:");
        lblSeleccioneArticulo.setBounds(30, 30, 200, 25);
        contentPane.add(lblSeleccioneArticulo);

        // Cargar artículos en el JComboBox para que el usuario seleccione uno
        choiceArticulos = new JComboBox<>(datos.choiceArticulos());
        choiceArticulos.setBounds(30, 70, 300, 25);
        contentPane.add(choiceArticulos);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(80, 150, 100, 30);
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 150, 100, 30);
        contentPane.add(btnVolver);

        // Acción para volver a la vista de artículos
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana de BajaArticuloView
            }
        });

        // Acción para eliminar el artículo seleccionado
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarArticuloSeleccionado();
            }
        });
    }

    private void eliminarArticuloSeleccionado() {
        // Obtener el artículo seleccionado
        String articuloSeleccionado = (String) choiceArticulos.getSelectedItem();
        
        if (articuloSeleccionado != null) {
            int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea eliminar el artículo seleccionado?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                // Extraer descripción, precio y stock del artículo seleccionado
                String[] parts = articuloSeleccionado.split(" - ");
                String descripcionArticulo = parts[0].trim();
                String precioArticulo = parts[1].trim();
                String stockArticulo = parts[2].trim();

                // Llamar al método de eliminación en Datos
                boolean eliminado = datos.eliminarArticulo(descripcionArticulo, precioArticulo, stockArticulo);

                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Artículo eliminado con éxito.");
                    choiceArticulos.removeItem(articuloSeleccionado); // Quitar de la lista
                } else {
                    JOptionPane.showMessageDialog(this, "Error: No se pudo eliminar el artículo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un artículo para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
