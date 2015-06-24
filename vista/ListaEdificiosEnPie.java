package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	public void cargar(InterfazPrincipal ip) {
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);		
		ip.getFramePrincipal().setTitle("Construcciones del Jugador: " + controladorJuego.getJugadorActual().getNombre() + " en pie");
		ip.getFramePrincipal().setBackground(Color.WHITE);		
		
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
			}
		});
		panelEdificios.add(lista);
		panelEdificios.add(avanzar);
		ip.getFramePrincipal().getContentPane().add(panelEdificios);
		ip.getFramePrincipal().setSize(700, 500);
		ip.getFramePrincipal().show();
	}
	private String generarTexto(Construccion auxiliar) {
		return (auxiliar.getNombre() + ". Ubicacion: ( " + auxiliar.getPosicionX() + "," + auxiliar.getPosicionY() + ")");
		
	}
	

}
