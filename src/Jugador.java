package src;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionEdificioPrevioRequerido;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.construcciones.Construccion;
import src.construcciones.Arquitecto;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.razas.Protoss;
import src.razas.Raza;
import src.unidades.Unidad;

public class Jugador {
	
	private String nombre;
	private String color;
	private Raza raza;
	private int poblacion;
	private Dinero dineroJugador;
	
	public String getNombre() {
		
		return nombre;
		
	}
	public void setNombre(String nombre) { 
	
		this.nombre = nombre; 
	
	}
	
	public String getColor() {
		
		return color;
		
	}
	public void setColor(String color) { 
		
		this.color = color;	
	
	}
	
	public void setRaza(Raza raza) { 
		
		this.raza = raza;
	
	}
	public Raza getRaza() { 
		
		return raza;
		
	}
	
	public int getPoblacion() { 
		
		return poblacion; 

	}
	
	public void setPoblacion(int numero) { 
		
		poblacion = numero; 
	
	}
	
	
	public Dinero getDinero() { 
		
		return dineroJugador; 
		
	}
	public void setDinero(int minerales,int gasVespeno) { 
		
		dineroJugador = new Dinero(minerales,gasVespeno);
		
	}
		
	public Jugador(String nombre, String color, Raza raza) {
		
		setNombre(nombre);
		setColor(color);
		setRaza(raza);
		setPoblacion(10);
		setDinero(800,400);
		
	}
	
	public Jugador() {
		
		setPoblacion(10);
		setDinero(800,400);
		
	}		
	
	public Construccion construir(Construccion edificio, Mapa map, int x, int y) 
			throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, 
			ExcepcionEdificioPrevioRequerido, ExcepcionYaHayElementoEnLaPosicion {
		
		if (this.getRaza().verificarEdificioPosible(edificio.getEdificioRequerido())) {
			this.gastarPlata(edificio.getCosto());
			edificio.setPosicionX(x);
			edificio.setPosicionY(y);
			edificio.setAlrededores();
			edificio.getConstructor().construir(map,edificio);
			this.getRaza().actualizarEdificios(edificio);//pensar 		
		}
		else {
			throw new ExcepcionEdificioPrevioRequerido("Requiere construir otro edificio antes del solicitado.");			
		}
		return edificio;
	}
	
	public Unidad crearUnidad(Unidad aEntrenar, Creadora edificio, Mapa map) 
			throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, 
			ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionYaHayElementoEnLaPosicion{
		
		if ( edificio.verificarUnidadCreable(aEntrenar) ){
			this.setPoblacion( this.getPoblacion() + aEntrenar.getSuministros() );
			this.gastarPlata(aEntrenar.getCosto());
			edificio.entrenarUnidad(aEntrenar,map); //pensar 
		}
		else {
			throw new ExcepcionEdificioNoPuedeCrearUnidad("No puede crearse esta unidad en este edificio");
		}		
		return aEntrenar;
	}
	
	private void gastarPlata(Dinero costo) {
		
		dineroJugador.restar(costo);		
		
	}
	
}
