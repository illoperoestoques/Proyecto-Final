package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import javafx.scene.control.Alert;


public class Utils {
	/**
	 * codifica palabras a sha-256
	 * @param input frase o palabra que se quiera codificar
	 * @return devuelve un string codificada a partir del string introducido
	 */
	public static String getSHA256(String input){

		String toReturn = null;
		try {
		    MessageDigest digest = MessageDigest.getInstance("SHA-256");
		    digest.reset();
		    digest.update(input.getBytes("utf8"));
		    toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		return toReturn;
	    }
	
	public static void error (String header, String context) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		alert.setContentText(context);
		alert.showAndWait();
	}
	
	public static void info (String header) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.show();
		}
	
	
	
	
	
	
	
	
		
		
		
		
		
		
}
