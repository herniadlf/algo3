package vista;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import excepciones.ExcepcionPosicionInvalida;
import src.Juego;
import src.mapa.Mapeable;
import src.mapa.Sector;

public class VistaMapa {
	
	Juego controladorJuego;
	InterfazPartida iPartida;
	InterfazPrincipal iPrincipal;
	JFrame frameMapa;
	
	public VistaMapa(Juego controlador, InterfazPartida interfazPartida, InterfazPrincipal ip) {
		controladorJuego = controlador;
		iPartida = interfazPartida;
		iPrincipal = ip;
		frameMapa = new JFrame();
	}

	public void cargar() {
		
		frameMapa.getContentPane().removeAll();
		frameMapa.setJMenuBar(null);
		frameMapa.setTitle("Mapa");	
		int tamanio = controladorJuego.getMapa().getTamanioMapa();
		String ruta = new String(System.getProperty("user.dir")+"\\trunk\\imagenes\\mapeables\\");
	   	
		JPanel panelMapa = new JPanel();
		GridLayout gL = new GridLayout(tamanio-1,tamanio-1);
		gL.setHgap(0);
		gL.setVgap(0);
		panelMapa.setLayout(gL);
		for (int i = 1; i < tamanio; i++){
			for (int j = 1; j < tamanio; j++){
				try {
					Sector obtenido = controladorJuego.getMapa().obtenerContenidoEnPosicion(i, j);
					if (!controladorJuego.getJugadorActual().getRangoDeVision().contains(obtenido)){
						panelMapa.add(new JLabel(new ImageIcon(ruta+"Desconocido.png")));
					}
					else{
						Mapeable m = obtenido.getElementoEnAire();
						ImageIcon imagenLabel;
						if (m.getNombre().equals("Espacio Disponible")){
							imagenLabel = new ImageIcon(ruta+obtenido.getElementoEnTierra().getNombre()+".png");	
						}
						else { 
							imagenLabel = new ImageIcon(ruta+m.getNombre()+".png");
						}
						panelMapa.add(new JLabel(imagenLabel));					
					}
				} catch (ExcepcionPosicionInvalida e) {}
				
			}
		}
		//panelMapa.setSize(Toolkit.getDefaultToolkit().getScreenSize().width-10,Toolkit.getDefaultToolkit().getScreenSize().height-10);
		frameMapa.getContentPane().add(panelMapa);			
		frameMapa.setLocation(tamanio/2,tamanio/2);
		int resX = Toolkit.getDefaultToolkit().getScreenSize().width;
		int resY = Toolkit.getDefaultToolkit().getScreenSize().height;
		frameMapa.setSize( resX - (int)(resX*0.25), resY - (int)(resY*0.25));
		frameMapa.setResizable(true);		
		frameMapa.show();	
	}

	public void limpiar() {
		frameMapa.getContentPane().removeAll();
		frameMapa.setJMenuBar(null);
		frameMapa.setVisible(false);
	}

}
