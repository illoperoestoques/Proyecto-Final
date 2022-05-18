package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.P_Articulo;
import Model.Pedido;
import ModelDao.P_ArticuloDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PagarCuentaController implements Initializable {
	@FXML 
	Label total;
	@FXML
	TableView<P_Articulo> pedido ;
	@FXML
	TableColumn<P_Articulo, Integer> id;
	@FXML
	TableColumn<P_Articulo, String> nombre;
	@FXML
	TableColumn<P_Articulo, Integer> cantidad;
	@FXML
	TableColumn<P_Articulo, Integer> precio;
	@FXML
	TableColumn<P_Articulo, String> empleado;
	
	@FXML
	Button cancelar;
	@FXML
	Button confirmar;
	
	//vuelve a camareroEleccion
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("camareroEleccion");
	}
	
	//actualia los datos de la tabla
	private void updateTable() {
		ArrayList<P_Articulo>pedidos_art= CamareroEleccionController.infoPedido();
		ObservableList<P_Articulo> oList = FXCollections.observableArrayList(pedidos_art);
		
		id.setCellValueFactory(InfoPedido -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(InfoPedido.getValue().getPedidido().getId());
			return a;
			
		});
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
		
		empleado.setCellValueFactory(InfoPedido -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(InfoPedido.getValue().getEmpleado().getUser());
			return a;
		});
		
		
		pedido.getItems().addAll(oList);
	
	}
	
	//cambia el estado de todos los p_Articulos asociados a un id de un pedido a "pagado"
	@FXML
	private void pagarCuenta() throws IOException {
		Pedido pedido_Pagar= ElegirAlimentoController.getPedido();
		P_ArticuloDao.pagarPedido(pedido_Pagar);
		App.setRoot("mesas");
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateTable();
		total.setText(String.valueOf(CamareroEleccionController.infoPrecio()));

	}
	
}
