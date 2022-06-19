package Miguel.a_proyectoprueba;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Articulo;
import ModelDao.ArticuloDao;
import Utils.Utils;
import interfaces.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ArticulosRootController implements Initializable, IArticulosRootController {

	@FXML
	TableView<Articulo> tb_articulos;
	@FXML
	TableColumn<Articulo, Integer> id;
	@FXML
	TableColumn<Articulo, String> nombre;
	@FXML
	TableColumn<Articulo, Integer> precio;
	@FXML
	TableColumn<Articulo, String> tipo;
	
	@FXML
	Button back;
	
	@FXML
	Button modificar;
	@FXML
	Button crear;
	
	//vuelve a la vista de root principal
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("root");
	}
	
	// cambia a la vista a creaArticuloRoot
	@FXML
	private void switchToCrearArticuloRoot() throws IOException {
		App.setRoot("creaArticuloRoot");
	}
	
	//actualiza los datos del tableWiev
	public void updateTable() {
		//se obtiene una observable list de los articulos que queremos mostrar en la tabla
		ArrayList<Articulo>articulos= ArticuloDao.getBebidas();
		articulos.addAll(ArticuloDao.getComidas());
		ObservableList<Articulo> oList = FXCollections.observableArrayList(articulos);
		
		//se setean las celdas para cada atributo del articulo
		nombre.setCellValueFactory(Articulo -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Articulo.getValue().getNombre());
			return a;
		});
		
		
		id.setCellValueFactory(Articulo -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(Articulo.getValue().getId());
			return a;
			
		});
		
		precio.setCellValueFactory(Articulo -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(Articulo.getValue().getPrecio());
			return a;
			
		});
		
		tipo.setCellValueFactory(Articulo -> {
			SimpleStringProperty a = new SimpleStringProperty();
			if(Articulo.getValue().getTipo()==0) {
				a.setValue("Bebida");
			}else {
				a.setValue("Comida");
			}
			return a;
		});
		
		//se añaden los objetos de la lista a la tabla
		tb_articulos.getItems().addAll(oList);
		
		
	
	}
	
	// coge el objeto de la tabla seleccionada, y cambia de vista a
	// modificaArticuloRoot
	@FXML
	public void altertablerow(ActionEvent event) throws IOException {
		// la siguiente vista obtiene el articulo seleccionado
		if (tb_articulos.getSelectionModel().getSelectedItem() != null) {
			ModificaArticuloRoot.setArticulo(tb_articulos.getSelectionModel().getSelectedItem());
			App.setRoot("modificaArticuloRoot");
		}else {
			Utils.error("Articulo no seleccionado", "Selecciona una articulo para modificarlo");
		}
	}
	
	
	//al iniziar la vista se actualiza la tabla
	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		updateTable();
		
		
		
	}
	
}
