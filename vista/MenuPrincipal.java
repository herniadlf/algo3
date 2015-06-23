package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import src.Juego;

public class MenuPrincipal {
	
	PedidoDeColor pedidoDeColor;
	Juego juego;
	
	public MenuPrincipal(Juego j){
		pedidoDeColor = new PedidoDeColor(j);
		juego = j;
		
	};
	
	protected void cargarMenuPrincipal(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("Menu Principal");
		ip.getFramePrincipal().setBackground(Color.blue);
		ip.getFramePrincipal().setResizable(false);

		JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.white);

        JButton botonNuevoJuego = new JButton("Nuevo Juego");
        JButton botonSalir = new JButton("Salir");

        botonNuevoJuego.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	pedidoDeColor.cargarPedidoDeColor(ip);
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });

        panel.add(botonNuevoJuego);
        panel.add(botonSalir);

        ip.getFramePrincipal().getContentPane().add(panel,BorderLayout.CENTER);

        ip.getFramePrincipal().pack();
        ip.getFramePrincipal().setLocation((int)Math.round(ip.getScreenSize().getWidth()/2) - ip.getFramePrincipal().getWidth()/2, (int)Math.round(ip.getScreenSize().getHeight()/2) - ip.getFramePrincipal().getHeight()/2);
        ip.getFramePrincipal().show();
        
     
	}
	

}
