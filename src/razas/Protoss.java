package src.razas;

import java.util.LinkedList;

import src.construcciones.Acceso;
import src.construcciones.ArchivosTemplarios;
import src.construcciones.Asimilador;
import src.construcciones.Barraca;
import src.construcciones.CentroDeMineral;
import src.construcciones.Construccion;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Fabrica;
import src.construcciones.NexoMineral;
import src.construcciones.Pilon;
import src.construcciones.PuertoEstelarProtoss;
import src.construcciones.PuertoEstelarTerran;
import src.construcciones.Refineria;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.FuenteDeMinerales;
import src.unidades.AltoTemplario;
import src.unidades.Dragon;
import src.unidades.Espectro;
import src.unidades.Golliat;
import src.unidades.Marine;
import src.unidades.NaveCiencia;
import src.unidades.NaveTransporteProtoss;
import src.unidades.NaveTransporteTerran;
import src.unidades.Scout;
import src.unidades.Unidad;
import src.unidades.Zealot;

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
	

	
}
