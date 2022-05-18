import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Miguel.a_proyectoprueba.App;
import Model.Empleado;
import ModelDao.EmpleadoDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class modificaEmpleadoRoot implements Initializable {
	
	private static Empleado empleado;
	jj
	
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
	
	
	
	@FXML
	private void switchToBack() throws IOException {
		App.setRoot("articulosRoot");
	}
	
	@FXML void modificaEmplado() throws IOException {
	Empleado nuevo = new Empleado(user.getText(), password.getText(), trabajo.getValue());
	Boolean valid=Utils.Utils.ValidUserRoot(nuevo);
		if(valid==true) {
			EmpleadoDao.insert(nuevo);
			App.setRoot("empleadoRoot");
		}
	}
	
	public static void setEmpleado(Empleado aux) {
		empleado=aux;
	}

	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trabajo.getItems().add("Camarero");
		trabajo.getItems().add("Cocinero");
		
		
	}
}
