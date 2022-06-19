package ModelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Miguel.a_proyectoprueba.PrimaryController;
import Model.Articulo;
import Model.Empleado;
import Model.P_Articulo;
import Model.Pedido;
import Utils.Connect;

public class P_ArticuloDao {
	/**
	 * inserta un P_articulo en la bd
	 * @param ob P_Articul a insertar
	 * @return true si se ha insertado correctamente
	 */
	public static boolean insert(P_Articulo ob) {
		//abre conexion
		Connection miconexion = Connect.getConnection();
		//boolean a devolver
		boolean result = false;
		
		String usuario = PrimaryController.getEmpleado().getUser();
		//sentencia sql
		String sql = "INSERT INTO p_articulo (id_p,id_a,cantidad,estado,user_emp) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se insertan los datos en la sentencis sql
			sentencia.setInt(1, ob.getPedidido().getId());
			sentencia.setInt(2, ob.getArticulo().getId());
			sentencia.setInt(3, ob.getCantidad());
			sentencia.setString(4, ob.getEstado());
			sentencia.setString(5, ob.getEmpleado().getUser());
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
	 * devuelve un Arraylist de todos los P_Articulo que tiene asociado un id de pedido
	 * @param pedido tiene el id con el que se van a identificar los id_Pedidos
	 * @return Arraylist de todos los P_Articulo
	 */
	public static ArrayList<P_Articulo> get(Pedido pedido) {
		Connection miconexion = Connect.getConnection();
		ArrayList<P_Articulo> a = null;
		//se obtiene el id del pedido
		int idp = pedido.getId();
		//sentenia sql
		String sql = "SELECT id_a,cantidad,estado,user_emp FROM p_articulo WHERE id_p=?";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se inserta el id en la sentencia 
			sentencia.setInt(1, idp);
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<P_Articulo>();
			//mientras tenga resguistros la consulta se añadiran P_Articulos
			while (miRs.next()) {
				//se obtiene los datos 
				int id_a = miRs.getInt("id_a");
				Articulo art_aux = ArticuloDao.get(id_a);
				int cant_aux = miRs.getInt("cantidad");
				String est_aux = miRs.getString("estado");
				String nombre_user = miRs.getString("user_emp");
				EmpleadoDao edao= new EmpleadoDao();
				Empleado user = edao.get(nombre_user);
				//se crea el P_Articulo
				P_Articulo auxi = new P_Articulo(pedido, art_aux, cant_aux, est_aux,user);
				//se añade al arraylist
				a.add(auxi);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return a;
	}

	/**
	 * se pasa el articulo que se quier aumentar la cantidad y el pedido con el id asociado 
	 * @param pedido tiene la id asociada al P_Articulo
	 * @param art articulo que del P_Articulo que se quiere aumentar la cantidad
	 */
	public static void addCantidad(Pedido pedido, Articulo art) {
		Connection miconexion = Connect.getConnection();
		//se obtiene los ids de ambos
		int idp = pedido.getId();
		int ida = art.getId();
		//sentencia sql
		String sql = "UPDATE p_articulo SET cantidad = (SELECT cantidad FROM p_articulo WHERE p_articulo.id_p = ? AND p_articulo.id_a = ? AND  p_articulo.estado = 'no entregado' ) + 1 WHERE p_articulo.id_p = ? AND p_articulo.id_a = ? AND  p_articulo.estado = 'no entregado';";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se insertan los valores valores 
			sentencia.setInt(1, idp);
			sentencia.setInt(2, ida);
			sentencia.setInt(3, idp);
			sentencia.setInt(4, ida);
			//se actualiza
			sentencia.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		} 

	}
		
	/**
	 * suma elnumero pasado a la cantidad original del P_articulo pasado
	 * @param aux
	 * @param cantidad
	 */
	public static void calculaCantidad(P_Articulo aux, int cantidad, String estado) {
		Connection miconexion = Connect.getConnection();
		//sentencia sql
		String sql = "UPDATE p_articulo SET cantidad = (SELECT cantidad FROM p_articulo WHERE p_articulo.id_p = ? AND p_articulo.id_a = ? AND  p_articulo.estado = ? ) + ? WHERE p_articulo.id_p = ? AND p_articulo.id_a = ? AND  p_articulo.estado = ? ;";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se insertan los valores valores 
			sentencia.setInt(1, aux.getPedidido().getId());
			sentencia.setInt(2, aux.getArticulo().getId());
			sentencia.setString(3, estado);
			sentencia.setInt(4, cantidad);
			sentencia.setInt(5, aux.getPedidido().getId());
			sentencia.setInt(6, aux.getArticulo().getId());
			sentencia.setString(7, estado);
			//se actualiza
			sentencia.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		} 

	}
	
	
	public static void cambiaCantidad(P_Articulo aux, int cantidad, String estado) {
		Connection miconexion = Connect.getConnection();
		//sentencia sql
		String sql = "UPDATE p_articulo SET cantidad = ? WHERE p_articulo.id_p = ? AND p_articulo.id_a = ? AND  p_articulo.estado = ? ;";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se insertan los valores valores 
			sentencia.setInt(1, cantidad);
			sentencia.setInt(2, aux.getPedidido().getId());
			sentencia.setInt(3, aux.getArticulo().getId());
			sentencia.setString(4, estado);
			//se actualiza
			sentencia.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		} 

	}
	
	
	/**
	 * Devuelve true o false segun si el pedido esta terminado o no
	 * @param id_pedido id del pedido que queremos saber si esta acabao o no
	 * @return true si esta acabado y false si no
	 */
	public static boolean EstadoFinish(int id_pedido) {
		Connection miconexion = Connect.getConnection();
		String sql = "SELECT id_p FROM p_articulo WHERE id_p = ? and estado IN('no entregado','preparado','entregado') limit 1;";
		int noUse = -1;
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se inserta el id
			sentencia.setInt(1, id_pedido);
			ResultSet miRs = sentencia.executeQuery();
			miRs.next();
			//se obtiene el id del pedido y se iguala al int anteriormente creado
			noUse = miRs.getInt("id_p");
			//si noUse no es igual a -1 significa que el pedido no esta acabado
			if (noUse != -1) {
				return false;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return true;
	}

	/**
	 * se elimina pedido pasado en la bd
	 * @param pedido es el pedido a eliminar
	 */
	public static void eliminaPedido(Pedido pedido) {
		Connection miconexion = Connect.getConnection();
		//se obtiene el id del pedido a eliminar
		int id = pedido.getId();
		String sql = "DELETE FROM p_articulo where id_p=?";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			// se pasa el id a la sentencia 
			sentencia.setInt(1, id);
			//se actualiza
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		} 

	}
	/**
	 * pone el estado a pagado a todos los p_Articulos con el id del  pedido pasado 
	 * @param pedido es el pedido asociado a todos los p_Articuos que se quieren actualizar a pagado
	 */
	public static void pagarPedido(Pedido pedido) {
		Connection miconexion = Connect.getConnection();
		//se obtiene el id del pedido que se quiere pagar
		int id = pedido.getId();
		String sql = "UPDATE `p_articulo` SET `estado` = 'pagado'WHERE `p_articulo`.`id_p` = ?";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			// se pasa el id a la sentencia 
			sentencia.setInt(1, id);
			//se actualiza
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		} 
		
	}
	
	
	public static String updateP_Articulo1(P_Articulo pedido,int cantidad) {
		String valid="";
		Connection miconexion = Connect.getConnection();
		//sentencia sql
		String sql = "SELECT IF(?= (SELECT cantidad FROM p_articulo WHERE id_p= ? AND id_a= ? AND estado= ?), 'true', 'false');";

		try {			
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se inserta el id
			sentencia.setInt(1, cantidad);
			sentencia.setInt(2, pedido.getPedido().getId());
			sentencia.setInt(3, pedido.getArticulo().getId());
			sentencia.setString(4, pedido.getEstado());
			ResultSet miRs = sentencia.executeQuery();
			miRs.next();
			//se obtiene el id del pedido y se iguala al int anteriormente creado
			valid = miRs.getString(1);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return valid;
	}
	
	
	public static String updateP_Articulo2(P_Articulo pedido, String estado,int cantidad) {
		String valid="";
		Connection miconexion = Connect.getConnection();
		//sentencia sql
		String sql = "SELECT IF(?<= (SELECT cantidad FROM p_articulo WHERE id_p= ? AND id_a= ? AND estado= ?), 'true' , 'false');";

		try {			
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se inserta el id
			sentencia.setInt(1, cantidad);
			sentencia.setInt(2, pedido.getPedido().getId());
			sentencia.setInt(3, pedido.getArticulo().getId());
			sentencia.setString(4, estado);
			ResultSet miRs = sentencia.executeQuery();
			miRs.next();
			//se obtiene el id del pedido y se iguala al int anteriormente creado
			valid = miRs.getString(1);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return valid;
	}
	
	
	/**
	 * elimina el P_Articulo a eliminar 
	 * @param auxP_Articulo a eliminar
	 */
	public static void eliminaP_Articulo(P_Articulo aux) {
		Connection miconexion = Connect.getConnection();
		//se obtiene el id del pedido a eliminar
		String sql = "DELETE FROM p_articulo where id_p=? and id_a=? and estado= ?";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			// se pasa el id a la sentencia 
			sentencia.setInt(1, aux.getPedidido().getId());
			sentencia.setInt(2, aux.getArticulo().getId());
			sentencia.setString(3, aux.getEstado());
			//se actualiza
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		} 

	}
	
	/**
	 * Obtiene todos los P_Articulos que su estad sea "no entregado" , que sean una comida para los cocineros
	 * @param aux Pedido con id asociado a los P_Articulos
	 * @return Arraylist con los P_Articulo seleccionados
	 */
	public static ArrayList<P_Articulo> P_ArticuloCocina(Pedido aux){
		Connection miconexion = Connect.getConnection();
		ArrayList<P_Articulo> a = null;
		//se obtiene el id del pedido
		//sentenia sql
		String sql = "SELECT pa.id_a, pa.id_p, pa.cantidad, pa.estado from p_articulo pa, articulos ar where id_p = ? and pa.estado = 'no entregado' and ar.tipo=1 and pa.id_a = ar.id;";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			sentencia.setInt(1, aux.getId());
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<P_Articulo>();
			//mientras tenga resguistros la consulta se añadiran P_Articulos
			while (miRs.next()) {
				P_Articulo auxi = new P_Articulo();
				//se obtiene los datos 
				int id_a = miRs.getInt(1);
				int id_p = miRs.getInt(2);
				int cantidad = miRs.getInt(3);
				String estado = miRs.getString(4);
				
				auxi.setPedidido(PedidoDao.get(id_p));
				auxi.setArticulo(ArticuloDao.get(id_a));
				auxi.setCantidad(cantidad);
				auxi.setEstado(estado);
				//se crea el P_Articulo
				
				//se añade al arraylist
				a.add(auxi);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return a;
	}
	

}
