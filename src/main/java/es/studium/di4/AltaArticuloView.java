package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaArticuloView extends JFrame {
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtStock;
	Datos datos = new Datos();

 
    public AltaArticuloView() {
    	
		datos.conectar();

        setTitle("Alta Artículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 30, 100, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 30, 200, 25);
        contentPane.add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 70, 100, 25);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 70, 200, 25);
        contentPane.add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(30, 110, 100, 25);
        contentPane.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(150, 110, 200, 25);
        contentPane.add(txtStock);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 180, 120, 30);
        contentPane.add(btnGuardar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 180, 120, 30);
        contentPane.add(btnVolver);

        // Action to go back to ArticulosView
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Action to save article
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 String descripcion = txtDescripcion.getText();
                String precio = txtPrecio.getText();
                String stock = txtStock.getText();

                try {
                    

                    if (datos.altaArticulo(descripcion, precio, stock)) {
                        JOptionPane.showMessageDialog(null, "Operación realizada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operación fallida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose(); // Cierra la ventana tras intentar guardar
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Introduce valores válidos para Precio y Stock", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
