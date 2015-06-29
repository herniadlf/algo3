package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.razas.Protoss;
import src.razas.Raza;
import src.razas.Terran;

public class PedidoDeRaza {
	
	PedidoDeColor pedidoDeColor;
	PedidoTamanioMapa pedidoMapa;
	
	public PedidoDeRaza (PedidoDeColor pedido){
		pedidoDeColor= pedido;
		pedidoMapa = new PedidoTamanioMapa(pedido.juego);
		
	}
		
	protected void cargarSeleccionDeRaza(final InterfazPrincipal ip) {
		
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("Elija la raza con la que desea jugar");
		
		JPanel panelRaza = new JPanel();
		JButton terran = new JButton("Terran");
		terran.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.getJuego().getJugadorActual().setRaza((Raza)new Terran());
				if (ip.getJuego().getJugadorActual() == ip.getJuego().getJugador1()){
					ip.getJuego().setJugadorActual(ip.getJuego().getJugador2());
					pedidoDeColor.cargarPedidoDeColor(ip);
				}
				else {
					pedidoMapa.cargarPedidoTamanioMapa(ip);
				}
			}
		});
		JButton protoss = new JButton("Protoss");
		protoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ip.getJuego().getJugadorActual().setRaza((Raza)new Protoss());
				if (ip.getJuego().getJugadorActual() == ip.getJuego().getJugador1()){
					ip.getJuego().setJugadorActual(ip.getJuego().getJugador2());
					pedidoDeColor.cargarPedidoDeColor(ip);
				}
				else {
					pedidoMapa.cargarPedidoTamanioMapa(ip);
				}
			}
		});
		panelRaza.add(terran);
		panelRaza.add(protoss);
		
		ip.getFramePrincipal().getContentPane().add(panelRaza,BorderLayout.CENTER);
		ip.getFramePrincipal().setSize(400, 100);
		ip.getFramePrincipal().show();
	}
	

}
