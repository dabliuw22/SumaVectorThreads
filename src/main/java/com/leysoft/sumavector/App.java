package com.leysoft.sumavector;

import java.util.Random;

import com.leysoft.sumavector.logics.SumaExclusionMutua;
import com.leysoft.sumavector.logics.SumaParalela;
import com.leysoft.sumavector.logics.SumaSecuencial;

public class App {
	
    public static void main( String[] args ) {
    	int NUM_HILOS = Runtime.getRuntime().availableProcessors();
    	int[] ARRAY = new int[100000000];
    	llenarArray(ARRAY);
		long start = System.currentTimeMillis();
		SumaSecuencial secuencial = new SumaSecuencial();
		System.out.println("Secuencial suma() = "+ secuencial.suma(ARRAY) +", time = " + (System.currentTimeMillis() - start) + "mseg");
		start = System.currentTimeMillis();
		SumaParalela paralela = new SumaParalela(NUM_HILOS);
    	System.out.println("Paralela suma() = "+ paralela.suma(ARRAY) +", time = " + (System.currentTimeMillis() - start) + "mseg");
    	SumaExclusionMutua exclusion = new SumaExclusionMutua(NUM_HILOS);
    	start = System.currentTimeMillis();
    	System.out.println("Exclusión Mutua suma() = "+ exclusion.suma(ARRAY) +", time = " + (System.currentTimeMillis() - start) + "mseg");
    }
    
    public static void llenarArray(int[] ARRAY) {
		Random random = new Random();
		for(int i = 0; i < ARRAY.length; i++) {
			ARRAY[i] = random.nextInt(100) + 1;
		}
	}
}