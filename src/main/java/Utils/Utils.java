package Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import Model.Empleado;

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
	
		/**
		 * comprueba que el empleado es valido
		 * @param ob empleado que se quiere saber si es correcto 
		 * @return
		 */
		public static boolean ValidUserRoot(Empleado ob) {
			//si el usuario y la contraseña tiene mas de 3 caracteres y el trabajo es camarero o cocinero el un usuario correcto
			String user = ob.getUser();
			String pass = ob.getPassword();
			String trabajo= ob.getTrabajo();
			if(user.length()>3 && pass.length()>3) {
				if(trabajo.equals("camarero") || trabajo.equals("cocinero") ) {
					return true;
				}
			}
			return false;
		}
}
