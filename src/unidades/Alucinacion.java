package src.unidades;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Atacable;
import src.mapa.Mapa;
import src.mapa.Posicion;

public class Alucinacion extends Magia{
	
	private Unidad copia;
	private boolean alucine;
	
	public Alucinacion(Unidad alucinado){
		
		energiaNecesaria = 100;
		mapa = alucinado.getMapa();
		posicionX = alucinado.getPosicionX();
		posicionY = alucinado.getPosicionY();
		nombre = "Alucinacion";
		this.setAlrededores();
		alucine = false;
		
	}
	
	public void atacar(Unidad unidad) 
		throws InstantiationException, IllegalAccessException, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, 
		ExcepcionYaHayElementoEnLaPosicion{
		
		this.setAlrededores();
		this.setearUnidadDuplicada(unidad);
		this.setearUnidadDuplicada(unidad);
		
	}	
	
	private void setearUnidadDuplicada(Unidad unidad) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion {
		
		copia = unidad.duplicarConAlucinacion();
		copia.setJugador(unidad.getJugador());
		copia.setDuenio(unidad.getDuenio());
		unidad.getJugador().agregarAUnidadesAlistadas(copia);
		copia.setAtaquesPermitidosPorTurno(unidad.getJugador().getAtaquesPermitidosPorTurno());
		Mapa mapa = unidad.getMapa();
		this.colocarUnidad(copia, mapa);
	}
	
	private void colocarUnidad (Unidad aColocar , Mapa map ) throws 
		ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion {
		
		Posicion auxiliar = aColocar.getColocador().posicionAColocar(aColocar,map,alrededores);		
		map.colocarEn(auxiliar.getX(), auxiliar.getY(), aColocar);
		aColocar.setMapa(map);
		aColocar.setPosicion(auxiliar);
		
	}

	public void atacarEnEstaPosicion(int x, int y) {
		
		if(!(alucine)){
			Unidad unidad = null;
			
			try {
				if(Unidad.class.isAssignableFrom(mapa.obtenerContenidoEnPosicion(x, y).getElementoEnTierra().getClass())){
					unidad = (Unidad) mapa.obtenerContenidoEnPosicion(x, y).getElementoEnTierra();
				}
				if(Unidad.class.isAssignableFrom(mapa.obtenerContenidoEnPosicion(x, y).getElementoEnAire().getClass())) {
					unidad = (Unidad) mapa.obtenerContenidoEnPosicion(x, y).getElementoEnAire();
				}
				this.atacar(unidad);
				alucine = true;
			}
			catch (ExcepcionPosicionInvalida |
					InstantiationException |
					IllegalAccessException |
					ExcepcionNoHayLugarParaCrear |
					ExcepcionYaHayElementoEnLaPosicion e) {
			 		
						e.printStackTrace();
	
		 			}
		
		}
	
	}
	
}