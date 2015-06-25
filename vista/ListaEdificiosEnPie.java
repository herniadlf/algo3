package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import src.Juego;
import src.construcciones.Construccion;

public class ListaEdificiosEnPie {

	Juego controladorJuego;
	InterfazPartida iPartida;
	
	public ListaEdificiosEnPie(Juego controlador, InterfazPartida interfazPartida) {
		controladorJuego = controlador;
		iPartida = interfazPartida;
	}
	public void cargar(final InterfazPrincipal ip) {
		final JFrame frameEdificiosEnPie = new JFrame();
		frameEdificiosEnPie.getContentPane().removeAll();
		frameEdificiosEnPie.setJMenuBar(null);
		frameEdificiosEnPie.setTitle("Construcciones del Jugador: " + controladorJuego.getJugadorActual().getNombre() + " en pie");	
		
		JPanel panelEdificios = new JPanel();
		JTextArea lista = new JTextArea("");
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
	

}
