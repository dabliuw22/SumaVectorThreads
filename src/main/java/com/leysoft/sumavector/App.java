package com.leysoft.sumavector;

import com.leysoft.sumavector.resorces.Recurso;
import com.leysoft.sumavector.threads.Worker;
import com.leysoft.sumavector.threads.WorkerParallel;

public class App {
	
	private static final int NUM_HILOS = 4;
	
	private static final int SALTO = 100;
	
	private static int[] ARRAY = new int[NUM_HILOS*SALTO];
	
	public static void llenarArray() {
		for(int i = 0; i < NUM_HILOS*SALTO; i++) {
			ARRAY[i] = i;
		}
	}
	
    public static void main( String[] args ) {
        Thread[] hilosEM = new Thread[NUM_HILOS];
        Thread[] hilosP = new Thread[NUM_HILOS];
        llenarArray();
        Recurso recurso = new Recurso(ARRAY);
    	
        /* INICIO EXCLUSIÓN MUTUA */
        for(int i = 0; i < NUM_HILOS; i++) {
        	hilosEM[i] = new Thread(new Worker(i*recurso.getArray().length/NUM_HILOS, 
        			i*recurso.getArray().length/NUM_HILOS + recurso.getArray().length/NUM_HILOS, recurso), 
        			"hiloEM[" + Integer.toString(i) + "]");
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	hilosEM[i].start();
        }
        /* FIN EXCLUSIÓN MUTUA */
        
        /* INICIO EJECUCIÓN PARALELA */
        for(int i = 0; i < NUM_HILOS; i++) {
        	hilosP[i] = new WorkerParallel(ARRAY, i*ARRAY.length/NUM_HILOS, 
        			i*ARRAY.length/NUM_HILOS + ARRAY.length/NUM_HILOS, "hiloP[" + Integer.toString(i) + "]");
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	hilosP[i].start();
        }
        
        /* FIN EJECUCIÓN PARALELA */
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	try {
				hilosEM[i].join();
				hilosP[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        int suma = 0;
        for(int i = 0; i < NUM_HILOS; i++) {
        	suma += ((WorkerParallel) hilosP[i]).getSuma();
        }
        System.out.println("Parallel: suma() = " + suma);
        System.out.println("Resource: suma() = " + recurso.getSuma());
    }
}