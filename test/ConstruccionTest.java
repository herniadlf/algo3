package test;

import org.junit.Assert;
import org.junit.Test;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionEdificioPrevioRequerido;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionExtractoraSinRecurso;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionNoPudoColocarseEdificio;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.*;
import src.construcciones.Acceso;
import src.construcciones.Barraca;
import src.construcciones.CentroDeMineral;
import src.construcciones.Construccion;
import src.construcciones.Creadora;
import src.construcciones.DepositoDeSuministros;
import src.construcciones.Extractora;
import src.construcciones.Fabrica;
import src.construcciones.NexoMineral;
import src.construcciones.Pilon;
import src.construcciones.PuertoEstelarTerran;
import src.construcciones.Refineria;
import src.mapa.Escombros;
import src.mapa.FuenteDeGasVespeno;
import src.mapa.FuenteDeMinerales;
import src.mapa.Mapa;
import src.mapa.EspacioDisponible;
import src.razas.Protoss;
import src.razas.Terran;
import src.unidades.Espectro;
import src.unidades.Marine;
import src.unidades.Zealot;
public class ConstruccionTest {
		
						
		@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
		public void noConstruyeFabricaSinBarraca() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionTamanioDelMapaInvalido {
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Terran());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			
			juego.ordenFabricacionDeEdificios(new Fabrica(), 3, 3);
		}
	
		
		@Test
		public void nuevoPilonAumentaSuministrosDeJugador() throws ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionTamanioDelMapaInvalido, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos{
			
			Jugador jug = new Jugador ("carlos","rojo",new Protoss());
			Jugador jug2 = new Jugador ("mario","verde",new Protoss());
			Juego juego = new Juego(jug, jug2, 50, 0);
			Mapa mapa = juego.getMapa();
			jug.colocar(new Pilon(),mapa,9,9);
			Assert.assertEquals(10, jug.getPoblacionDisponible());
			jug.colocar(new Pilon(),mapa,5,5);
			Assert.assertEquals(15, jug.getPoblacionDisponible());
		}
		
		
		@Test
		public void creacionDeUnidadesVoladorasAlrededorDeEdificio() 
				throws ExcepcionNoPudoColocarseEdificio, 
				ExcepcionPosicionInvalida, 
				ExcepcionNoHayLugarParaCrear, 
				ExcepcionYaHayElementoEnLaPosicion, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido, ExcepcionSuperaLimenteDeArbolesPermitos  {
			Jugador jug = new Jugador ("carlos","rojo",new Terran());
			Jugador jug2 = new Jugador ("mario","verde",new Protoss());
			Juego juego = new Juego(jug, jug2,100, 0);
			Mapa mapa = juego.getMapa();
			
			Construccion barraca = jug.colocar(new Barraca(),mapa,77,78);
			jug.getConstruccionesEnPie().add(barraca);
			Construccion fabrica = jug.colocar(new Fabrica(),mapa, 77,79);
			jug.getConstruccionesEnPie().add(fabrica);
			Creadora puertoEstelarTerran = (Creadora) jug.colocar(new PuertoEstelarTerran(), mapa, 71, 71);
			//Como creo 8 espectrs que requieren 2 suministros, en total necesito 16 suministros,
			jug.setPoblacionDisponible(999999);		

			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			
			// VERIFICO QUE SE CREAN A LOS COSTADOS DEL EDIFICIO
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(70,70).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(70,71).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(70,72).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(71,70).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(71,72).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(72,70).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(72,71).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(72,72).getElementoEnAire().esLoMismo(new Espectro()));
		}
		
		@Test 
		public void creacionDeUnidadesTerrestresAlrededorDeEdificio() 
				throws ExcepcionNoPudoColocarseEdificio, 
				ExcepcionPosicionInvalida,
				ExcepcionNoHayLugarParaCrear, 
				ExcepcionYaHayElementoEnLaPosicion, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido, ExcepcionSuperaLimenteDeArbolesPermitos {
			
			Jugador jug = new Jugador ("carlos","rojo",new Terran());
			Jugador jug2 = new Jugador ("mario","verde",new Protoss());
			Juego juego = new Juego(jug, jug2, 100, 0);
			Mapa mapa = juego.getMapa();
			jug.setDinero(999999, 999999);
			jug.colocar(new DepositoDeSuministros(), mapa, 70, 70);
			Creadora barraca = (Creadora) jug.colocar(new Barraca(),mapa,20,20);
			
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			
			// VERIFICO QUE SE CREAN A LOS COSTADOS DEL EDIFICIO
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(19,19).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(19,20).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(19,21).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(20,19).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(20,21).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(21,19).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(21,20).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(21,21).getElementoEnTierra().esLoMismo(new Marine()));
		}
		
		@Test
		public void seColectanRecursosAlPasarTurno() 
				throws ExcepcionPosicionInvalida, 
				ExcepcionYaHayElementoEnLaPosicion, 
				ExcepcionNoPudoColocarseEdificio, 
				ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Terran());
			Juego juego = new Juego (jugador1, jugador2, 100, 0);
			Mapa mapa = juego.getMapa();
			mapa.colocarEn(4, 4, new FuenteDeMinerales());
			mapa.colocarEn(5,5, new FuenteDeGasVespeno());
			
			Assert.assertTrue(jugador1.getDinero().getMinerales() == 200); // INICIALES
			Assert.assertTrue(jugador1.getDinero().getGasVespeno() == 200); // INICIALES
			
			Extractora deMinerales = (Extractora) jugador1.colocar(new CentroDeMineral(),mapa,4,4);
			Extractora deGas = (Extractora) jugador1.colocar(new Refineria(), mapa, 5, 5);
			jugador1.getConstruccionesEnPie().add(deMinerales);
			jugador1.getConstruccionesEnPie().add(deGas);
			
			juego.pasarTurno();
			juego.pasarTurno();
			
			Assert.assertTrue(jugador1.getDinero().getMinerales() == 210); // +10 por turno
			Assert.assertTrue(jugador1.getDinero().getGasVespeno() == 210); // +10 por turno
		}
		
		@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
		public void seReservaLugarAunqueLaConstruccionNoEstaFinalizada() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida, ExcepcionErrorPasoDeTurno, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
			
			Mapa mapa = new Mapa(50);
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2,100, 0);
			
			juego.ordenFabricacionDeEdificios(new Barraca(), 3, 3);
			Assert.assertFalse(mapa.obtenerContenidoEnPosicion(3, 3).getElementoEnTierra().esLoMismo(new Barraca()));
			//todavia no se termino la construccion
			juego.pasarTurno();
			juego.ordenFabricacionDeEdificios(new Acceso(), 3, 3);
			//el otro jugador intenta construir, pero ya esta el espacio reservado
		}
			
		@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
		public void seReservaLugarParaExtractoraDeRecursos() 
				throws ExcepcionPosicionInvalida, 
				ExcepcionYaHayElementoEnLaPosicion, 
				ExcepcionNoPudoColocarseEdificio,
				ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
		
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			Mapa mapa = juego.getMapa();
			mapa.colocarEn(3, 3, new FuenteDeMinerales());
			
			juego.ordenFabricacionDeEdificios(new CentroDeMineral(), 5, 5);
			Assert.assertFalse(mapa.obtenerContenidoEnPosicion(3, 3).getElementoEnTierra().esLoMismo(new CentroDeMineral()));
			
			juego.pasarTurno();
			juego.ordenFabricacionDeEdificios(new NexoMineral(), 5, 5);
		}
		
		@Test
		public void testDestruccionDeEdificioProvocaEliminacionDelMapa() throws ExcepcionPosicionInvalida, ExcepcionNoPudoColocarseEdificio, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionTamanioDelMapaInvalido {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Terran());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			Mapa mapa = juego.getMapa();
			jugador1.setDinero(99999, 99999);	
			
			Creadora acceso = (Creadora) jugador2.colocar(new Barraca(), mapa, 2, 2);		
			
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(2, 2).getElementoEnTierra().getNombre()=="Barraca");
					
			acceso.getVida().aumentarDanioARecibir(600);
			acceso.recibirDanio();
			
			Assert.assertTrue(acceso.getVida().obtenerVida()==400);
			
			acceso.getVida().aumentarDanioARecibir(400);
			acceso.recibirDanio();
			
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(2, 2).getElementoEnTierra().getNombre()=="Espacio Disponible");

			}
		
		@Test
		public void testDestruccionDeEdificioLoEliminaDeEdificiosEnPie() throws ExcepcionPosicionInvalida, ExcepcionNoPudoColocarseEdificio, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			jugador1.setDinero(99999, 99999);	
			
			Acceso acceso = new Acceso();
			juego.ordenFabricacionDeEdificios(acceso, 70, 75); 	
			
			for (int i = 0; i < 8; i++){
				juego.pasarTurno();
				juego.pasarTurno();				
			}
			Assert.assertTrue(jugador1.getConstruccionesEnPie().contains(acceso));
			
			
			acceso.getVida().aumentarDanioARecibir(1000);
			acceso.recibirDanio();
			juego.pasarTurno();
			juego.pasarTurno();
			Assert.assertFalse(jugador1.getConstruccionesEnPie().contains(acceso));

			}
		
		@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
		public void testDestruccionDeEdificioRequeridoImpideCreacion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionTamanioDelMapaInvalido{
			Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			jugador1.setDinero(99999, 99999);
			
			Construccion barraca = jugador1.colocar(new Barraca(), juego.getMapa(), 5,5);
			Construccion fabrica = jugador1.colocar(new Fabrica(), juego.getMapa(), 6, 8);
			
			Assert.assertTrue ( juego.getMapa().obtenerContenidoEnPosicion(6, 8).getElementoEnTierra().esLoMismo(fabrica));
			
			barraca.getVida().aumentarDanioARecibir(1001);
			barraca.recibirDanio();
			
			juego.pasarTurno();
			juego.pasarTurno();
			
			Construccion fabrica_2 = new Fabrica();
			juego.ordenFabricacionDeEdificios(fabrica_2, 10, 10);
			
		}
		
		@Test (expected = ExcepcionConstruccionNoCorrespondiente.class)
		public void testCrearEdificioDeOtraRazaLanzaExcepcion() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionNoPudoColocarseEdificio, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionTamanioDelMapaInvalido {
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			jugador1.setDinero(99999, 99999);
			Acceso acceso = new Acceso();
			
			Construccion construccion = jugador1.colocar(acceso, juego.getMapa(), 5,5);
			jugador1.verificacionEdificio(acceso);
	
			
		}
		
		@Test
		public void testCrearDosEdificiosElMismoTurno() throws ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionErrorPasoDeTurno, ExcepcionConstruccionNoCorrespondiente, ExcepcionRecursoInsuficiente, ExcepcionUnidadNoCorrespondiente, ExcepcionNoPudoColocarseEdificio, ExcepcionTamanioDelMapaInvalido {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100, 0);
			jugador1.setDinero(99999, 99999);
			
			juego.ordenFabricacionDeEdificios(new Barraca(), 74, 77);
			
			juego.ordenFabricacionDeEdificios(new Barraca(), 80, 80);
						
			for (int i = 0; i < 12; i++){
				juego.pasarTurno();
				juego.pasarTurno();				
			}

			Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(74, 77).getElementoEnTierra().esLoMismo(new Barraca()));
			Assert.assertTrue(juego.getMapa().obtenerContenidoEnPosicion(80, 80).getElementoEnTierra().esLoMismo(new Barraca()));
			
		}
		
}
