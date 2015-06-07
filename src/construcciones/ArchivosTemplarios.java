package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.unidades.AltoTemplario;

public class ArchivosTemplarios extends Creadora {
	
	public ArchivosTemplarios(){
		super();
		unidadesCreables = new LinkedList();
		setNombre("Archivos Templarios");
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_ARCHIVOSTEMPLARIOS);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_ARCHIVOSTEMPLARIOS,ConstantesAlgoCraft.COSTO_GAS_ARCHIVOSTEMPLARIOS);
		setVida(ConstantesAlgoCraft.HP_ARCHIVOSTEMPLARIOS);
		setEdificioRequerido(new PuertoEstelarProtoss());
	}

	public AltoTemplario crearAltoTemplario() {
		
		AltoTemplario altoTemplario = new AltoTemplario();
		return altoTemplario;
		
	}
		
}
