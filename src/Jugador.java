package src;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import src.construcciones.Construccion;
import src.construcciones.ConstructorStrategy;
import src.mapa.Mapa;
import src.razas.Protoss;
import src.razas.Raza;
import test.ExcepcionEdificioPrevioRequerido;

public class Jugador {
	
	private String nombre;
	public String getNombre(){return nombre;}
	public void setNombre(String n){ nombre = n; }
	
	private String color;
	public String getColor(){return color;}
	public void setColor(String c){ color = c;	}
	
	private Raza raza;
	public void setRaza(Raza r){ raza = r; }
	public Raza getRaza(){
		return raza;
	}
	
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
	
	public void construir(Construccion edificio, Mapa map, int x, int y) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionEdificioPrevioRequerido {
		if (this.getRaza().verificarEdificioPosible(edificio.getEdificioRequerido())){
			edificio.setPosicionX(x);
			edificio.setPosicionY(y);
			edificio.getConstructor().construir(map,edificio);		
			this.gastarPlata(edificio.getCosto());
			this.getRaza().actualizarEdificios(edificio);
		}
		else {
				throw new ExcepcionEdificioPrevioRequerido("Requiere construir otro edificio antes del solicitado.");
		}
	}
	private void gastarPlata(Dinero costo) {
		dineroJugador.restar(costo);		
	}
	
}
