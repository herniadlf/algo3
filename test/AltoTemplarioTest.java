package test;

import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Assert;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Jugador;
import src.construcciones.ArchivosTemplarios;
import src.construcciones.Fabrica;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Terran;
import src.unidades.AltoTemplario;
import src.unidades.Espectro;
import src.unidades.Scout;
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
		altoTemplario = new AltoTemplario();
		Posicion pos = new Posicion(30, 30);
		altoTemplario.setPosicion(pos);
		altoTemplario.setMapa(mapa);
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
		Assert.assertTrue(altoTemplario.obtenerEnergia() == 5);
		
	}
	
	public void  testAlucinacionCuesta100Energia() throws InstantiationException, IllegalAccessException, ExcepcionPosicionInvalida, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		Espectro espectro = new Espectro();
		espectro.setMapa(mapa);
		Posicion posicion = new Posicion(10, 10);
		espectro.setPosicion(posicion);
		
		altoTemplario.pasoTurno();//65
		altoTemplario.pasoTurno();//80
		altoTemplario.pasoTurno();//95
		altoTemplario.pasoTurno();//110
		altoTemplario.alucinacion(espectro);
		
		Assert.assertTrue(altoTemplario.obtenerEnergia() == 10);
		
	}
	
	
	public void testTormentaPsionicaDaņa100DuranteDosTurnosAtodasLasUnidades() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion{
		
		altoTemplario.setMapa(mapa);
		Scout scout = new Scout();
		scout.setMapa(mapa);
		Fabrica fabrica = new Fabrica();
		//Marine marine = new Marine();
		
		mapa.colocarEn(21, 21, scout);
		Posicion posicionScout = new Posicion(21,21);
		scout.setPosicion(posicionScout);
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
	
	
	public void testAltoTemplarioAlucinaUnScout() throws ExcepcionPosicionInvalida, InstantiationException, IllegalAccessException, ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion{
		
		Scout scout = new Scout();
		scout.setMapa(mapa);
		Posicion posicionScout = new Posicion(20,20);
		scout.setPosicion(posicionScout);
		altoTemplario.pasoTurno();
		altoTemplario.pasoTurno();
		altoTemplario.pasoTurno();
		altoTemplario.pasoTurno();
		altoTemplario.alucinacion(scout);
		int cantidadDeScouts = 0;
		int posicionX = scout.getPosicionX();
		int posicionY = scout.getPosicionY();
		LinkedList<Posicion> alrededores;	
		
		alrededores = new LinkedList<Posicion>();
		alrededores.add(new Posicion(posicionX-1,posicionY+1));
		alrededores.add(new Posicion(posicionX,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY+1));
		alrededores.add(new Posicion(posicionX+1,posicionY));
		alrededores.add(new Posicion(posicionX+1,posicionY-1));
		alrededores.add(new Posicion(posicionX,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY-1));
		alrededores.add(new Posicion(posicionX-1,posicionY));	
		
		Iterator<Posicion> i = alrededores.iterator();
		while(i.hasNext()){
			Posicion posicion = i.next();
			if(mapa.obtenerContenidoEnPosicion(posicion.getX(), posicion.getY()).getElementoEnAire().esLoMismo(new Scout())){
					
				cantidadDeScouts++;
			}
		}
	
		Assert.assertEquals(2, cantidadDeScouts);
	}
}
