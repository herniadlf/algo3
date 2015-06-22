package src.unidades;

import java.util.Iterator;

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
		
		//Class clase = unidad.getClass();
		
		//copia1 = (Unidad) clase.newInstance();
		
		copia2 = new Scout();
			
		ColocadorDeUnidades colocador = unidad.getColocador();
		
		Mapa mapa = unidad.getMapa();
		
		//this.colocarUnidad(copia1, mapa, colocador);
		this.colocarUnidad(copia2, mapa, colocador);
		
	}
	
	private void colocarUnidad(Unidad unidad,Mapa mapa, ColocadorDeUnidades colocador) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		Posicion posicion;

			posicion = colocador.posicionAColocar(unidad, mapa, alrededores);
			System.out.println(posicion.getX());
			System.out.println(posicion.getY());
			mapa.colocarEn(posicion.getX(), posicion.getY(), unidad);
			unidad.setMapa(mapa);
			unidad.setPosicion(posicion);
			
	
		
	}
		
	
}
