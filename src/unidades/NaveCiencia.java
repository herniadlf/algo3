package src.unidades;

import src.Danio;
import src.Dinero;
import src.ReglaDeDanioTerran;
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
		energiaPorTurno = 10;
		colocador = (ColocadorUnidadVoladora) new ColocadorUnidadVoladora();
		reglaDeDanio = new ReglaDeDanioTerran();

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
	

	public int obtenerEnergia() {
	
		return energia.obtenerCantidad(); 
	}
	
	public void emp(int x, int y) {
		
		EMP emp = new EMP(mapa, x, y);
		if(energia.obtenerCantidad() >= emp.obtenerEnergiaNecesaria()){
			emp.atacar();
			energia.disminuirEnergia(emp.obtenerEnergiaNecesaria());
		}
	
	}
	
	public void radiacion(Unidad unidad){
		
		Radiacion radiacion = new Radiacion(unidad,mapa);
		if(energia.obtenerCantidad() >= radiacion.obtenerEnergiaNecesaria()){
			radiacion.atacar();
			energia.disminuirEnergia(radiacion.obtenerEnergiaNecesaria());
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
		// no afecta
		
	}
	
	public void afectadoPorTormentaPsionica(int danio){
		
		vida.aumentarDanioARecibir(danio);
		
	}

	public void afectadoPorRadiacion(int danio){
		
		//supuesto: no afecta a naves
		
	}
	

	

	

}
