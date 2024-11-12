package es.studium.di4;

import javax.swing.*;
 
public class ConsultaTicketView extends JFrame {
	Datos datos = new Datos();

    public ConsultaTicketView( ) {
		datos.conectar();

        setTitle("Consulta Tickets");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = {"ID", "Fecha", "Total Precio"};
        Object[][] data = getTicketsData();  // Método para obtener datos de la base de datos

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 420, 250);
        contentPane.add(scrollPane);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 300, 100, 25);
        contentPane.add(btnVolver);

        // Acción para volver a la vista de tickets anterior
        btnVolver.addActionListener(e -> dispose());
    }

    private Object[][] getTicketsData() {
        // Obtiene los datos de tickets desde la base de datos
        String[] tickets = datos.choiceTickets();
        Object[][] data = new Object[tickets.length][3];

        // Procesa cada línea y la convierte en un array para el JTable
        for (int i = 0; i < tickets.length; i++) {
            String[] parts = tickets[i].split(" - ");
            data[i][0] = i + 1;          // ID (generado)
            data[i][1] = parts[0];       // Fecha
            data[i][2] = parts[1];       // Total Precio
        }

        return data;
    }
}
