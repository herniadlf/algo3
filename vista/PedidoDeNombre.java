package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Juego;
import excepciones.ExcepcionNombreElegido;

public class PedidoDeNombre {
	
	PedidoDeRaza pedidoDeRaza;
	PedidoDeColor pedidoDeColor;
	
	public PedidoDeNombre(PedidoDeColor pedido){
		pedidoDeRaza = new PedidoDeRaza(pedido);
		pedidoDeColor = pedido;
	}
	
	protected void cargarPedidoNombre(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("Escriba su nombre");
		
		JPanel cuadroDeTexto = new JPanel();
		JLabel nombre = new JLabel("Nombre: ");
		final JTextField ingreso = new JTextField(15);
		ingreso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (ingreso.getText().equals(ip.getJuego().getJugador1().getNombre())){
						JOptionPane.showMessageDialog(null, "Nombre Ya Elegido");
						throw new ExcepcionNombreElegido();
					}
					ip.getJuego().getJugadorActual().setNombre(ingreso.getText());
					JOptionPane.showMessageDialog(null, "¡Bienvenido/a " + ip.getJuego().getJugadorActual().getNombre() + "!");
					pedidoDeRaza.cargarSeleccionDeRaza(ip);
				} catch (ExcepcionNombreElegido e){
					cargarPedidoNombre(ip);
				}
			}
		});	
		cuadroDeTexto.add(nombre);
		cuadroDeTexto.add(ingreso);
		
		ip.getFramePrincipal().getContentPane().add(cuadroDeTexto,BorderLayout.CENTER);
		ip.getFramePrincipal().setSize(400, 100);
		ip.getFramePrincipal().show();
	}

	
	

}
