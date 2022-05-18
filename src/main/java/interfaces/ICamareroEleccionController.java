package interfaces;

import java.io.IOException;

public interface ICamareroEleccionController {
	public void switchToBack() throws IOException;
	public void switchToMenu() throws IOException;
	public void switchToEliminaPedido() throws IOException;
	public void switchToPagarCuenta() throws IOException;
	public void switchToModificaPedido() throws IOException ;
	
}
