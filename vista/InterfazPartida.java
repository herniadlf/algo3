package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import src.Juego;
import src.Jugador;

public class InterfazPartida {
	
	Juego controladorJuego;
	Jugador jugador;
	public InterfazPartida(Juego juego) {
		controladorJuego = juego;
		jugador = controladorJuego.getJugadorActual();
	}

	public void cargar(InterfazPrincipal ip) {
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
		//informacion.append("Turno: " + controladorJuego.getTurno());
		panelJuego.add(informacion);
		//JButton cambioTurno = new JButton("Fin turno");
		/*cambioTurno.addActionListener(new ActionListener() {
			
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
		});*/
		
		ip.getFramePrincipal().getContentPane().add(panelJuego,BorderLayout.WEST);	
		ip.getFramePrincipal().setSize(700, 500);
		ip.getFramePrincipal().show();		
	}

}
