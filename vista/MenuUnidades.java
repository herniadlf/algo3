package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.ExcepcionNoHayConstruccionesCreadoras;
import src.Jugador;

public class MenuUnidades {
	
	Jugador jugadorActual;
	CrearUnidades opcionDeCrearUnidades;
	AtacarUnidades opcionAtacarUnidades;
	AtacarConMagia opcionAtacarUsandoMagia;
	MoverUnidad opcionMoverUnidad;
	InterfazPartida menuAnterior;
	ListaUnidadesPropias unidadesPropias;
	JFrame frameMenuUnidades;
	ListaDeEntrenamiento unidadesEntrenandose;
	TransportarUnidad transportarUnidades; 
	
	public MenuUnidades(Jugador jugador, InterfazPartida interfazPartida) {
		menuAnterior = interfazPartida;
		jugadorActual = jugador;
		opcionDeCrearUnidades = new CrearUnidades(interfazPartida);
		opcionAtacarUnidades = new AtacarUnidades(interfazPartida);
		opcionAtacarUsandoMagia =new AtacarConMagia(interfazPartida);
		opcionMoverUnidad = new MoverUnidad(interfazPartida);
		unidadesPropias = new ListaUnidadesPropias(interfazPartida);
		unidadesEntrenandose = new ListaDeEntrenamiento(interfazPartida);
		transportarUnidades = new TransportarUnidad(interfazPartida);
		frameMenuUnidades= new JFrame();
	}
	
	protected void cargar(final InterfazPrincipal ip) {
		
		frameMenuUnidades.getContentPane().removeAll();
		frameMenuUnidades.setJMenuBar(null);
		frameMenuUnidades.setTitle("Menu Unidades");
		
		
		JPanel panelUnidades = new JPanel();
		
		String ruta = new String(System.getProperty("user.dir")+"\\trunk\\imagenes\\");
		ImageIcon unidades = new ImageIcon(ruta+"unidades.png");
		JLabel labelColor = new JLabel(unidades);
		JLabel labelUnidad = new JLabel(unidades);
		labelColor.setBounds(0,0,unidades.getIconWidth(),unidades.getIconHeight());
		panelUnidades.add(labelUnidad);
		
		JButton crearUnidades = new JButton("Crear Unidades");
		crearUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try {					
					limpiar();
					opcionDeCrearUnidades.cargar(ip);
				} catch (ExcepcionNoHayConstruccionesCreadoras e) {
					JOptionPane.showMessageDialog(null, e.getMessage());					
					limpiar();
					menuAnterior.cargar(ip);
				}				
			}
		});
		
	
		JButton atacarConMagia= new JButton("RealizarAtaqueConMagia");
		atacarConMagia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				opcionAtacarUsandoMagia.cargar(ip);				
			}
		});
		
		
		JButton moverUnidades = new JButton("Mover Unidades");
		moverUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				opcionMoverUnidad.cargar(ip);				
			}
		});
		
		
		JButton atacar = new JButton("Atacar");
		atacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				opcionAtacarUnidades.cargar(ip);
				
			}
		});
		
		JButton verUnidades = new JButton("Unidades Creadas");
		verUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				unidadesPropias.cargar(ip);
			}
		});	
		
		JButton botonTransportarUnidades = new JButton("Transportar Unidades");
		botonTransportarUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				transportarUnidades.cargar(ip);
			}
		});		
		
		JButton verEntrenamiento = new JButton("Unidades en entrenamiento");
		verEntrenamiento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
				unidadesEntrenandose.cargar(ip);
			}
		});	
			
		panelUnidades.add(crearUnidades);
		panelUnidades.add (atacarConMagia);
		panelUnidades.add(botonTransportarUnidades);
		panelUnidades.add(moverUnidades);
		panelUnidades.add(atacar);
		panelUnidades.add(verUnidades);
		panelUnidades.add(verEntrenamiento);
		
		frameMenuUnidades.getContentPane().add(panelUnidades);
		frameMenuUnidades.setSize(700, 500);
		frameMenuUnidades.setLocation(450,250);
		frameMenuUnidades.show();
		
		
	}

	public void limpiar() {
		opcionDeCrearUnidades.limpiar();
		opcionAtacarUnidades.limpiar();
		opcionAtacarUsandoMagia.limpiar();
		opcionMoverUnidad.limpiar();		
		unidadesPropias.limpiar();	
		frameMenuUnidades.getContentPane().removeAll();
		frameMenuUnidades.setJMenuBar(null);
		frameMenuUnidades.setVisible(false);
	}
		
}