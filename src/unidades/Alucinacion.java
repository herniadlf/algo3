package src.unidades;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.mapa.Mapa;
import src.mapa.Posicion;

public class Alucinacion extends Magia{
	
	private Unidad alucinado;
	private Unidad copia1;
	private Unidad copia2;
	private boolean alucine;
	
	public Alucinacion(Unidad alucinado){
		
		this.alucinado = alucinado;
		energiaNecesaria = 100;
		mapa = alucinado.getMapa();
		posicionX = alucinado.getPosicionX();
		posicionY = alucinado.getPosicionY();
		nombre = "Alucinacion";
		this.setAlrededores();
		alucine = false;
		
	}
	
	public void atacar(Unidad unidad) throws InstantiationException, IllegalAccessException, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		this.setAlrededores();
		
		Class clase = unidad.getClass();
		
		copia1 = (Unidad) clase.newInstance();
		
		copia2 = (Unidad) clase.newInstance();
		
		Mapa mapa = unidad.getMapa();
		
	    this.colocarUnidad(copia1, mapa);
		this.colocarUnidad(copia2, mapa);
	}	
	
	private void colocarUnidad (Unidad aColocar , Mapa map ) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion {
		
		Posicion auxiliar = aColocar.getColocador().posicionAColocar(aColocar,map,alrededores);		
		map.colocarEn(auxiliar.getX(), auxiliar.getY(), aColocar);
		aColocar.setMapa(map);
		aColocar.setPosicion(auxiliar);
		
	}

	@Override
	void atacarEnEstaPosicion(int x, int y) {
		
		if(!(alucine)){
		Unidad unidad = null;
		try {
			if(mapa.obtenerContenidoEnPosicion(x, y).getElementoEnAire().getNombre() == "Espacio Disponible"){
				unidad = (Unidad) mapa.obtenerContenidoEnPosicion(x, y).getElementoEnTierra();
				
			}
			else{
				unidad = (Unidad) mapa.obtenerContenidoEnPosicion(x, y).getElementoEnAire();
			}
			
			this.atacar(unidad);
			alucine = true;
			
		}
		 catch (ExcepcionPosicionInvalida e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ExcepcionNoHayLugarParaCrear e) {
			e.printStackTrace();
		} catch (ExcepcionYaHayElementoEnLaPosicion e) {
			e.printStackTrace();
		}

		}
		
	}
	
}
