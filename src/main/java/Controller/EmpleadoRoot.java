package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Empleado;
import Utils.Utils;

public class EmpleadoRoot {
	/**
	 * comprueba que el empleado es valido
	 * @param ob empleado que se quiere saber si es correcto 
	 * @return
	 */
	public static boolean ValidUserRoot(Empleado ob) {
		//si el usuario y la contraseña tiene mas de 3 caracteres y el trabajo es camarero o cocinero el un usuario correcto
		String user = ob.getUser();
		boolean validname = validNameUser(user);
		String pass = ob.getPassword();
		boolean validpass = validPassUser(pass);
		String trabajo= ob.getTrabajo();
		if(validname && validpass && (trabajo.equals("camarero") || trabajo.equals("cocinero"))) {
			return true;
		}
		return false;
	}
	
	private static boolean validNameUser(String palabra) {
		 Pattern pat = Pattern.compile("^[A-Za-z]{4,15}$");
		 Matcher mat = pat.matcher(palabra);
		 if(mat.find()){
			 return true;
		 }else{
			 Utils.error("Nombre de usuario no valido", "Introduce otro nombre de usuario valido");
			 return false;
		 }
	}
	private static boolean validPassUser(String palabra) {
		 Pattern pat = Pattern.compile("^[a-zA-Z0-9]{4,}$");
		 Matcher mat = pat.matcher(palabra);
		 if(mat.find()){
			 return true;
		 }else{
			 Utils.error("Contraseña de usuario no valida", "Introduce otra Contraseña de usuario valido");
			 return false;
		 }
	}
}
