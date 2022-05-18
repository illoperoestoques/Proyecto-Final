package interfaces;

import java.io.IOException;

import javafx.event.ActionEvent;

public interface iCocineroPedidoController {
	public void switchToBack() throws IOException;
	public void updateTable();
	public void altertablerow(ActionEvent event) throws IOException;
}
