package src;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioDestruido;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionEdificioPrevioRequerido;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoColocarseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuministrosInsuficientes;
import excepciones.ExcepcionTopeDePoblacionMaxima;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.construcciones.Construccion;
import src.construcciones.Arquitecto;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.razas.Protoss;
import src.razas.Raza;
import src.unidades.Unidad;

public class Jugador {
	
	private static final int POBLACION_MAXIMA = 200;
	
	private String nombre;
	private String color;
	private Raza raza;
	private int poblacionDisponible; // cantidad de unidades que pueden crearse porque hay "casas" construidas
	private int poblacionActual; // cantidad de unidades ya creadas
	private Dinero dineroJugador;
	ArrayList <Construccion> construccionesEnCamino;
	ArrayList<Construccion> construccionesEnPie;
	AtaquesPermitidosPorTurno ataques;
	private Juego juego;
	ArrayList<Unidad> unidadesAlistadas;
	
	public Jugador(String nombre, String color, Raza raza) {
		
		setNombre(nombre);
		setColor(color);
		setRaza(raza);
		setPoblacionDisponible(5);
		setDinero(800,400);
		this.construccionesEnCamino= new ArrayList<Construccion>();	
		this.construccionesEnPie= new ArrayList <Construccion>();
		this.ataques= new  AtaquesPermitidosPorTurno();
		this.unidadesAlistadas= new ArrayList<Unidad>();
			
	}
	
	public Jugador() {
		
		setPoblacionDisponible(10);
		setDinero(800,400);
		
	}		
	
	public String getNombre() {
		
		return nombre;
		
	}
	
	public AtaquesPermitidosPorTurno getAtaquesPermitidosPorTurno(){
		
		return ataques;
	
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
	
	public int getPoblacionDisponible() { 
		
		return poblacionDisponible; 

	}
	
	public void setJuego (Juego juego){
		
		this.juego= juego;
		
	}
	
	public Juego getJuego(){
		
		return this.juego;
		
	}
	
	public void setPoblacionDisponible(int numero) { 
		
		poblacionDisponible = numero; 
	
	}
	
	public Dinero getDinero() { 
		
		return dineroJugador; 
		
	}
	
	public void setDinero(int minerales,int gasVespeno) { 
		
		dineroJugador = new Dinero(minerales,gasVespeno);
		
	}
		
	public Construccion colocar(Construccion edificio, Mapa map, int i, int j) throws ExcepcionNoPudoColocarseEdificio  {
	
		try {				
			edificio.setPosicionX(i);
			edificio.setPosicionY(j);
			edificio.setAlrededores();
			edificio.colocar(map);
			this.getRaza().actualizarEdificios(edificio);
			this.gastarPlata(edificio.getCosto());
			poblacionDisponible = poblacionDisponible + edificio.getCantidadDeSuministros();			
		} catch (ExcepcionPosicionInvalida | ExcepcionExtractoraSinRecurso
				| ExcepcionYaHayElementoEnLaPosicion  e) {
				throw new ExcepcionNoPudoColocarseEdificio(e);
		}		
		
		return edificio;
	}
	
	public void verificacionEdificio(Construccion edificio) throws ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente{
		
		this.getRaza().verificarEdificioPosible(edificio.getEdificioRequerido());
		this.gastoPosible(edificio.getCosto());
		
	}
	
	private void gastoPosible(Dinero costo) throws ExcepcionRecursoInsuficiente{
		
		if (this.getDinero().getMinerales() < costo.getMinerales()){
			throw new ExcepcionRecursoInsuficiente("No hay suficientes minerales");
		}
		if (this.getDinero().getGasVespeno() < costo.getGasVespeno()){
			throw new ExcepcionRecursoInsuficiente("No hay suficiente gas");
		}
		
	}
	
	public Unidad actualizarPorNuevaUnidad(Unidad aEntrenar, Creadora edificio, Mapa map) {
		
			poblacionDisponible = poblacionDisponible - aEntrenar.getSuministros();
			poblacionActual = poblacionActual + aEntrenar.getSuministros();
			this.gastarPlata(aEntrenar.getCosto());					
			return aEntrenar;
		
	}
	
	public void verificacionUnidad(Unidad unidad, Creadora edificio) throws ExcepcionUnidadNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionSuministrosInsuficientes, ExcepcionTopeDePoblacionMaxima{ 
		
		edificio.verificarUnidadCreable(unidad);
		this.gastoPosible(unidad.getCosto());
		this.alcanzanSuministros(unidad.getSuministros());
		
	}
	
	public void alcanzanSuministros(int suministros) throws ExcepcionSuministrosInsuficientes, ExcepcionTopeDePoblacionMaxima {
		
		if (poblacionDisponible < suministros ){
			throw new ExcepcionSuministrosInsuficientes("No hay suministros suficientes");
		}
		if (poblacionActual + suministros > POBLACION_MAXIMA){
			throw new ExcepcionTopeDePoblacionMaxima("No pueden crearse mas unidades");
		}
		
	}
	
	public void gastarPlata(Dinero costo) {
		
		dineroJugador.restar(costo);		
		
	}
	
	public void actualizarTurnoEnConstrucciones (Turno turno, Mapa map) throws ExcepcionNoPudoColocarseUnidad {
		
		int i = 0;
		while (construccionesEnPie.size()>i){
			try{
				construccionesEnPie.get(i).pasoTurno(turno,map,this);
				i++;
			}
			catch (ExcepcionEdificioDestruido e){
				construccionesEnPie.remove(i);
			}
		}
		
	}
		
	public ArrayList<Construccion> obtenerConstruccionesEnCamino (){
			
		return construccionesEnCamino;	
			
	}
	
	public ArrayList<Construccion> getConstruccionesEnPie(){
		
		return construccionesEnPie;
		
	}
	
	public void actualizarFabricacionConstrucciones (Turno turno, Mapa mapa) 
			throws ExcepcionNoPudoColocarseEdificio 	{
		
		int i=0;
		int turnosPasados;
		int turnoOrdenoFabricacion;
		
		while (this.construccionesEnCamino.size()>i){
			
			turnoOrdenoFabricacion = construccionesEnCamino.get(i).getTurnoInicioDeConstruccion();
			turnosPasados = (turno.devolverTurnoActual()- turnoOrdenoFabricacion)/2; 			
			
			if ( turnosPasados  >= construccionesEnCamino.get(i).getTiempoDeConstruccion()){				
				colocar(construccionesEnCamino.get(i), mapa, construccionesEnCamino.get(i).getPosicionX(), construccionesEnCamino.get(i).getPosicionY());
				construccionesEnPie.add(construccionesEnCamino.get(i));
				construccionesEnCamino.remove(i);				
			} 
			
			i++;	
		}
		
	}
	
	public void agragarAUnidadesAlistadas (Unidad unidad){
		
		this.unidadesAlistadas.add(unidad);	
		
	}

	public void pasoTurno(Turno turno, Mapa mapa) throws ExcepcionErrorPasoDeTurno 	{
		
		try{
			actualizarTurnoEnConstrucciones(turno, mapa);	
			actualizarFabricacionConstrucciones(turno, mapa);
			
		} catch ( ExcepcionNoPudoColocarseEdificio | ExcepcionNoPudoColocarseUnidad e){
			throw new ExcepcionErrorPasoDeTurno(e);
		}
		
		
		
		Iterator<Unidad> i = unidadesAlistadas.iterator();
		while(i.hasNext()){
			Unidad unidad = i.next();
			unidad.pasoTurno();
		}
	
	}
	
}
