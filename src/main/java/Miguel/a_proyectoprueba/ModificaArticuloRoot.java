package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Articulo;
import ModelDao.ArticuloDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModificaArticuloRoot implements Initializable {
	private static Articulo art;
	
	@FXML
	TextField nombre;
	
	@FXML
	TextField precio;
	
	@FXML
	TextField tipo;
	
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
		int i = Integer.parseInt(precio.getText());
		int tip = Integer.parseInt(tipo.getText());
		Articulo nuevo= new Articulo(art.getId(), nombre.getText(), i, tip);
		//actualiza el articulo
		ArticuloDao.updateArticulo(nuevo);
		//cambia de vista
		App.setRoot("articulosRoot");
	}
	
	private void updateData() {
		nombre.setText(art.getNombre());
		precio.setText(String.valueOf(art.getPrecio()));
		tipo.setText(String.valueOf(art.getTipo()));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateData();
		
	}
}
