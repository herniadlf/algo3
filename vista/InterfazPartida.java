package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;



import javax.swing.LayoutStyle;

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
	VistaMapa vistaMapa;
	
	public InterfazPartida() {
	}

	public void cargar(final InterfazPrincipal ip) {
		
		controladorJuego= ip.getJuego();
		Jugador jugador = controladorJuego.getJugadorActual();
		menuUnidades = new MenuUnidades(jugador,this);
		menuConstrucciones = new MenuConstrucciones(controladorJuego,this,ip);
		vistaMapa = new VistaMapa(controladorJuego,this,ip);
			
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("AlgoCraft");
		
		JPanel panelJuego = new JPanel();
	   
		String ruta = new String(System.getProperty("user.dir")+"\\trunk\\imagenes\\");
	    ImageIcon imagenBandera = new ImageIcon(ruta+"bandera"+jugador.getColor()+".png");
	    JLabel labelColor = new JLabel(imagenBandera);
	    labelColor.setBounds(0,0,imagenBandera.getIconWidth(),imagenBandera.getIconHeight());
	    panelJuego.add(labelColor);
	    
		JTextArea informacion = new JTextArea("Nombre: " + jugador.getNombre() +"\n");
		informacion.setSize(150, 400);		
		informacion.setLineWrap(true);
		informacion.append("Raza: " + jugador.getRaza().getNombre() +"\n");
		informacion.append("Minerales: " + jugador.getDinero().getMinerales() + "\n" + "Gas Vespeno: " + jugador.getDinero().getGasVespeno() + "\n");
		informacion.append("Turno: " + controladorJuego.getTurno() );
		informacion.append("\n Tamaño del Mapa: " + controladorJuego.getMapa().getTamanioMapa());
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
					JOptionPane.showMessageDialog(null, e1.getMessage());
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
				menuConstrucciones.cargar();
			}
		});
		
		JButton verMapa = new JButton("Ver Mapa");
		verMapa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vistaMapa.cargar();
				
			}
		});
			
		panelJuego.add(cambioTurno);
		panelJuego.add (unidades);
		panelJuego.add (construcciones);
		panelJuego.add(verMapa);
		ip.getFramePrincipal().getContentPane().add(panelJuego,BorderLayout.WEST);			
		ip.getFramePrincipal().setSize(700, 500);
		ip.getFramePrincipal().setLocation(450,250);
		ip.getFramePrincipal().show();		
	}
	public Juego getControlador(){
		return controladorJuego;
	}
}
