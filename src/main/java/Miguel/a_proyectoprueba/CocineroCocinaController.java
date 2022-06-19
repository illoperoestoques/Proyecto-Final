package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.P_Articulo;
import Model.Pedido;
import ModelDao.P_ArticuloDao;
import interfaces.ICocineroCocinaController;
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
import javafx.scene.image.ImageView;

public class CocineroCocinaController implements Initializable, ICocineroCocinaController {
	private static Pedido pedido;
	
	public static void setPedido(Pedido aux) {
		pedido = aux;
	}
	
	@FXML
	TableView<P_Articulo> tb_pedido;
	@FXML
	TableColumn<P_Articulo, String> nombre;
	@FXML
	TableColumn<P_Articulo, Integer> cantidad;
	@FXML
	TableColumn<P_Articulo, String> estado;

	@FXML
	private ChoiceBox<String> estado_insertar;
	@FXML
	private Button update;
	@FXML
	private ImageView back;

	// cambia de vista a cocineroPedidos cuando se clika back
	@FXML
	public void switchToBack() throws IOException {
		App.setRoot("cocineroPedidos");
	}
	//actualiza tb_pedido
	public void updateTable() {
		//se borran los objetos que tenga
		tb_pedido.getItems().clear();
		//se obtiene una observable list de los P_Articulo que se quieren mostrar 
		ArrayList<P_Articulo> pedidos_art = P_ArticuloDao.P_ArticuloCocina(pedido);
		ObservableList<P_Articulo> oList = FXCollections.observableArrayList(pedidos_art);
		
		//se setean las celdas para cada atributo del articulo
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

		tb_pedido.getItems().addAll(oList);
		 
	}

	//opciones que se se pudede escoger en el choiceBox
	public void InsertChoiceBox() {
		estado_insertar.getItems().add("no entregado");
		estado_insertar.getItems().add("cocinado");

	}

	//se actualiza el choiceBox con el estado del objeto seleecionado 
	@FXML
	public void updatevalues() throws IOException {
		P_Articulo nuevo = tb_pedido.getSelectionModel().getSelectedItem();
		estado_insertar.setValue(nuevo.getEstado());
		
	}
	
	//actualiza los datos del objeto seleccionado 
	@FXML
	public void updatePedido() {
		if( estado_insertar.getValue()!=null) {
			P_Articulo nuevo = tb_pedido.getSelectionModel().getSelectedItem();
			nuevo.setEmpleado(PrimaryController.getEmpleado());
			String estado = estado_insertar.getValue();
			boolean valid=nuevo.setEstadoSave(estado);
			if(valid==true && nuevo!=null) {
				//si hay la misma cantidad que la del P_articulo seleccionado devuelve true
				String vdDrop=P_ArticuloDao.updateP_Articulo1(nuevo,1);
				if(vdDrop.equals("true")) {
					//se elina el P_Articulo original
					P_ArticuloDao.eliminaP_Articulo(nuevo);
				}else {
					//se elimina x cantidad al p_articulo seleccionado
					P_ArticuloDao.calculaCantidad(nuevo, -1, nuevo.getEstado());
					
				}
				//devuelve true si ya hay algun P_Articulo seleccionado con el estado seleccionado
				String vdCreate=P_ArticuloDao.updateP_Articulo2(nuevo,estado,1);
				if(vdCreate.equals("true")) {
					P_ArticuloDao.calculaCantidad(nuevo, 1, estado);
				}else {
					nuevo.setEstado(estado);
					P_ArticuloDao.insert(nuevo);
				}
				
				updateTable();
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