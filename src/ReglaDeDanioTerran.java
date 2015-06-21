package src;

public class ReglaDeDanioTerran extends ReglaDeDanio {
	
	public void recibirDanio(Atacable a){
		a.getVida().disminuirVidaPorDanio();
	}
}
