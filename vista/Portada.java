package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.Juego;

public class Portada {
	
	MenuPrincipal menu;
	Juego juego;
	
	public Portada(Juego j){
		menu = new MenuPrincipal(j);
		juego = j; // el juego se crea en menu principal esto para mi no iria pero no lo quiero sacar
		
	}
	
	public void cargarPortada(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		
		ip.getFramePrincipal().setTitle("Bienvenido a AlgoCraft");
		ip.getFramePrincipal().setBackground(Color.blue);
		ip.getFramePrincipal().setResizable(true);

		JPanel panel = new JPanel(null);
        panel.setBackground(Color.white);

        String ruta = new String(System.getProperty("user.dir")+"\\trunk\\imagenes\\");
        ImageIcon imagenPortada = new ImageIcon(ruta+"algo3ppal.jpg");
        
        JButton botonContinuar = new JButton(imagenPortada);

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menu.cargarMenuPrincipal(ip);
            }
        });
        
        botonContinuar.setBounds(0,0,imagenPortada.getIconWidth(),imagenPortada.getIconHeight()-30);

        panel.add(botonContinuar);
        
        ip.getFramePrincipal().getRootPane().setDefaultButton(botonContinuar);
        ip.getFramePrincipal().getContentPane().add(panel);
        
        ip.getFramePrincipal().setSize(imagenPortada.getIconWidth(),imagenPortada.getIconHeight());
        ip.getFramePrincipal().setLocation((int)Math.round(ip.getScreenSize().getWidth()/2) - ip.getFramePrincipal().getWidth()/2, (int)Math.round(ip.getScreenSize().getHeight()/2) - ip.getFramePrincipal().getHeight()/2);
        ip.getFramePrincipal().show();
	}

	

}
