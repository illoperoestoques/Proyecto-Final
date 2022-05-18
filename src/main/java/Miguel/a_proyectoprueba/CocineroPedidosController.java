package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.P_Articulo;
import Model.Pedido;
import ModelDao.PedidoDao;
import interfaces.iCocineroPedidoController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CocineroPedidosController implements Initializable, iCocineroPedidoController {

	@FXML
	Button back;

	@FXML
	Button select;

	@FXML
	TableView<Pedido> pedidos;

	@FXML
	TableColumn<Pedido, Integer> id_P;

	@FXML
	TableColumn<Pedido, Integer> id_Mesa;

	// cambia a la vista de primary
	@FXML
	public void switchToBack() throws IOException {
		App.setRoot("primary");
	}

	// actualiza los datos de la tabla
	public void updateTable() {
		// se obtiene los pedidos que se quiere mostrar
		ArrayList<Pedido> pedidos_art = PedidoDao.PedidosCocina();
		ObservableList<Pedido> oList = FXCollections.observableArrayList(pedidos_art);

		// se setean las celdas a los valores del pedido

		id_P.setCellValueFactory(Pedido -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(Pedido.getValue().getId());
			return a;

		});

		id_Mesa.setCellValueFactory(Pedido -> {
			ObservableValue<Integer> a = new SimpleIntegerProperty().asObject();
			((ObjectProperty<Integer>) a).setValue(Pedido.getValue().getId_mesa());
			return a;

		});

		pedidos.getItems().addAll(oList);

	}

	// se cambia a la vista de cocineroCocina
	@FXML
	public void altertablerow(ActionEvent event) throws IOException {
		// se pasa el objeto sellecionado a la clase controlador de la siguiente vista
		CocineroCocinaController.setPedido(pedidos.getSelectionModel().getSelectedItem());
		App.setRoot("cocineroCocina");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateTable();

	}

}
