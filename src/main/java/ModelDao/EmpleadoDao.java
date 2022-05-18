package ModelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import Model.Articulo;
import Model.Empleado;
import Utils.Connect;
import Utils.Utils;

public class EmpleadoDao {
	private Connection miconexion;

	public EmpleadoDao() {
		miconexion = Connect.getConnection();
	}

	
	/**
	 * Obtiene un Empleado a traves de su usuario
	 * @param user nombre del usuario
	 * @return empleado asociado al nombre de usuario que se le pasa
	 */
	public Empleado get(String user) {
		//se crea Empleado
		Empleado l = null;
		//sentencia sql
		String sql = "SELECT user,password,trabajo FROM empleados WHERE user=?";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			sentencia.setString(1, user);
			ResultSet miRs = sentencia.executeQuery();
			l = new Empleado();
			miRs.next();
			//se obtiene los datos del select y se añaden al Empleado
			l.setUser(miRs.getString("user"));
			l.setPassword(miRs.getString("password"));
			l.setTrabajo(miRs.getString("trabajo"));
			;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return l;
	}

	/**
	 * comprueba que el usuario y contraseña coinciden con un reguistro de la bd
	 * @param user nombre de usuario
	 * @param pass nombre de contraseña
	 * @return boolean si coincide o no
	 */
	public boolean validUser(String user, String pass) {
		//sentencia sql
		String sql = "SELECT user,password FROM empleados WHERE user=? and password=?";
		boolean result = false;
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			sentencia.setString(1, user);
			String password = Utils.getSHA256(pass);
			sentencia.setString(2, password);
			ResultSet miRs = sentencia.executeQuery();
			//se crea un empleado por defecto
			Empleado l = new Empleado();
			miRs.next();
			//se obtiene los datos del select y se añaden al Empleado
			l.setUser(miRs.getString("user"));
			l.setPassword(miRs.getString("password"));
			//si el usuario tiene un usuario y una contraseña diferente a la del
			//usuario por defecto signifia que hay un empleado asociado a ese user y password
			if (l.getUser() != "nadie" && l.getPassword() != "nada") {
				//se devuelve true ya que existe el usuario
				result = true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return result;
	}
	

	
	/**
	 * se obtiene todos los empleados 
	 * @return Arraylis de empleados
	 */
	public static ArrayList<Empleado> getAllEmpleados() {
		//se abre conexion
		Connection miconexion = Connect.getConnection();
		//se crea arraylist
		ArrayList<Empleado> a = null;
		//sentencia de sql
		String sql = "SELECT user, password,trabajo FROM empleados";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<Empleado>();
			//se recorren los resultados de la bd hasta que no haya ninguno
			while (miRs.next()) {
				//se crea un articulo 
				Empleado aux = new Empleado();
				//se insertan los valores obtenidos
				aux.setUser(miRs.getString(1));
				aux.setPassword(miRs.getString(2));
				aux.setTrabajo(miRs.getString(3));
				//se añade al arraylist
				a.add(aux);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
	
	/**
	 * inserta el empleado pasado
	 * @param ob empleado que se quiere insertar
	 * @return true si se a insertado correctamente 
	 */
	public static boolean insert(Empleado ob) {
		//abre conexion
		Connection miconexion = Connect.getConnection();
		//boolean a devolver
		boolean result = false;
		
		//sentencia sql
		String sql = "INSERT INTO empleados  (user, password,trabajo) VALUES (?,?,?)";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se insertan los datos en la sentencis sql
			sentencia.setString(1, ob.getUser());
			String aux= ob.getPassword();
			String pass=Utils.getSHA256(aux);
			sentencia.setString(2, pass);
			sentencia.setString(3, ob.getTrabajo());
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
	 * actualiza el empleado con los datos pasados
	 * @param aux Epleado con los datos nuevos que se quieren introducir
	 * @param oldUser antiguo usuario del empleado
	 * @param oldPassword antigua contraseña del empleado
	 * @return true si se aha actualizado correctamente
	 */
	public static boolean updateEmpleado(Empleado aux, String oldUser, String oldPassword) {
		//abre conexion
				Connection miconexion = Connect.getConnection();
				//boolean a devolver
				boolean result = false;
				//sentencia sql
				String sql = "UPDATE empleados SET user=?, password=?, trabajo=? WHERE user = ?;";
				try {
					PreparedStatement sentencia = miconexion.prepareStatement(sql);
					//se insertan los datos en la sentencis sql
					sentencia.setString(1, aux.getUser());
					if(aux.getPassword().equals(oldPassword)) {
						sentencia.setString(2, aux.getPassword());
					}else {
						String pass=Utils.getSHA256(aux.getPassword());
						sentencia.setString(2, pass);
					}
					sentencia.setString(3, aux.getTrabajo());
					sentencia.setString(4, oldUser);
					sentencia.executeUpdate();
					//si despues de actualizar no habido fallo el result es true y significa que 
					//todo ha ido correcto 
					result = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return result;
	}
	
	public static ArrayList<Empleado> getAllEmpleadosNoAdmin() {
		//se abre conexion
		Connection miconexion = Connect.getConnection();
		//se crea arraylist
		ArrayList<Empleado> a = null;
		//sentencia de sql
		String sql = "SELECT user, password,trabajo FROM empleados where trabajo <> 'admin';";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<Empleado>();
			//se recorren los resultados de la bd hasta que no haya ninguno
			while (miRs.next()) {
				//se crea un articulo 
				Empleado aux = new Empleado();
				//se insertan los valores obtenidos
				aux.setUser(miRs.getString(1));
				aux.setPassword(miRs.getString(2));
				aux.setTrabajo(miRs.getString(3));
				//se añade al arraylist
				a.add(aux);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}


}
