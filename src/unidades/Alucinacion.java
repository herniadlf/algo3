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
	
	public Alucinacion(Unidad alucinado){
		
		this.alucinado = alucinado;
		//danio = 20; //supuesto
		energiaNecesaria = 100;
		posicionX = alucinado.getPosicionX();
		posicionY = alucinado.getPosicionY();
		
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
		
	
}
