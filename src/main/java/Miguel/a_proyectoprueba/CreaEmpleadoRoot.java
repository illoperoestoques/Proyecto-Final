package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.EmpleadoRoot;
import Model.Empleado;
import ModelDao.EmpleadoDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreaEmpleadoRoot implements Initializable {
	@FXML
	TextField user;

	@FXML
	TextField password;

	@FXML
	ChoiceBox<String> trabajo;

	@FXML
	Button cancelar;

	@FXML
	Button crear;

	// vuelve a la vista de articulosRoot
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("empleadoRoot");
	}

	// crea un empleado y cambia de vista a empleadoRoot
	@FXML
	void crearEmplado() throws IOException {
		// se crea un empleado con los datos de los campos
		Empleado nuevo = new Empleado(user.getText(), password.getText(), trabajo.getValue());
		// si el usuario es valido se crea el empleado y se cambia de vista
		Boolean valid = EmpleadoRoot.ValidUserRoot(nuevo);
		if (valid == true) {
			EmpleadoDao.insert(nuevo);
			App.setRoot("empleadoRoot");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// el choicebox te deja eleguir entre camarero o cocinero para el trabajo del
		// nuevo empleado
		trabajo.setValue("camarero");
		trabajo.getItems().add("camarero");
		trabajo.getItems().add("cocinero");

	}
}
