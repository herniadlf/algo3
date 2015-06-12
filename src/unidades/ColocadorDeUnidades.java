package src.unidades;

import java.util.LinkedList;

import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;
import src.mapa.Posicion;

public interface ColocadorDeUnidades {

		public Posicion posicionAColocar(Unidad aColocar, Mapa map, LinkedList<Posicion> alrededores) throws ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear;
}
