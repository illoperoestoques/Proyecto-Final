package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.P_Articulo;
import interfaces.IInfoPedidoController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;


public class InfoPedidoController implements Initializable, IInfoPedidoController {
	@FXML
	TableView<P_Articulo> pedido;
	@FXML
	TableColumn<P_Articulo, String> nombre;
	@FXML
	TableColumn<P_Articulo, Integer> cantidad;
	@FXML
	TableColumn<P_Articulo, Integer> precio;
	@FXML
	TableColumn<P_Articulo, String> estado;
	
	

	@FXML
	private ImageView back;

	//cambia de vista a menu cuando se clika back
	@FXML
	public void switchToBack() throws IOException {
		App.setRoot("menu");
	}
	
	//actualiza la tableWiev con todos los P_Articulo que tiene un mismo id de pedido
	public void updateTable() {
		//se obtiene los p_Articulo a insertar en la tablewiev
		ArrayList<P_Articulo>pedidos_art= CamareroEleccionController.infoPedido();
		ObservableList<P_Articulo> oList = FXCollections.observableArrayList(pedidos_art);
		
		//se setean las celdas con los valores de P_Articulo
		nombre.setCellValueFactory(InfoPedido -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(InfoPedido.getValue().getArticulo().getNombre());
			return a;
		});
		
		
		cantidad.setCellValueFactory(InfoPedido -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(InfoPedido.getValue().getCantidad());
			return a;
			
		});
		
		precio.setCellValueFactory(InfoPedido -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(InfoPedido.getValue().getArticulo().getPrecio());
			return a;
			
		});
		
		estado.setCellValueFactory(InfoPedido -> {
			SimpleStringProperty a = new SimpleStringProperty();
			String estado=InfoPedido.getValue().getEstado();
			a.setValue(estado);
			return a;
		});
		
		//se añaden a la tabla los objetos de la observablelist
		pedido.getItems().addAll(oList);
	
	}
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateTable();

	}

}
