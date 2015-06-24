package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import src.Juego;
import src.Jugador;

public class InterfazPartida {
	
	Juego controladorJuego;
	MenuUnidades menuUnidades;
	MenuConstrucciones menuConstrucciones;
	
	public InterfazPartida() {
	}

	public void cargar(final InterfazPrincipal ip) {
		
		controladorJuego= ip.getJuego();
		Jugador jugador = controladorJuego.getJugadorActual();
		menuUnidades = new MenuUnidades(jugador);
		menuConstrucciones = new MenuConstrucciones(jugador,this);
		
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("AlgoCraft");
		ip.getFramePrincipal().setBackground(Color.WHITE);
		
		JPanel panelJuego = new JPanel();	
		panelJuego.setBackground(Color.WHITE);
		JTextArea informacion = new JTextArea("Nombre: " + jugador.getNombre() +"\n");
		informacion.setSize(150, 400);
		informacion.setBackground(Color.WHITE);
		informacion.setLineWrap(true);
		informacion.append("Raza: " + jugador.getRaza().getNombre() +"\n");
		informacion.append("Minerales: " + jugador.getDinero().getMinerales() + "\n" + "Gas Vespeno: " + jugador.getDinero().getGasVespeno() + "\n");
		informacion.append("Turno: " + controladorJuego.getTurno() );
		panelJuego.add(informacion);
		JButton cambioTurno = new JButton("Fin turno");
		cambioTurno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controladorJuego.pasarTurno();
					cargar(ip);
				} catch (ExcepcionErrorPasoDeTurno
						| ExcepcionConstruccionNoCorrespondiente
						| ExcepcionRecursoInsuficiente
						| ExcepcionUnidadNoCorrespondiente e1) {
				}
			}
		});
		
		
		JButton unidades = new JButton("Unidades");
		unidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuUnidades.cargar(ip);
			}
		});
		

		JButton construcciones= new JButton("Construcciones");
		construcciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				menuConstrucciones.cargar(ip);
			}
		});
		
		
		panelJuego.add(cambioTurno);
		panelJuego.add (unidades);
		panelJuego.add (construcciones);
		ip.getFramePrincipal().getContentPane().add(panelJuego,BorderLayout.WEST);	
		ip.getFramePrincipal().setSize(700, 500);
		ip.getFramePrincipal().show();		
	}
	public Juego getControlador(){
		return controladorJuego;
	}
}
