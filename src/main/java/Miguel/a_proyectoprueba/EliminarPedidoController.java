package Miguel.a_proyectoprueba;

import java.io.IOException;

import Model.Pedido;
import ModelDao.P_ArticuloDao;
import ModelDao.PedidoDao;
import Utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EliminarPedidoController {
	private static Pedido pedido;

	@FXML
	Button cancelar;
	@FXML
	Button confirmar;
	
	//cuando clikas cancelar se cambia a la vista camareroEleccion
	@FXML
	private void switchToCamareroEleccion() throws IOException {
		App.setRoot("camareroEleccion");
	}
	
	//cuando clikas confirmar elimina el pedido y cambia de vista
	@FXML
	private void eliminarPedido() throws IOException {
		//elimina todos los P_Articulos con el id de pedido
		P_ArticuloDao.eliminaPedido(pedido);
		//elimina el pedido pasado
		PedidoDao.eliminaPedido(pedido);
		//cambia de vista a camareroEleccion
		Utils.info("Pedido Eliminado");
		App.setRoot("camareroEleccion");
	}
	/**
	 * se elimina un pedido de la base de datos 
	 * @param aux pedido a eliminar
	 */
	public static void setPedido(Pedido aux) {
		pedido = aux;
	}

}
