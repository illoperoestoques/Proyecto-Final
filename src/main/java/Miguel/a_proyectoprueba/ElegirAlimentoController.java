package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Articulo;
import Model.P_Articulo;
import Model.Pedido;
import ModelDao.P_ArticuloDao;
import ModelDao.PedidoDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

public class ElegirAlimentoController implements Initializable {

	private static Pedido pedido;

	@FXML
	private ImageView alimentos;
	@FXML
	private ImageView bebidas;
	
	//cambia a la vista bebidas cuando se clika bebidas 
	@FXML
	private void switchToBebidas() throws IOException {
		App.setRoot("bebidas");
	}
	//cambia a la vista comida cuando se clika alimentos
	@FXML
	private void switchToComida() throws IOException {
		App.setRoot("comida");
	}

	//devuelve el pedido
	public static Pedido getPedido() {
		return pedido;
	}
	
	/**
	 * cambia pedido 
	 * @param pd el pedido que va a ser atributo pedido
	 */
	public static void setPedido(Pedido pd) {
		pedido = pd;
	}

	//se crea un pedido nuevo y este va a ser el del atributo pedido 
	public static void newPedido(int mesa) {
		pedido = PedidoDao.insert(mesa);
	}

	public static void newP_articulo(Articulo art) {

		// se crea P_articulo que queremos insertar
		P_Articulo p_art = new P_Articulo(pedido, art, 1, "no entregado",PrimaryController.getEmpleado());
		// se crea un nuevo P_ArticuloDao para insertar un nuevo
		// P_articulo o actualizar la cantidad de uno
		// se obtiene todos los P_Articulos que hay con el id del pedido
		ArrayList<P_Articulo> P_articulos = P_ArticuloDao.get(pedido);
		// deveuelbe un boolean si uno de los P_Articulos de la base de datos
		// contiene el Articulo a Insertar
		boolean tiene = p_art.equalArray(P_articulos);
		// si devuelve true significa que ya hay otro P_articulo con el articulo
		// que queremos insertar
		if (tiene == true) {
			// modificamos el P_articulo de la base de datos que tiene articulo
			// que queremos insertar
			P_ArticuloDao.addCantidad(pedido, art);
		} else if (tiene == false) {
			// en este añadimos el P_articulo a la bse de datos ya que no hay un
			// P_articulo con el articulo que queremos insertar
			P_ArticuloDao.insert(p_art);
		}
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
