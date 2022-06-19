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

public class ModificaEmpleadoRoot implements Initializable {
	
	private static Empleado empleado;
	
	@FXML
	TextField user;
	
	@FXML
	TextField password;
	
	@FXML
	ChoiceBox<String> trabajo;
	
	
	@FXML
	Button cancelar;
	
	@FXML
	Button modificar;
	
	
	//vuelve a la vista empleadoRoot
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("empleadoRoot");
	}
	
	//modifica el empleado con los datos de los campos y cambia de vista a empleadoroot
	@FXML void modificaEmplado() throws IOException {
	//se crea un empleado con los datos campos
	Empleado nuevo = new Empleado(user.getText(), password.getText(), trabajo.getValue());
	//si el usuario es correcto se inserta y se cambia de vista
	Boolean valid=EmpleadoRoot.ValidUserRoot(nuevo);
		if(valid) {
			EmpleadoDao.updateEmpleado(nuevo, empleado.getUser(), empleado.getPassword());
			App.setRoot("empleadoRoot");
		}
	}
	
	public static void setEmpleado(Empleado aux) {
		empleado=aux;
	}
	
	private void updateData() {
		user.setText(empleado.getUser());
		password.setText(empleado.getPassword());
		trabajo.setValue(empleado.getTrabajo());
	}

	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trabajo.getItems().add("camarero");
		trabajo.getItems().add("cocinero");
		updateData();
		
		
	}
}
