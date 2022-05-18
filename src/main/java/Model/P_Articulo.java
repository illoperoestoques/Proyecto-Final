package Model;

import java.util.ArrayList;


public class P_Articulo {
	private Pedido pedido;
	private Articulo articulo;
	private int cantidad;
	private String estado;
	private Empleado empleado;

	

	public P_Articulo(Pedido pedido, Articulo articulo, int cantidad, String estado, Empleado empleado) {
		this.pedido = pedido;
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.estado = estado;
		this.empleado = empleado;
	}
	
	

	public P_Articulo() {
		this.pedido = null;
		this.articulo = null;
		this.cantidad = 0;
		this.estado =  "nada";
		this.empleado = null;
	}



	public Pedido getPedidido() {
		return pedido;
	}

	public void setPedidido(Pedido pedidido) {
		this.pedido = pedidido;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	

	public Pedido getPedido() {
		return pedido;
	}



	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Empleado getEmpleado() {
		return this.empleado;
	}
	
	public void setEmpleado(Empleado empleado) {
		this.empleado=empleado;
	}
	
	

	@Override
	public String toString() {
		return "P_Articulo [pedido=" + pedido + ", articulo=" + articulo + ", cantidad=" + cantidad + ", estado="
				+ estado + ", empleado=" + empleado + "]";
	}

	/**
	 * compueba y devuelve si el P_Articulo esta en el ArrayList
	 * @param p_art Arraylist con P_Articulos
	 * @return true si el P_Articulo esta en la arraylist
	 */
	public boolean equalArray(ArrayList<P_Articulo> p_art) {
		//si el arraylist no esta vacio 
		if (!p_art.isEmpty()) {
			//recorre el arraylist
			for (P_Articulo a : p_art) {
				//si uno de los articulos son iguales devuelve true 
				if (a.getArticulo().equals(this.articulo)) {
					return true;
				}
			}
		}
		return false;
	}

	
	
	/**
	 * suma el precion de todos los P_Articuls de todos los articulos 
	 * @param p_art Arraylist con P_Articulos
	 * @return
	 */
	public static int ArraytoPrice(ArrayList<P_Articulo> p_art) {
		int total=0;
		//recorre el arraylist
		for(P_Articulo a  : p_art) {
				Articulo aux = a.getArticulo();
				int cantidad=a.getCantidad();
				int price=aux.getPrecio();
				//calcula el precio * la cantidad de producto
				total+=(price*cantidad);
			}
		return total;
	}
		
	
	
	
	
	
	
	
	
	
	
	

	
}
