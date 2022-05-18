package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.P_Articulo;
import ModelDao.P_ArticuloDao;
import interfaces.IModificaPedidoController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class ModificaPedidoController implements Initializable, IModificaPedidoController {
	@FXML
	TableView<P_Articulo> pedido;
	@FXML
	TableColumn<P_Articulo, String> nombre;
	@FXML
	TableColumn<P_Articulo, Integer> cantidad;
	@FXML
	TableColumn<P_Articulo, String> estado;

	@FXML
	private TextField cantidad_insertar;
	@FXML
	private ChoiceBox<String> estado_insertar;
	@FXML
	private Button update;
	@FXML
	private ImageView back;

	// cambia de vista a camareroEleccion
	@FXML
	public void switchToBack() throws IOException {
		App.setRoot("camareroEleccion");
	}
	
	//actualiza los datos de la tabla 
	public void updateTable() {
		pedido.getItems().clear();
		ArrayList<P_Articulo> pedidos_art = CamareroEleccionController.infoPedido();
		ObservableList<P_Articulo> oList = FXCollections.observableArrayList(pedidos_art);

		nombre.setCellValueFactory(Pedido -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Pedido.getValue().getArticulo().getNombre());
			return a;
		});

		cantidad.setCellValueFactory(InfoPedido -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(InfoPedido.getValue().getCantidad());
			return a;

		});

		estado.setCellValueFactory(Pedido -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Pedido.getValue().getEstado());
			return a;
		});

		pedido.getItems().addAll(oList);

	}
	
	//se insertan los posibles valores que se puden elegir en el choiceBox
	public void InsertChoiceBox() {
		estado_insertar.getItems().add("no entregado");
		estado_insertar.getItems().add("entregado");

	}

	// se actualizan los datos de los campos con los del objeto sellecionado
	@FXML
	public void updatevalues() throws IOException {
		P_Articulo nuevo = pedido.getSelectionModel().getSelectedItem();
		cantidad_insertar.setText(String.valueOf(nuevo.getCantidad()));
		estado_insertar.setValue(nuevo.getEstado());
		
	}
	
	//actualiza lel pedido con los datos de los campos y actualiza la tabla
	@FXML
	public void updatePedido() {
		if(cantidad_insertar.getText()!=null && estado_insertar.getValue()!=null) {
			P_Articulo nuevo = pedido.getSelectionModel().getSelectedItem();
			int cantidad=Integer.parseInt(cantidad_insertar.getText());
			String estado = estado_insertar.getValue();
			nuevo.setCantidad(cantidad);
			nuevo.setEstado(estado);
			if(cantidad ==0 || cantidad < 0) {
				P_ArticuloDao.eliminaP_Articulo(nuevo);
			}else {				
				P_ArticuloDao.updateP_Articulo(nuevo);
			}
			
			updateTable();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateTable();
		InsertChoiceBox();

	}

}
