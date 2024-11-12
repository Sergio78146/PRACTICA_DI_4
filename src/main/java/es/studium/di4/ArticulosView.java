package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArticulosView extends JFrame {
    private JPanel contentPane;

    public ArticulosView() {
        setTitle("Gestión de Artículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Alta Artículo button
        JButton btnAlta = new JButton("Alta Artículo");
        btnAlta.setBounds(180, 50, 140, 25);
        contentPane.add(btnAlta);

        // Consulta Artículos button
        JButton btnConsulta = new JButton("Consulta Artículos");
        btnConsulta.setBounds(180, 100, 140, 25);
        contentPane.add(btnConsulta);

        // Modificar Artículo button
        JButton btnModificacion = new JButton("Modificar Artículo");
        btnModificacion.setBounds(180, 150, 140, 25);
        contentPane.add(btnModificacion);

        // Baja Artículo button
        JButton btnBaja = new JButton("Eliminar Artículo");
        btnBaja.setBounds(180, 200, 140, 25);
        contentPane.add(btnBaja);

        // Volver button
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 300, 140, 25);
        contentPane.add(btnVolver);

        // Action for Alta Artículo button
        btnAlta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AltaArticuloView altaArticuloView = new AltaArticuloView();
                altaArticuloView.setVisible(true); // Opens the AltaArticuloView window
            }
        });

        // Action for Consulta Artículos button
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsultaArticuloView consultaArticuloView = new ConsultaArticuloView();
                consultaArticuloView.setVisible(true); // Opens the ConsultaArticuloView window
            }
        });

        // Action for Modificar Artículo button
        btnModificacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModificarArticuloView modificarArticuloView = new ModificarArticuloView();
                modificarArticuloView.setVisible(true); // Opens the ModificarArticuloView window
            }
        });

        // Action for Baja Artículo button
        btnBaja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BajaArticuloView bajaArticuloView = new BajaArticuloView();
                bajaArticuloView.setVisible(true); // Opens the BajaArticuloView window
            }
        });

        // Action for Volver button
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the ArticulosView window
            }
        });
    }
}
