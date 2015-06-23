package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Visual.Portada;
import excepciones.ExcepcionColorYaElegido;
import excepciones.ExcepcionNombreElegido;
import src.Juego;


public class InterfazPrincipal extends JFrame {

	private static Dimension screenSize;

	JFrame framePrincipal;
	
	Juego juego;
	
	private Portada portada;
	
	public InterfazPrincipal(){
		
		setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
		JFrame.setDefaultLookAndFeelDecorated(true);
		JPopupMenu.setDefaultLightWeightPopupEnabled(true);
		
		setFramePrincipal(new JFrame());
		getFramePrincipal().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new Juego();
		portada = new Portada(juego);
		portada.cargarPortada(this);
	}
	
	public Juego getJuego (){
		return juego;
		
		
	}
	
	
	public JFrame getFramePrincipal(){
		return framePrincipal;
	}
	public void setFramePrincipal(JFrame f){
		framePrincipal = f;
	}
	
	public void setScreenSize(Dimension dim) {
		// TODO Auto-generated method stub
		screenSize = dim;
	}
	

	

	protected void cargarInterfazJuego() {
		// TODO Auto-generated method stub
		getFramePrincipal().getContentPane().removeAll();
		getFramePrincipal().setJMenuBar(null);
		
		getFramePrincipal().setTitle("AlgoCraft");
		getFramePrincipal().setResizable(true);
		
		JMenuBar barraDeMenu = new JMenuBar(); 
		
		JMenu menuJuego = new JMenu("Juego");
		JMenuItem itemReiniciarPartida = new JMenuItem("Reiniciar Juego");
		itemReiniciarPartida.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//juegonuevo
			}
		});
		menuJuego.add(itemReiniciarPartida);
		JMenuItem itemSalirDeLaPartida = new JMenuItem("Salir");
		itemSalirDeLaPartida.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		menuJuego.add(itemSalirDeLaPartida);
		barraDeMenu.add(menuJuego);
		
		getFramePrincipal().setJMenuBar(barraDeMenu);
		/*
		getFramePrincipal().pack();
        getFramePrincipal().setLocation((int)Math.round(getScreenSize().getWidth()/2) - getFramePrincipal().getWidth()/2, (int)Math.round(getScreenSize().getHeight()/2) - getFramePrincipal().getHeight()/2);
        getFramePrincipal().show();
	*/
	}

	public Dimension getScreenSize() {
		// TODO Auto-generated method stub
		return screenSize;
	}

	public void setJuego(Juego juegoDefinitivo) {
		juego = juegoDefinitivo;
		
	}
	
	
	}
