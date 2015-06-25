package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionTamanioDelMapaInvalido;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;

public class PedidoTamanioMapa {
	private static int TAMANIO_CHICO = 100;
	private static int TAMANIO_MEDIANO = 300;
	private static int TAMANIO_GRANDE = 500;
	
	Juego juego;
	InterfazPartida interfazPartida;
	
	protected PedidoTamanioMapa(Juego j) {
		juego = j;
		interfazPartida = new InterfazPartida();
	}
	public void cargarPedidoTamanioMapa(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("Ingrese tamanio del mapa");
		
		JPanel panelTamanio = new JPanel();
		
		JButton botonChico = new JButton("Chico");
		botonChico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.setJuego(generarJuego(TAMANIO_CHICO,ip));
				interfazPartida.cargar(ip);
			}
		});
		JButton botonMediano = new JButton("Mediano");
		botonMediano.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.setJuego(generarJuego(TAMANIO_MEDIANO,ip));
				interfazPartida.cargar(ip);
				
			}
		});
		JButton botonGrande = new JButton("Grande");
		botonGrande.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.setJuego(generarJuego(TAMANIO_GRANDE,ip));
				interfazPartida.cargar(ip);
			}
		});
		panelTamanio.add(botonChico);
		panelTamanio.add(botonMediano);
		panelTamanio.add(botonGrande);
		
		ip.getFramePrincipal().getContentPane().add(panelTamanio,BorderLayout.CENTER);
		ip.getFramePrincipal().setSize(400, 100);
		ip.getFramePrincipal().show();
	}
	protected Juego generarJuego(int tamanioMapa, InterfazPrincipal ip) {
		try {
			Juego juegoDefinitivo = new Juego(ip.getJuego().getJugador1(),ip.getJuego().getJugador2(),tamanioMapa,0);
			return (juegoDefinitivo);			
		}
		catch (ExcepcionPosicionInvalida | ExcepcionTamanioDelMapaInvalido | ExcepcionYaHayElementoEnLaPosicion | ExcepcionSuperaLimenteDeArbolesPermitos e) {
			cargarPedidoTamanioMapa(ip);
			return null;
		}
		
		
	}
	}
