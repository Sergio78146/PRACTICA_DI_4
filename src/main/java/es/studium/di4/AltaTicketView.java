package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AltaTicketView extends JFrame {
    private JTextField txtFechaTicket;
    private JTextField txtTotalPrecio;
    Datos datos = new Datos();

    public AltaTicketView() {
        datos.conectar(); // Conectar a la base de datos

        setTitle("Alta de Tickets");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblFechaTicket = new JLabel("Fecha de Ticket:");
        lblFechaTicket.setBounds(30, 30, 150, 25);
        contentPane.add(lblFechaTicket);

        txtFechaTicket = new JTextField();
        txtFechaTicket.setBounds(150, 30, 200, 25);
        contentPane.add(txtFechaTicket);

        JLabel lblTotalPrecio = new JLabel("Total Precio Ticket:");
        lblTotalPrecio.setBounds(30, 70, 150, 25);
        contentPane.add(lblTotalPrecio);

        txtTotalPrecio = new JTextField();
        txtTotalPrecio.setBounds(150, 70, 200, 25);
        contentPane.add(txtTotalPrecio);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(80, 150, 100, 30);
        contentPane.add(btnAceptar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 150, 100, 30);
        contentPane.add(btnVolver);

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                altaTicket();
            }
        });

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana de AltaTicketView
            }
        });
    }

    private void altaTicket() {
        String fechaTicket = txtFechaTicket.getText();
        String totalPrecioTicket = txtTotalPrecio.getText();

        // Llamada al método de alta de ticket en la clase Datos
        int idTicket = datos.altaTicket(fechaTicket, totalPrecioTicket);

        if (idTicket != -1) {
            new RelacionTicketArticuloView(idTicket).setVisible(true); // Abre la ventana para relacionar el ticket con un artículo
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el ticket.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
