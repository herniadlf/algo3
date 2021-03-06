package src.unidades;

import java.util.ArrayList;
import java.util.LinkedList;

import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionElTransporteNoEstaEnElAlcancePermitido;
import excepciones.ExcepcionFueraDelRangoDeVision;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionNoSePuedenTransportasUnidadesVoladoras;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;
import src.mapa.Posicion;

public abstract class DeTransporte extends Unidad {

	protected ArrayList<Unidad> unidadesAbordo;
	protected LinkedList<Posicion> alrededores;
	private static final int TRANSPORTE = 8;
	
	public void llevar(Unidad unidad) throws ExcepcionNoSePuedeTransportar, ExcepcionPosicionInvalida{
		
		try{
			
			verificarDistancia(unidad);
			verificarAsientosDisponibles(unidad);
			verificarNaveVoladora(unidad);
			
			mapa.eliminarElementoTerrestreEnPosicion(unidad.getPosicionX(), unidad.getPosicionY());
			unidad.setPosicion(getPosicion());
			getUnidadesAbordo().add(unidad);	
				
		}
		catch (ExcepcionNoSePuedenTransportasUnidadesVoladoras | ExcepcionElTransporteEstaLleno |
				ExcepcionElTransporteNoEstaEnElAlcancePermitido e){
			
				throw new ExcepcionNoSePuedeTransportar(e);
				
		}
		
	}
	
	public ArrayList<Unidad> getUnidadesAbordo() {
		
		return this.unidadesAbordo;
	}
	
	private void verificarNaveVoladora(Unidad unidad) throws ExcepcionNoSePuedenTransportasUnidadesVoladoras {
		
		if (unidad.esAereo()){
			throw new ExcepcionNoSePuedenTransportasUnidadesVoladoras("Solo transporto unidades terrestres"); 
		}
		
	}

	private void verificarAsientosDisponibles(Unidad unidad) throws ExcepcionElTransporteEstaLleno {
		
		if (unidadesAbordo.size() + unidad.getTransporte() > TRANSPORTE){
			throw new ExcepcionElTransporteEstaLleno("El transporte esta lleno");
		}
		
	}

	private void verificarDistancia(Unidad unidad) throws ExcepcionElTransporteNoEstaEnElAlcancePermitido {
		
		int distANave = mapa.distanciaEntreLosPuntos(unidad.getPosicionX(), unidad.getPosicionY(), this.getPosicionX(), this.getPosicionY());
		if (distANave > getVision() ) {
			throw new ExcepcionElTransporteNoEstaEnElAlcancePermitido("El transporte esta demasiado lejos para abordarlo");
		}
		
	}

	public int cantidadPasajeros() {
		
		return unidadesAbordo.size();
		
	}
	
	public void transportarUnidades(int x, int y) throws 
		ExcepcionNoPuedeMoverseUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		int i=0;
		Mapa mapa = this.getMapa();
		moverAPosicionDeterminada(x, y);		
		setAlrededores();
		
		while (!(unidadesAbordo.size() == 0)){
			Unidad unidad = unidadesAbordo.get(i);
			this.colocarUnidad(unidad, unidad.getMapa());
			unidadesAbordo.remove(i);					
		}
		
	} 
	
	public void setAlrededores(){
		
		alrededores = new LinkedList<Posicion>();
		alrededores.add(new Posicion(this.getPosicionX()-1,this.getPosicionY()+1));
		alrededores.add(new Posicion(this.getPosicionX(),this.getPosicionY()+1));
		alrededores.add(new Posicion(this.getPosicionX()+1,this.getPosicionY()+1));
		alrededores.add(new Posicion(this.getPosicionX()+1,this.getPosicionY()));
		alrededores.add(new Posicion(this.getPosicionX()+1,this.getPosicionY()-1));
		alrededores.add(new Posicion(this.getPosicionX(),this.getPosicionY()-1));
		alrededores.add(new Posicion(this.getPosicionX()-1,this.getPosicionY()-1));
		alrededores.add(new Posicion(this.getPosicionX()-1,this.getPosicionY()));	
		
	}

	public void colocarUnidad ( Unidad aColocar , Mapa map ) 
		throws ExcepcionPosicionInvalida, 
		ExcepcionNoHayLugarParaCrear, 
		ExcepcionYaHayElementoEnLaPosicion {		
	
		Posicion auxiliar = aColocar.getColocador().posicionAColocar(aColocar,map,alrededores);		
		map.colocarEn(auxiliar.getX(), auxiliar.getY(), aColocar);
		aColocar.setMapa(map);
		aColocar.setPosicion(auxiliar);
	
	}
	
	
	public boolean esTerrestre() {
		
		return false;
		
	}


	public boolean esAereo() {
		
		return true;
		
	}

	public void afectadoPorRadiacion(int danio){
		
		//supuesto: no afecta a naves
		
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
			actualizarUnidadesABordo(getPosicion());
			jugador.setRangoDeVision(x,y,vision,mapa);
		
		}
		
		catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion | ExcepcionFueraDelRangoDeVision e) {
			
			throw new ExcepcionNoPuedeMoverseUnidad(e);
		}
		
	}

	private void actualizarUnidadesABordo(Posicion pos) throws ExcepcionNoPuedeMoverseUnidad {
		for (int i = 0; i < unidadesAbordo.size(); i++){
			unidadesAbordo.get(i).setPosicion(pos);;
		}		
	}
}
