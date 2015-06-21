package src;

public class ReglaDeDanioProtoss extends ReglaDeDanio{
	
	Escudo escudo;
	
	public ReglaDeDanioProtoss(Escudo e){
		escudo = e;
	}
	public void recibirDanio(Atacable a){
		escudo.atacar(a.getVida().obtenerDanioRecibido());
	}
}
