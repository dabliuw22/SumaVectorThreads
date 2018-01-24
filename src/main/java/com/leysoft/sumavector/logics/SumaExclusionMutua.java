package com.leysoft.sumavector.logics;

import com.leysoft.sumavector.resorces.Recurso;
import com.leysoft.sumavector.threads.Worker;

public class SumaExclusionMutua {
	
	private Thread[] hilos;
	private int NUM_HILOS;
	
	public SumaExclusionMutua(int NUM_HILOS) {
		this.NUM_HILOS = NUM_HILOS;
		this.hilos = new Thread[NUM_HILOS];
	}

	public int suma(int[] array) {
		Recurso recurso = new Recurso(array);
		int size = (int) Math.ceil(1.0*recurso.getArray().length/NUM_HILOS);
		
		for(int i = 0; i < NUM_HILOS; i++) {
        	hilos[i] = new Thread(new Worker(i*size, size*(1 + i), recurso), "hilo[" + Integer.toString(i) + "]");
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