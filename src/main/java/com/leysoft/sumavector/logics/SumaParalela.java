package com.leysoft.sumavector.logics;

import com.leysoft.sumavector.threads.WorkerParallel;

public class SumaParalela {
	
	private int[] array;
	
	private int NUM_HILOS;
	
	public SumaParalela(int[] array, int NUM_HILOS) {
		this.array = array;
		this.NUM_HILOS = NUM_HILOS;
	}

	public int suma() {
		int suma = 0;
		WorkerParallel[] hilos = new WorkerParallel[NUM_HILOS];
		
		for(int i = 0; i < NUM_HILOS; i++) {
        	hilos[i] = new WorkerParallel(array, i*array.length/NUM_HILOS, 
        			i*array.length/NUM_HILOS + array.length/NUM_HILOS, "hilo[" + Integer.toString(i) + "]");
        	hilos[i].start();
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        for(int i = 0; i < NUM_HILOS; i++) {
        	suma += hilos[i].getSuma();
        }
        return suma;
	}
}
