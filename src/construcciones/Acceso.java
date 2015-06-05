package src.construcciones;

import java.util.LinkedList;

import src.ConstantesAlgoCraft;

public class Acceso extends Creadora {

		public Acceso (){
			super();
			unidadesCreables = new LinkedList();
			setNombre("Acceso");
			setVida(ConstantesAlgoCraft.HP_ACCESO);
			setCosto(ConstantesAlgoCraft.COSTO_MINERALES_ACCESO,0);
			setTiempoDeConstruccion(ConstantesAlgoCraft.TIEMPO_ACCESO);
			setEdificioRequerido(new Pilon());
		}
}
