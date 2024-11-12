package es.studium.di4;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 


/*
 * Clase Datos: Maneja la conexión a la base de datos, además de los métodos.
 */
public class Datos
{
	// Datos de la conexión a la base de datos
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tiendecitasergio";
	String login = "root";
	String password = "Studium2023;";

	// Objetos para la gestión de la conexión y las consultas
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	// CONEXIÓN 
	Datos() {}
	public boolean conectar()
	{

		boolean conexionCorrecta = true;

		//Cargar el Driver

		try
		{
			Class.forName(driver);
		}

		catch(ClassNotFoundException e)
		{
			System.out.println("Se ha producido un error al cargar el Driver");
			conexionCorrecta = false;
		}

		//Establecer la conexión con la base dedatos
		try
		{
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e)
		{
			System.out.println("Se produjo un error al conectar a la Base de Datos");
			conexionCorrecta = false;
		}
		return conexionCorrecta;
	}

	/*
	 * Desconecta de la base de datos cerrando la conexión.
	 */
	public void desconectar()
	{
		try
		{
			statement.close();
			connection.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error al cerrar " + e.toString());
		}
	}
	
	public boolean altaArticulo(String descripcionArticulo, String precioArticulo, String stockArticulo) {
	    boolean altaCorrecta = true;
	    String sentencia = "INSERT INTO Articulo VALUES(null, '" + descripcionArticulo + "', " + precioArticulo + ", " + stockArticulo + ");";

	    try {
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        statement.executeUpdate(sentencia);
	    } catch (SQLException e) {
	        System.out.println("Error en la sentencia SQL: " + e.toString());
	        altaCorrecta = false;
	    }
	    return altaCorrecta;
	}
	

	
	public String[] choiceArticulos() {
	    String contenido = "";
	    String sentencia = "SELECT * FROM Articulo";


	    try {
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rs = statement.executeQuery(sentencia);

	        while (rs.next()) {
	            contenido = contenido
	                    + rs.getString("descripcionArticulo") + " - "
	                    + rs.getString("precioArticulo") + " - "
	                    + rs.getString("stockArticulo") + "*";
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la sentencia SQL: " + e.toString());
	    }

	    return contenido.split("\\*");
	}
	
	public String[] choiceTickets() {
	    String contenido = "";
	    String sentencia = "SELECT * FROM Ticket";

	    try {
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rs = statement.executeQuery(sentencia);

	        while (rs.next()) {
	            contenido = contenido
	                    + rs.getString("fechaTicket") + " - "
	                    + rs.getString("totalPrecioTicket") + "*";
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la sentencia SQL: " + e.toString());
	    }

	    return contenido.split("\\*");
	}
	
	/*public String dameArticulos() {
	    String contenido = "";
	    String sentencia = "SELECT * FROM Articulo";


	    try {
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rs = statement.executeQuery(sentencia);

	        while (rs.next()) {
	            contenido = contenido
	                    + rs.getString("descripcionArticulo") + " - "
	                    + rs.getDouble("precioArticulo") + " - "
	                    + rs.getInt("stockArticulo") + "\n";
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la sentencia SQL: " + e.toString());
	    }

	    return contenido;
	}*/
	
	public String dameTickets() {
	    String contenido = "";
	    String sentencia = "SELECT * FROM Ticket";
 
	    try {
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        rs = statement.executeQuery(sentencia);

	        while (rs.next()) {
	            contenido = contenido
	                    + rs.getString("fechaTicket") + " - "
	                    + rs.getDouble("totalPrecioTicket") + "\n";
	        }
	    } catch (SQLException e) {
	        System.out.println("Error en la sentencia SQL: " + e.toString());
	    }

	    return contenido;
	}
	
	public boolean modificacionArticulos(String descripcionArticuloAnterior, String precioArticuloAnterior, String stockArticuloAnterior, String descripcionArticulo, String precioArticulo, String stockArticulo) {
	    boolean modificacionCorrecta = true;
	    String sentencia = "UPDATE Articulo SET descripcionArticulo = '" + descripcionArticulo + "', precioArticulo = " + precioArticulo + ", stockArticulo = " + stockArticulo
	            + " WHERE descripcionArticulo = '" + descripcionArticuloAnterior + "' AND precioArticulo = " + precioArticuloAnterior + " AND stockArticulo = " + stockArticuloAnterior + ";";

	    try {
	        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        statement.executeUpdate(sentencia);
	    } catch (SQLException e) {
	        System.out.println("Error en la sentencia SQL: " + e.toString());
	        modificacionCorrecta = false;
	    }
	    return modificacionCorrecta;
	}
	
	public boolean eliminarArticulo(String descripcion, String precio, String stock) {
	    boolean eliminado = false;

	    try {
	        // Iniciar una transacción
	        connection.setAutoCommit(false);

	        // Obtener el idArticulo del artículo a eliminar, verificando que coincidan todos los atributos
	        String obtenerIdSQL = "SELECT idArticulo FROM Articulo " +
	                              "WHERE descripcionArticulo = '" + descripcion + "' " +
	                              "AND precioArticulo = " + precio + " " +
	                              "AND stockArticulo = " + stock;
	        Statement obtenerIdStmt = connection.createStatement();
	        ResultSet rs = obtenerIdStmt.executeQuery(obtenerIdSQL);

	        if (rs.next()) {
	            int idArticulo = rs.getInt("idArticulo");

	            // Eliminar las relaciones en la tabla RelacionTicketArticulo
	            String eliminarRelacionSQL = "DELETE FROM RelacionTicketArticulo WHERE idArticuloFK = " + idArticulo;
	            Statement eliminarRelacionStmt = connection.createStatement();
	            eliminarRelacionStmt.executeUpdate(eliminarRelacionSQL);

	            // Eliminar el artículo de la tabla Articulo
	            String eliminarArticuloSQL = "DELETE FROM Articulo WHERE idArticulo = " + idArticulo;
	            Statement eliminarArticuloStmt = connection.createStatement();
	            int filasAfectadas = eliminarArticuloStmt.executeUpdate(eliminarArticuloSQL);

	            // Confirmar si el artículo se eliminó correctamente
	            eliminado = filasAfectadas > 0;
	            connection.commit();
	        } else {
	            System.out.println("Artículo no encontrado o no coincide con los datos proporcionados.");
	        }

	        // Cerrar recursos
	        rs.close();
	        obtenerIdStmt.close();

	    } catch (SQLException e) {
	        try {
	            connection.rollback(); // Revertir cambios en caso de error
	            System.err.println("Error al eliminar artículo: " + e.getMessage());
	        } catch (SQLException rollbackEx) {
	            System.err.println("Error al revertir cambios: " + rollbackEx.getMessage());
	        }
	    } finally {
	        try {
	            connection.setAutoCommit(true); // Restaurar auto-commit
	        } catch (SQLException e) {
	            System.err.println("Error al restaurar auto-commit: " + e.getMessage());
	        }
	    }
	    
	    return eliminado;
	}
	
	
	
	 public int altaTicket(String fechaTicket, String totalPrecioTicket) {
	        int idTicket = -1; // Para almacenar el id del ticket creado
	        String sentencia = "INSERT INTO Ticket (fechaTicket, totalPrecioTicket) VALUES('" + fechaTicket + "', " + totalPrecioTicket + ");";

	        try {
	            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
	            // Ejecutar la sentencia de inserción
	            statement.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);

	            // Obtener el ID generado
	            ResultSet generatedKeys = statement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                idTicket = generatedKeys.getInt(1); // Asignar el ID generado a idTicket
	            }
	        } catch (SQLException e) {
	            System.out.println("Error en la sentencia SQL: " + e.toString());
	        }
	        return idTicket; // Devolver el ID del ticket creado, o -1 si hubo un error
	    }

