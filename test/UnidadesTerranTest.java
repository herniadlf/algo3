package test;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;
import src.unidades.*;

import org.junit.Assert;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElTransporteEstaLleno;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionGeneral;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionUnidadNoCorrespondiente;
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
		
	public void testEspectroSeCreaCon120deVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		Espectro espectro = new Espectro();
		Assert.assertTrue(espectro.getVida().obtenerVida() == 120);
			
	}
			
	public void testmarineAtacaZealotSoloRompeEscudo() 
		throws ExcepcionNoPudoColocarseEdificio, 
		ExcepcionPosicionInvalida, 
		ExcepcionNoHayLugarParaCrear, 
		ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente {
			
		/*--------------------------------------------------------------------------------*/
		Jugador jug1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jug2 = new Jugador ("williams", "azul", new Protoss());
		Juego juego = new Juego(jug1, jug2, 100, 0);
		Mapa mapa = juego.getMapa();
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
			
		Creadora barraca = (Creadora) jug1.colocar(new Barraca(),mapa,5,5);
		Creadora acceso = (Creadora) jug2.colocar(new Acceso(), mapa, 8, 8);
			
		Unidad marine = new Marine();
		barraca.colocarUnidad(marine, mapa);
		Unidad zealot = new Zealot();
		acceso.colocarUnidad(zealot, mapa);
		marine.setAtaquesPermitidosPorTurno(ataques);
		ataques.setJuego(juego);
		jug1.getUnidadesAlistades().add(marine);
		jug1.atacarCon(marine, zealot);
		Vida vida= zealot.getVida();
		Assert.assertTrue((vida.obtenerVida()) == 100);		
	
	}	
		
	public void testEspectroPuedeAtacarEdificiosEnemigos() 
		throws ExcepcionPosicionInvalida, 
		ExcepcionNoHayLugarParaCrear, 
		ExcepcionYaHayElementoEnLaPosicion, ExcepcionNoPudoColocarseEdificio, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente{
			
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
		Juego juego = new Juego(jug1, jug2, 100, 0);
		Mapa mapa = juego.getMapa();
			
		Unidad espectro = new Espectro();
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
		espectro.setAtaquesPermitidosPorTurno(ataques);
		ataques.setJuego(juego);
		puertoEstelarTerran.colocarUnidad(espectro, mapa);
		jug1.getUnidadesAlistades().add(espectro);
		Assert.assertTrue(nexo.getEscudo().obtenerResistenciaActual() == 250 );
		jug1.atacarCon(espectro, nexo);
		Assert.assertTrue(nexo.getEscudo().obtenerResistenciaActual() == 242 );
					
	}
	
}
