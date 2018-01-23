package com.leysoft.sumavector;

import com.leysoft.sumavector.resorces.Recurso;
import com.leysoft.sumavector.threads.Worker;

public class App {
	
	private static final int NUM_HILOS = 4;
	
	private static final int SALTO = 4;
	
	private static int[] ARRAY = new int[NUM_HILOS*SALTO];
	
	public static void llenarArray() {
		for(int i = 0; i < NUM_HILOS*SALTO; i++) {
			ARRAY[i] = i;
		}
	}
	
    public static void main( String[] args ) {
        Thread[] hilos = new Thread[NUM_HILOS];
        llenarArray();
        Recurso recurso = new Recurso(ARRAY);
    	
        for(int i = 0; i < NUM_HILOS; i++) {
        	hilos[i] = new Thread(new Worker(i*recurso.getArray().length/NUM_HILOS, 
        			i*recurso.getArray().length/NUM_HILOS + recurso.getArray().length/NUM_HILOS, recurso), 
        			"hilo[" + Integer.toString(i) + "]");
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	hilos[i].start();
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        System.out.println("Resource: suma() = " + recurso.getSuma());
    }
}