package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Juego;
import src.construcciones.Construccion;

public class ListaEdificiosEnPie {

	Juego controladorJuego;
	InterfazPartida iPartida;
	JFrame frameEdificiosEnPie ;
	
	public ListaEdificiosEnPie(Juego controlador, InterfazPartida interfazPartida) {
		controladorJuego = controlador;
		iPartida = interfazPartida;
		frameEdificiosEnPie = new JFrame();
	}
	public void cargar(final InterfazPrincipal ip) {
	
		frameEdificiosEnPie.getContentPane().removeAll();
		frameEdificiosEnPie.setJMenuBar(null);
		frameEdificiosEnPie.setTitle("Construcciones del Jugador: " + controladorJuego.getJugadorActual().getNombre() + " en pie");	
		
		JPanel panelEdificios = new JPanel();
		JTextArea lista = new JTextArea("");
		lista.setFocusable(false);
		for (int i = 0; i < controladorJuego.getJugadorActual().getConstruccionesEnPie().size() ; i++){
			Construccion auxiliar = controladorJuego.getJugadorActual().getConstruccionesEnPie().get(i);
			lista.append("\n" + generarTexto(auxiliar));
		}
		JButton avanzar = new JButton("Ok");
		avanzar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				iPartida.cargar(ip);	
				frameEdificiosEnPie.getContentPane().removeAll();
				frameEdificiosEnPie.setJMenuBar(null);
				frameEdificiosEnPie.setVisible(false);
			}
		});
		panelEdificios.add(lista);
		panelEdificios.add(avanzar);
		frameEdificiosEnPie.getContentPane().add(panelEdificios);
		frameEdificiosEnPie.setSize(700, 500);
		frameEdificiosEnPie.show();
	}
	private String generarTexto(Construccion auxiliar) {
		
		return (auxiliar.getNombre() + ". Ubicacion: ( " + auxiliar.getPosicionX() + "," + auxiliar.getPosicionY() + ") Vida: " + auxiliar.getVida().obtenerVida());
		
	}
	public void limpiar() {
		frameEdificiosEnPie.getContentPane().removeAll();
		frameEdificiosEnPie.setJMenuBar(null);
		frameEdificiosEnPie.setVisible(false);
	}
	

}
