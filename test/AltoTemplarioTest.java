package test;

import src.Jugador;
import src.construcciones.ArchivosTemplarios;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.AltoTemplario;
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
		
		altoTemplario.pasoTurno();
		altoTemplario.pasoTurno();
		altoTemplario.tormentaPsionica(30,30);
		Assert.assertTrue(altoTemplario.obtenerEnegia() == 5);
		
	}

}
