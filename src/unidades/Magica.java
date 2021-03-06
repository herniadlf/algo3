package src.unidades;

import src.mapa.Mapeable;

public abstract class Magica extends Unidad {

	int energiaPorTurno;
	Energia energia;
	protected boolean tormentaEnCurso;
	private TormentaPsionica tormenta;
	
	public void pasoTurno(){
		
		energia.aumentarEnergia(energiaPorTurno);
		if(tormentaEnCurso){
			tormenta.atacar();
			tormentaEnCurso = false;
		}
		if(afectadoPorRadiacion){
			Radiacion radiacion = new Radiacion(this);
			radiacion.atacar();
		}
		
	}
		
	public void atacar(Magia magia){

		if(energia.obtenerCantidad() >= magia.obtenerEnergiaNecesaria()) {
			magia.atacar();
			energia.disminuirEnergia(magia.obtenerEnergiaNecesaria());
			if(magia.obtenerNombre() == "TormentaPsionica"){
				tormenta = (TormentaPsionica)magia;
				tormentaEnCurso = true;
			}
		
		}
		
	}
	
	public int obtenerEnergia() {
		
		return energia.obtenerCantidad(); 
		
	}


	
}
