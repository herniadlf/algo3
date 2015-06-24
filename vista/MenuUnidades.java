package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionNombreElegido;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import src.Jugador;
import src.razas.Protoss;
import src.razas.Raza;
import src.razas.Terran;
import src.unidades.Marine;
import src.unidades.Unidad;

public class MenuUnidades {
	
	Jugador jugadorActual;
	CrearUnidades opcionDeCrearUnidades;

	public MenuUnidades(Jugador jugador) {
		jugadorActual =jugador;
		opcionDeCrearUnidades = new CrearUnidades();
		
	}
	
	
	/*public void cargarListaConUnidadesDisponibles (InterfazPrincipal ip, Object JButton){
		ArrayList<Unidad> unidades = jugadorActual.getUnidadesAlistades(); 
		Unidad marine1 = new Marine();
		unidades.add(marine1);
		int i=0;
		
		while (unidades.size()>i){
			
			
			JButton unidad= new JButton("Fin turno");
			//cambioTurno.addActionListener(new ActionListener() {
				
				
						/*controladorJuego.pasarTurno();
						cargar(ip);
					i++;
				}
			);
			
			i++;
			
			
		}
		
		
		}*/
	
	
	
	protected void cargar(final InterfazPrincipal ip) {
		// TODO Auto-generated method stub
		ip.getFramePrincipal().getContentPane().removeAll();
		ip.getFramePrincipal().setJMenuBar(null);
		ip.getFramePrincipal().setTitle("Menu Unidades");
		
		
		ip.getFramePrincipal().setBackground(Color.BLACK);
		
		JPanel panelUnidades = new JPanel();	
		panelUnidades.setBackground(Color.BLACK);
		
		JButton CrearUnidades = new JButton("Crear Unidades");
		CrearUnidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				opcionDeCrearUnidades.cargar(ip);
				
			}
		});
		
		panelUnidades.add(CrearUnidades);
		
		
		
		
		/*JPanel cuadroDeTexto = new JPanel();
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
		cuadroDeTexto.add(ingreso);*/
		
		
		
	
		ip.getFramePrincipal().getContentPane().add(panelUnidades,BorderLayout.CENTER);
		ip.getFramePrincipal().setSize(700, 500);
		ip.getFramePrincipal().show();
	}

	
		
		
		
		
		
	}

	
	
	
	
	
	


