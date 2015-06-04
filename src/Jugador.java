package src;

import src.construcciones.Construccion;
import src.razas.Protoss;
import src.razas.Raza;

public class Jugador {
	
	private String nombre;
	public String getNombre(){return nombre;}
	public void setNombre(String n){ nombre = n; }
	
	private String color;
	public String getColor(){return color;}
	public void setColor(String c){ color = c;	}
	
	private Raza raza;
	public void setRaza(Raza r){ raza = r; }
	
	private int poblacion;
	public int getPoblacion(){ return poblacion; }
	public void setPoblacion(int numero){ poblacion = numero; }
	
	private Dinero dineroJugador;
	public Dinero getDinero(){ return dineroJugador; }
	public void setDinero(int minerales,int gasVespeno){ 
		dineroJugador = new Dinero(minerales,gasVespeno);
	}
	
	public Jugador(String nombre, String color, Raza raza){
		setNombre(nombre);
		setColor(color);
		setRaza(raza);
		setPoblacion(10);
		setDinero(800,400);
	}
	
	public Jugador(){
		setPoblacion(10);
		setDinero(800,400);
	}		
	
	public void construir(Construccion edificio) {
		int cantidadMineral = dineroJugador.getMinerales();
		cantidadMineral = cantidadMineral - edificio.getCosto().getMinerales();
		dineroJugador.setMinerales(cantidadMineral);		
	}
	
}
