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
	
	public NaveCiencia (){
		setCosto(ConstantesAlgoCraft.COSTO_MINERALES_NAVECIENCIA,ConstantesAlgoCraft.COSTO_GAS_NAVECIENCIA);
		setDanio(DANIO_AIRE,DANIO_TIERRA);
		setNombre("Nave Ciencia");
		setRangoDeAtaques(RANGO_ATAQUE);
		setSuministros(SUMINISTRO);
		setTiempoDeCreacion(TIEMPO_CREACION);
		setTransporte(TRANSPORTE);
		setVida(new Vida(VIDA));
		setVision(VISION);
		energia = new Energia(ENERGIA);
		magias.add(new EMP());
		magias.add(new Radiacion());
	}	
	
	public void pasoTurno(){
		
		energia.aumentarEnergia(ENERGIA_POR_TURNO);
		
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

	@Override
	public void atacarEnTierra(Unidad unidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atacarEnAire(Unidad unidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recibirDanio(int Danio) {
		// TODO Auto-generated method stub
		
	}


	public int obtenerEnergia() {
	
		return energia.obtenerCantidad(); 
	}


	public ArrayList<Magia> obtenerMagias() {
		
		return magias;
		
	}


	public void atacarConEMP(Unidad unidad) {
		
		Magia emp = magias.get(0);
		if(energia.obtenerCantidad() >= emp.obtenerEnergiaNecesaria()){
			emp.atacar(unidad);
			energia.disminuirEnergia(emp.obtenerEnergiaNecesaria());
		}
	}
	
	public void atacarConRadiacion(Unidad unidad) {
			
		Magia radiacion = magias.get(1);
		if(energia.obtenerCantidad() >= radiacion.obtenerEnergiaNecesaria()){
			radiacion.atacar(unidad);
			energia.disminuirEnergia(radiacion.obtenerEnergiaNecesaria());
		}	
		
	}

	@Override
	public boolean esPisable() {
		// TODO Auto-generated method stub
		return false;
	}

}
