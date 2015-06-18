package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.unidades.*;

public class PuertoEstelarTerran extends Creadora {
	
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 100;
	private static final int VIDA = 1300;
	private static final int TIEMPO = 10;
	
	public PuertoEstelarTerran(){
		
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new NaveTransporteTerran());
		unidadesCreables.add(new NaveCiencia());
		unidadesCreables.add(new Espectro());
		setNombre("Puerto Estelar Terran");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
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

	public void recibirDanio (){
		
		this.getVida().disminuirVidaPorDanio();
		
	}
	
}