	 public boolean relacionTicketArticulo(int idTicket, String descripcionArticulo) {
		    int idArticulo = -1; // Variable para almacenar el idArticulo

		    // Primer paso: obtener el idArticulo usando la descripcionArticulo
		    String queryObtenerId = "SELECT idArticulo FROM Articulo WHERE descripcionArticulo = '" + descripcionArticulo + "'";

		    System.out.println(idArticulo);
		    System.out.println(descripcionArticulo);

		    try {
		        statement = connection.createStatement();
		        ResultSet rs = statement.executeQuery(queryObtenerId);

		        if (rs.next()) {
		            idArticulo = rs.getInt("idArticulo"); // Asignar el idArticulo encontrado
		        } else {
		            System.out.println("Artículo no encontrado con la descripción dada.");
		            return false; // Salir si no se encuentra el artículo
		        }

		        rs.close();

		        // Segundo paso: insertar la relación en la tabla RelacionTicketArticulo
		        String queryInsertarRelacion = "INSERT INTO RelacionTicketArticulo (idTicketFK, idArticuloFK) VALUES (" + idTicket + ", " + idArticulo + ")";
		        int filasAfectadas = statement.executeUpdate(queryInsertarRelacion);
		        return filasAfectadas > 0; // Devolver true si la inserción fue exitosa

		    } catch (SQLException e) {
		        System.out.println("Error al crear la relación: " + e.toString());
		        return false; // Devolver false si hubo un error
		    }
		}




}

