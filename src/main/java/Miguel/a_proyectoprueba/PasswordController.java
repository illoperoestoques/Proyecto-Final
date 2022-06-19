package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Empleado;
import ModelDao.EmpleadoDao;
import Utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class PasswordController implements Initializable {
	private EmpleadoDao eDao = new EmpleadoDao();
	private static Empleado empleadoModify;
	
	@FXML
	private Label texto;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button ok;
	
	@FXML
	private Button cancelar;
	
	@FXML
	private void validPassword() throws IOException {
		boolean valid = eDao.validUser(empleadoModify.getUser(), password.getText());

		if (valid == true) {
			ModificaEmpleadoRoot.setEmpleado(empleadoModify);
			App.setRoot("modificaEmpleadoRoot");
		}else {
			Utils.error("Contrasela Incorrecta", "Introduce la contraseña correcta del usuario para poder modificarlo");
		}
	}
	
	public static void setEmpleado(Empleado e) {
		empleadoModify = e;
	}
	
	@FXML
	private void switchToEmpleadoRoot() throws IOException {
		App.setRoot("empleadoRoot");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		texto.setText("Introduce la contraseña de " + empleadoModify.getUser());
		
	}
	

}
