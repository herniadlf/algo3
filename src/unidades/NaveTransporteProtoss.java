package src.unidades;

import java.util.ArrayList;

import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionPosicionInvalida;
import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Escudo;
import src.Vida;
import src.mapa.Mapeable;

public class NaveTransporteProtoss extends DeTransporte {
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 5;
	private static final int TRANSPORTE = 8;// capacidad
	private static final int VIDA = 80;
	private static final int ESCUDO = 60;
	private static final int VISION = 8;
	private static final int COSTO_MINERALES = 200;
	private static final int COSTO_GAS = 0;	
	Escudo escudo;
	
	public NaveTransporteProtoss() {		
	
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Nave Transporte Protoss";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		escudo = new Escudo (ESCUDO,this);
		colocador = (ColocadorUnidadVoladora) new ColocadorUnidadVoladora();
		unidadesAbordo = new ArrayList<Unidad>();
		
	}	
		
public void pasoTurno() {
		
		escudo.pasoTurno();
		
		
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

	public ColocadorUnidadVoladora getColocador(){
		return (ColocadorUnidadVoladora)colocador;
	}
	
	
	public void recibirDanio () throws ExcepcionPosicionInvalida{
		
		escudo.atacar(this.getVida().obtenerDanioRecibido());
		boolean estadoDeVidaFinalizado= vida.devolverEstadoDeVida();
		  if (estadoDeVidaFinalizado==true){
			 super.mapa.eliminarElementoTerrestreEnPosicion(super.getPosicionX(), super.getPosicionY());
			 }
		
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
		return true;
	}

	@Override
	public void atacarConEMP(int danio) {
		escudo.atacar(danio);
		
	}


	public void afectadoPorTormentaPsionica(int danio){
		
		escudo.atacar(danio);
		
	}
	
	
}
