package src.unidades;

import java.util.ArrayList;

import src.ConstantesAlgoCraft;
import src.Danio;
import src.Dinero;
import src.Vida;
import src.mapa.Mapeable;


public class NaveCiencia extends Magica {

	private static int ENERGIA_POR_TURNO = 10;
	
	private static final int DANIO_AIRE = 0;
	private static final int DANIO_TIERRA = 0;
	private static final int RANGO_ATAQUE = 0;
	private static final int SUMINISTRO = 2;
	private static final int TIEMPO_CREACION = 10;
	private static final int TRANSPORTE = 0;
	private static final int VIDA = 200;
	private static final int VISION = 10;
	private static final int ENERGIA = 50;
	private static final int COSTO_MINERALES = 100;
	private static final int COSTO_GAS = 225;
	
	public NaveCiencia (){
		
		costo = new Dinero(COSTO_MINERALES,COSTO_GAS);
		danio = new Danio(DANIO_AIRE,DANIO_TIERRA);
		nombre = "Nave Ciencia";
		rangoAtaque = RANGO_ATAQUE;
		suministro = SUMINISTRO;
		tiempoDeCreacion = TIEMPO_CREACION;
		transporte = TRANSPORTE;
		vida = new Vida(VIDA);
		vision = VISION;
		energia = new Energia(ENERGIA);
		magias.add(new EMP());
		magias.add(new Radiacion());
		energiaPorTurno = 10;
		entrenador = (EntrenadorUnidadVoladora) new EntrenadorUnidadVoladora();
		
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

	public EntrenadorUnidadVoladora getEntrenador(){
		return (EntrenadorUnidadVoladora)entrenador;
	}
	

	public int obtenerEnergia() {
	
		return energia.obtenerCantidad(); 
	}


	public ArrayList<Magia> obtenerMagias() {
		
		return magias;
		
	}


	public void atacarConEMP(int x, int y) {
		
		PorRangoAtaque emp = (PorRangoAtaque)magias.get(0);
		if(energia.obtenerCantidad() >= emp.obtenerEnergiaNecesaria()){
			emp.atacar(x,y);
			energia.disminuirEnergia(emp.obtenerEnergiaNecesaria());
		}
	}
	
	public void atacarConRadiacion(Unidad unidad) {
			
		PorUnidad radiacion = (PorUnidad)magias.get(1);
		if(energia.obtenerCantidad() >= radiacion.obtenerEnergiaNecesaria()){
			radiacion.atacar(unidad);
			energia.disminuirEnergia(radiacion.obtenerEnergiaNecesaria());
		}	
		
	}
	

	
	public void recibirDanio (){
		
	
		vida.dismunuirVidaPorDanio();;
		
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


	

	

}
