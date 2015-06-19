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
		
		@Test
		public void construccionExitosaPorRecursosSuficientes() throws ExcepcionNoPudoColocarseEdificio {
				Mapa mapa = new Mapa(50);
				Jugador jug = new Jugador("carlos","rojo",new Terran()); // Jugador se crea con 800 M			
				
				Construccion barraca = jug.colocar(new Barraca(),mapa,2,2);// BARRACA CUESTA 150 M				
			
				Assert.assertEquals(650, jug.getDinero().getMinerales());
		}
			
		@Test
		public void construccionExitosaEnElMapa() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo",new Terran());
			jug.setDinero(9999999, 999999); // ya que pruebo construir en todos los puntos del mapa necesito mucha plata
			
			for (int i=1; i< mapa.getTamanioMapa();i++){
				for (int j=1; j<mapa.getTamanioMapa(); j++){
					Construccion b = new Barraca();
					b = jug.colocar(b, mapa, i, j);
					Assert.assertTrue( ( mapa.obtenerContenidoEnPosicion(i, j) ).getElementoEnTierra().esLoMismo(b));					
				}				
			}
		} 
				
		@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
		public void noConstruyeFabricaSinBarraca() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos {
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Terran());
			
			Juego juego = new Juego(jugador1, jugador2, 100);
			
			juego.ordenFabricacionDeEdificios(new Fabrica(), 3, 3);
		}
	
		
		@Test
		public void construccionExitosaDeFabricaConBarraca() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida {
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador("carlos","rojo", new Terran());
			
			Construccion barraca = jug.colocar(new Barraca(), mapa, 5,5);
			Construccion fabrica = jug.colocar(new Fabrica(), mapa, 6, 8);
			
			Assert.assertTrue ( mapa.obtenerContenidoEnPosicion(6, 8).getElementoEnTierra().esLoMismo(fabrica));
		}
		
		@Test
		public void nuevoPilonAumentaSuministrosDeJugador() throws ExcepcionNoPudoColocarseEdificio{
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador ("carlos","rojo",new Protoss());
			
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
				ExcepcionYaHayElementoEnLaPosicion  {
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador ("carlos","rojo",new Terran());
			jug.setDinero(999999, 999999);
			
			Construccion barraca = jug.colocar(new Barraca(),mapa,5,5);
			Construccion fabrica = jug.colocar(new Fabrica(),mapa, 9,9);
			Creadora puertoEstelarTerran = (Creadora) jug.colocar(new PuertoEstelarTerran(), mapa, 16, 16);
			//Como creo 8 espectrs que requieren 2 suministros, en total necesito 16 suministros,
			//por lo cual construyo 4 depositosdesuminitros 
			//para tener 20 suministros disponibles (5 suministros extras x edificio)
			jug.colocar(new DepositoDeSuministros(), mapa, 1, 1);
			jug.colocar(new DepositoDeSuministros(), mapa, 9, 8);
			jug.colocar(new DepositoDeSuministros(), mapa, 10, 2);
			jug.colocar(new DepositoDeSuministros(), mapa, 10, 4);			

			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			puertoEstelarTerran.colocarUnidad(new Espectro(), mapa);
			
			// VERIFICO QUE SE CREAN A LOS COSTADOS DEL EDIFICIO
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(15,17).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(16,17).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(17,17).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(17,16).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(17,15).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(16,15).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(15,15).getElementoEnAire().esLoMismo(new Espectro()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(15,16).getElementoEnAire().esLoMismo(new Espectro()));
		}
		@Test
		public void creacionDeUnidadesTerrestresAlrededorDeEdificio() 
				throws ExcepcionNoPudoColocarseEdificio, 
				ExcepcionPosicionInvalida,
				ExcepcionNoHayLugarParaCrear, 
				ExcepcionYaHayElementoEnLaPosicion {
			
			Mapa mapa = new Mapa(50);
			Jugador jug = new Jugador ("carlos","rojo",new Terran());
			jug.setDinero(999999, 999999);
			jug.colocar(new DepositoDeSuministros(), mapa, 30, 30);
			Creadora barraca = (Creadora) jug.colocar(new Barraca(),mapa,5,5);
			
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			barraca.colocarUnidad(new Marine(), mapa);
			
			// VERIFICO QUE SE CREAN A LOS COSTADOS DEL EDIFICIO
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(4,4).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(4,5).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(4,6).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(6,4).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(6,5).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(6,6).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(5,4).getElementoEnTierra().esLoMismo(new Marine()));
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(5,6).getElementoEnTierra().esLoMismo(new Marine()));
		}
		
		@Test
		public void seColectanRecursosAlPasarTurno() 
				throws ExcepcionPosicionInvalida, 
				ExcepcionYaHayElementoEnLaPosicion, 
				ExcepcionNoPudoColocarseEdificio, 
				ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Terran());
			Juego juego = new Juego (jugador1, jugador2, 100);
			Mapa mapa = juego.getMapa();
			mapa.colocarEn(4, 4, new FuenteDeMinerales());
			mapa.colocarEn(5,5, new FuenteDeGasVespeno());
			
			Assert.assertTrue(jugador1.getDinero().getMinerales() == 800); // INICIALES
			Assert.assertTrue(jugador1.getDinero().getGasVespeno() == 400); // INICIALES
			
			Extractora deMinerales = (Extractora) jugador1.colocar(new CentroDeMineral(),mapa,4,4);
			Extractora deGas = (Extractora) jugador1.colocar(new Refineria(), mapa, 5, 5);
			jugador1.getConstruccionesEnPie().add(deMinerales);
			jugador1.getConstruccionesEnPie().add(deGas);
			
			Assert.assertTrue(jugador1.getDinero().getMinerales() == 650); // gasto de construccion
			Assert.assertTrue(jugador1.getDinero().getGasVespeno() == 400); // gasto de construccion
			
			juego.pasarTurno();
			juego.pasarTurno();
			
			Assert.assertTrue(jugador1.getDinero().getMinerales() == 660); // +10 por turno
			Assert.assertTrue(jugador1.getDinero().getGasVespeno() == 410); // +10 por turno
		}
		
		@Test (expected = ExcepcionNoPudoColocarseEdificio.class)
		public void seReservaLugarAunqueLaConstruccionNoEstaFinalizada() throws ExcepcionNoPudoColocarseEdificio, ExcepcionPosicionInvalida, ExcepcionErrorPasoDeTurno, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos {
			
			Mapa mapa = new Mapa(50);
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2,100);
			
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
				ExcepcionErrorPasoDeTurno, ExcepcionSuperaLimenteDeArbolesPermitos {
		
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100);
			Mapa mapa = juego.getMapa();
			mapa.colocarEn(3, 3, new FuenteDeMinerales());
			
			juego.ordenFabricacionDeEdificios(new CentroDeMineral(), 5, 5);
			Assert.assertFalse(mapa.obtenerContenidoEnPosicion(3, 3).getElementoEnTierra().esLoMismo(new CentroDeMineral()));
			
			juego.pasarTurno();
			juego.ordenFabricacionDeEdificios(new NexoMineral(), 5, 5);
		}
		
		@Test
		public void testDestruccionDeEdificioProvocaEliminacionDelMapa() throws ExcepcionPosicionInvalida, ExcepcionNoPudoColocarseEdificio, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Terran());
			Jugador jugador2 = new Jugador ("dean","azul",new Terran());
			
			Juego juego = new Juego(jugador1, jugador2, 100);
			Mapa mapa = juego.getMapa();
			jugador1.setDinero(99999, 99999);	
			
			Creadora acceso = (Creadora) jugador2.colocar(new Acceso(), mapa, 2, 2);		
			
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(2, 2).getElementoEnTierra().getNombre()=="Acceso");
					
			acceso.getVida().aumentarDanioARecibir(600);
			acceso.recibirDanio();
			
			Assert.assertTrue(acceso.getVida().obtenerVida()==400);
			
			acceso.getVida().aumentarDanioARecibir(400);
			acceso.recibirDanio();
			
			Assert.assertTrue(mapa.obtenerContenidoEnPosicion(2, 2).getElementoEnTierra().getNombre()=="Espacio Disponible");

			}
		
		@Test
		public void testDestruccionDeEdificioLoEliminaDeEdificiosEnPie() throws ExcepcionPosicionInvalida, ExcepcionNoPudoColocarseEdificio, ExcepcionYaHayElementoEnLaPosicion, ExcepcionSuperaLimenteDeArbolesPermitos, ExcepcionErrorPasoDeTurno {
			
			Jugador jugador1 = new Jugador ("carlos","rojo",new Protoss());
			Jugador jugador2 = new Jugador ("dean","azul",new Protoss());
			
			Juego juego = new Juego(jugador1, jugador2, 100);
			jugador1.setDinero(99999, 99999);	
			
			Acceso acceso = new Acceso();
			juego.ordenFabricacionDeEdificios(acceso, 5, 5); 	
			
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
		
}
