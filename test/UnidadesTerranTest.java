package test;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;
import src.unidades.*;

import org.junit.Assert;

import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionGeneral;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.*;
import src.construcciones.*;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Marine;

public class UnidadesTerranTest extends TestCase{
	
	private Mapa mapa;
	private Jugador jugador;
	
	protected void setUp() throws Exception {
			
		super.setUp();
		mapa = new Mapa(50);
		jugador = new Jugador("carlos","rojo", new Terran());
			
	}
		
	public void testmarineCuesta50mineralYseCreaCon40deVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso {

		Barraca barraca = new Barraca();
		try {
			jugador.colocar(barraca,mapa,3,4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Marine marine = barraca.crearMarine();
			
		Assert.assertTrue((marine.getVida().obtenerVida()) == 40);
		//falta chequear el mineral
		
	}
		
	public void testgolliatSeCreaCon125DeVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		Fabrica fabrica = new Fabrica();
		try {
			jugador.colocar(fabrica,mapa,4,4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Golliat golliat = fabrica.crearGolliat();
			
		Assert.assertTrue((golliat.getVida().obtenerVida()) == 125);
			
	}
		
	
	public void testespectroSeCreaCon120deVidaYNaveTransporteCon150() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		PuertoEstelarTerran puertoEstelar = new PuertoEstelarTerran();
		try {
			jugador.colocar(puertoEstelar,mapa,30,30);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Espectro espectro = puertoEstelar.crearEspectro();
		NaveTransporteTerran naveTransporte = puertoEstelar.crearNaveTransporteTerran();
			
		Assert.assertTrue(espectro.getVida().obtenerVida() == 120);
		Assert.assertTrue(naveTransporte.getVida().obtenerVida() == 150);
			
	}
			
	public void testmarineAtacaZealotSoloRompeEscudo() 
		throws ExcepcionNoPudoColocarseEdificio, 
		ExcepcionPosicionInvalida, 
		ExcepcionNoHayLugarParaCrear, 
		ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno {
			
		/*--------------------------------------------------------------------------------*/
		Mapa mapa = new Mapa(50);
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
		Juego juego = new Juego(mapa, jug1, jug2);
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
			
		Creadora barraca = (Creadora) jug1.colocar(new Barraca(),mapa,5,5);
		Creadora acceso = (Creadora) jug2.colocar(new Acceso(), mapa, 8, 8);
			
		Unidad marine = new Marine();
		barraca.colocarUnidad(marine, mapa);
		Unidad zealot = new Zealot();
		acceso.colocarUnidad(zealot, mapa);
		marine.setAtaquesPermitidosPorTurno(ataques);
		ataques.setJuego(juego);
			
		marine.atacarEnAire(zealot);
		Vida vida= zealot.getVida();
		Assert.assertTrue((vida.obtenerVida()) == 100);		
	
	}	
		
	public void testEspectroPuedeAtacarEdificiosEnemigos() 
		throws ExcepcionPosicionInvalida, 
		ExcepcionNoHayLugarParaCrear, 
		ExcepcionYaHayElementoEnLaPosicion, ExcepcionNoPudoColocarseEdificio, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno{
			
		Mapa mapa = new Mapa(50);
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jug2 = new Jugador ("Williams", "azul", new Protoss());
		jug1.setDinero(999999, 999999);
		jug2.setDinero(9999, 9999);
		
		Creadora barraca = (Creadora) jug1.colocar(new Barraca(),mapa,5,5);
		Creadora fabrica = (Creadora) jug1.colocar(new Fabrica(),mapa, 9,9);
		Creadora puertoEstelarTerran = (Creadora) jug1.colocar(new PuertoEstelarTerran(), mapa, 16, 16);
		NexoMineral nexo =  (NexoMineral) jug2.colocar(new NexoMineral(), mapa, 18, 18);
		//Como creo 8 espectrs que requieren 2 suministros, en total necesito 16 suministros,
		//por lo cual construyo 3 depositosdesuminitros 
		//para tener 20 suministros disponibles (5 suministros extras x edificio + lo inicial)
		jug1.colocar(new DepositoDeSuministros(), mapa, 9, 8);
		jug1.colocar(new DepositoDeSuministros(), mapa, 10, 2);
		jug1.colocar(new DepositoDeSuministros(), mapa, 10, 4);
		Juego juego = new Juego(mapa, jug1, jug2);
			
		Unidad espectro = new Espectro();
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
		espectro.setAtaquesPermitidosPorTurno(ataques);
		ataques.setJuego(juego);
		puertoEstelarTerran.colocarUnidad(espectro, mapa);
		Assert.assertTrue(nexo.getEscudo().obtenerResistenciaActual() == 250 );
		espectro.atacarEnAire(nexo);
		Assert.assertTrue(nexo.getEscudo().obtenerResistenciaActual() == 230 );
					
			
		/*Espectro espectro = new Espectro();
		NexoMineral nexoMineral = new NexoMineral();
		Assert.assertTrue(nexoMineral.getEscudo().obtenerResistenciaActual() == 250 );
		espectro.atacarEnAire(nexoMineral);
		Assert.assertTrue(nexoMineral.getEscudo().obtenerResistenciaActual() == 230 );*/
			
	}
	
	public void testNaveTransporteTerranTransporta8Marines(){
			
		NaveTransporteTerran naveTransporte = new NaveTransporteTerran();
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
		
		NaveTransporteTerran naveTransporte = new NaveTransporteTerran();
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
	
}
