package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Juego;
import excepciones.ExcepcionColorYaElegido;

public class PedidoDeColor {
	
	PedidoDeNombre pedidoDeNombre;
	Juego juego;
	
	public PedidoDeColor(Juego j){
		juego = j;
		pedidoDeNombre = new PedidoDeNombre (this);
		
	}
	
	
	public void cargarPedidoDeColor(final InterfazPrincipal ip) {
		
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("Seleccione un color");
		ip.getFramePrincipal().setResizable(true);
		
		JPanel panel = new JPanel();
				
		JButton verde = new JButton("Verde");
		JButton azul = new JButton("Azul");
		JButton rojo = new JButton("Rojo");
		verde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (juego.getJugador1().getColor() == "Verde"){
						JOptionPane.showMessageDialog(null, "Color Ya Elegido");
						throw new ExcepcionColorYaElegido();					
					}
					juego.getJugadorActual().setColor("Verde");
					pedidoDeNombre.cargarPedidoNombre(ip);
					
				} catch (ExcepcionColorYaElegido e) {
					cargarPedidoDeColor(ip);
					}
			}
		});
		azul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (juego.getJugador1().getColor() == "Azul"){
						JOptionPane.showMessageDialog(null, "Color Ya Elegido");
						throw new ExcepcionColorYaElegido();					
					}
					juego.getJugadorActual().setColor("Azul");
					pedidoDeNombre.cargarPedidoNombre(ip);
				} catch (ExcepcionColorYaElegido e) {
					cargarPedidoDeColor(ip);
					}
			}
		});
		rojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (juego.getJugador1().getColor() == "Rojo"){
						JOptionPane.showMessageDialog(null, "Color Ya Elegido");
						throw new ExcepcionColorYaElegido();					
					}
					juego.getJugadorActual().setColor("Rojo");
					pedidoDeNombre.cargarPedidoNombre(ip);
				} catch (ExcepcionColorYaElegido e) {
					cargarPedidoDeColor(ip);
					}
			}
		});
		panel.add(verde);
		panel.add(azul);
		panel.add(rojo);
		
		ip.getFramePrincipal().getContentPane().add(panel,BorderLayout.CENTER);
		ip.getFramePrincipal().setSize(400, 100);
		ip.getFramePrincipal().show();
	}
	

}
