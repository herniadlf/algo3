package src;

import java.util.ArrayList;
import java.util.Iterator;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioDestruido;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElEdificioNoPerteneceATusConstrucciones;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionFueraDelRangoDeVision;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoColocarseUnidad;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuministrosInsuficientes;
import excepciones.ExcepcionTopeDePoblacionMaxima;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.mapa.Sector;
import src.razas.Raza;
import src.unidades.Magia;
import src.unidades.Magica;
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
	ArrayList<Unidad> unidadesAlistadas;
	ArrayList<Sector> rangoDeVision;

	private Juego juego;
	
	public Jugador(String nombre, String color, Raza raza) {
		
		setNombre(nombre);
		setColor(color);
		setRaza(raza);
		setPoblacionDisponible(5);
		setDinero(999,999);
		this.construccionesEnCamino= new ArrayList<Construccion>();	
		this.construccionesEnPie= new ArrayList <Construccion>();
		this.ataques= new  AtaquesPermitidosPorTurno();
		this.unidadesAlistadas= new ArrayList<Unidad>();
		rangoDeVision = new ArrayList<Sector>();
			
	}
	
	public Jugador() {
		
		setPoblacionDisponible(5);
		setDinero(999,999);
		this.construccionesEnCamino= new ArrayList<Construccion>();	
		this.construccionesEnPie= new ArrayList <Construccion>();
		this.ataques= new  AtaquesPermitidosPorTurno();
		this.unidadesAlistadas= new ArrayList<Unidad>();
		rangoDeVision = new ArrayList<Sector>();
		
	}			
	
	public ArrayList<Unidad> getUnidadesAlistadas (){
		
		return this.unidadesAlistadas;
		
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
	
	public void setPoblacionDisponible(int numero) { 
		
		poblacionDisponible = numero; 
	
	}
	
	public Dinero getDinero() { 
		
		return dineroJugador; 
		
	}
	
	public void setDinero(int minerales,int gasVespeno) { 
		
		dineroJugador = new Dinero(minerales,gasVespeno);
		
	}
		
	public Construccion colocar(Construccion edificio, Mapa map, int i, int j) throws 
		ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente {
	
		try {				
			//verificacionEdificio(edificio);
			edificio.setPosicionX(i);
			edificio.setPosicionY(j);
			edificio.setAlrededores();
			edificio.colocar(map);
			this.getRaza().actualizarEdificios(edificio);
			poblacionDisponible = poblacionDisponible + edificio.getCantidadDeSuministros();	
			
		} catch (ExcepcionPosicionInvalida | ExcepcionExtractoraSinRecurso
				| /*ExcepcionConstruccionNoCorrespondiente
				|*/ ExcepcionYaHayElementoEnLaPosicion  e) {
				throw new ExcepcionNoPudoColocarseEdificio(e);
		}		
		
		return edificio;
		
	}
	/* for testing */
	public void verificacionEdificio(Construccion edificio) throws ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente{
		verificarEdificioPrevio(edificio.getEdificioRequerido());
		this.getRaza().verificarEdificioPosible(edificio);
		this.gastoPosible(edificio.getCosto());
		
	}
	public void verificacionEdificio(Construccion edificio, int x, int y, Mapa map) throws 
		ExcepcionConstruccionNoCorrespondiente, ExcepcionPosicionInvalida, ExcepcionFueraDelRangoDeVision, ExcepcionRecursoInsuficiente{
		
		Sector auxiliar = map.obtenerContenidoEnPosicion(x, y);
		if (!rangoDeVision.contains(auxiliar)) { throw new ExcepcionFueraDelRangoDeVision("Fuera del rango de vision"); }
		getRaza().verificarEdificioPosible(edificio);
		verificarEdificioPrevio(edificio.getEdificioRequerido());
		gastoPosible(edificio.getCosto());
		
	}
	
	private void verificarEdificioPrevio(Construccion edificioRequerido) throws ExcepcionConstruccionNoCorrespondiente {
		int i= 0;
		Boolean founded = false;
		while ( (i < construccionesEnPie.size()) && !founded ) {
			founded = (construccionesEnPie.get(i).esLoMismo(edificioRequerido));
			i++;
		}
		if (!founded) {
			throw new ExcepcionConstruccionNoCorrespondiente("Edificio no habilitado");
		}
		
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
	
	public void verificacionUnidad(Unidad unidad, Creadora edificio) throws 
		ExcepcionUnidadNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionSuministrosInsuficientes, ExcepcionTopeDePoblacionMaxima{ 
		
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
	
	public void actualizarTurnoEnConstrucciones (Turno turno, Mapa map) throws 
		ExcepcionNoPudoColocarseUnidad, ExcepcionUnidadNoCorrespondiente {
		
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
	
	public void actualizarFabricacionConstrucciones (Turno turno, Mapa mapa) throws
		ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente 	{
		
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
				i--;
			} 
			
			i++;	
		}
		
	}
	
	public void agregarAUnidadesAlistadas (Unidad unidad){
		
		this.unidadesAlistadas.add(unidad);	
		
	}
	
	public void moverUnidadAPosicion(Unidad unidad, int posX, int posY) throws ExcepcionNoPuedeMoverseUnidad, ExcepcionLaUnidadNoPertenceATuTropa {
		
		if(contieneALaUnidad(unidad)){
			unidad.moverAPosicionDeterminada(posX, posY);
		}
		else{	
			throw new ExcepcionLaUnidadNoPertenceATuTropa("La unidad seleccionada no pertenece a tu tropa de unidades");
		}
		
	}

	public void pasoTurno(Turno turno, Mapa mapa) throws 
		ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente{
		
		try{
			
			actualizarTurnoEnRaza();
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

	private void actualizarTurnoEnRaza() {
		
		getRaza().pasoTurno();		
		
	}
	
	public boolean contieneALaUnidad(Unidad unaUnidad) {
		
		return unidadesAlistadas.contains(unaUnidad);
		
	}
	
	public boolean contieneALaConstruccion(Construccion unaConstruccion) {
		
		return construccionesEnPie.contains(unaConstruccion);
		
	}
	
	public void verificarUnidad(Unidad unaUnidad) throws ExcepcionLaUnidadNoPertenceATuTropa{
		
		if(!this.contieneALaUnidad(unaUnidad)){
			
			throw new ExcepcionLaUnidadNoPertenceATuTropa("La unidad seleccionada no pertenece a tu tropa de unidades");
			
		}
		
	}
	
	public void atacarCon(Unidad agresor, Atacable victima) throws ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente{
			
		this.verificarUnidad(agresor);
		agresor.atacar(victima);	
		
	}
	
	public void atacarCon(Magica agresor, Magia magia) throws ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa{
			
		this.verificarUnidad(agresor);
		agresor.atacar(magia);		
		
	}

	public void verificarEdificio(Creadora edificio) throws ExcepcionElEdificioNoPerteneceATusConstrucciones {
		
		if(!getConstruccionesEnPie().contains(edificio)){
			
			throw new ExcepcionElEdificioNoPerteneceATusConstrucciones("El edificio seleccionado no pertenece a tus construcciones");
		}
		
	}

	public void setRangoDeVision(int posX,int posY,int rango, Mapa map) {
		
		for (int i = posX-rango; i <= posX+rango; i++){
			for (int j = posY-rango; j<= posY+rango;j++){
				try {
					Sector auxiliar = map.obtenerContenidoEnPosicion(i, j);
					if (!rangoDeVision.contains(auxiliar)){
						rangoDeVision.add(auxiliar);					
					}
				}
				catch (ExcepcionPosicionInvalida e){}
			}
		}	
		
	}
	
	public ArrayList<Sector> getRangoDeVision(){
		return rangoDeVision;
	}
	public void setJuego(Juego nuevoJuego) {
		
		juego = nuevoJuego;
		
	}

	public void actualizarMuerteDeUnidad(Unidad unidad) {
		Boolean fin = false;
		int i = 0;
		while (i < unidadesAlistadas.size() && !fin) {
			if (unidadesAlistadas.get(i) == unidad){
				unidadesAlistadas.remove(i);
				poblacionActual = poblacionActual - unidadesAlistadas.get(i).getSuministros();
				poblacionDisponible = poblacionDisponible + unidadesAlistadas.get(i).getSuministros();
				fin = true;
			}
			i++;
		}
		
	}
	
	public int getPoblacionActual(){
		return poblacionActual;
	}
				
}
