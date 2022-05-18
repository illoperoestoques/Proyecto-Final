package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Model.Empleado;
import ModelDao.EmpleadoDao;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmpleadoRootController implements Initializable {
	@FXML
	TableView<Empleado> tb_empleados;
	@FXML
	TableColumn<Empleado, String> user;
	@FXML
	TableColumn<Empleado, String> pass;
	@FXML
	TableColumn<Empleado, String> trabajo;
	
	@FXML
	Button back;
	
	@FXML
	Button modificar;
	@FXML
	Button crear;
	
	//vuelve a la vista de root
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("root");
	}
	
	//cambia a la vista de creaEmpleadoRoot
	@FXML
	private void switchToCrearEmpleadoRoot() throws IOException {
		App.setRoot("creaEmpleadoRoot");
	}
	
	//actualiza la tablaWiev
	private void updateTable() {
		//se obtiene todos los empleados que no sean administrador
		ArrayList<Empleado>empleados= EmpleadoDao.getAllEmpleadosNoAdmin();
		ObservableList<Empleado> oList = FXCollections.observableArrayList(empleados);
		
		//se setean las celdas de la tableWiev con los datos de la ObservableList
		user.setCellValueFactory(Empleado -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Empleado.getValue().getUser());
			return a;
		});
		
		
		pass.setCellValueFactory(Empleado -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Empleado.getValue().getPassword());
			return a;
		});
		
		trabajo.setCellValueFactory(Empleado -> {
			SimpleStringProperty a = new SimpleStringProperty();
			a.setValue(Empleado.getValue().getTrabajo());
			return a;
		});
		
	
		
		tb_empleados.getItems().addAll(oList);
		
		
	
	}
	
	//cambia de vista a modificaEmpleadoRoot
	@FXML
	private void altertablerow(ActionEvent event) throws IOException {
		//pasa el empleado a modificar a la clase controladora de la vista modificaEmpleadoRoot
		ModificaEmpleadoRoot.setEmpleado(tb_empleados.getSelectionModel().getSelectedItem());
		App.setRoot("modificaEmpleadoRoot");		
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		updateTable();
		
		
		
	}
}
