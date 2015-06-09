package src.construcciones;

import java.util.LinkedList;

import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;
import src.mapa.Posicion;


public class Construccion implements Mapeable{
	
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
	public int getVida(){ return vida.obtenerVida(); }
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
	protected Arquitecto constructor;
	public void setConstructor(Arquitecto c){
		constructor = c;
	}
	public Arquitecto getConstructor(){
		return constructor;
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
	
	
}
