package src.unidades;


import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;

public abstract class Unidad implements Mapeable{
	
	protected String nombre;
	protected int transporte;
	protected int vision;
	protected Dinero costo;
	protected int tiempoDeCreacion;
	protected Danio danio;
	protected int suministro;
	protected int rangoAtaque;
	protected Vida vida;	
	
	public Unidad(){
			
	}
	
	public void setNombre(String s){
		nombre = s;
	}
	public  void setTransporte(int t){
		transporte = t;
	}
	public  void setVision(int v){
		vision = v;
	}
	public void setCosto(int minerales, int gas){
		costo = new Dinero(minerales,gas);	
	}
	public  void setTiempoDeCreacion(int t){
		tiempoDeCreacion = t;
	}
	public  void setSuministros(int s){
		suministro = s;
	}
	public  void setRangoDeAtaques(int r){
		rangoAtaque = r;
	}
	public  void setVida(Vida v){
		vida = v;
	}
	public void setDanio(int danioAire, int danioTierra){
		danio = new Danio(danioAire,danioTierra);		
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
	
	public abstract void atacarEnTierra (Unidad unidad);
	public abstract void atacarEnAire (Unidad unidad);
	public abstract void recibirDanio (int Danio);
	public boolean esLoMismo(Mapeable aComparar){
		return (this.getNombre() == aComparar.getNombre());
	}
	
}