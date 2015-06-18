package src.construcciones;

import java.util.LinkedList;

import excepciones.ExcepcionPosicionInvalida;
import src.ConstantesAlgoCraft;
import src.Escudo;
import src.unidades.*;

public class PuertoEstelarProtoss extends Creadora {
	
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 150;
	private static final int VIDA = 600;
	private static final int ESCUDO = 600;
	private static final int TIEMPO = 10;
	private Escudo escudo;
	
	public PuertoEstelarProtoss(){
		
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new Scout());
		unidadesCreables.add(new NaveTransporteProtoss());
		setNombre("Puerto Estelar Protoss");
		setTiempoDeConstruccion(TIEMPO);
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setEdificioRequerido(new Acceso());
		escudo = new Escudo(ESCUDO,this);
		
	}
	
	public void recibirDanio () throws ExcepcionPosicionInvalida{
		
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		boolean estadoDeVidaFinalizado= vida.devolverEstadoDeVida();
		  if (estadoDeVidaFinalizado==true){
			 mapa.eliminarElementoTerrestreEnPosicion(super.getPosicionX(), super.getPosicionY());
			 }
	}
	
	
}
