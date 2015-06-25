package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionNombreElegido;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import src.Jugador;
import src.razas.Protoss;
import src.razas.Raza;
import src.razas.Terran;
import src.unidades.Marine;
import src.unidades.Unidad;

public class MenuUnidades {
	
	Jugador jugadorActual;
	CrearUnidades opcionDeCrearUnidades;
	AtacarUnidades opcionAtacarUnidades;
	AtacarConMagia opcionAtacarUsandoMagia;
	MoverUnidad opcionMoverUnidad;

	public MenuUnidades(Jugador jugador) {
		jugadorActual =jugador;
		opcionDeCrearUnidades = new CrearUnidades();
		opcionAtacarUnidades = new AtacarUnidades();
		opcionAtacarUsandoMagia =new AtacarConMagia();
		opcionMoverUnidad = new MoverUnidad();
		
	}
	
	
	
	
	
	protected void cargar(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		
		JFrame frameMenuUnidades= new JFrame();
		frameMenuUnidades.getContentPane().removeAll();
		frameMenuUnidades.setJMenuBar(null);
		frameMenuUnidades.setTitle("AtaqueConMagia: Unidad   Edificio   UnidadEnemiga");
		
		
		
		
		JPanel panelUnidades = new JPanel();	
		panelUnidades.setBackground(Color.BLACK);
		
		JButton crearUnidades = new JButton("Crear Unidades");
		crearUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcionDeCrearUnidades.cargar(ip);
				
			}
		});
		
		
	
		JButton atacarConMagia= new JButton("RealizarAtaqueConMagia");
		atacarConMagia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcionAtacarUsandoMagia.cargar(ip);
				
			}
		});
		
		
		JButton moverUnidades = new JButton("Mover Unidades");
		moverUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//opcionMoverUnidad.cargar(ip);
				
			}
		});
		
		
		JButton atacar = new JButton("Atacar");
		atacar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcionAtacarUnidades.cargar(ip);
				
			}
		});
		
		
		
		
		
		panelUnidades.add(crearUnidades);
		panelUnidades.add (atacarConMagia);
		panelUnidades.add(moverUnidades);
		panelUnidades.add(atacar);
		
		
		
		
		
		
		
		
		
	
		frameMenuUnidades.getContentPane().add(panelUnidades);
		frameMenuUnidades.setSize(700, 500);
		frameMenuUnidades.setLocation(650,250);
		frameMenuUnidades.show();
		
	}

	
		
		
		
		
		
	}

	
	
	
	
	
	


