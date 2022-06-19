package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.ArticuloRoot;
import Model.Articulo;
import ModelDao.ArticuloDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ModificaArticuloRoot implements Initializable {
	private static Articulo art;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField precio;
	
	@FXML
	ChoiceBox<String> tipo;
	
	@FXML
	Button cancelar;
	
	@FXML
	Button modificar;
	
	/**
	 * obtiene los datos para el articulo estatico
	 * @param articulo tiene los vaores que tendra articulo art
	 */
	public static void setArticulo(Articulo articulo) {
		art=articulo;
	}
	
	//cambia la vista a articulosRoot
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("articulosRoot");
	}
	
	//modifica el articulo y cambia de vista a articulosRoot
	@FXML void modificarArticulo() throws IOException {
		//se obtienen los datos
		String nom = nombre.getText(); 
		boolean validna= ArticuloRoot.validNombreArtRoot(nom);
		int pre = ArticuloRoot.ValidPrecioComidaRoot(precio.getText());
		int tip =ArticuloRoot.ValidTipoComidaRoot(tipo.getValue());
		boolean valid=ArticuloRoot.validArtRoot(validna, pre, tip);
		if(valid) {
			Articulo nuevo= new Articulo(art.getId(), nombre.getText(), pre, tip);
			//actualiza el articulo
			ArticuloDao.updateArticulo(nuevo);
			//cambia de vista
			App.setRoot("articulosRoot");
		}
	}
	
	private void updateData() {
		nombre.setText(art.getNombre());
		precio.setText(String.valueOf(art.getPrecio()));
		tipo.setValue(Articulo.tipoComida(art.getTipo()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tipo.getItems().add("Comida");
		tipo.getItems().add("Bebida");
		updateData();
		
	}
}
