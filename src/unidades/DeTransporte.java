package src.unidades;

import java.util.ArrayList;
import java.util.LinkedList;

import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionElTransporteNoEstaEnElAlcancePermitido;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionNoSePuedeTransportar;
import excepciones.ExcepcionNoSePuedenTransportasUnidadesVoladoras;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;
import src.mapa.Mapeable;
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
			unidadesAbordo.add(unidad);
			
				
		}
		catch (ExcepcionNoSePuedenTransportasUnidadesVoladoras | ExcepcionElTransporteEstaLleno |
				ExcepcionElTransporteNoEstaEnElAlcancePermitido e){
			
				throw new ExcepcionNoSePuedeTransportar(e);
		}
	}
	
	private void verificarNaveVoladora(Unidad unidad) throws ExcepcionNoSePuedenTransportasUnidadesVoladoras {
		if (unidad.getTransporte() == 0){
			throw new ExcepcionNoSePuedenTransportasUnidadesVoladoras("Solo transporto unidades terrestres"); 
		}
		
	}

	private void verificarAsientosDisponibles(Unidad unidad) throws ExcepcionElTransporteEstaLleno {
		if (unidadesAbordo.size() + unidad.getTransporte() > TRANSPORTE){
			throw new ExcepcionElTransporteEstaLleno("El transporte esta lleno");
		}
		
	}

	private void verificarDistancia(Unidad unidad) throws ExcepcionElTransporteNoEstaEnElAlcancePermitido {
		int distanciaALaNave = mapa.distanciaEntreLosPuntos(unidad.getPosicionX(), unidad.getPosicionY(), this.getPosicionX(), this.getPosicionY());
		if (distanciaALaNave > getVision() ) {
			throw new ExcepcionElTransporteNoEstaEnElAlcancePermitido("El transporte esta demasiado lejos para abordarlo");
		}
		
	}

	public int cantidadPasajeros() {
		
		return unidadesAbordo.size();
		
	}
	
	
	public void transportarUnidades(int x, int y) throws ExcepcionNoPuedeMoverseUnidad, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		int i=0;
		Mapa mapa = this.getMapa();
		this.moverAPosicionDeterminada(x, y);
		mapa.eliminarElementoAereoEnPosicion(this.getPosicionX(), this.getPosicionY());
		Posicion auxiliar = new Posicion (x,y);
		this.setPosicion(auxiliar);
		this.setAlrededores();
		
		while (i< unidadesAbordo.size()){
			Unidad unidad = unidadesAbordo.get(i);
			this.colocarUnidad(unidad, unidad.getMapa());
			
			i++;
			
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

	
	@Override
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
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
		return false;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void recibirDanio() throws ExcepcionPosicionInvalida {
		// TODO Auto-generated method stub

	}
	
	public void afectadoPorRadiacion(int danio){
		
		//supuesto: no afecta a naves
		
	}

}
