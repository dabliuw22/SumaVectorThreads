package com.leysoft.sumavector.logics;

import com.leysoft.sumavector.threads.WorkerParallel;

public class SumaParalela {
	
	private WorkerParallel[] hilos;
	
	private int NUM_HILOS;
	
	public SumaParalela(int NUM_HILOS) {
		this.NUM_HILOS = NUM_HILOS;
		this.hilos = new WorkerParallel[NUM_HILOS];
	}

	public int suma(int[] array) {
		int suma = 0;
		int size = (int) Math.ceil(array.length*1.0/NUM_HILOS);
		for(int i = 0; i < NUM_HILOS; i++) {
        	this.hilos[i] = new WorkerParallel(array, i*size, size*(i + 1), "hilo[" + Integer.toString(i) + "]");
        	this.hilos[i].start();
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
