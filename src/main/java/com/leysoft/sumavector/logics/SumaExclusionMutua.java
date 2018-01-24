package com.leysoft.sumavector.logics;

import com.leysoft.sumavector.resorces.Recurso;
import com.leysoft.sumavector.threads.Worker;

public class SumaExclusionMutua {
	
	private int[] array;
	private int NUM_HILOS;
	
	public SumaExclusionMutua(int[] array, int NUM_HILOS) {
		this.array = array;
		this.NUM_HILOS = NUM_HILOS;
	}

	public int suma() {
		Thread[] hilos = new Thread[NUM_HILOS];
		Recurso recurso = new Recurso(array);
		
		for(int i = 0; i < NUM_HILOS; i++) {
        	hilos[i] = new Thread(new Worker(i*recurso.getArray().length/NUM_HILOS, 
        			i*recurso.getArray().length/NUM_HILOS + recurso.getArray().length/NUM_HILOS, recurso), 
        			"hilo[" + Integer.toString(i) + "]");
        	hilos[i].start();
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        return recurso.getSuma();
	}
}