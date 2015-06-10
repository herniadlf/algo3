package src.unidades;


import java.util.ArrayList;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Atacable;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.mapa.Posicion;

public abstract class Unidad implements Atacable{
	
	protected String nombre;
	protected int transporte;
	protected int vision;
	protected Dinero costo;
	protected int tiempoDeCreacion;
	protected Danio danio;
	protected int suministro;
	protected int rangoAtaque;
	protected Vida vida;	
	protected ArrayList <Unidad> atacantes;
	protected Posicion posicion;
	protected Mapa mapa;
	protected Entrenador entrenador;
	protected int turnoInicioEnterenamiento;
	protected Creadora edificio;
	
	public void setMapa (Mapa mapa){
		
		this.mapa =mapa;
		
	}
	
	public void setEdificio (Creadora e){
		edificio = e;
		
		}
	
	public Creadora getEdifico (){
		return edificio;
		
		
	} 
	
	
	public void setPosicion (Posicion posicion){
		
		this.posicion = posicion;
		
	}
	
	public void setTurnoInicioDeEntrenamiento (int turno){
		
		this.turnoInicioEnterenamiento= turno;
		
		
	}
	public int getTurnoDeEntrenamiento (){
		return this.turnoInicioEnterenamiento;
		
		
	}
	
	public Entrenador getEntrenador(){
		
		return entrenador;
		
	}
	public  String getNombre(){
		
		return nombre;
		
	}
	public  int getTransporte(){
		
		return transporte;
		
	}
	public  int getVision (){
		
		return vision;
		
	}
	public  Dinero getCosto(){
		
		return costo;
		
	}
	public  int getTiempoDeCreacion(){
		
		return tiempoDeCreacion;
		
	}
	public  int getSuministros(){
		
		return suministro;
		
	}
	public  int getRangoDeAtaques(){
		
		return rangoAtaque;
		
	}
	public  Vida getVida(){
		
		return vida;
		
	}
	public  Danio getDanio(){
		
		return danio;
		
	}
	
	public ArrayList<Unidad> getUnidadesAtacantes(){
		
		return atacantes;
	
	}
	
	public void atacarEnAire (Atacable atacado){
		
		int danio = this.getDanio().getDanioAire();
		atacado.getVida().aumentarDanioARecibir(danio);
		atacado.recibirDanio();

	} 
	
	
	public void atacarEnTierra(Atacable atacado){
		
		int danio = this.getDanio().getDanioTierra();
		atacado.getVida().aumentarDanioARecibir(danio);
		atacado.recibirDanio();
		
	}

	public abstract void recibirDanio ();
	
	public boolean esLoMismo(Mapeable aComparar){
		
		return (this.getNombre() == aComparar.getNombre());
		
	}
	
	public void moverAPosicionDeterminada (int x, int y) throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		mapa.colocarEn(x, y, this);
		mapa.eliminarElementoTerrestreEnPosicion(posicion.getX(), posicion.getY());
		
		/* eliminar de las cordenadas anteriores*/
		
	}
	
}