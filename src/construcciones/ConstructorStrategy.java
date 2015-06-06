package src.construcciones;

import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import src.mapa.Mapa;

public abstract class ConstructorStrategy {
		
	public void construir(Mapa map, Construccion edificio) throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{}
	public void setConstruccion(Construccion construccion){};

}
