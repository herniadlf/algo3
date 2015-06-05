package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.unidades.Espectro;
import src.unidades.NaveCiencia;
import src.unidades.NaveTransporteTerran;

public class PuertoEstelarTerran extends Creadora {

	public PuertoEstelarTerran(){
		super();
		unidadesCreables = new LinkedList();
		setNombre("Puerto Estelar Terran");
		setVida(ConstantesAlgoCraft.HP_PUERTOESTELAR_TERRAN);
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_PUERTOESTELAR,ConstantesAlgoCraft.COSTO_GAS_PUERTOESTELAR);
		setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_PUERTOS);
		setEdificioRequerido(new Fabrica());
	}
	
	public Espectro crearEspectro() {
		
		Espectro espectro = new Espectro();
		return espectro;
		
	}

	public NaveCiencia crearNaveCiencia() {
		
		NaveCiencia naveCiencia = new NaveCiencia();	
		return naveCiencia;
	}

	public NaveTransporteTerran crearNaveTransporteTerran() {
		
		NaveTransporteTerran naveTransporte = new NaveTransporteTerran();
		return naveTransporte;
		
	}

}
