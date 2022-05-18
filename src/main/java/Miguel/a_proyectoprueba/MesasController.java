package Miguel.a_proyectoprueba;

import java.io.IOException;

import interfaces.IMesasController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MesasController implements IMesasController {
	@FXML
	private ImageView button;
	@FXML
	private Label button2;
	@FXML
	private Button mesa1;
	@FXML
	private Button mesa2;
	@FXML
	private Button mesa3;
	@FXML
	private Button mesa4;
	@FXML
	private Button mesa5;
	@FXML
	private Button mesa6;
	@FXML
	private Button mesa7;
	@FXML
	private Button mesa8;
	@FXML
	private Button mesa9;
	
	//cambia a la vista primary cuando clika en boton1 o boton2
	@FXML
	public void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	//le pasa la mesa escogida a Camarero Controller y cambia de vista
	@FXML
	public void sMesa(MouseEvent event) throws IOException {
		Button source = (Button) event.getSource();
		//obtiene el int de la mesa escogida
		int mesa = Integer.parseInt(source.getText());
		//se agrega la mesa escogida a CamareroEleccion
		CamareroEleccionController.addMesa(mesa);
		//cabia de vista
		App.setRoot("camareroEleccion");

	}

}