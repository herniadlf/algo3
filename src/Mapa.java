package src;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.mapa.Mapeable;
import src.mapa.Posicion;

public class Mapa {

	private Map<Posicion,Mapeable> mapa;
	private int tamanio;
	
	private static Mapa INSTANCE = null;
	
	private Mapa (){};
	
	private synchronized static void createInstance() {
		if (INSTANCE == null) { 
	       INSTANCE = new Mapa();
	    }
	}

	public static Mapa getInstance() {
	    if (INSTANCE == null) 
	    	createInstance();
	    return INSTANCE;
	}
		
}
