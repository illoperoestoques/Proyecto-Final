package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.Utils;

public class ArticuloRoot {

	public static int ValidTipoComidaRoot(String tipo) {
		int result = -1;
		if (tipo.equals("Comida")) {
			result = 1;  
		} else if (tipo.equals("Bebida")) {
			result = 0;
		}
		return result;
	}
	
	public static int ValidPrecioComidaRoot(String precio) {
		try {
			int tip = Integer.parseInt(precio);
			if(tip >0 && tip <300) {
				return tip;
			}else {
			}
		} catch (Exception e) {
		
		}
		return -1;
	}
	
	public static boolean validNombreArtRoot(String palabra) {
		 Pattern pat = Pattern.compile("^[A-Za-z]{2,15}$");
		 Matcher mat = pat.matcher(palabra);
		 if(mat.find()){
			 return true;
		 }else{
			 return false;
		 }
	}
	
	public static boolean validArtRoot(boolean nombre, int pre, int tip) {
		boolean result = true;
		 if(!nombre) {
			 //nombre no valido
			 Utils.error("Nombre Erroneo", "El nombre introducido del articulo no es valido");
			 result = false;
		 }
		 if(pre == -1) {
			//precio no valido
			 Utils.error("Precio erroneo", "El precio introducido en el articulo no es valido");
			result = false;
		 }
		 if(tip == -1) {
			//tipo no valido
			 Utils.error("Tipo erroneo", "El tipo del articulo seleccionado no es valido");
			 result = false;
		 }
		 
		 return result;
	}
	
}
