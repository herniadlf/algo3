package src.razas;

import src.construcciones.*;
import src.mapa.*;
import src.unidades.*;

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
		construccionesPosibles.add(new DepositoDeSuministros());
		construccionesPosibles.add(new Refineria());
		construccionesPosibles.add(new Barraca());	
		construccionesPosibles.add(new Fabrica());
		// estas se habilitan una vez que se construyen los edificios requeridos
		construccionesPosibles.add(new PuertoEstelarTerran());
		
	}
	
	public Barraca construirBarraca(Posicion posicion) {
	
		Barraca barraca = new Barraca();
		return barraca;
		
	}

	public Construccion getEdificioPrincipal() {
		
		return new DepositoDeSuministros();
		
	}
	
	public String getNombre(){
		
		return "Terran";
		
	}
	
}
