package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Articulo;
import ModelDao.ArticuloDao;
import Utils.Utils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ComidaController implements Initializable {

	@FXML
	private HBox comidas;

	@FXML
	private ImageView buttonAtras;
	@FXML
	private ImageView buttonOk;
	@FXML
	private ImageView buttonShowMenu;

		
	//cambia a menu cuando clikas ButtonAtras
	@FXML
	private void switchToMenu() throws IOException {
		App.setRoot("menu");
	}
	//cambia a mesas cuando clikas ButtonOK
	@FXML
	private void switchToMesas() throws IOException {
		App.setRoot("mesas");
	}
	//cambia a infoPedido cuando clikas 
	@FXML
	private void switchToPedido() throws IOException {
		App.setRoot("infoPedido");
	}
	//muestra todas las comidasen en el HBox en forma de boton
	@FXML
	private void mostrarComidas() {
		//se obtiene un arraylist de Articulo con todas los tipos de comida
		ArrayList<Articulo> bdcomidas = ArticuloDao.getComidas();
		//se recorre el arraylist
		for (Articulo aux : bdcomidas) {
			//se crea el boton
			Button articulo = new Button();
			//se crea un font para el texto del boton
			Font myFont1 = new Font("Serif", 20);
			//se añade el font al boton
			articulo.setFont(myFont1);
			String nombre = aux.getNombre();
			//se agrega el nombre del articulo como texto del boton 
			articulo.setText(nombre);
			//cuando clicke el boton se ejecutara el metodo addComidas
			articulo.setOnMouseClicked(addComidas);
			//se agrega el boton al hbox
			comidas.getChildren().add(articulo);
		}
	}

	//agrega un P_Articulo a la base de datos 
	@FXML
	EventHandler<MouseEvent> addComidas = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			//se obtien el boton que ha llamado el metodo 
			Button source = (Button) event.getSource();
			String nombre = source.getText();
			//se crea un articulo a traves del nomre del boton 
			Articulo nuevo = ArticuloDao.get(nombre);
			//se crea un new P_Articulo
			ElegirAlimentoController.newP_articulo(nuevo);
			Utils.info("Se añadio el articulo " + nombre);
			
			

		}
	};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//al iniziar la vista se muestran los botones en el HBox
		mostrarComidas();
	}

}
