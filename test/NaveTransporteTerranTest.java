package test;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;
import src.Jugador;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Terran;
import src.unidades.*;

import org.junit.Assert;

import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionGeneral;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionNoSePuedenTransportasUnidadesVoladoras;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;

public class NaveTransporteTerranTest extends TestCase {
	
	private Mapa mapa;
	private Jugador jugador;
	private NaveTransporteTerran naveTransporte;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		mapa = new Mapa(50);
		jugador = new Jugador("Pepe","rojo", new Terran());
		
		PuertoEstelarTerran puertoEstelar = new PuertoEstelarTerran();
		try {
			jugador.colocar(puertoEstelar,mapa,30,30);			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		naveTransporte = puertoEstelar.crearNaveTransporteTerran();	
	}
	
	public void testNaveTransporteSeCreaCon150deVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		Assert.assertTrue(naveTransporte.getVida().obtenerVida() == 150);
			
	}
	
	public void testNaveTransporteTerranTransporta8Marines(){
		
		ArrayList<Marine> marines = new ArrayList<>();
			
		for(int i = 0 ; i < 8 ; i++){	
			Marine marine = new Marine();
			marines.add(marine);
		}
				
		Iterator<Marine> i = marines.iterator();
		
		while(i.hasNext()){	
			try{
				naveTransporte.llevar(i.next());
			} catch (ExcepcionGeneral e){	
				e.getMessage();
			}
		}
			
		Assert.assertTrue(naveTransporte.cantidadPasajeros() == 8);		
			
	}
	
	public void testNaveTransporteSoloPuedeLlevar4Golliats(){
		
		ArrayList<Golliat> golliats = new ArrayList<>();
		
		for(int i = 0 ; i < 4 ; i++){	
			Golliat golliat = new Golliat();
			golliats.add(golliat);
		}
				
		Iterator<Golliat> i = golliats.iterator();
		
		while(i.hasNext()){	
			try{
				naveTransporte.llevar(i.next());
			} catch (ExcepcionGeneral e){	
				e.getMessage();
			}
		}
		
		Assert.assertTrue(naveTransporte.cantidadPasajeros() == 8);
	}
	
	
	public void testTransporteDeUnidadesAUnDeterminadoPunto() throws ExcepcionNoPudoColocarseEdificio, ExcepcionNoPudoCrearseUnidad, ExcepcionErrorPasoDeTurno, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionElTransporteEstaLleno, ExcepcionNoHayLugarParaCrear, ExcepcionNoSePuedenTransportasUnidadesVoladoras{
		
		Marine marine1= new Marine();
		Marine marine2= new Marine();
		Marine marine3= new Marine();
		marine1.setMapa(mapa);
		marine2.setMapa(mapa);
		marine3.setMapa(mapa);
		
		mapa.colocarEn(1, 2, naveTransporte);
		Posicion posicion = new Posicion (1,2);
		naveTransporte.setPosicion(posicion);
		naveTransporte.setMapa(mapa);
		
		mapa.colocarEn(2,5,marine1);
		Posicion posicion1 = new Posicion (2,5);
		marine1.setPosicion(posicion1);
		mapa.colocarEn(3, 5, marine2);
		Posicion posicion2 = new Posicion (3,5);
		marine2.setPosicion(posicion2);
		mapa.colocarEn(4, 5, marine3);
		Posicion posicion3 = new Posicion (6,6);
		marine3.setPosicion(posicion3);
		
		naveTransporte.llevar(marine1);
		naveTransporte.llevar(marine2);
		naveTransporte.llevar(marine3);
		naveTransporte.transportarUnidades(8, 8);
		
		// Verifico que las unidades hallan viajado a la posicion determinada 
		Assert.assertTrue(marine1.getPosicionX() == 7);
		Assert.assertTrue(marine1.getPosicionY()==9);
		
		Assert.assertTrue(marine2.getPosicionX() == 8);
		Assert.assertTrue(marine2.getPosicionY()==9);
		
		Assert.assertTrue(marine3.getPosicionX() == 9);
		Assert.assertTrue(marine3.getPosicionY()==9);

	}
	
	public void testNaveTranporteNoPuedeLlevarEspectro() throws ExcepcionElTransporteEstaLleno{
		
		Espectro espectro = new Espectro();
		try {
			naveTransporte.llevar(espectro);
		} catch (ExcepcionNoSePuedenTransportasUnidadesVoladoras e) {
			e.printStackTrace();
		}
		
	}
	
	public void testNaveTranporteNoPuedeLlevarNaveCiencia() throws ExcepcionElTransporteEstaLleno{
		
		NaveCiencia naveCiencia = new NaveCiencia();
		try {
			naveTransporte.llevar(naveCiencia);
		} catch (ExcepcionNoSePuedenTransportasUnidadesVoladoras e) {
			e.printStackTrace();
		}
		
	}
	
	
}
