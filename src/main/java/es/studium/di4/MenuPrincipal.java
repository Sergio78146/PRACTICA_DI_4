package es.studium.di4;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MenuPrincipal frame = new MenuPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnArticulos = new JButton("Gestión de Artículos");
        btnArticulos.setBounds(150, 80, 150, 25);
        contentPane.add(btnArticulos);

        JButton btnTickets = new JButton("Gestión de Tickets");
        btnTickets.setBounds(150, 120, 150, 25);
        contentPane.add(btnTickets);

        btnArticulos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ArticulosView().setVisible(true);
            }
        });

        btnTickets.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TicketsView().setVisible(true);
            }
        });
    }
}
