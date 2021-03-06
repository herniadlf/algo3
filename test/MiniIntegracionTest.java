package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElEdificioNoPerteneceATusConstrucciones;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionFinDeTurnoPorMaximoDeAtaques;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionNoPudoCrearseUnidad;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.AtaquesPermitidosPorTurno;
import src.Juego;
import src.Jugador;
import src.Turno;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Fabrica;
import src.construcciones.PuertoEstelarTerran;
import src.mapa.Escombros;
import src.mapa.Mapa;
import src.mapa.Posicion;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.AltoTemplario;
import src.unidades.Espectro;
import src.unidades.Marine;
import src.unidades.TormentaPsionica;
import src.unidades.Unidad;
import src.unidades.Zealot;

public class MiniIntegracionTest {
	
	
	@Test
	public void testCrearUnidadesSegunTurno() 
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionNoPudoCrearseUnidad, 
			ExcepcionErrorPasoDeTurno, 
			ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		
		jugador1.colocar(new DepositoDeSuministros(), mapa, 30, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 40, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 10, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 20, 30);
		jugador1.colocar(new DepositoDeSuministros(), mapa, 20, 42);		
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);		
		jugador1.getConstruccionesEnPie().add(barraca);
		
		Unidad marine = new Marine();
		Unidad marine2 = new Marine ();
		Unidad marine3 = new Marine ();		
		
		juego.ordenarFabricacionUnidad(marine,(Creadora) barraca);
		juego.ordenarFabricacionUnidad(marine2,(Creadora) barraca);
		juego.ordenarFabricacionUnidad(marine3,(Creadora) barraca);
		
		for (int i = 0; i<4;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();
		}		
		
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(4,6).getElementoEnTierra().esLoMismo(new Marine()));
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(6,6).getElementoEnTierra().esLoMismo(new Marine()));
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(5,6).getElementoEnTierra().esLoMismo(new Marine()));
			
		Assert.assertTrue(juego.getJugadorActual().contieneALaUnidad(marine));
		Assert.assertTrue(juego.getJugadorActual().contieneALaUnidad(marine2));
		Assert.assertTrue(juego.getJugadorActual().contieneALaUnidad(marine3));
			
	}
	
	@Test
	public void testCrearEdificiosSegunTurno()
			throws ExcepcionNoPudoColocarseEdificio, 
			ExcepcionPosicionInvalida, ExcepcionErrorPasoDeTurno, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {

		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		
		Construccion barraca = new Barraca();
		
		juego.ordenFabricacionDeEdificios(barraca, 70, 70);
		
		Assert.assertFalse(juego.getMapa().obtenerContenidoEnPosicion(70, 70).getElementoEnTierra().esLoMismo(new Barraca()));
		
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(70, 70).getElementoEnTierra().esLoMismo(new Escombros()));
		
		for (int i = 0; i<13;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();
		}			
		
		
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(70, 70).getElementoEnTierra().esLoMismo(new Barraca()));
		
		Assert.assertTrue(juego.getJugadorActual().contieneALaConstruccion(barraca));
	}
	
	@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
	public void noConstruyeFabricaSinBarraca() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido
			 {

		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		
		juego.ordenFabricacionDeEdificios(new Fabrica(), 10, 10);			
				 
	}
	
	
	@Test (expected = ExcepcionFinDeTurnoPorMaximoDeAtaques.class)
	public void luegoDeDiezAtaquesPasaElTurno() throws ExcepcionPosicionInvalida,
	ExcepcionNoHayLugarParaCrear, ExcepcionYaHayElementoEnLaPosicion, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionNoPudoColocarseEdificio, ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido, ExcepcionFinDeTurnoPorMaximoDeAtaques{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
		Turno turno = new Turno();
		
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setJuego(juego);
		jugador2.setJuego(juego);
		
		jugador1.setDinero(99999, 99999);
		jugador2.setDinero(9999, 99999);
		jugador1.getAtaquesPermitidosPorTurno().setJuego(juego);
		
		
		
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);		
		
		Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 2, 2);		
		
		AtaquesPermitidosPorTurno ataques = new AtaquesPermitidosPorTurno();
		Unidad primerMarine = new Marine();
		primerMarine.setJugador(jugador1);
		Unidad segundoMarine = new Marine();
		segundoMarine.setJugador(jugador1);
		Unidad tercerMarine = new Marine();
		tercerMarine.setJugador(jugador1);
		Unidad zealot = new Zealot();
		zealot.setJugador(jugador2);
		primerMarine.setAtaquesPermitidosPorTurno(ataques);
		segundoMarine.setAtaquesPermitidosPorTurno(ataques);
		tercerMarine.setAtaquesPermitidosPorTurno(ataques);
		ataques.setJuego(juego);
		
		barraca.colocarUnidad(primerMarine, mapa);	
		barraca.colocarUnidad(segundoMarine, mapa);
		barraca.colocarUnidad(tercerMarine, mapa);
		jugador1.getUnidadesAlistadas().add(primerMarine);
		jugador1.getUnidadesAlistadas().add(segundoMarine);
		jugador1.getUnidadesAlistadas().add(tercerMarine);
		acceso.colocarUnidad(zealot, mapa);
		Assert.assertTrue(juego.getJugadorActual().getNombre() == "carlos");
				
		
		jugador1.atacarCon(primerMarine, zealot);
		jugador1.atacarCon(segundoMarine, zealot);
		jugador1.atacarCon(tercerMarine, zealot);
		jugador1.atacarCon(primerMarine, zealot);
		jugador1.atacarCon(segundoMarine, zealot);
		jugador1.atacarCon(tercerMarine, zealot);
		jugador1.atacarCon(primerMarine, zealot);
		jugador1.atacarCon(segundoMarine, zealot);
		jugador1.atacarCon(tercerMarine, zealot);
		jugador1.atacarCon(primerMarine, zealot);
		
		
	}
	
	@Test
	public void creacionDeEspectroYEdificiosNecesariosATravesDeJuego() 
	throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, 
		ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoCrearseUnidad, ExcepcionNoPudoColocarseEdificio, ExcepcionErrorPasoDeTurno, ExcepcionNoHayLugarParaCrear, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		
		
		DepositoDeSuministros  depositoDeSuministro1 = new DepositoDeSuministros();
		DepositoDeSuministros  depositoDeSuministro2 = new DepositoDeSuministros();
		Barraca barraca= new Barraca();
		Fabrica fabrica = new Fabrica();
		PuertoEstelarTerran puertoEstelar= new PuertoEstelarTerran();
		
		
		juego.ordenFabricacionDeEdificios(depositoDeSuministro1, 72, 77);
		juego.ordenFabricacionDeEdificios(depositoDeSuministro2, 70, 75);
		juego.ordenFabricacionDeEdificios(barraca, 74, 74);
		
		for (int i = 0; i<12;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();
		}		
		
		//Verifico que se crearon los depositosDeSuministros en el mapa 
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(72,77).getElementoEnTierra().esLoMismo(new DepositoDeSuministros()));
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(70,75).getElementoEnTierra().esLoMismo(new DepositoDeSuministros()));
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(74,74).getElementoEnTierra().esLoMismo(new Barraca()));
		
		juego.ordenFabricacionDeEdificios(fabrica, 72, 72);
		
		for (int i = 0; i<12;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();}
		
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(72,72).getElementoEnTierra().esLoMismo(new Fabrica()));
		
		
		juego.ordenFabricacionDeEdificios(puertoEstelar, 70, 77);
		
		for (int i = 0; i<10;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();}
		
		Assert.assertTrue(mapa.obtenerContenidoEnPosicion(70,77).getElementoEnTierra().esLoMismo(new PuertoEstelarTerran()));
		
		
		
		Unidad espectro = new Espectro();
		
		juego.ordenarFabricacionUnidad(espectro,(Creadora) puertoEstelar);
		
		for (int i = 0; i<8;i++){ // pasar turnos para que se creen
			juego.pasarTurno();
			juego.pasarTurno();
		}		
		
	
		Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(69,78).getElementoEnAire().esLoMismo(new Espectro()));
		
		}
	
	@Test (expected = ExcepcionNoPudoCrearseUnidad.class)
	public void intentarCrearUnidadQueNoCorrespondeAlEdificioLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionNoPudoCrearseUnidad, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionTamanioDelMapaInvalido{
	
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
			
		Creadora barraca = (Creadora) jugador1.colocar(new Barraca(), mapa, 5, 5);		
		jugador1.getConstruccionesEnPie().add(barraca);
		
		Unidad espectro = new Espectro();
		
		juego.ordenarFabricacionUnidad(espectro,(Creadora) barraca);	
	
	}
	
	@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
	public void intentarCrearEdificioQueNoCorrespondeALaRazaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionNoPudoCrearseUnidad, ExcepcionElEdificioNoPerteneceATusConstrucciones, ExcepcionTamanioDelMapaInvalido{
	
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Terran());
		
		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		
		Construccion acceso = new Acceso();
		
		juego.ordenFabricacionDeEdificios(acceso, 10, 10);
	
	}
	
	@Test
	public void jugadorAtacarConMagia() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido, ExcepcionEdificioNoPuedeCrearUnidad, ExcepcionNoHayLugarParaCrear, ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionElementoFueraDelRangoDeAtaque, ExcepcionLaUnidadNoPertenceATuTropa{
		
		Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
		Jugador jugador2 = new Jugador ("dean","azul",new Protoss());

		Juego juego = new Juego(jugador1, jugador2, 100, 0);
		Mapa mapa = juego.getMapa();
		jugador1.setDinero(99999, 99999);
		
		Marine marine = new Marine();
		Posicion posMarine = new Posicion(10,10);
		marine.setPosicion(posMarine);
		marine.setMapa(mapa);
		mapa.colocarEn(10, 10, marine);
		
		AltoTemplario alto = new AltoTemplario();
		Posicion posAlto = new Posicion(15,15);
		alto.setPosicion(posAlto);
		alto.setMapa(mapa);
		mapa.colocarEn(15, 15, alto);
		
		jugador1.agregarAUnidadesAlistadas(marine);
		jugador2.agregarAUnidadesAlistadas(alto);
		
		alto.pasoTurno();
		alto.pasoTurno();
		alto.pasoTurno();
		marine.setJugador(jugador1);
		
		jugador2.atacarCon(alto, new TormentaPsionica(marine));
		
		Assert.assertTrue(marine.getVida().estaMuerto());
		
		
	}
	
	
	
}
