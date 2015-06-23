package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionSuperaLimenteDeArbolesPermitos;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;

public class PedidoTamanioMapa {
	private static int TAMANIO_CHICO = 50;
	private static int TAMANIO_MEDIANO = 75;
	private static int TAMANIO_GRANDE = 100;
	
	Juego juego;
	protected PedidoTamanioMapa(Juego j) {
		// TODO Auto-generated constructor stub
		juego = j;
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
			}
		});
		JButton botonMediano = new JButton("Mediano");
		botonMediano.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.setJuego(generarJuego(TAMANIO_MEDIANO,ip));				
			}
		});
		JButton botonGrande = new JButton("Grande");
		botonGrande.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.setJuego(generarJuego(TAMANIO_GRANDE,ip));
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
			return (new Juego(juego.getJugador1(),juego.getJugador2(),tamanioMapa,0));
		}
		catch (ExcepcionPosicionInvalida | ExcepcionYaHayElementoEnLaPosicion | ExcepcionSuperaLimenteDeArbolesPermitos e) {
			cargarPedidoTamanioMapa(ip);
			return null;
		}
		
	}
	}
