package ModelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Miguel.a_proyectoprueba.PrimaryController;
import Model.Articulo;
import Model.P_Articulo;
import Utils.Connect;

public class ArticuloDao {
	/**
	 * recibe un id y devuelve el articulo asociaado a esa id
	 * @param id es el id asociado al articulo a buscar
	 * @return articulo con el id pasado
	 */
	public static Articulo get(Integer id) {
		//se abre coneccion
		Connection miconexion = Connect.getConnection();
		//se crea el articulo que se va a devolver
		Articulo a = null;
		//sentecia sql a ejecutar
		String sql = "SELECT id,nombre,precio,tipo FROM articulos WHERE id=?";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			sentencia.setInt(1, id);
			ResultSet miRs = sentencia.executeQuery();
			a = new Articulo();
			miRs.next();
			//se obtiene los datos del select y se añaden al Artuculo
			a.setId(miRs.getInt("id"));
			a.setNombre(miRs.getString("nombre"));
			a.setPrecio(miRs.getInt("precio"));
			a.setTipo(miRs.getInt("tipo"));

		} catch (Exception e) {
			// TODO: handle exception
		} 
		return a;
	}
	/**
	 * recibe un nombre y devuelve el articulo asociaado a ese nombre
	 * @param nombre es el nombre asociado al articulo a buscar
	 * @return articulo con el nombre pasado
	 */
	public static Articulo get(String nombre) {
		//se abre conexion
		Connection miconexion = Connect.getConnection();
		//se crea articulo que se va a devolver
		Articulo a = null;
		//sentenca sql a ejecutar
		String sql = "SELECT id,nombre,precio,tipo FROM articulos WHERE nombre=?";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			sentencia.setString(1, nombre);
			ResultSet miRs = sentencia.executeQuery();
			a = new Articulo();
			miRs.next();
			//se obtiene los datos del select y se añaden al Artuculo
			a.setId(miRs.getInt("id"));
			a.setNombre(miRs.getString("nombre"));
			a.setPrecio(miRs.getInt("precio"));
			a.setTipo(miRs.getInt("tipo"));

		} catch (Exception e) {
			// TODO: handle exception
		} 
		return a;
	}
	
	/**
	 * devuelve un arraylist de articulos que sean bebidas
	 * @return Arraylit de bebidas
	 */
	public static ArrayList<Articulo> getBebidas() {
		//se abre conexion
		Connection miconexion = Connect.getConnection();
		//se crea arraylist
		ArrayList<Articulo> a = null;
		//sentencia de sql
		String sql = "SELECT id,nombre,precio,tipo FROM articulos WHERE tipo=0";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<Articulo>();
			//se recorren los resultados de la bd hasta que no haya ninguno
			while (miRs.next()) {
				//se crea un articulo 
				Articulo aux = new Articulo();
				//se insertan los valores obtenidos
				aux.setId(miRs.getInt("id"));
				aux.setNombre(miRs.getString("nombre"));
				aux.setPrecio(miRs.getInt("precio"));
				aux.setTipo(miRs.getInt("tipo"));
				
				//se añade al arraylist
				a.add(aux);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	
	/**
	 * devuelve un arraylist de articulos que sean comida
	 * @return Arraylit de comidas
	 */
	public static ArrayList<Articulo> getComidas() {
		Connection miconexion = Connect.getConnection();
		ArrayList<Articulo> a = null;
		String sql = "SELECT id,nombre,precio,tipo FROM articulos WHERE tipo=1";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<Articulo>();
			while (miRs.next()) {
				Articulo aux = new Articulo();
				aux.setId(miRs.getInt("id"));
				aux.setNombre(miRs.getString("nombre"));
				aux.setPrecio(miRs.getInt("precio"));
				aux.setTipo(miRs.getInt("tipo"));

				a.add(aux);

			}

		} catch (Exception e) {
			// TODO: handle exception
		} 
		return a;
	}
	
	/**
	 * actuliza un articulo con los valores de un articulo
	 * @param aux tiene los valores que se van a actualizar el articulo
	 * @return true si se a actualizado correctamente
	 */
	public static boolean updateArticulo(Articulo aux) {
		//abre conexion
				Connection miconexion = Connect.getConnection();
				//boolean a devolver
				boolean result = false;
				//sentencia sql
				String sql = "UPDATE articulos SET nombre=?, precio=?, tipo=? WHERE id = ?;";
				try {
					PreparedStatement sentencia = miconexion.prepareStatement(sql);
					//se insertan los datos en la sentencis sql
					sentencia.setString(1, aux.getNombre());
					sentencia.setInt(2, aux.getPrecio());
					sentencia.setInt(3, aux.getTipo());
					sentencia.setInt(4, aux.getId());
					sentencia.executeUpdate();
					//si despues de actualizar no habido fallo el result es true y significa que 
					//todo ha ido correcto 
					result = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return result;
	}
	
	/**
	 * insserta en la bd el articulo pasado
	 * @param ob articulo a insertar
	 * @return true si se a insertado correctamente
	 */
	public static boolean insert(Articulo ob) {
		//abre conexion
		Connection miconexion = Connect.getConnection();
		//boolean a devolver
		boolean result = false;
		
		//sentencia sql
		String sql = "INSERT INTO articulos (nombre,precio,tipo) VALUES (?,?,?)";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se insertan los datos en la sentencis sql
			sentencia.setString(1, ob.getNombre());
			sentencia.setInt(2, ob.getPrecio());
			sentencia.setInt(3, ob.getTipo());
			sentencia.executeUpdate();
			//si despues de actualizar no habido fallo el result es true y significa que 
			//todo ha ido correcto 
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	
}
