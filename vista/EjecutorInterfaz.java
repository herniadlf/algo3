package vista;

import src.Juego;




public class EjecutorInterfaz {
//-------------------------------------------------- COMIENZO DE MAIN ---------------------------------------
	static Juego juego;
	private static Portada portada;
	static InterfazPrincipal interfazPrincipal;
	
	public static void main(String[] args) {		
		juego = new Juego(); // el juego se genera em pedido de mapa para mi aca no habria que
		interfazPrincipal = new InterfazPrincipal(juego);// crearlo ni pasarlo nos manejamos con la interfaz,
		portada = new Portada(juego);// y le pedimos el juego
		portada.cargarPortada(interfazPrincipal);
	}
}
	

