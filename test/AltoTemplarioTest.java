package test;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Jugador;
import src.Mineral;
import src.construcciones.ArchivosTemplarios;
import src.construcciones.Fabrica;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.AltoTemplario;
import src.unidades.Dragon;
import src.unidades.Marine;
import src.unidades.NaveCiencia;
import src.unidades.Scout;
import src.unidades.Zealot;
import junit.framework.Assert;
import junit.framework.TestCase;

public class AltoTemplarioTest extends TestCase {
	
	Mapa mapa;
	Jugador jugador;
	ArchivosTemplarios archivosTemplarios;
	AltoTemplario altoTemplario;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		mapa = new Mapa(50);
		jugador = new Jugador("Atila","rojo", new Terran());
		archivosTemplarios = new ArchivosTemplarios();
		altoTemplario = archivosTemplarios.crearAltoTemplario();
		
	}
	
	
	public void testAltoTemplarioComienzaCon40Vida40EscudoY50Energia() {
		
		assertTrue(altoTemplario.getVida().obtenerVida() == 40);
		assertTrue(altoTemplario.getEscudo().obtenerResistenciaActual() == 40);
		assertTrue(altoTemplario.obtenerEnergia() == 50);
		
	}
	
	
	public void testTormentaPsionicaCuesta75Energia(){
		
		altoTemplario.setMapa(mapa);
		altoTemplario.pasoTurno();
		altoTemplario.pasoTurno();
		altoTemplario.tormentaPsionica(30,30);
		Assert.assertTrue(altoTemplario.obtenerEnergia() == 5);
		
	}
	
	
	public void testTormentaPsionicaDaña100DuranteDosTurnosAtodasLasUnidades() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		altoTemplario.setMapa(mapa);
		Scout scout = new Scout();
		Fabrica fabrica = new Fabrica();
		//Marine marine = new Marine();
		
		mapa.colocarEn(21, 21, scout);
		//mapa.colocarEn(20, 21, marine);
		mapa.colocarEn(20, 20, fabrica);
		
		//cargar energia
		altoTemplario.pasoTurno();//65
		altoTemplario.pasoTurno();//80
		
		altoTemplario.tormentaPsionica(20, 20); //5
		Assert.assertTrue(altoTemplario.obtenerEnergia() == 5);
		
		//1er Turno saco 100 solo a UNIDADES
		Assert.assertTrue(scout.getEscudo().obtenerResistenciaActual() == 0);
		Assert.assertTrue(scout.getVida().obtenerVida() == 150);
		Assert.assertTrue(fabrica.getVida().obtenerVida() == 1250);
		//Assert.assertTrue(marine.getVida().obtenerVida() == 0);
		
		//2do Turno saca 100 mas
		altoTemplario.pasoTurno();//20
		
		Assert.assertTrue(altoTemplario.obtenerEnergia() == 20);
		Assert.assertTrue(scout.getEscudo().obtenerResistenciaActual() == 0);
		Assert.assertTrue(scout.getVida().obtenerVida() == 50);
		Assert.assertTrue(fabrica.getVida().obtenerVida() == 1250);
		//Assert.assertTrue(marine.getVida().obtenerVida() == 0);
		
	}

}
