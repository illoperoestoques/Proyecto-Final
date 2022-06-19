package Model;

import interfaces.IArticulo;

public class Articulo implements IArticulo {
	private int id;
	private String nombre;
	private int precio;
	private int tipo;
	
	//constructor por defecto
	public Articulo() {
		this.id = 0;
		this.nombre = "nada";
		this.precio = 0;
		this.tipo = 0;
	}
	//constructor con atricutos
	public Articulo(int id, String nombre, int precio, int tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + "]";
	}
	//compara dos articulos por su nombre
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null) {
			if (this == obj) {
				result = true;
			} else {
				if (obj instanceof Articulo) {
					if (this.nombre != null && this.nombre.equals(((Articulo) obj).nombre)) {
						result = true;
					}

				}
			}
		}
	return result;
}
	
	public static String tipoComida(int tipo) {
		if(tipo==1) {
			return "Comida";
		}else if(tipo==0) {
			return "Bebida";
		}
		return "nada";
	}
 

}



