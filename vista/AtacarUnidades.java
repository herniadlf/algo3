package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.ExcepcionConstruccionNoCorrespondiente;
import excepciones.ExcepcionEdificioNoPuedeCrearUnidad;
import excepciones.ExcepcionElementoFueraDelRangoDeAtaque;
import excepciones.ExcepcionErrorPasoDeTurno;
import excepciones.ExcepcionFinDeTurnoPorMaximoDeAtaques;
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;
import src.unidades.Magica;
import src.unidades.Unidad;

	public class AtacarUnidades extends CargarInformacionUnidades {
		
	
		private static final long serialVersionUID = 1L;
		Juego juego;
		Jugador jugador;
		
		ArrayList <Unidad> unidadesAlistadas;
		ArrayList <Construccion> construccionesEnemigas;
		ArrayList <Unidad> unidadesEnemigas;
		
		String unidadEnemiga;
		String unidadAlistada;
		String construccionEnemiga;
		
		
		JButton atacar;
		private ArrayList <Construccion> construccionesIndexadas;
		private ArrayList <Unidad> unidadesAlistadasIndexadas;
		private ArrayList <Unidad> unidadesEnemigasIndexadas;
		
		JComboBox desplegableUnidadesAlistadas;
		JComboBox desplegableEdificiosEnemigos;
		JComboBox desplegableUnidadesEnemigas;
		
		int indiceUnidadAlistadas;
		int indiceUnidadEnemigas;
		int indiceConstruccionesEnemigas;
		InterfazPartida menuAnterior;
		InterfazPrincipal iPrincipal;
		JFrame frameAtacar;
		
	public AtacarUnidades(InterfazPartida iPartida){
			menuAnterior = iPartida;
			unidadesAlistadas= new ArrayList<Unidad>();
			construccionesEnemigas = new ArrayList<Construccion>();
			unidadesEnemigas = new ArrayList <Unidad>();
			indiceUnidadAlistadas=0;
			indiceUnidadEnemigas =0;
			
			unidadAlistada = new String();
			construccionEnemiga = new String ();
			unidadEnemiga = new String ();
			
			construccionesIndexadas = new ArrayList<Construccion>();
			unidadesAlistadasIndexadas = new ArrayList<Unidad>();
			unidadesEnemigasIndexadas = new ArrayList <Unidad>();
			
			desplegableUnidadesAlistadas= new JComboBox();
			desplegableEdificiosEnemigos = new JComboBox();
			desplegableUnidadesEnemigas = new JComboBox();
			frameAtacar = new JFrame();
		}
		
		
	protected void cargar(final InterfazPrincipal ip)  {
			
		iPrincipal = ip;
		juego = ip.getJuego();
		jugador = ip.getJuego().getJugadorActual();
		
		cargarJugadores ();
		
		unidadesAlistadas= jugador.getUnidadesAlistadas();
		
		
		
		frameAtacar.getContentPane().removeAll();
		frameAtacar.setJMenuBar(null);
		frameAtacar.setTitle("Atacar Enemigo: Unidad   UnidadEnemiga   EdificioEnemigo");
		JPanel panelAtaque = new JPanel();
		
			desplegableUnidadesAlistadas.addItem("");
			desplegableEdificiosEnemigos.addItem("");
			desplegableUnidadesEnemigas.addItem("");
			
			caragarListasDesplegablesUnidades (desplegableUnidadesAlistadas, unidadesAlistadasIndexadas,unidadesAlistadas);
			caragarListasDesplegablesUnidades (desplegableUnidadesEnemigas, unidadesEnemigasIndexadas,unidadesEnemigas);
			cargarListaDesplegableConstrucciones (desplegableEdificiosEnemigos, construccionesIndexadas, construccionesEnemigas);
			
			agregarAccionesListasDesplegables();
			
			
			
			panelAtaque.add(desplegableUnidadesAlistadas);
			panelAtaque.add (desplegableUnidadesEnemigas);
			panelAtaque.add(desplegableEdificiosEnemigos);
			
	//-----------------------------------------------------------------------------------------------------
			
			JButton atacarUnidad = new JButton("AtacarUnidad");
			atacarUnidad.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					agregarAccionAtacarUnidad();
				}
			});
			
		
		//-------------------------------------------------------------------------------------------------------
			
			JButton atacarEdificio = new JButton("AtacarEdificio");
			atacarEdificio.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					agregarAccionesAtacarEdificio ();
		
							
						}
			});
			
			panelAtaque.add(atacarUnidad);
			panelAtaque.add (atacarEdificio);
	
			frameAtacar.getContentPane().add(panelAtaque);
			frameAtacar.setSize(700, 500);
			frameAtacar.setLocation(650,250);
			frameAtacar.show();
	}
	
	//------------------------------------------------------------------------------------------------------------
	
	
	
	
	//------------------------------------------------------------------------------------------------	
	

	

	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------------
	
	
	
	public void agregarAccionAtacarUnidad(){
		
		String indiceBuscadoUnidadesAlistadas="";
		int posicionPuntoUnidadesAlistadas=1;
		String indiceBuscadoUnidadesEnemigas="";
		int posicionPuntoUnidadesEnemigas=1;
		indiceUnidadEnemigas = obtenerIndiceDeElemento (posicionPuntoUnidadesEnemigas, unidadEnemiga, indiceBuscadoUnidadesEnemigas);
		indiceUnidadAlistadas= obtenerIndiceDeElemento (posicionPuntoUnidadesAlistadas,unidadAlistada, indiceBuscadoUnidadesAlistadas);
				
			try {
				jugador.atacarCon( unidadesAlistadasIndexadas.get(indiceUnidadAlistadas),	unidadesEnemigasIndexadas.get(indiceUnidadEnemigas));
				JOptionPane.showMessageDialog(null, "Ataque de "+unidadesAlistadasIndexadas.get(indiceUnidadAlistadas).getNombre()+" exitoso!");
			} 
			
			catch (ExcepcionLaUnidadNoPertenceATuTropa
					| ExcepcionPosicionInvalida
					| ExcepcionElementoFueraDelRangoDeAtaque e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} 
			
			catch (ExcepcionFinDeTurnoPorMaximoDeAtaques e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			
				try {
					juego.pasarTurno();
				}
				catch (ExcepcionErrorPasoDeTurno
							| ExcepcionConstruccionNoCorrespondiente
							| ExcepcionRecursoInsuficiente
							| ExcepcionUnidadNoCorrespondiente e1) {
						
				}				
			}
		
		limpiar();
		menuAnterior.cargar(iPrincipal);	
	}
	
	
	public void agregarAccionesAtacarEdificio (){
		String indiceBuscadoUnidadesAlistadas="";
		int posicionPuntoUnidadesAlistadas=1;
		String indiceBuscadoConstruccionesEnemigas="";
		int posicionPuntoConstruccionesEnemigas=1;
		
		indiceConstruccionesEnemigas= obtenerIndiceDeElemento (posicionPuntoConstruccionesEnemigas, construccionEnemiga, indiceBuscadoConstruccionesEnemigas);	
		indiceUnidadAlistadas = obtenerIndiceDeElemento (posicionPuntoUnidadesAlistadas, unidadAlistada, indiceBuscadoUnidadesAlistadas);	
		
			
		
			try {
				jugador.atacarCon( unidadesAlistadasIndexadas.get(indiceUnidadAlistadas),	construccionesIndexadas.get(indiceConstruccionesEnemigas));
				JOptionPane.showMessageDialog(null, "Ataque de "+unidadesAlistadasIndexadas.get(indiceUnidadAlistadas).getNombre()+" exitoso!");
			} 
			catch (ExcepcionLaUnidadNoPertenceATuTropa
					| ExcepcionPosicionInvalida
					| ExcepcionElementoFueraDelRangoDeAtaque e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}	
			catch (ExcepcionFinDeTurnoPorMaximoDeAtaques e){
					JOptionPane.showMessageDialog(null, e.getMessage());				
					try {
						juego.pasarTurno();
					}
					catch (ExcepcionErrorPasoDeTurno
								| ExcepcionConstruccionNoCorrespondiente
								| ExcepcionRecursoInsuficiente
								| ExcepcionUnidadNoCorrespondiente e1) {
							
					}		
			}
		
		limpiar();
		menuAnterior.cargar(iPrincipal);
		
	}
	
	
	public void agregarAccionesListasDesplegables(){
		
		desplegableUnidadesAlistadas.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidadAlistada = event.getItem().toString();
							
					}
				}
			);
		
		
		desplegableUnidadesEnemigas.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidadEnemiga= event.getItem().toString();
							
					}
				}
				);
		
		desplegableEdificiosEnemigos.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							construccionEnemiga= event.getItem().toString();
							
					}
				}
				);
		
	};
	
	
	public void limpiar() {
		frameAtacar.getContentPane().removeAll();
		frameAtacar.setJMenuBar(null);
		frameAtacar.setVisible(false);
	}
	
	
	public void cargarJugadores (){
		if (jugador ==juego.getJugador1()){
			unidadesEnemigas= juego.getJugador2().getUnidadesAlistadas();
			construccionesEnemigas = juego.getJugador2().getConstruccionesEnPie();
			}
		
		else {
			unidadesEnemigas= juego.getJugador1().getUnidadesAlistadas();
			construccionesEnemigas = juego.getJugador1().getConstruccionesEnPie();
			}
		
		
		
	}
	
	
}
