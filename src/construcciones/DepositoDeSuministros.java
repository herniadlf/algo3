package src.construcciones;

import src.ConstantesAlgoCraft;

public class DepositoDeSuministros extends NoExtractora{
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 0;
	private static final int VIDA = 500;
	private static final int TIEMPO = 6;
	private static final int SUMINISTROS = 5;
	
	public DepositoDeSuministros(){
		
		super();
		setNombre("Deposito de Suministros");
		setVida(VIDA);
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setTiempoDeConstruccion(TIEMPO);
		setEdificioRequerido(this);
		capacidadDeSuministros = SUMINISTROS;
		
	}
	
	
	public void recibirDanio(){
			
		this.getVida().dismunuirVidaPorDanio();
			
	}
	
}
