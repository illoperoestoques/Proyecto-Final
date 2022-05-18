package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.P_Articulo;
import Model.Pedido;
import ModelDao.P_ArticuloDao;
import ModelDao.PedidoDao;
import interfaces.ICamareroEleccionController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class CamareroEleccionController implements Initializable, ICamareroEleccionController {
	private static int mesa;
	
	@FXML
	private Button tomarN;
	@FXML
	private Button eliminarP;
	@FXML
	private Button modificarP;
	@FXML
	private Button PagarC;
	
	
	@FXML
	private Button back;
	
	
	@FXML
	public void switchToBack() throws IOException {
		App.setRoot("mesas");
	}

	/**
	 * Cuando se hace click en tomarN se crea o se escoge un pedido y se cambia de vista a menu
	 */
	@FXML
	public void switchToMenu() throws IOException {
		//se obtiene el id del ultimo pedido de la mesa escogida
		int id = PedidoDao.getLastId(mesa);
		//si el estado del uno los P_articulo no esta acabado devuelve false
		boolean finish = P_ArticuloDao.EstadoFinish(id);
		//si es false se pasa el pedido al controlador de la siguiente vista
		if (finish == false) {
			ElegirAlimentoController.setPedido(PedidoDao.get(id));
		} else {
			//si es true se crea un nuevo pedido
			ElegirAlimentoController.newPedido(mesa);
		}
		//se cambia de vista
		App.setRoot("menu");
	}
	
	/**
	 * Si se clika eliminaP se cambia de vista
	 */
	@FXML
	public void switchToEliminaPedido() throws IOException {
		//se obtiene el id del ultimo pedido
		int id = PedidoDao.getLastId(mesa);
		//si el estado del uno los P_articulo no esta acabado devuelve false
		boolean finish = P_ArticuloDao.EstadoFinish(id);
		//si es false se pasa el pedido al controlador de eliminaPedido
		if (finish == false) {
			EliminarPedidoController.setPedido(PedidoDao.get(id));
		}
		//se cambia de vista
		App.setRoot("eliminarPedido");

	}
	
	//se cambia de vista a Pagar Cuenta cunado clika PagarC
	@FXML
	public void switchToPagarCuenta() throws IOException {
		int id = PedidoDao.getLastId(mesa);
		boolean finish = P_ArticuloDao.EstadoFinish(id);
		//si uno de los P_Articulos no ha acabado se pasa el pedido al controlador de ElegirAlimento
		if (finish == false) {
			ElegirAlimentoController.setPedido(PedidoDao.get(id));
		}
		//se cambia de vista
		App.setRoot("pagarCuenta");
	}
	
	//se cambia de vista a modificaPedido cuando se clika modificaP
	@FXML
	public void switchToModificaPedido() throws IOException {
		int id = PedidoDao.getLastId(mesa);
		boolean finish = P_ArticuloDao.EstadoFinish(id);
		//si uno de los P_Articulos no ha acabado se pasa el pedido al controlador de ElegirAlimento
		if (finish == false) {
			ElegirAlimentoController.setPedido(PedidoDao.get(id));
		}
		//se cambia de vista a modificaPedido
		App.setRoot("modificaPedido");
	}
	
	//pasa un int a mesa 
	public static void addMesa(int me) {
		mesa = me;
	}
	
	//obtiene todos los P_Articulo asociados al pedido 
	public static ArrayList<P_Articulo> infoPedido() {
		//se obtiene el pedido 
		Pedido pedido = ElegirAlimentoController.getPedido();
		//Se obtiene un Arraylis de todos los P_Articulos del pedido anterior
		ArrayList<P_Articulo> pedidos = P_ArticuloDao.get(pedido);
		return pedidos;
		
	}
	//obtiene el precio de un pedido
	public static int infoPrecio() {
		//se obtiene el pedido
		Pedido pedido = ElegirAlimentoController.getPedido();
		//Se obtiene un Arraylis de todos los P_Articulos del pedido anterior
		ArrayList<P_Articulo> pedidos = P_ArticuloDao.get(pedido);
		//se obtien un int de todos los precios de P_Articulos que habia en Pedidos
		int precio = P_Articulo.ArraytoPrice(pedidos);
		//deveuelve precio
		return precio;
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	
}
