package interfaces;

import java.io.IOException;

import javafx.scene.input.MouseEvent;

public interface IMesasController {
	public void switchToPrimary() throws IOException;
	public void sMesa(MouseEvent event) throws IOException;
}
