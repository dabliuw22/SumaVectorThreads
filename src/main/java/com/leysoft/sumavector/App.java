package com.leysoft.sumavector;

import java.util.Random;

import com.leysoft.sumavector.logics.SumaParalela;
import com.leysoft.sumavector.logics.SumaSecuencial;

public class App {
	
    public static void main( String[] args ) {
    	int NUM_HILOS = Runtime.getRuntime().availableProcessors();
    	int SALTO = 100000;
    	int[] ARRAY = new int[NUM_HILOS*SALTO];
    	llenarArray(NUM_HILOS, ARRAY, SALTO);
		long start = System.currentTimeMillis();
		SumaSecuencial secuencial = new SumaSecuencial(ARRAY);
		System.out.println("Secuencial suma() = "+ secuencial.suma() +", time = " + (System.currentTimeMillis() - start) + "mseg");
    	SumaParalela paralela = new SumaParalela(ARRAY, NUM_HILOS);
    	System.out.println("Parallel suma() = "+ paralela.suma() +", time = " + (System.currentTimeMillis() - start) + "mseg");
    }
    
    public static void llenarArray(int NUM_HILOS, int[] ARRAY, int SALTO) {
		Random random = new Random();
		for(int i = 0; i < NUM_HILOS*SALTO; i++) {
			ARRAY[i] = random.nextInt(100) + 1;
		}
	}
}