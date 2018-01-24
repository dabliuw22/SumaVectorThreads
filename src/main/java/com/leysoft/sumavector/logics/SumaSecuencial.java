package com.leysoft.sumavector.logics;

public class SumaSecuencial {

	public SumaSecuencial() {}
	
	public int suma(int[] array) {
		int suma = 0;
		for(int i = 0; i < array.length; i++) {
			suma += array[i];
		}
		return suma;
	}
}