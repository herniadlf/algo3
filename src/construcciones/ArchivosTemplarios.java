package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;

public class ArchivosTemplarios extends Creadora {
	
	public ArchivosTemplarios(){
		unidadesCreables = new LinkedList();
		setNombre("Archivos Templarios");
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_ARCHIVOSTEMPLARIOS);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_ARCHIVOSTEMPLARIOS,ConstantesAlgoCraft.COSTO_GAS_ARCHIVOSTEMPLARIOS);
		setVida(ConstantesAlgoCraft.HP_ARCHIVOSTEMPLARIOS);
	}
		
}
