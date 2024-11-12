package es.studium.di4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketsView extends JFrame {
    private JPanel contentPane;

    public TicketsView() {
        setTitle("Gestión de Tickets");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Alta Ticket button
        JButton btnAlta = new JButton("Alta Ticket");
        btnAlta.setBounds(180, 50, 140, 25);
        contentPane.add(btnAlta);

        // Consulta Tickets button
        JButton btnConsulta = new JButton("Consulta Tickets");
        btnConsulta.setBounds(180, 100, 140, 25);
        contentPane.add(btnConsulta);

        // Volver button
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(180, 300, 140, 25);
        contentPane.add(btnVolver);

        // Action for Alta Ticket button
        btnAlta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AltaTicketView altaTicketView = new AltaTicketView();
                altaTicketView.setVisible(true); // Opens the AltaTicketView window
            }
        });

        // Action for Consulta Tickets button
        btnConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsultaTicketView consultaTicketView = new ConsultaTicketView();
                consultaTicketView.setVisible(true); // Opens the ConsultaTicketView window
            }
        });

        // Action for Volver button
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the TicketsView window
            }
        });
    }
}
