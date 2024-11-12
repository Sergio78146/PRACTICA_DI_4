package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelacionTicketArticuloView extends JFrame {
    private JComboBox<String> choiceArticulos;
    private int idTicket;
    Datos datos = new Datos();

    public RelacionTicketArticuloView(int idTicket) {
        datos.conectar(); // Conectar a la base de datos
        this.idTicket = idTicket;

        setTitle("Relación Ticket-Artículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTicketID = new JLabel("ID Ticket: " + idTicket);
        lblTicketID.setBounds(30, 20, 200, 25);
        contentPane.add(lblTicketID);

        JLabel lblArticulos = new JLabel("Seleccione Artículo:");
        lblArticulos.setBounds(30, 70, 150, 25);
        contentPane.add(lblArticulos);

        // Obtener artículos desde la base de datos y llenar el JComboBox
        String[] articulos = datos.choiceArticulos();
        choiceArticulos = new JComboBox<>(articulos);
        choiceArticulos.setBounds(150, 70, 200, 25);
        contentPane.add(choiceArticulos);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(80, 200, 100, 30);
        contentPane.add(btnAgregar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 200, 100, 30);
        contentPane.add(btnVolver);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarRelacion();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });
    }

    private void agregarRelacion() {
    	String articulo = (String) choiceArticulos.getSelectedItem();
    	String primeraParte = articulo.split(" - ")[0];
    	
        if (datos.relacionTicketArticulo(idTicket, primeraParte)) {
            JOptionPane.showMessageDialog(this, "Operación realizada.");
            dispose(); // Cierra la ventana si la operación fue exitosa
        } else {
            JOptionPane.showMessageDialog(this, "Operación no realizada.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
