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
import excepciones.ExcepcionLaUnidadNoPertenceATuTropa;
import excepciones.ExcepcionNoHayLugarParaCrear;
import excepciones.ExcepcionPosicionInvalida;
import excepciones.ExcepcionRecursoInsuficiente;
import excepciones.ExcepcionUnidadNoCorrespondiente;
import excepciones.ExcepcionYaHayElementoEnLaPosicion;
import src.Juego;
import src.Jugador;
import src.construcciones.Construccion;
import src.mapa.Posicion;
import src.unidades.AltoTemplario;
import src.unidades.Alucinacion;
import src.unidades.EMP;
import src.unidades.Magia;
import src.unidades.Magica;
import src.unidades.Marine;
import src.unidades.NaveCiencia;
import src.unidades.Radiacion;
import src.unidades.TormentaPsionica;
import src.unidades.Unidad;

	public class AtacarConMagia extends CargarInformacionAtaques {
	
		private CargarInformacionAtaques metodosDeCarga;
		
		Juego juego;
		Jugador jugador;
		int indiceUnidadMagica;
		int indiceUnidadEnemiga;
		ArrayList<Unidad> unidadesMagicas;
		ArrayList <Unidad> unidadesEnemigas;
		String unidadEnemiga;
		String unidadMagica;
		JButton atacar;
		Magia magia;
		private ArrayList <Unidad> unidadesMagicasIndexadas;
		private ArrayList <Unidad> unidadesEnemigasIndexadas;
		JComboBox desplegableUnidadesMagicas;
		JComboBox desplegableUnidadesEnemigas;
		JComboBox desplegableMagiasPermitidas;
		JButton magia1;
		JButton magia2;
		JButton atacarUnidad;
		MenuUnidades menuAnterior;
		JFrame frameAtacarConMagia;
		JFrame framemagia;
		JPanel panelDeMagias;
		Magica agresor;
		InterfazPrincipal ip;
		
	public AtacarConMagia(MenuUnidades menuUnidades){
		
		
		menuAnterior = menuUnidades;
		unidadesMagicas= new ArrayList<Unidad>();
		unidadesEnemigas = new ArrayList <Unidad>();
		unidadMagica = new String();
		unidadEnemiga = new String ();
		unidadesMagicasIndexadas = new ArrayList<Unidad>();
		unidadesEnemigasIndexadas = new ArrayList <Unidad>();
		desplegableUnidadesMagicas= new JComboBox();
		desplegableUnidadesEnemigas = new JComboBox();
		desplegableMagiasPermitidas = new JComboBox();
		indiceUnidadMagica= 0;
		indiceUnidadEnemiga= 0;
		frameAtacarConMagia = new JFrame();
		
	}
	
	
	protected void cargar (final InterfazPrincipal ip){
		
		juego = ip.getJuego();
		jugador = ip.getJuego().getJugadorActual();
		
		this.setInterfaz(ip);
		cargarJugadores ();
		

					
		frameAtacarConMagia.getContentPane().removeAll();
		frameAtacarConMagia.setJMenuBar(null);
		frameAtacarConMagia.setTitle("AtaqueConMagia");
		JPanel panelAtaqueConMagias = new JPanel();
		
		
		desplegableUnidadesMagicas.addItem("");
		desplegableUnidadesEnemigas.addItem("");
		cargarListaDesplegablesUnidadesMagicas( desplegableUnidadesMagicas, unidadesMagicas, jugador);
		caragarListasDesplegablesUnidades (desplegableUnidadesEnemigas, unidadesEnemigasIndexadas,unidadesEnemigas);
		agregarAccionesListasDesplegables();
		panelAtaqueConMagias.add(desplegableUnidadesMagicas);
		panelAtaqueConMagias.add(desplegableUnidadesEnemigas);
		
		atacarUnidad = new JButton("Atacar Unidad Con Magia");
		atacarUnidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CargarAccionAtacarConMagia();
				magia1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						 CargarAccionAtacarConMagiaUno();
						
					}
				});
				
				
				magia2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						CargarAccionAtacarConMagiaDos();
						
					}
				});
				
				panelDeMagias.add(magia1);
				panelDeMagias.add(magia2);
				
				framemagia.getContentPane().add(panelDeMagias);
				framemagia.setSize(700, 500);
				framemagia.setLocation(650,250);
				framemagia.show();
				}
			
			});
		
		
		panelAtaqueConMagias.add(atacarUnidad);
		frameAtacarConMagia.getContentPane().add(panelAtaqueConMagias);
		frameAtacarConMagia.setSize(700, 500);
		frameAtacarConMagia.setLocation(650,250);
		frameAtacarConMagia.show();
		
	}  
	
	
	
	public void setInterfaz(InterfazPrincipal interfaz){
		ip= interfaz; 
	}
	
	
	
	public void crearBotonesMagias (int indiceUnidadMagica){
		if (unidadesMagicas.get(indiceUnidadMagica).getNombre()=="Alto Templario"){
				
				 magia1= new JButton ("Tormenta psionica");
				 magia2 = new JButton("Alucinacion");}
				
			
			else {
					 magia1= new JButton ("EMP");
					 magia2 = new JButton("Radiacion");
			}
}
	
	
	
	
	public void CargarAccionAtacarConMagia (){
		framemagia = new JFrame();
		framemagia.getContentPane().removeAll();
		framemagia.setJMenuBar(null);
		framemagia.setTitle("Seleccione Magia");	
		panelDeMagias= new JPanel();
		String indiceBuscadoUnidadesMagicas="";
		int posicionPuntoUnidadesMagicas=0;
		String indiceBuscadoUnidadesEnemigas="";
		int posicionPuntoUnidadesEnemigas=0;
		
		indiceUnidadEnemiga= obtenerIndiceDeElemento(posicionPuntoUnidadesEnemigas, unidadEnemiga, indiceBuscadoUnidadesEnemigas);
		indiceUnidadMagica= obtenerIndiceDeElemento(posicionPuntoUnidadesMagicas, unidadMagica, indiceBuscadoUnidadesMagicas);
		

		crearBotonesMagias (indiceUnidadMagica);
	}
	
	public void CargarAccionAtacarConMagiaUno(){
			 
			agresor =(Magica) unidadesMagicas.get(indiceUnidadMagica);
			
			
			if (magia1.getActionCommand()=="Tormenta psionica"){
				
				magia = new TormentaPsionica (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));
				
			} else {
				
				magia = new EMP (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));
				
			}
			
			try {
				jugador.atacarCon(agresor, magia);
				JOptionPane.showMessageDialog(null, "Ataque exitoso con"+magia.obtenerNombre());
				
				
			} catch (ExcepcionEdificioNoPuedeCrearUnidad
					| ExcepcionPosicionInvalida
					| ExcepcionNoHayLugarParaCrear
					| ExcepcionYaHayElementoEnLaPosicion
					| ExcepcionErrorPasoDeTurno
					| ExcepcionConstruccionNoCorrespondiente
					| ExcepcionRecursoInsuficiente
					| ExcepcionUnidadNoCorrespondiente
					| ExcepcionElementoFueraDelRangoDeAtaque
					| ExcepcionLaUnidadNoPertenceATuTropa e1) {						
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			menuAnterior.cargar(ip);
		
		
	}
	
	public void CargarAccionAtacarConMagiaDos(){
		agresor = (Magica) unidadesMagicas.get(indiceUnidadMagica);
		if (magia2.getActionCommand()=="Alucinacion"){
			magia = new Alucinacion (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));
			
			
			
			
			
		
			
		} else {
		
			magia = new Radiacion (unidadesEnemigasIndexadas.get(indiceUnidadEnemiga));	
			
			
			
			
			
		}
		
		try {
			jugador.atacarCon(agresor, magia);
			JOptionPane.showMessageDialog(null, "Ataque exitoso con"+magia.obtenerNombre());
			System.out.print(magia);
		} catch (ExcepcionEdificioNoPuedeCrearUnidad
				| ExcepcionPosicionInvalida
				| ExcepcionNoHayLugarParaCrear
				| ExcepcionYaHayElementoEnLaPosicion
				| ExcepcionErrorPasoDeTurno
				| ExcepcionConstruccionNoCorrespondiente
				| ExcepcionRecursoInsuficiente
				| ExcepcionUnidadNoCorrespondiente
				| ExcepcionElementoFueraDelRangoDeAtaque
				| ExcepcionLaUnidadNoPertenceATuTropa e1) {						
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		menuAnterior.cargar(ip);
		
	}
	
	
	public void agregarAccionesListasDesplegables(){
		
		desplegableUnidadesMagicas.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent event){
						if(event.getStateChange()==ItemEvent.SELECTED)
							unidadMagica = event.getItem().toString();
							
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
		
	}
	
	
	public void cargarJugadores (){

		if (jugador ==juego.getJugador1()){
			unidadesEnemigas= juego.getJugador2().getUnidadesAlistadas();
			
			}
		
		else {
			unidadesEnemigas= juego.getJugador1().getUnidadesAlistadas();
			
			}
		
		
	}
	
	
	
	public void limpiar() {
		frameAtacarConMagia.getContentPane().removeAll();
		frameAtacarConMagia.setJMenuBar(null);
		frameAtacarConMagia.setVisible(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
