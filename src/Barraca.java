package src;

public class Barraca extends Creadora {
	
	public Barraca(){
		super();
		setVida(ConstantesAlgoCraft.HP_BARRACA);
		//setCosto(ConstantesAlgoCraft.COSTO_MINERALES_BARRACA,0);
		setCosto(150,0);
	}
}
