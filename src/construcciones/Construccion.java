package src.construcciones;

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
	
	private int posicionX;
	public int getPosicion(){ return posicionX; };
	public void setPosicion(int posX){
		posX = p;
	}
	private int posicionY;
	public int getPosicion(){ return posicionY; };
	public void setPosicion(int posY){
		posY = p;
	}
	
	public Construccion(){}
	
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
	
	
}