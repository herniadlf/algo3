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
		construccionesPosibles.add(new Asimilador());
		construccionesPosibles.add(new Acceso());
		construccionesPosibles.add(new PuertoEstelarProtoss());		
		construccionesPosibles.add(new ArchivosTemplarios());
		
	}
	
	public Construccion getEdificioPrincipal () {
		
		return new Pilon();
		
	}
	
	public String getNombre(){
		
		return "Protoss";
		
	}
	
}
