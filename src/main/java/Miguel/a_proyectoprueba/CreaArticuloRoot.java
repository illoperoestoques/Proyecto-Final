package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.ArticuloRoot;
import Utils.Utils;

import Model.Articulo;
import ModelDao.ArticuloDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreaArticuloRoot implements Initializable {
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField precio;
	
	@FXML
	ChoiceBox<String> tipo;
	
	@FXML
	Button cancelar;
	
	@FXML
	Button crear;
	
	
	//vuelve a la vista de articulosRoot
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("articulosRoot");
	}
	//crea un articulo a traves de los datos insertados en los campos y cambia de vita a artiulosRoot
	@FXML void crearArticulo() throws IOException {
		//se obtiene los datos de los campos
		String nom = nombre.getText(); 
		boolean validna= ArticuloRoot.validNombreArtRoot(nom);
		int pre = ArticuloRoot.ValidPrecioComidaRoot(precio.getText());
		int tip =ArticuloRoot.ValidTipoComidaRoot(tipo.getValue());
		boolean valid=ArticuloRoot.validArtRoot(validna, pre, tip);
		if(valid) {
			//se crea un articulo a traves de los datos
			Articulo nuevo= new Articulo(0, nom, pre, tip);
			//se inserta en la bd
			ArticuloDao.insert(nuevo);
			App.setRoot("articulosRoot");
		}
		
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tipo.setValue("Bebida");
		tipo.getItems().add("Comida");
		tipo.getItems().add("Bebida");
		
	}
}
