package interfaces;

import java.io.IOException;

public interface IBebidasController {
	public void switchToMenu() throws IOException;
	public void switchToMesas() throws IOException;
	public void switchToPedido() throws IOException;
	public void mostrarBebidas() ;
}
