package com.leysoft.sumavector;

import com.leysoft.sumavector.resorces.Recurso;
import com.leysoft.sumavector.threads.Worker;

public class App {
    public static void main( String[] args ) {
    	final int NUM_HILOS = 3;
        Thread[] hilos = new Thread[NUM_HILOS];
        Recurso recurso = new Recurso(new int[] {1, 2, 3, 4, 5, 6});
    	
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