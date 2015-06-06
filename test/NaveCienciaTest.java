package test;

import org.junit.Assert;

import src.Jugador;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.Mapa;
import src.razas.Terran;
import src.unidades.Dragon;
import src.unidades.NaveCiencia;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionPosicionInvalida;
import junit.framework.TestCase;

public class NaveCienciaTest extends TestCase {

	Mapa mapa;
	Jugador jugador;
	
	protected void setUp() throws Exception {
		
		super.setUp();
		mapa = new Mapa(50);
		jugador = new Jugador("carlos","rojo", new Terran());
		
	}
	
	public void testNaveCienciaSeCreaCon200DeVida() throws ExcepcionPosicionInvalida, ExcepcionExtractoraSinRecurso{
		
		PuertoEstelarTerran puertoEstelar = new PuertoEstelarTerran();
		try {
			jugador.construir(puertoEstelar,mapa,30,30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		NaveCiencia naveCiencia = puertoEstelar.crearNaveCiencia();
	
		Assert.assertTrue(naveCiencia.getVida().obtenerVida() == 200);

	}
	
	
	public void testNaveCienciaUsaRadiacionContraEnemigoYPierde75Energia(){
		
		NaveCiencia naveCiencia = new NaveCiencia();
		Dragon dragon = new Dragon();
		
		Assert.assertTrue(naveCiencia.obtenerEnergia() == 50);
		
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		
		Assert.assertTrue(naveCiencia.obtenerEnergia() == 80);
		naveCiencia.atacarConRadiacion(dragon);
		Assert.assertTrue(naveCiencia.obtenerEnergia() == 5);	
		
	}
	
	
}
