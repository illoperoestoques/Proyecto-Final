package Miguel.a_proyectoprueba;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RootController {
	@FXML
	Button empleados;
	@FXML
	Button articulos;
	
	
	@FXML
	Button Back;
	
	//cambia a la vista primary
	@FXML
	private void swithBack() throws IOException {
		App.setRoot("primary");
	}
	
	//cambia a la vista articulosRoot
	@FXML
	private void swithtoArticulosRoot() throws IOException {
		App.setRoot("articulosRoot");
	}
	
	//cambia a la vista empleadosRoot
	@FXML
	private void swithtoEmpleadosRoot() throws IOException {
		App.setRoot("empleadoRoot");
	}

}
