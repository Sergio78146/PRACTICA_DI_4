package es.studium.di4;

import javax.swing.*;
 
public class ConsultaArticuloView extends JFrame {
	Datos datos = new Datos();

    public ConsultaArticuloView() {
		datos.conectar();

        setTitle("Consulta Artículos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = {"ID", "Descripción", "Precio", "Stock"};
        Object[][] data = getDataFromDatabase();  // Llamada al método para obtener datos de la base de datos

         
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 420, 250);
        contentPane.add(scrollPane);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(200, 300, 100, 25);
        contentPane.add(btnVolver);

        // Acción para cerrar la vista al hacer clic en "Volver"
        btnVolver.addActionListener(e -> dispose());
    }

    private Object[][] getDataFromDatabase() {
        // Llama al método de Datos para obtener los artículos como una cadena
        String[] articulos = datos.choiceArticulos();
        Object[][] data = new Object[articulos.length][4];

        // Rellena la matriz con los datos obtenidos
        for (int i = 0; i < articulos.length; i++) {
            String[] parts = articulos[i].split(" - ");
            data[i][0] = i + 1;  // Usar índice como ID (o ajustar según base de datos)
            data[i][1] = parts[0];  // Descripción
            data[i][2] = parts[1];  // Precio
            data[i][3] = parts[2];  // Stock
        }

        return data;
    }
}
