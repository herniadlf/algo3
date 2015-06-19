package vista;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import excepciones.ExcepcionColorYaElegido;
import excepciones.ExcepcionNombreElegido;
import src.Juego;
import src.Jugador;

public class Interfaz {
	
	private static Dimension screenSize;

	JFrame framePrincipal;
	
	Juego juego;
	
	public Interfaz(){
		
		setScreenSize(Toolkit.getDefaultToolkit().getScreenSize());
		JFrame.setDefaultLookAndFeelDecorated(true);
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		setFramePrincipal(new JFrame());
		getFramePrincipal().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego = new Juego();
		cargarPortada();
	}
	
	public JFrame getFramePrincipal(){
		return framePrincipal;
	}
	public void setFramePrincipal(JFrame f){
		framePrincipal = f;
	}
	
	private void setScreenSize(Dimension dim) {
		// TODO Auto-generated method stub
		screenSize = dim;
	}
	private void cargarPortada() {
		// TODO Auto-generated method stub
		getFramePrincipal().getContentPane().removeAll();
		getFramePrincipal().setJMenuBar(null);
		
		getFramePrincipal().setTitle("Bienvenido a AlgoCraft");
		getFramePrincipal().setBackground(Color.blue);
		getFramePrincipal().setResizable(true);

		JPanel panel = new JPanel(null);
        panel.setBackground(Color.white);

        String ruta = new String(System.getProperty("user.dir")+"\\trunk\\imagenes\\");
        ImageIcon imagenPortada = new ImageIcon(ruta+"algo3ppal.jpg");
        
        JButton botonContinuar = new JButton(imagenPortada);

        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	cargarMenuPrincipal();
            }
        });

        botonContinuar.setBounds(0,0,imagenPortada.getIconWidth(),imagenPortada.getIconHeight());

        panel.add(botonContinuar);
        
        getFramePrincipal().getRootPane().setDefaultButton(botonContinuar);
        getFramePrincipal().getContentPane().add(panel);
        
        getFramePrincipal().setSize(imagenPortada.getIconWidth(),imagenPortada.getIconHeight());
        getFramePrincipal().setLocation((int)Math.round(getScreenSize().getWidth()/2) - getFramePrincipal().getWidth()/2, (int)Math.round(getScreenSize().getHeight()/2) - getFramePrincipal().getHeight()/2);
        getFramePrincipal().show();
	}

	protected void cargarMenuPrincipal() {
		// TODO Auto-generated method stub
		getFramePrincipal().getContentPane().removeAll();
		getFramePrincipal().setJMenuBar(null);
		
		getFramePrincipal().setTitle("Menu Principal");
		getFramePrincipal().setBackground(Color.blue);
		getFramePrincipal().setResizable(false);

		JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.white);

        JButton botonNuevoJuego = new JButton("Nuevo Juego");
        JButton botonSalir = new JButton("Salir");

        botonNuevoJuego.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	cargarPedidoDeColor();
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });

        panel.add(botonNuevoJuego);
        panel.add(botonSalir);

        getFramePrincipal().getContentPane().add(panel,BorderLayout.CENTER);

        getFramePrincipal().pack();
        getFramePrincipal().setLocation((int)Math.round(getScreenSize().getWidth()/2) - getFramePrincipal().getWidth()/2, (int)Math.round(getScreenSize().getHeight()/2) - getFramePrincipal().getHeight()/2);
        getFramePrincipal().show();
	}


	protected void cargarPedidoDeColor() {
		getFramePrincipal().getContentPane().removeAll();
		getFramePrincipal().setJMenuBar(null);
		
		getFramePrincipal().setTitle("Seleccione un color");
		getFramePrincipal().setResizable(true);
		
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
					cargarPedidoNombre();
				} catch (ExcepcionColorYaElegido e) {
					cargarPedidoDeColor();
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
					cargarPedidoNombre();
				} catch (ExcepcionColorYaElegido e) {
					cargarPedidoDeColor();
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
					cargarPedidoNombre();
				} catch (ExcepcionColorYaElegido e) {
					cargarPedidoDeColor();
					}
			}
		});
		panel.add(verde);
		panel.add(azul);
		panel.add(rojo);
		
		getFramePrincipal().getContentPane().add(panel,BorderLayout.CENTER);
		getFramePrincipal().setSize(400, 100);
		getFramePrincipal().show();
	}

	protected void cargarPedidoNombre() {
		// TODO Auto-generated method stub
		getFramePrincipal().getContentPane().removeAll();
		getFramePrincipal().setJMenuBar(null);
		
		getFramePrincipal().setTitle("Escriba su nombre");
		
		JPanel cuadroDeTexto = new JPanel();
		JLabel nombre = new JLabel("Nombre: ");
		JTextField texto = new JTextField(15);
		texto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					if (texto.getText().equals(juego.getJugador1().getNombre())){
						JOptionPane.showMessageDialog(null, "Nombre Ya Elegido");
						throw new ExcepcionNombreElegido();
					}
					juego.getJugadorActual().setNombre(texto.getText());
					JOptionPane.showMessageDialog(null, "Bienvenido " + juego.getJugadorActual().getNombre() + "!!");
					cargarSeleccionDeRaza();
				} catch (ExcepcionNombreElegido e){
					cargarPedidoNombre();
				}
			}
		});	
		cuadroDeTexto.add(nombre);
		cuadroDeTexto.add(texto);
		
		getFramePrincipal().getContentPane().add(cuadroDeTexto,BorderLayout.CENTER);
		getFramePrincipal().setSize(400, 100);
		getFramePrincipal().show();
	}

	protected void cargarSeleccionDeRaza() {
		
		getFramePrincipal().getContentPane().removeAll();
		getFramePrincipal().setJMenuBar(null);
		
		getFramePrincipal().setTitle("Elija la raza con la que desea jugar");
		
		JPanel panelRaza = new JPanel();
		JButton terran = new JButton("Terran");
		terran.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (juego.getJugadorActual() == juego.getJugador1()){
					juego.setJugadorActual(juego.getJugador2());
					cargarPedidoDeColor();
				}
			}
		});
		JButton protoss = new JButton("Protoss");
		protoss.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (juego.getJugadorActual() == juego.getJugador1()){
					juego.setJugadorActual(juego.getJugador2());
					cargarPedidoDeColor();
				}
			}
		});
		panelRaza.add(terran);
		panelRaza.add(protoss);
		
		getFramePrincipal().getContentPane().add(panelRaza,BorderLayout.CENTER);
		getFramePrincipal().setSize(400, 100);
		getFramePrincipal().show();
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

	private Dimension getScreenSize() {
		// TODO Auto-generated method stub
		return screenSize;
	}

}
