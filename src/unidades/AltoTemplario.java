package src.unidades;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;

public class AltoTemplario extends Magica {
	
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 7;
	private static final int TRANSPORTE = 2;
	private static final int VIDA = 40;
	private static final int ESCUDO = 40;
	private static final int VISION = 7;
	private static final int COSTO_MINERALES = 50;
	private static final int COSTO_GAS = 150;
	private static int ENERGIA = 50;
	
	Escudo escudo;
	
	public AltoTemplario() {		
		setCosto(COSTO_MINERALES,COSTO_GAS);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Alto Templario");
		setRangoDeAtaques(RANGO_ATAQUE);
		setSuministros(SUMINISTRO);
		setTiempoDeCreacion(TIEMPO_CREACION);
		setTransporte(TRANSPORTE);
		setVida(new Vida(VIDA));
		setVision(VISION);	
		escudo = new Escudo (ESCUDO,this);
		energia = new Energia(ENERGIA);
		magias.add(new TormentaPsionica());
		magias.add(new Alucinacion());
		energiaPorTurno = 15;
		entrenador = (EntrenadorUnidadTerrestre) new EntrenadorUnidadTerrestre();
	}
	
	@Override
	public Mapeable colocarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable dibujar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable quitarContenido() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mapeable mover() {
		// TODO Auto-generated method stub
		return null;
	}

	public EntrenadorUnidadTerrestre getEntrenador(){
		return (EntrenadorUnidadTerrestre)entrenador;
	}

	public Escudo getEscudo() {
	
		return escudo;
	}

	public int obtenerEnergia() {
	
		return energia.obtenerCantidad();
	}

	public void tormentaPsionica(int x, int y) {

		PorRangoAtaque tormenta = (PorRangoAtaque)magias.get(0);
		if(energia.obtenerCantidad() >= tormenta.obtenerEnergiaNecesaria()) {
			tormenta.atacar(x,y);
			energia.disminuirEnergia(tormenta.obtenerEnergiaNecesaria());
		
	}

}

	public int obtenerEnegia() {
		
		return energia.obtenerCantidad();
	}
	

	
	
	public void recibirDanio (){
		
	
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		
	}

	@Override
	public boolean esOcupable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esTerrestre() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esAereo() {
		// TODO Auto-generated method stub
		return false;
	}


	

	
}



