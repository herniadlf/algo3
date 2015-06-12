package src.construcciones;

import java.util.LinkedList;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Atacable;
import src.Dinero;
import src.Jugador;
import src.Turno;
import src.Vida;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.mapa.Posicion;


public class Construccion implements Atacable {
	
	private int turnoInicioDeConstruccion;
	public void setTurnoInicioDEConstruccion(int t){
		
		this.turnoInicioDeConstruccion=t;
		
	}
	
	public int getTurnoInicioDeConstruccion (){
		return this.turnoInicioDeConstruccion;
		
		
	} 
	
	private String nombre;
	public String getNombre(){return nombre;}
	public void setNombre(String n){ nombre = n;	}
	
	private int tiempoDeConstruccion;
	public int getTiempoDeConstruccion(){	return tiempoDeConstruccion;	}
	public void setTiempoDeConstruccion(int tiempo){ tiempoDeConstruccion = tiempo;	}
	
	private Dinero costo;
	public Dinero getCosto(){ 
		return costo; 
	}
	public void setCosto(int minerales,int gas){ 
		costo = new Dinero(minerales,gas);
	}	
	
	private Vida vida;
	public Vida getVida(){ return vida; }
	public void setVida(int v) {
		vida = new Vida(v); 
	}
	
	protected int posicionX;
	public int getPosicionX(){ return posicionX; };
	public void setPosicionX(int posX){
		posicionX = posX;
	}
	protected int posicionY;
	public int getPosicionY(){ return posicionY; };
	public void setPosicionY(int posY){
		posicionY = posY;
	}
	protected Arquitecto arquitecto;
	public void setArquitecto(Arquitecto c){
		arquitecto = c;
	}
	public Arquitecto getArquitecto(){
		return arquitecto;
	}
	
	public Construccion edificioRequerido;
	
	public Construccion getEdificioRequerido() {
		return edificioRequerido;
	}
	public void setEdificioRequerido(Construccion edificioRequerido) {
		this.edificioRequerido = edificioRequerido;
	}
	
	protected int capacidadDeSuministros;
	public int getCantidadDeSuministros(){
		return capacidadDeSuministros;
	}
	
	public Construccion(){
		capacidadDeSuministros = 0;
	}
	protected LinkedList<Posicion> alrededores;
	
	public void setAlrededores(){
		alrededores = new LinkedList<Posicion>();
		alrededores.add(new Posicion(posicionX-1,posicionY+1));
		alrededores.add(new Posicion(posicionX,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY));
		alrededores.add(new Posicion(posicionX+1,posicionY-1));
		alrededores.add(new Posicion(posicionX,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY));		
	}
	
	public boolean esLoMismo(Mapeable aComparar){
		return (this.getNombre() == aComparar.getNombre());
	}
	
	public Construccion colocarContenido(){
		return null;
	}

	@Override
	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void recibirDanio() {
		
		
	}

	@Override
	public void atacarConEMP(int danio) {
		//no afecta a construcciones
	}

	public void pasoTurno(Turno turno, Mapa map, Jugador jugador) throws ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion {
		//sera implementado		
	}

	public void verificarTerreno(Mapa map, int x, int y) 
			throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionYaHayElementoEnLaPosicion {
		arquitecto.verificarTerreno(map,x,y,this);	
	}

	public void colocar(Mapa map) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso, ExcepcionYaHayElementoEnLaPosicion {
		arquitecto.colocar(map, this);
	}
	
	
}
