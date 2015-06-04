package src.razas;

import src.construcciones.Barraca;
import src.construcciones.CentroDeMineral;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Fabrica;
import src.construcciones.PuertoEstelarTerran;
import src.construcciones.Refineria;
import src.mapa.Posicion;
import src.unidades.Espectro;
import src.unidades.Golliat;
import src.unidades.Marine;
import src.unidades.NaveCiencia;
import src.unidades.NaveTransporteTerran;
import java.util.*;

public class Terran extends Raza {

	public Terran(){
		unidadesPosibles = new LinkedList();
		construccionesPosibles = new LinkedList();
		unidadesPosibles.add(new Marine());
		unidadesPosibles.add(new Golliat());
		unidadesPosibles.add(new Espectro());
		unidadesPosibles.add(new NaveCiencia());
		unidadesPosibles.add(new NaveTransporteTerran());
		construccionesPosibles.add(new CentroDeMineral());
		construccionesPosibles.add(new Barraca());
		construccionesPosibles.add(new DepositoDeSuministros());
		construccionesPosibles.add(new Refineria());
		construccionesPosibles.add(new Fabrica());
		construccionesPosibles.add(new PuertoEstelarTerran());
	}
	
	public Barraca construirBarraca(Posicion posicion) {
	
	Barraca barraca = new Barraca();
	return barraca;
		
	}
	
}
