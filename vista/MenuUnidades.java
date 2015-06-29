package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	TransportarUnidad transportarUnidades; 
	InterfazPartida interfazAnterior;
	ListaUnidadesPropias unidadesPropias;
	ListaDeEntrenamiento unidadesEntrenandose;	
	JFrame frameMenuUnidades;
	
	public MenuUnidades(Jugador jugador, InterfazPartida interfazPartida) {
		interfazAnterior = interfazPartida;
		jugadorActual =jugador;
		opcionDeCrearUnidades = new CrearUnidades(interfazAnterior);
		opcionAtacarUnidades = new AtacarUnidades(interfazAnterior);
		opcionAtacarUsandoMagia =new AtacarConMagia(interfazAnterior);
		opcionMoverUnidad = new MoverUnidad(interfazAnterior);
		unidadesPropias = new ListaUnidadesPropias(interfazAnterior);
		unidadesEntrenandose = new ListaDeEntrenamiento(interfazAnterior);
		transportarUnidades = new TransportarUnidad(interfazAnterior);
		frameMenuUnidades= new JFrame();
	}
	
	protected void cargar(final InterfazPrincipal ip) {
		
		frameMenuUnidades.getContentPane().removeAll();
		frameMenuUnidades.setJMenuBar(null);
		frameMenuUnidades.setTitle("Menu Unidades");
		
		
		JPanel panelUnidades = new JPanel();	
		
		JButton crearUnidades = new JButton("Crear Unidades");
		crearUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try {
					/*frameMenuUnidades.getContentPane().removeAll();
					frameMenuUnidades.setJMenuBar(null);
					frameMenuUnidades.setVisible(false);*/
					limpiar();
					opcionDeCrearUnidades.cargar(ip);
				} catch (ExcepcionNoHayConstruccionesCreadoras e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					/*frameMenuUnidades.getContentPane().removeAll();
					frameMenuUnidades.setJMenuBar(null);
					frameMenuUnidades.setVisible(false);*/
					limpiar();
					cargar(ip);
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
		frameMenuUnidades.setLocation(650,250);
		frameMenuUnidades.show();
		
	}

	public void limpiar() {
		opcionDeCrearUnidades.limpiar();
		opcionAtacarUnidades.limpiar();
		opcionAtacarUsandoMagia.limpiar();
		opcionMoverUnidad.limpiar();		
		unidadesPropias.limpiar();	
		unidadesEntrenandose.limpiar();
		transportarUnidades.limpiar();
		frameMenuUnidades.getContentPane().removeAll();
		frameMenuUnidades.setJMenuBar(null);
		frameMenuUnidades.setVisible(false);
	}
		
}