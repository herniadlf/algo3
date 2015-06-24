package src.razas;

import java.util.LinkedList;

import src.construcciones.*;
import src.unidades.*;

public class Protoss extends Raza {

	public Protoss(){
		
		unidadesPosibles = new LinkedList<Unidad>();
		construccionesPosibles = new LinkedList<Construccion>();
		unidadesPosibles.add(new Zealot());
		unidadesPosibles.add(new Dragon());
		unidadesPosibles.add(new Scout());
		unidadesPosibles.add(new AltoTemplario());
		unidadesPosibles.add(new NaveTransporteProtoss());
		construccionesPosibles.add(new NexoMineral());
		construccionesPosibles.add(new Pilon());
		//construccionesPosibles.add(new Acceso());
		construccionesPosibles.add(new Asimilador());
		// estas se habilitan una vez que se construyen los edificios requeridos
		//construccionesPosibles.add(new ArchivosTemplarios());
		//construccionesPosibles.add(new PuertoEstelarProtoss());
		
	}
	
	public Construccion getEdificioPrincipal () {
		
		return new Pilon();
		
	}
	
	public String getNombre(){
		return "Protoss";
	}
	
	
	
	
	
}
