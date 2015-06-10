package src;

import java.util.ArrayList;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionEdificioPrevioRequerido;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
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
	ArrayList <Unidad> unidadesEnFabricacion;
	ArrayList<Mapeable> construccionesEnFabricacion;
	
	public Jugador(String nombre, String color, Raza raza) {
		
		setNombre(nombre);
		setColor(color);
		setRaza(raza);
		setPoblacionDisponible(0);
		setDinero(800,400);
		this.unidadesEnFabricacion= new ArrayList<Unidad>();	
		this.construccionesEnFabricacion= new ArrayList <Mapeable>();
		
	}
	
	public Jugador() {
		
		setPoblacionDisponible(10);
		setDinero(800,400);
		
	}		
	
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
	
	public int getPoblacionDisponible() { 
		
		return poblacionDisponible; 

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
		
	
	public Construccion construir(Construccion edificio, Mapa map, int x, int y)  {
	
		try {
			
			this.verificacionEdificio(edificio);
			edificio.setPosicionX(x);
			edificio.setPosicionY(y);
			edificio.setAlrededores();
			edificio.getConstructor().construir(map,edificio);
			this.getRaza().actualizarEdificios(edificio);
			this.gastarPlata(edificio.getCosto());
			poblacionDisponible = poblacionDisponible + edificio.getCantidadDeSuministros();
			
		} catch (ExcepcionConstruccionNoCorrespondiente
				| ExcepcionRecursoInsuficiente | ExcepcionPosicionInvalida | ExcepcionExtractoraSinRecurso
				| ExcepcionYaHayElementoEnLaPosicion  e) {
	
			e.printStackTrace();
		}		
		
		return edificio;
	}
	
	private void verificacionEdificio(Construccion edificio) throws ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente{
		
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
	
	public Unidad crearUnidad(Unidad aEntrenar, Creadora edificio, Mapa map) throws ExcepcionEdificioNoPuedeCrearUnidad {
		
		try {
			
			this.verificacionUnidad(aEntrenar, edificio);
			poblacionDisponible = poblacionDisponible - aEntrenar.getSuministros();
			poblacionActual = poblacionActual + aEntrenar.getSuministros();
			this.gastarPlata(aEntrenar.getCosto());
			edificio.entrenarUnidad(aEntrenar,map);
			
		} catch (ExcepcionUnidadNoCorrespondiente
				| ExcepcionRecursoInsuficiente
				| ExcepcionSuministrosInsuficientes | ExcepcionPosicionInvalida | ExcepcionNoHayLugarParaCrear
				| ExcepcionYaHayElementoEnLaPosicion |
				ExcepcionTopeDePoblacionMaxima e) {
			throw new ExcepcionEdificioNoPuedeCrearUnidad("No pudo crearse la unidad requerida");	
		}
		
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
	
	public void actualizarFabricacionUnidades (Turno turno, Mapa map) throws ExcepcionEdificioNoPuedeCrearUnidad{
		
		int i=1;
		int turnosPasados;
		int turnoOrdenoFabricacion;
		while (this.unidadesEnFabricacion.size()>i){
			
			turnoOrdenoFabricacion = this.unidadesEnFabricacion.get(i).getTurnoDeEntrenamiento();
			turnosPasados = (turno.devolverTurnoActual()- turnoOrdenoFabricacion); 
			
			if ( turnosPasados  >= unidadesEnFabricacion.get(i).getTiempoDeCreacion()){
				
				this.crearUnidad(unidadesEnFabricacion.get(i),unidadesEnFabricacion.get(i).getEdifico(), map);
				unidadesEnFabricacion.remove(i);
				
			} 
			
			i++;	
		}
		
	}
		
		
	public ArrayList<Unidad> obtenerListaDeUnidadesAFabrica (){
			
		return unidadesEnFabricacion;	
			
	}

	public void actualizarFabricacionConstrucciones (){
		
	}
	
}
