package src;

public abstract class Unidad implements Mapeable{
	
	protected String nombre;
	protected int transporte;
	protected int vision;
	protected int costo;
	protected int tiempoDeCreacion;
	protected Danio danio;
	protected int	suministro;
	protected int rangoAtaque;
	protected Vida vida;
	
	public Unidad(){
		
		
	}
	
	public abstract void setearNombre();
	public abstract void setearTransporte();
	public abstract void setearVision ();
	public abstract void setearCosto();
	public abstract void setearTiempoDeCreacion();
	public abstract void setearSuministros();
	public abstract void setearRangoDeAtaques();
	public abstract void setearVida();
	public abstract void setearDanio();
	
	public abstract String getNombre();
	public abstract int getTransporte();
	public abstract int getVision ();
	public abstract int getCosto();
	public abstract int getTiempoDeCreacion();
	public abstract int getSuministros();
	public abstract int getRangoDeAtaques();
	public abstract Vida getVida();
	public abstract Danio getDanio();
	
	
	
	
}
