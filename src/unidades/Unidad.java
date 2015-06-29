package src.unidades;


import java.util.ArrayList;

import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;

import excepciones.ExcepcionFinDeTurnoPorMaximoDeAtaques;
import excepciones.ExcepcionFueraDelRangoDeVision;

import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Atacable;
import src.AtaquesPermitidosPorTurno;
import src.Danio;
import src.Dinero;
import src.Escudo;
import src.Jugador;
import src.ReglaDeDanio;
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
	protected ReglaDeDanio reglaDeDanio;
	protected Jugador jugador;
	protected int danioRadiacion;
	
	public Unidad(){};
	
	
	public void setJugador (Jugador jugador){
		
		this.jugador = jugador;
		
	}
	
	public Jugador getJugador (){
		
		return this.jugador;
		
	}
	
	public void setMapa (Mapa mapa){
		
		this.mapa = mapa;
		
	}
	
	public Mapa getMapa(){
		
		return mapa;
		
	}
	
	public void pasoTurno (){
		
		if(afectadoPorRadiacion){
			Radiacion radiacion = new Radiacion(this);
			radiacion.atacar();
		}
		
	}
	
	public void setAtaquesPermitidosPorTurno(AtaquesPermitidosPorTurno ataques){
		
		this.ataques = ataques;
		
	} 
	
	public void setEdificio (Creadora edificio){
		
		this.edificio = edificio;
		
	}
	
	public Creadora getEdifico (){
		
		return edificio;
		
	} 
	
	public void setPosicion (Posicion posicion){
		
		this.posicion = posicion;
		
	}
	
	public Posicion getPosicion (){
		return posicion;
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
	
	public void atacar(Atacable atacado) throws ExcepcionPosicionInvalida, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionFinDeTurnoPorMaximoDeAtaques {
		
		int distEntreEnemigos = mapa.distanciaEntreLosPuntos(atacado.getPosicionX(),atacado.getPosicionY(),this.getPosicionX(),this.getPosicionY());
		int danioARecibir = 0;
		if ((distEntreEnemigos) < this.getVision()){
			
			if(atacado.esAereo()) {
				danioARecibir = this.getDanio().getDanioAire();
			}
			if(atacado.esTerrestre()) {
				danioARecibir = this.getDanio().getDanioTierra();
			}
			
			atacado.getVida().aumentarDanioARecibir(danioARecibir);
			atacado.recibirDanio();
			ataques.contabilizarAtaquesEnTurno();
		
		} else {
			
			throw new ExcepcionElementoFueraDelRangoDeAtaque("El enemigo se encuentra fuera del rango de ataque");
		
		}
		
	}

	public void recibirDanio () throws ExcepcionPosicionInvalida{
		
		reglaDeDanio.recibirDanio(this);
		this.getVida().reestablecerDanioRecibido();
		boolean estadoDeVidaFinalizado= vida.estaMuerto();
		  if (estadoDeVidaFinalizado==true){
			 mapa.eliminarElementoTerrestreEnPosicion(getPosicionX(),getPosicionY());
			 jugador.actualizarMuerteDeUnidad(this);
		 }
		  
	}
	
	public boolean esLoMismo(Mapeable aComparar){
		
		return (this.getNombre() == aComparar.getNombre());
		
	}
	
	public void moverAPosicionDeterminada (int x, int y) throws ExcepcionNoPuedeMoverseUnidad {
		
		try {		
			if ( mapa.distanciaEntreLosPuntos(x, y, getPosicionX(), getPosicionY()) > vision ) { throw new ExcepcionFueraDelRangoDeVision("No puede moverse unidad tan lejos"); }
			mapa.colocarEn(x, y, this);		
			if(this.esTerrestre()){
				
				mapa.eliminarElementoTerrestreEnPosicion(posicion.getX(), posicion.getY());
			}
			if(this.esAereo()) {
				
				mapa.eliminarElementoAereoEnPosicion(posicion.getX(), posicion.getY());
			}
		
			Posicion nuevaPosicion = new Posicion(x,y);
			setPosicion(nuevaPosicion);
			jugador.setRangoDeVision(x,y,vision,mapa);
		
		}
		
		catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion | ExcepcionFueraDelRangoDeVision e) {
			
			throw new ExcepcionNoPuedeMoverseUnidad(e);
		}
		
	}
	
	public void afectadoPorTormentaPsionica(int danio) throws ExcepcionPosicionInvalida{
		
		vida.aumentarDanioARecibir(danio);
		this.recibirDanio();
		vida.reestablecerDanioRecibido();
		
	}
	
	public void afectadoPorRadiacion(int danio){
		
		afectadoPorRadiacion = true;
		danioRadiacion = danio;
		vida.aumentarDanioARecibir(danioRadiacion);
		try {
			this.recibirDanio();
		} catch (ExcepcionPosicionInvalida e) {
			
		}
		vida.reestablecerDanioRecibido();
		
	}
	
		
	public String getDuenio() {
		
		return duenio;
		
	}
	
	public void setDuenio(String duenio) {
		
		this.duenio = duenio;
		
	}
	
	public Unidad duplicarConAlucinacion() {
		
		return this;
		
	}
	
	protected void modificarVidaYAtaqueDeUnidadAlucinada(Unidad duplicado){
		
		duplicado.vida = new Vida(1);
		duplicado.danio = new Danio(0, 0);
		
	}
	
	public abstract Escudo getEscudo();
		
}
