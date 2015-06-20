package src.unidades;


import java.util.ArrayList;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Atacable;
import src.AtaquesPermitidosPorTurno;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.construcciones.Creadora;
import src.mapa.Mapa;
import src.mapa.Mapeable;
import src.mapa.Posicion;

public abstract class Unidad implements Atacable{
	
	protected String duenio;
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
	protected ColocadorDeUnidades colocador;
	protected int turnoInicioEntrenamiento;
	protected Creadora edificio;
	protected AtaquesPermitidosPorTurno ataques;
	protected boolean afectadoPorRadiacion;
	
	public void setMapa (Mapa mapa){
		
		this.mapa =mapa;
		
	}
	
	public Mapa getMapa(){
		
		return mapa;
		
	}
	
	public abstract void pasoTurno ();
	
	public void setAtaquesPermitidosPorTurno(AtaquesPermitidosPorTurno ata){
		this.ataques = ata;
		
		
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
		
		this.turnoInicioEntrenamiento= turno;
		
		
	}
	public int getTurnoDeEntrenamiento (){
		return this.turnoInicioEntrenamiento;
		
		
	}
	
	public ColocadorDeUnidades getColocador(){
		
		return colocador;
		
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
	
	public int getPosicionX (){
		return posicion.getX();
		}
	
	public int getPosicionY(){
		return posicion.getY();	
		
	}	
	
	public ArrayList<Unidad> getUnidadesAtacantes(){
		
		return atacantes;
	
	}
	
	public void atacarEnAire (Atacable atacado) throws ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente{
		
		int distanciaEntreEnemigos = mapa.distanciaEntreLosPuntos(atacado.getPosicionX(), atacado.getPosicionY(), this.getPosicionX(), this.getPosicionY());
		if ((distanciaEntreEnemigos) < this.getVision()){

		int danio = this.getDanio().getDanioAire();
		atacado.getVida().aumentarDanioARecibir(danio);
		atacado.recibirDanio();
		ataques.aumentarAtaquesPermitidos();
		
		} else {
			
			throw new ExcepcionElementoFueraDelRangoDeAtaque("El enemigo se encuentra fuera del rango de ataque");
		
		}
	} 
	
	
	public void atacarEnTierra(Atacable atacado) throws ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionErrorPasoDeTurno, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente{
		int distanciaEntreEnemigos = mapa.distanciaEntreLosPuntos(atacado.getPosicionX(), atacado.getPosicionY(), this.getPosicionX(), this.getPosicionY());
		
		if ((distanciaEntreEnemigos) < this.getVision()){
		
		int danio = this.getDanio().getDanioTierra();
		atacado.getVida().aumentarDanioARecibir(danio);
		atacado.recibirDanio();
		ataques.aumentarAtaquesPermitidos();
		
		}else {
			
			throw new ExcepcionElementoFueraDelRangoDeAtaque("El enemigo se encuentra fuera del rango de ataque");
		
		}
		
	}

	public abstract void recibirDanio () throws ExcepcionPosicionInvalida;
	
	public boolean esLoMismo(Mapeable aComparar){
		
		return (this.getNombre() == aComparar.getNombre());
		
	}
	
	public void moverAPosicionDeterminada (int x, int y) throws ExcepcionNoPuedeMoverseUnidad {
		
		try {
			
		mapa.colocarEn(x, y, this);
		mapa.eliminarElementoTerrestreEnPosicion(posicion.getX(), posicion.getY());
		Posicion nuevaPosicion = new Posicion(x,y);
		this.setPosicion(nuevaPosicion);
		
		}
		
		catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion e) {
			
			throw new ExcepcionNoPuedeMoverseUnidad(e);
		}
	}
	
		
	public String getDuenio() {
		return duenio;
	}
	

	public void setDuenio(String duenio) {
		this.duenio = duenio;
	}
			
		
}
