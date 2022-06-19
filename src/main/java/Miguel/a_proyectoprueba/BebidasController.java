package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Articulo;
import ModelDao.ArticuloDao;
import Utils.Utils;
import interfaces.IBebidasController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class BebidasController implements Initializable, IBebidasController {

	@FXML
	private HBox bebidas;

	@FXML
	private ImageView buttonAtras;
	@FXML
	private ImageView buttonOk;
	@FXML
	private ImageView buttonShowMenu;
	
	/**
	 * Cundo clikas buttonAtras cambia a la vista de menu
	 */
	@FXML
	public void switchToMenu() throws IOException {
		App.setRoot("menu");
	}
	/**
	 * Cundo clikas buttonOk cambia a la vista de mesas
	 */
	@FXML
	public void switchToMesas() throws IOException {
		App.setRoot("mesas");
	}
	/**
	 * Cambia clikas buttonShowMenu cambia la vista de infoPedido
	 */
	@FXML
	public void switchToPedido() throws IOException {
		App.setRoot("infoPedido");
	}
	
	/**
	 * muestra todas las bebidas de la bd en el HorizontalBox "bebidas", en forma de boton
	 * cada bebida con su nombre.
	 */
	@FXML
	public void mostrarBebidas() {
		//dbbebidas guarda un arrayList de Articulo con todas las bebidas de la bd
		ArrayList<Articulo> bdbebidas = ArticuloDao.getBebidas();
		for (Articulo aux : bdbebidas) {
			//por cada bebida del Arraylist se crea un boton
			Button articulo = new Button();
			//para darle un formato de letra mas grade al boton se crea un Font
			Font myFont1 = new Font("Serif", 20);
			//se añade el font al boton 
			articulo.setFont(myFont1);
			String nombre = aux.getNombre();
			//el texto del boton sera el nombre del articulo
			articulo.setText(nombre);
			//cuando se clike el boton se hara el metodo addBebidas
			articulo.setOnMouseClicked(addBebidas);
			//el boton se añade al HBox
			bebidas.getChildren().add(articulo);
		}
	}
	
	/**
	 * obtiene un articulo a traves del boton que ha llamado el metodo y crea un new P_Articulo en la bd
	 */
	@FXML
	EventHandler<MouseEvent> addBebidas = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			//devuelve el objeto que ha llamado el elvento y se hace un casting a button
			Button source = (Button) event.getSource();
			//se obtiene el nombre del boton
			String nombre = source.getText();
			//se obtiene un articulo a traves del nombre 
			Articulo nuevo = ArticuloDao.get(nombre);
			//se crea un nuevo P_articulo con el articulo obtenido
			ElegirAlimentoController.newP_articulo(nuevo);
			Utils.info("Se añadio el articulo " + nombre);

		}
	};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//se muestra bebidas al iniziar la vista
		mostrarBebidas();

	}

}
