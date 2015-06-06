package test;

import org.junit.Assert;

import src.Jugador;
import src.construcciones.Acceso;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.*;
import junit.framework.TestCase;

public class UnidadesProtossTest extends TestCase{
	
	
	protected void setUp() throws Exception {
		

		super.setUp();
		Mapa mapa = new Mapa(50);
		Jugador jugador = new Jugador("El cid campeador","rojo", new Terran());
		
	}
	
	
	public void testZealotSeCreaCon60escudoY100deVida(){
		
		Acceso acceso = new Acceso();
		Zealot zealot = acceso.crearZealot();
		
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 60 );
		
	}
	
	public void testAtacarZealotNuevoPorMarineSoloDaniaEscudo(){
		
		Acceso acceso = new Acceso();
		Zealot zealot = acceso.crearZealot();
		Marine marine = new Marine();
		
		marine.atacarEnTierra(zealot);
		Assert.assertTrue((zealot.getVida().obtenerVida()) == 100);
		Assert.assertTrue((zealot.getEscudo().obtenerResistenciaActual()) == 54 );
		
	}
	
	
	
	
	

}