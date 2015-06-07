package src.razas;

import src.construcciones.Barraca;
import src.construcciones.CentroDeMineral;
import src.construcciones.Construccion;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Fabrica;
import src.construcciones.PuertoEstelarTerran;
import src.construcciones.Refineria;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.FuenteDeMinerales;
import src.mapa.Posicion;
import src.unidades.Espectro;
import src.unidades.Golliat;
import src.unidades.Marine;
import src.unidades.NaveCiencia;
import src.unidades.NaveTransporteTerran;
import src.unidades.Unidad;

import java.util.*;

public class Terran extends Raza {

	public Terran(){
		unidadesPosibles = new LinkedList<Unidad>();
		construccionesPosibles = new LinkedList<Construccion>();
		unidadesPosibles.add(new Marine());
		unidadesPosibles.add(new Golliat());
		unidadesPosibles.add(new Espectro());
		unidadesPosibles.add(new NaveCiencia());
		unidadesPosibles.add(new NaveTransporteTerran());
		construccionesPosibles.add(new CentroDeMineral());
		//construccionesPosibles.add(new Barraca());
		construccionesPosibles.add(new DepositoDeSuministros());
		construccionesPosibles.add(new Refineria());
		// estas se habilitan una vez que se construyen los edificios requeridos
		//construccionesPosibles.add(new Fabrica());
		//construccionesPosibles.add(new PuertoEstelarTerran());
	}
	
	public Barraca construirBarraca(Posicion posicion) {
	
	Barraca barraca = new Barraca();
	return barraca;
		
	}
	
}
