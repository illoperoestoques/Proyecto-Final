package Miguel.a_proyectoprueba;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Camarero;
import Model.Cocinero;
import Model.Empleado;
import ModelDao.EmpleadoDao;
import interfaces.IPrimaryController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable, IPrimaryController {
	private EmpleadoDao eDao = new EmpleadoDao();
	private static Empleado empleadoLogin;

	@FXML
	private ImageView button;
	@FXML
	private TextField user;
	@FXML
	private PasswordField password;
	@FXML
	private ImageView buttonExit;
	@FXML
	private Label button2Exit;

	//verificael usuario en la bd y si es correcto lo cambia a una vista
	@FXML
	public void logIn() throws IOException {
		//se obtiene el usuario y contraseña
		String usuario = user.getText();
		String pass = password.getText();
		//verifica el usuario
		boolean valid = eDao.validUser(usuario, pass);
		Empleado e = null;
		// si esta en la bd
		if (valid == true) {
			//se obtiene un usuario a traves del nombre de usuario
			e = eDao.get(usuario);
			//se obtien el trabajo que tiene 
			String trabajo = e.getTrabajo();
			//si el trabajo es camarero la vista cambia a mesas
			if (trabajo.equals("camarero")) {
				empleadoLogin= new Camarero(usuario, pass);
				App.setRoot("mesas");
			} else if (e.getTrabajo().equals("cocinero")) {
				empleadoLogin= new Cocinero(usuario, pass);
				App.setRoot("cocineroPedidos");
			}else if(e.getTrabajo().equals("admin")) {
				App.setRoot("root");
			}
		
		}

	}
	
	//salir del programa
	@FXML
	public void exit() {
		handle();
	}
	//Obtiene el emplado que a iniciado secion
	public static Empleado getEmpleado() {
		return empleadoLogin;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void handle() {
		Platform.exit();
		System.exit(0);
	}

}
