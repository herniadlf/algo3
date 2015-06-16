package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;
import src.Escudo;
import src.unidades.*;

public class Acceso extends Creadora {
		
	private static final int COSTO_MINERALES = 150;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 500;
	private static final int ESCUDO = 500;
	private static final int TIEMPO = 8;
	private Escudo escudo;
	
	public Acceso (){
			
		super();
		unidadesCreables = new LinkedList<Unidad>();
		unidadesCreables.add(new Dragon());
		unidadesCreables.add(new Zealot());
		setNombre("Acceso");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(new Pilon());
		escudo = new Escudo(ESCUDO,this);
	}
		
	public Zealot crearZealot(){
			
		Zealot zealot = new Zealot();
		return zealot;
			
	}
		
	public AltoTemplario crearAltoTemplario(){
			
		AltoTemplario alto = new AltoTemplario();
		return alto;
			
	}
		
	public Escudo getEscudo(){
			
		return escudo;
		
	}
		
	public void recibirDanio(){
					
		escudo.atacar(this.getVida().obtenerDanioRecibido());
	
	}

	public Dragon crearDragon() {

		Dragon dragon = new Dragon();
		return dragon;
		
	}	
			
}
