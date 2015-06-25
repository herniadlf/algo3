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
	InterfazPartida interfazAnterior;
	
	public MenuUnidades(Jugador jugador, InterfazPartida interfazPartida) {
		interfazAnterior = interfazPartida;
		jugadorActual =jugador;
		opcionDeCrearUnidades = new CrearUnidades(this);
		opcionAtacarUnidades = new AtacarUnidades(this);
		opcionAtacarUsandoMagia =new AtacarConMagia(this);
		opcionMoverUnidad = new MoverUnidad(this,interfazPartida);
		
	}
	
	protected void cargar(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		
		JFrame frameMenuUnidades= new JFrame();
		frameMenuUnidades.getContentPane().removeAll();
		frameMenuUnidades.setJMenuBar(null);
		frameMenuUnidades.setTitle("Menu Unidades");
		
		
		JPanel panelUnidades = new JPanel();	
		
		JButton crearUnidades = new JButton("Crear Unidades");
		crearUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				try {
					frameMenuUnidades.getContentPane().removeAll();
					frameMenuUnidades.setJMenuBar(null);
					frameMenuUnidades.setVisible(false);
					opcionDeCrearUnidades.cargar(ip);
				} catch (ExcepcionNoHayConstruccionesCreadoras e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					frameMenuUnidades.getContentPane().removeAll();
					frameMenuUnidades.setJMenuBar(null);
					frameMenuUnidades.setVisible(false);
					cargar(ip);
				}				
			}
		});
		
	
		JButton atacarConMagia= new JButton("RealizarAtaqueConMagia");
		atacarConMagia.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameMenuUnidades.getContentPane().removeAll();
				frameMenuUnidades.setJMenuBar(null);
				frameMenuUnidades.setVisible(false);
				opcionAtacarUsandoMagia.cargar(ip);
				
			}
		});
		
		
		JButton moverUnidades = new JButton("Mover Unidades");
		moverUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameMenuUnidades.getContentPane().removeAll();
				frameMenuUnidades.setJMenuBar(null);
				frameMenuUnidades.setVisible(false);
				opcionMoverUnidad.cargar(ip);				
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

	
	
	
	
	
	


