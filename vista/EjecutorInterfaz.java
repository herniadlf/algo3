package vista;

import src.Juego;




public class EjecutorInterfaz {
//-------------------------------------------------- COMIENZO DE MAIN ---------------------------------------
	static Juego juego;
	private static Portada portada;
	static InterfazPrincipal interfazPrincipal;
	
	public static void main(String[] args) {		
		juego = new Juego();
		interfazPrincipal = new InterfazPrincipal(juego);
		portada = new Portada(juego);
		portada.cargarPortada(interfazPrincipal);
	}
}
	

