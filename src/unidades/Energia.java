package src.unidades;

public class Energia {
	
	int cantidad;
	
	public Energia(int cantidad){
		
		this.cantidad = cantidad;
		
	}
	
	public void disminuirEnergia(int energiaDisminuida){
		
		cantidad = cantidad - energiaDisminuida;
		
	}
	
	public void aumentarEnergia(int masCantidad){
		
		cantidad = cantidad + masCantidad;
		
	}
	
	public int obtenerCantidad(){
		
		return cantidad;
		
	}
	
	
}
