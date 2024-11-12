package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificarArticuloView extends JFrame {
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JComboBox<String> choiceArticulos;
    Datos datos = new Datos();

    public ModificarArticuloView() {
        datos.conectar();

        setTitle("Modificar Artículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 350);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccioneArticulo = new JLabel("Seleccione Artículo:");
        lblSeleccioneArticulo.setBounds(30, 20, 150, 25);
        contentPane.add(lblSeleccioneArticulo);

        choiceArticulos = new JComboBox<>(datos.choiceArticulos()); // Cargar los artículos en el JComboBox
        choiceArticulos.setBounds(150, 20, 200, 25);
        contentPane.add(choiceArticulos);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 70, 100, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 70, 200, 25);
        contentPane.add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 110, 100, 25);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 110, 200, 25);
        contentPane.add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(30, 150, 100, 25);
        contentPane.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(150, 150, 200, 25);
        contentPane.add(txtStock);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBounds(50, 220, 150, 30);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(220, 220, 120, 30);
        contentPane.add(btnVolver);

        // Cargar los datos del artículo seleccionado cuando cambie la selección
        choiceArticulos.addActionListener(e -> cargarDatosArticulo());

        // Acción para volver a la vista de artículos
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Acción para guardar los cambios en el artículo seleccionado
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCambiosArticulo();
            }
        });

        // Cargar los datos del primer artículo en los campos al inicio
        if (choiceArticulos.getItemCount() > 0) {
            cargarDatosArticulo();
        }
    }

    private void cargarDatosArticulo() {
        // Obtener el artículo seleccionado
        String articuloSeleccionado = (String) choiceArticulos.getSelectedItem();
        if (articuloSeleccionado != null) {
            String[] parts = articuloSeleccionado.split(" - ");
            if (parts.length == 3) {
                txtDescripcion.setText(parts[0].trim());
                txtPrecio.setText(parts[1].trim());
                txtStock.setText(parts[2].trim());
            }
        }
    }

    private void guardarCambiosArticulo() {
        // Obtener los valores actuales de los campos
        String descripcionAnterior = (String) choiceArticulos.getSelectedItem();
        String descripcion = txtDescripcion.getText().trim();
        String precio = txtPrecio.getText().trim();
        String stock = txtStock.getText().trim();

        if (descripcionAnterior != null && !descripcion.isEmpty() && !precio.isEmpty() && !stock.isEmpty()) {
            try {
                // Intentar parsear el precio y el stock para verificar que son válidos
                double precioDouble = Double.parseDouble(precio);
                int stockInt = Integer.parseInt(stock);

                // Obtener valores originales para encontrar el artículo en la base de datos
                String[] parts = descripcionAnterior.split(" - ");
                String descripcionArticuloAnterior = parts[0].trim();
                String precioArticuloAnterior = parts[1].trim();
                String stockArticuloAnterior = parts[2].trim();

                // Llamar a la función de modificación en Datos
                datos.modificacionArticulos(descripcionArticuloAnterior, precioArticuloAnterior, stockArticuloAnterior, descripcion, String.valueOf(precioDouble), String.valueOf(stockInt));
                JOptionPane.showMessageDialog(this, "Artículo modificado con éxito.");
                dispose(); // Cerrar la ventana tras guardar (opcional)
            } catch (NumberFormatException e) {
                // Mensaje de error en caso de formato incorrecto en precio o stock
                JOptionPane.showMessageDialog(this, "Operación fallida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Mensaje de error si algún campo está vacío
            JOptionPane.showMessageDialog(this, "Operación fallida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
