package test;

import org.junit.Assert;

import src.Jugador;
import src.Mineral;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Terran;
import src.unidades.AltoTemplario;
import src.unidades.Dragon;
import src.unidades.NaveCiencia;
import src.unidades.Zealot;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoPuedeMoverseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
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
			jugador.colocar(puertoEstelar,mapa,30,30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		NaveCiencia naveCiencia = new NaveCiencia();
	
		Assert.assertTrue(naveCiencia.getVida().obtenerVida() == 200);

	}
	
	public void testNaveCienciaUsaRadiacionContraEnemigoYPierde75Energia() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		NaveCiencia naveCiencia = new NaveCiencia();
		Dragon dragon = new Dragon();
		naveCiencia.setMapa(mapa);
		Posicion posicionDragon = new Posicion(20,20);
		dragon.setPosicion(posicionDragon);
		mapa.colocarEn(20, 20, dragon);
		
		
		Assert.assertTrue(naveCiencia.obtenerEnergia() == 50);
		
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		
		Assert.assertTrue(naveCiencia.obtenerEnergia() == 80);
		naveCiencia.radiacion(dragon);
		Assert.assertTrue(naveCiencia.obtenerEnergia() == 5);	
		
	}
	
	
	public void testNaveCienciaUtilizaEMPDaniaEscudoProtoss() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		NaveCiencia naveCiencia = new NaveCiencia();
		naveCiencia.setMapa(mapa);
		Dragon dragon = new Dragon();
		Zealot zealot = new Zealot();
		AltoTemplario altoTemplario = new AltoTemplario();
		
		mapa.colocarEn(21, 21, dragon);
		mapa.colocarEn(20, 21, zealot);
		mapa.colocarEn(20, 20, altoTemplario);
		
		
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();

		naveCiencia.emp(20, 20); //saca 20 escudo 0 20 energia

		Assert.assertTrue(dragon.getEscudo().obtenerResistenciaActual() == 60);
		Assert.assertTrue(zealot.getEscudo().obtenerResistenciaActual() == 40);
		Assert.assertTrue(altoTemplario.obtenerEnergia() == 30);
		
	}
	
	public void testNaveCienciaUtilizaRadiacionContraAltoTemplarioHastaMatarlo() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		AltoTemplario altoTemplario = new AltoTemplario();
		altoTemplario.setMapa(mapa);
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		
		Posicion posicionAltoTemplario = new Posicion(20,20);
		altoTemplario.setPosicion(posicionAltoTemplario);
		mapa.colocarEn(20, 20, altoTemplario);
		naveCiencia.setMapa(mapa);
		
		naveCiencia.radiacion(altoTemplario);
		Assert.assertTrue(altoTemplario.getEscudo().obtenerResistenciaActual() == 10);
		
		altoTemplario.pasoTurno();
		Assert.assertTrue(altoTemplario.getEscudo().obtenerResistenciaActual() == 0);
		Assert.assertTrue(altoTemplario.getVida().obtenerVida() == 20);
		
		altoTemplario.pasoTurno();
		Assert.assertTrue(altoTemplario.getVida().devolverEstadoDeVida());
		
	}
	
	public void testAltoTemplarioAfectadoPorRadiacionAlMoverseDaniaUnidadesASuAlrededor() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionNoPuedeMoverseUnidad{
		
		AltoTemplario altoTemplario = new AltoTemplario();
		altoTemplario.setMapa(mapa);
		
		Dragon dragon = new Dragon();
		dragon.setMapa(mapa);
		Posicion posicionDragon = new Posicion(10,17);
		dragon.setPosicion(posicionDragon);
		mapa.colocarEn(10,17,dragon);
		
		NaveCiencia naveCiencia = new NaveCiencia();
		
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		naveCiencia.pasoTurno();
		
		Posicion posicionAltoTemplario = new Posicion(10,10);
		altoTemplario.setPosicion(posicionAltoTemplario);
		mapa.colocarEn(10, 10, altoTemplario);
		naveCiencia.setMapa(mapa);
		
		naveCiencia.radiacion(altoTemplario);
		Assert.assertTrue(dragon.getEscudo().obtenerResistenciaActual() == 80); //no lo afecto
		
		altoTemplario.moverAPosicionDeterminada(10,16);
		
		altoTemplario.pasoTurno();
		
		Assert.assertTrue(dragon.getEscudo().obtenerResistenciaActual() == 50);
		
		
	}
	
	
	
}
