package ModelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Articulo;
import Model.Empleado;
import Model.P_Articulo;
import Model.Pedido;
import Utils.Connect;

public class PedidoDao {
	/**
	 * se crea un pedido a traves del numero de una mesa y devuelve elpedido creado en la bd
	 * @param mesa numero de mesa que se va ausar como id_mesa
	 * @return pedido creado
	 */
	public static Pedido insert(int mesa) {
		Connection miconexion = Connect.getConnection();
		//pedido que se va a devolver
		Pedido a = null;
		//se hacen 2 sentencias sql
		//una para insertar el pedido
		String sql = "INSERT INTO pedidos (id_mesa) VALUES (?)";
		//segunda sentencia para devolver el pedido
		String sql2 = "SELECT id, id_mesa FROM pedidos WHERE id_mesa=? ORDER by id DESC LIMIT 1";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			sentencia.setInt(1, mesa);
			//se ejecuta la primera sentencia on el id de la mesa introducido
			sentencia.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql2);
			sentencia.setInt(1, mesa);
			ResultSet miRs = sentencia.executeQuery();
			a = new Pedido();
			miRs.next();
			//se obtiene los valores del pedido y se insetan en pedido a
			int id = miRs.getInt("id");
			int id_mesa = miRs.getInt("id_mesa");
			a.setId(id);
			a.setId_mesa(id_mesa);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return a;
	}

	/**
	 * se obtiene un pedido a traves de su id
	 * @param id es el id del pedido 
	 * @return pedido que coincide con el id pasado
	 */
	public static Pedido get(Integer id) {
		Connection miconexion = Connect.getConnection();
		//pedido que se va a devlver 
		Pedido a = null;
		String sql = "SELECT id,id_mesa FROM pedidos WHERE id=?";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//inserta el id en la sentencia 
			sentencia.setInt(1, id);
			//se ejecuta
			ResultSet miRs = sentencia.executeQuery();
			a = new Pedido();
			miRs.next();
			// se obtiene los valores y se insertan en el pedido 
			a.setId(miRs.getInt("id"));
			a.setId_mesa(miRs.getInt("id_mesa"));
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return a;
	}

	/**
	 * obtiene el ultimo id del pedido asociado al id de la mesa que se le pasa 
	 * @param id_mesa el es id_mesa del pedido a buscar 
	 * @return ultimo pedido asociado al id_mesa que se le pasa
	 */
	public static int getLastId(Integer id_mesa) {
		Connection miconexion = Connect.getConnection();
		//id del pedido que se va a devolver
		int id = 0;
		String sql = "SELECT id FROM pedidos WHERE id_mesa=? order by id desc limit 1";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//se inserta el id_mesa en la sentencia 
			sentencia.setInt(1, id_mesa);
			ResultSet miRs = sentencia.executeQuery();
			miRs.next();
			//se obtiene el id del pedido y se iguala al int que se devuelve 
			id = (miRs.getInt("id"));
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return id;
	}
	
	/**
	 * elimina el pedido que se pasa 
	 * @param pedido es el pedido a eliminar 
	 */
	public static void eliminaPedido(Pedido pedido) {
		Connection miconexion = Connect.getConnection();
		//se obtiene el id del pedido que se quiere eliminar
		int id = pedido.getId();
		String sql = "DELETE FROM pedidos where id=?";
		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			//seinserta el id en la sentencia
			sentencia.setInt(1, id);
			//se ejecuta y se elimina el pedido
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
		} 

	}
	
	/**
	 * Obtien un Arraylist con todos los pedidos que tengaun articulo que sea una comida y su estado sea "no entregado"
	 * @return
	 */
	public static ArrayList<Pedido> PedidosCocina(){
		Connection miconexion = Connect.getConnection();
		ArrayList<Pedido> a = null;
		//se obtiene el id del pedido
		//sentenia sql
		String sql = "SELECT p.id, p.id_mesa FROM pedidos p,p_articulo pa,articulos ar WHERE p.id IN (SELECT pa.id_p WHERE pa.estado='no entregado' and pa.id_a in(SELECT ar.id where ar.tipo=1)) GROUP by p.id;";

		try {
			PreparedStatement sentencia = miconexion.prepareStatement(sql);
			ResultSet miRs = sentencia.executeQuery();
			a = new ArrayList<Pedido>();
			//mientras tenga resguistros la consulta se añadiran P_Articulos
			while (miRs.next()) {
				//se obtiene los datos 
				int id_p = miRs.getInt(1);
				int id_mesa = miRs.getInt(2);
				//se crea el P_Articulo
				Pedido auxi = new Pedido(id_p,id_mesa);
				//se añade al arraylist
				a.add(auxi);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} 
		return a;
	}

}
